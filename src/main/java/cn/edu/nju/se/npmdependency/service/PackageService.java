package cn.edu.nju.se.npmdependency.service;

import cn.edu.nju.se.npmdependency.enums.StatTypeEnum;
import cn.edu.nju.se.npmdependency.enums.TimeUnitEnum;
import cn.edu.nju.se.npmdependency.vo.DependencyTendencyVO;
import cn.edu.nju.se.npmdependency.vo.PackageVO;
import cn.edu.nju.se.npmdependency.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PackageService {
    public ResultVO<PackageVO> getPackageDetail(String packageName, String version);
    public ResultVO<PackageVO> getPackageDependencyTree(String packageName, String version);
    public ResultVO<List<PackageVO>> getPackagesByName(String packageName);

    public DependencyTendencyVO getPackageTendency(
            TimeUnitEnum timeUnitEnum,
            StatTypeEnum statTypeEnum,String packageName,
            String version,
            String start,
            String end
    );

}
