package cn.edu.nju.se.npmdependency.service;

import cn.edu.nju.se.npmdependency.enums.StatTypeEnum;
import cn.edu.nju.se.npmdependency.enums.TimeUnitEnum;
import cn.edu.nju.se.npmdependency.vo.DependencyTendencyVO;
import cn.edu.nju.se.npmdependency.vo.PackageVO;
import cn.edu.nju.se.npmdependency.vo.RankUnitVO;
import cn.edu.nju.se.npmdependency.vo.ResultVO;

import java.util.List;

public interface PackageService {
    ResultVO<PackageVO> getPackageDetail(String packageName, String version);
    ResultVO<PackageVO> getPackageDependencyTree(String packageName, String version);
    ResultVO<List<PackageVO>> getPackagesByName(String packageName);

    ResultVO<DependencyTendencyVO> getPackageTendency(
            TimeUnitEnum timeUnitEnum,
            StatTypeEnum statTypeEnum, String packageName,
            String version,
            String start,
            String end
    );

    ResultVO<List<RankUnitVO>> getPackageRank(StatTypeEnum statTypeEnum, String packageName, String start, String end, int limit);

    ResultVO<String> getUpdateTime(StatTypeEnum statTypeEnum);
}
