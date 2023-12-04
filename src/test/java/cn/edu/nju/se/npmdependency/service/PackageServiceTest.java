package cn.edu.nju.se.npmdependency.service;

import cn.edu.nju.se.npmdependency.enums.StatTypeEnum;
import cn.edu.nju.se.npmdependency.enums.TimeUnitEnum;
import cn.edu.nju.se.npmdependency.vo.DependencyTendencyVO;
import cn.edu.nju.se.npmdependency.vo.RankUnitVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PackageServiceTest {
    @Autowired
    PackageService packageService;

    @Test
    public void testGetPackagesByName() {
        System.out.println(packageService.getPackagesByName("axios").getResult());
    }

//    @Test
    public void testGetPackageDetail() {
        System.out.println(packageService.getPackageDetail("package_name", "0.1").getResult());
    }

//    @Test
    public void testGetPackageDependencyTree() {
        System.out.println(packageService.getPackageDependencyTree("package_name", "0.1").getResult());
    }

    @Test
    void getPackageTendency() {
        DependencyTendencyVO dependencyTendencyVO = packageService.getPackageTendency(TimeUnitEnum.YEAR, StatTypeEnum.GITHUB, null, null, "2001-01-01", "2019-01-02").getResult();

        System.out.println(JSON.toJSONString(dependencyTendencyVO));
    }

    @Test
    void getPackageTendencyWithPackageName() {
        DependencyTendencyVO dependencyTendencyVO = packageService.getPackageTendency(TimeUnitEnum.YEAR, StatTypeEnum.NPM, "0uth", null, "2001-01-01", "2029-01-02").getResult();
        System.out.println(JSON.toJSONString(dependencyTendencyVO));
    }


    @Test
    void getPackageRank() {
        List<RankUnitVO> rankUnitVOList = packageService.getPackageRank(StatTypeEnum.NPM, null, "2001-01-01", "2029-01-02", 100).getResult();
    }

    @Test
    void getPackageRank1() {
        List<RankUnitVO> rankUnitVOList = packageService.getPackageRank(StatTypeEnum.NPM, "axios", "2001-01-01", "2029-01-02", 100).getResult();
    }

    @Test
    void getPackageRank1_no_index() {
        List<RankUnitVO> rankUnitVOList = packageService.getPackageRank(StatTypeEnum.NPM, "axios", "2001-01-01", "2029-01-02", 100).getResult();
    }

    @Test
    void getUpdateTime() {
    }
}
