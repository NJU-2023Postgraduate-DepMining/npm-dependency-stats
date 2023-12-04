package cn.edu.nju.se.npmdependency.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoDBRepositoryTest {
    @Autowired
    private PackageInfoRepository packageInfoRepository;

    @Test
    public void testPackInfoRepository() {
//        System.out.println(packageInfoRepository.findAll());
        System.out.println(packageInfoRepository.findPackageInfoEntitiesByPackageName("package_name"));
    }

}
