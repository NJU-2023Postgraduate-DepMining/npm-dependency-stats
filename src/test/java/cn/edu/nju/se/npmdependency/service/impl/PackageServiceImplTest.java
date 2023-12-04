package cn.edu.nju.se.npmdependency.service.impl;

import cn.edu.nju.se.npmdependency.enums.StatTypeEnum;
import cn.edu.nju.se.npmdependency.enums.TimeUnitEnum;
import cn.edu.nju.se.npmdependency.vo.DependencyTendencyVO;
import cn.edu.nju.se.npmdependency.vo.RankUnitVO;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PackageServiceImplTest {
    @Autowired
    private PackageServiceImpl packageService;

    @Test
    void getPackageTendency() {

        DependencyTendencyVO dependencyTendencyVO =  packageService.getPackageTendency(TimeUnitEnum.YEAR, StatTypeEnum.TEST,null, null, "2022-10-10", "2023-12-12").getResult();

        System.out.println(JSON.toJSONString(dependencyTendencyVO));

    }
    @Test
    void getPackageTendency1() {

        DependencyTendencyVO dependencyTendencyVO =  packageService.getPackageTendency(TimeUnitEnum.MONTH, StatTypeEnum.TEST,null, null, null, "2023-12-12").getResult();

        System.out.println(JSON.toJSONString(dependencyTendencyVO));

    }
    @Test
    void getPackageTendency2() {

        DependencyTendencyVO dependencyTendencyVO =  packageService.getPackageTendency(TimeUnitEnum.DAY, StatTypeEnum.TEST,null, null, "2022-10-10", "2023-12-12").getResult();

        System.out.println(JSON.toJSONString(dependencyTendencyVO));

    }
    @Test
    void getPackageTendency3() {

        DependencyTendencyVO dependencyTendencyVO =  packageService.getPackageTendency(TimeUnitEnum.YEAR, StatTypeEnum.TEST,"a", null, "2028-10-10", "2029-12-12").getResult();

        System.out.println(JSON.toJSONString(dependencyTendencyVO));

    }

    @Test
    void getPackageTendency4() {
        // Test with specific package name and version
        DependencyTendencyVO dependencyTendencyVO = packageService.getPackageTendency(TimeUnitEnum.MONTH, StatTypeEnum.TEST, "yourPackageName", "1.0.0", "2022-01-01", "2023-01-01").getResult();
        System.out.println(JSON.toJSONString(dependencyTendencyVO));
    }

    @Test
    void getPackageTendency5() {
        // Test with specific start and end dates
        DependencyTendencyVO dependencyTendencyVO = packageService.getPackageTendency(TimeUnitEnum.YEAR, StatTypeEnum.TEST, null, null, "2021-01-01", "2023-01-01").getResult();
        System.out.println(JSON.toJSONString(dependencyTendencyVO));
    }

    @Test
    void getPackageTendency6() {
        // Test with default values (no start and end dates)
        DependencyTendencyVO dependencyTendencyVO = packageService.getPackageTendency(TimeUnitEnum.DAY, StatTypeEnum.TEST, null, null, null, null).getResult();
        System.out.println(JSON.toJSONString(dependencyTendencyVO));
    }

    @Test
    void getPackageTendency7() {
        // Test with a specific time unit and GitHub as the stat type
        DependencyTendencyVO dependencyTendencyVO = packageService.getPackageTendency(TimeUnitEnum.MONTH, StatTypeEnum.TEST, null, null, "2022-01-01", "2023-01-01").getResult();
        System.out.println(JSON.toJSONString(dependencyTendencyVO));
    }

    @Test
    void getPackageTendency8() {
        // Test with an invalid package name and version
        DependencyTendencyVO dependencyTendencyVO = packageService.getPackageTendency(TimeUnitEnum.DAY, StatTypeEnum.TEST, "invalidPackage", "invalidVersion", "2022-01-01", "2023-01-01").getResult();
        System.out.println(JSON.toJSONString(dependencyTendencyVO));
    }

    @Test
    void getPackageRank() {
        List<RankUnitVO> rankUnitVOList = packageService.getPackageRank(StatTypeEnum.NPM, "axios", "2012-01-01", "2032-01-01",  100).getResult();
        System.out.println(JSON.toJSONString(rankUnitVOList));
    }
}