package cn.edu.nju.se.npmdependency.service.impl;

import cn.edu.nju.se.npmdependency.entity.PackageInfoEntity;
import cn.edu.nju.se.npmdependency.enums.StatTypeEnum;
import cn.edu.nju.se.npmdependency.enums.TimeUnitEnum;
import cn.edu.nju.se.npmdependency.repository.PackageInfoRepository;
import cn.edu.nju.se.npmdependency.service.PackageService;
import cn.edu.nju.se.npmdependency.util.ClickHouseUtils;
import cn.edu.nju.se.npmdependency.util.DateUtils;
import cn.edu.nju.se.npmdependency.util.ValueConverter;
import cn.edu.nju.se.npmdependency.vo.DependencyTendencyVO;
import cn.edu.nju.se.npmdependency.vo.PackageVO;
import cn.edu.nju.se.npmdependency.vo.ResultVO;
import cn.edu.nju.se.npmdependency.vo.TendencyUnitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.clickhouse.ClickHouseUtil;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageInfoRepository packageInfoRepository;

    @Override
    public ResultVO<PackageVO> getPackageDetail(String packageName, String version) {
        PackageInfoEntity packageInfoEntity = packageInfoRepository.findPackageInfoEntityById(packageName + version);
        PackageVO packageVO = new PackageVO(packageInfoEntity);
        for (String id: packageInfoEntity.getDependencies()) {
            PackageInfoEntity dependedPackageInfoEntity = packageInfoRepository.findPackageInfoEntityById(id);
            packageVO.getDependencies().add(new PackageVO(dependedPackageInfoEntity));
        }
        return ResultVO.buildSuccess(packageVO);
    }

    @Override
    public ResultVO<PackageVO> getPackageDependencyTree(String packageName, String version) {
        PackageInfoEntity packageInfoEntity = packageInfoRepository.findPackageInfoEntityById(packageName + version);

        PackageVO packageVO = new PackageVO(packageInfoEntity);
        Map<PackageInfoEntity, PackageVO> map = new HashMap<>();
        List<PackageInfoEntity> queue = new LinkedList<>();

        map.put(packageInfoEntity, packageVO);
        queue.add(packageInfoEntity);

        while (!queue.isEmpty()) {
            PackageInfoEntity entity = queue.remove(0);
            PackageVO vo = map.get(entity);

            for (String id: entity.getDependencies()) {
                PackageInfoEntity dependedPackageInfoEntity = packageInfoRepository.findPackageInfoEntityById(id);
                PackageVO dependedPackageVO = new PackageVO(dependedPackageInfoEntity);

                vo.getDependencies().add(dependedPackageVO);
                map.put(dependedPackageInfoEntity, dependedPackageVO);
                queue.add(dependedPackageInfoEntity);
            }
        }
        return ResultVO.buildSuccess(packageVO);
    }

    @Override
    public ResultVO<List<PackageVO>> getPackagesByName(String packageName) {
        return ResultVO.buildSuccess(packageInfoRepository.findPackageInfoEntitiesByPackageName(packageName).stream()
                .map(PackageVO::new)
                .collect(Collectors.toList()));
    }

    @Autowired
    ClickHouseUtils clickHouseUtils;

    /**
     *
     * @author   fanyanpeng
     * @date 2023/12/4 4:57
     * @param timeUnitEnum  时间单位，DAY, MONTH, YEAR。默认为DAY
     * @param statTypeEnum  统计的表格，NPM or GITHUB。默认为NPM
     * @param packageName 包名称，若为空则返回所有包的趋势
     * @param version 包版本，若为空则返回所有版本的趋势
     * @param start 起始时间，若为空则：
     *              对于时间单位为day，则为当前时间前7天；
     *              对于时间单位为month，则为当前时间前12个月；
     *              对于时间单位为year，则为当前时间前3年
     * @param end 结束时间，若为空则为当前时间
     * @return cn.edu.nju.se.npmdependency.vo.ResultVO<cn.edu.nju.se.npmdependency.vo.PackageVO>
     */
    @Override
    public DependencyTendencyVO getPackageTendency(TimeUnitEnum timeUnitEnum, StatTypeEnum statTypeEnum, String packageName, String version, String start, String end) {

        if (end == null || end.isEmpty()) {
            // Set default end time as the newest time
            end = clickHouseUtils.getNewestDay(statTypeEnum.getStatTable());
        }

        // Default values for start and end if not provided
        if (start == null || start.isEmpty()) {
            // Set default start time based on timeUnitEnum
            start = DateUtils.ago(end,timeUnitEnum,10);
        }


        // Construct SQL query based on parameters
        String sql = constructQuery(timeUnitEnum, statTypeEnum, packageName, version, start, end);

        // Execute the query in ClickHouse
        List<Map<String, Object>> result = clickHouseUtils.execute(sql);
        List<TendencyUnitVO> tendencyUnitVOList = mapResultToDependencyTendencyVO(result);


        return DependencyTendencyVO.builder()
                .statTypeEnum(statTypeEnum)
                .timeUnitEnum(timeUnitEnum)
                .unitCount(tendencyUnitVOList.size())
                .tendencyUnitVOList(tendencyUnitVOList)
                .build();
    }




    private String getFilterConditionForPackageNameAndVersion(String packageName,String version) {
        if (packageName == null || packageName.isEmpty()) {
            return "";
        }
        String ans = String.format(" AND package_name = '%s' ",packageName);

        if(version == null || version.isEmpty()) {
            return ans;
        }else {
            return ans + String.format(" AND version = '%s'",version);
        }
    }


    private String constructQuery(TimeUnitEnum timeUnitEnum, StatTypeEnum statTypeEnum, String packageName, String version, String start, String end) {
        String timeColumnName = timeUnitEnum.getTimeUnit();
        String tableName = statTypeEnum.getStatTable();
        Long startTimeStamp = DateUtils.string2Timestamp(start);
        Long endTimeStamp = DateUtils.string2Timestamp(end);

        String sql = "SELECT " + timeColumnName + " as time, count(*) as count from " + tableName +
                " where depended_time_stamp >="+startTimeStamp +" and depended_time_stamp <=" +endTimeStamp +
                getFilterConditionForPackageNameAndVersion(packageName,version) +
                " group by " + timeColumnName +
                " order by " + timeColumnName;

        return sql;
    }


    private List<TendencyUnitVO> mapResultToDependencyTendencyVO(List<Map<String, Object>> result) {
        List<TendencyUnitVO> tendencyUnitVOList = new ArrayList<>();
        for (Map<String, Object> map : result) {
            TendencyUnitVO tendencyUnitVO = TendencyUnitVO.builder()
                    .time((String) map.get("time"))
                    .count(ValueConverter.BigIntegerToLong((BigInteger) map.get("count")))
                    .build();
            tendencyUnitVOList.add(tendencyUnitVO);
        }

        return tendencyUnitVOList;
    }
}
