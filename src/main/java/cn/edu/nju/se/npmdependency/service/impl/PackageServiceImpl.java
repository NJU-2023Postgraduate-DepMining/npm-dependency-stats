package cn.edu.nju.se.npmdependency.service.impl;

import cn.edu.nju.se.npmdependency.entity.PackageInfoEntity;
import cn.edu.nju.se.npmdependency.enums.StatTypeEnum;
import cn.edu.nju.se.npmdependency.enums.TimeUnitEnum;
import cn.edu.nju.se.npmdependency.repository.PackageInfoRepository;
import cn.edu.nju.se.npmdependency.service.PackageService;
import cn.edu.nju.se.npmdependency.vo.DependencyTendencyVO;
import cn.edu.nju.se.npmdependency.vo.PackageVO;
import cn.edu.nju.se.npmdependency.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    @Override
    public DependencyTendencyVO getPackageTendency(TimeUnitEnum timeUnitEnum, StatTypeEnum statTypeEnum, String packageName, String version, String start, String end) {

        return null;
    }
}
