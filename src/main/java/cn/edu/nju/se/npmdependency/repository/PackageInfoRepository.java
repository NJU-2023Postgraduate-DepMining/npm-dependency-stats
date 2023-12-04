package cn.edu.nju.se.npmdependency.repository;

import cn.edu.nju.se.npmdependency.entity.PackageInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PackageInfoRepository extends MongoRepository<PackageInfoEntity, String> {

    @Query("{'package' :'?0', 'version' :'?1'}")
    PackageInfoEntity findPackageInfoEntityByPackageNameAndVersion(String packageName, String version);

    @Query("{'package' :'?0'}")
    List<PackageInfoEntity> findPackageInfoEntitiesByPackageName(String packageName);
}
