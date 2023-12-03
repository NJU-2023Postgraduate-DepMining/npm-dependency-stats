package cn.edu.nju.se.npmdependency.repository;

import cn.edu.nju.se.npmdependency.entity.PackageInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PackageInfoRepository extends MongoRepository<PackageInfoEntity, String> {

    @Query("{'id' :'?0'}")
    PackageInfoEntity findPackageInfoEntityById(String id);

    @Query("{'package_name' :'?0'}")
    List<PackageInfoEntity> findPackageInfoEntitiesByPackageName(String packageName);
}
