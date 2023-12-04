package cn.edu.nju.se.npmdependency.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PackageServiceTest {
    @Autowired
    PackageService packageService;

    @Test
    public void testGetPackagesByName() {
        System.out.println(packageService.getPackagesByName("package_name").getResult());
    }

//    @Test
    public void testGetPackageDetail() {
        System.out.println(packageService.getPackageDetail("package_name", "0.1").getResult());
    }

//    @Test
    public void testGetPackageDependencyTree() {
        System.out.println(packageService.getPackageDependencyTree("package_name", "0.1").getResult());
    }
}
