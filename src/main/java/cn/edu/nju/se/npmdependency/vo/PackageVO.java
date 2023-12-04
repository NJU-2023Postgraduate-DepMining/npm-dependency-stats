package cn.edu.nju.se.npmdependency.vo;

import cn.edu.nju.se.npmdependency.entity.PackageInfoEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PackageVO {
    String packageId;
    String packageName;
    String version;
    List<PackageVO> dependencies;

    public PackageVO(PackageInfoEntity packageInfoEntity) {
        this.packageId = packageInfoEntity.getPackageName() + ":" + packageInfoEntity.getVersion();
        this.packageName = packageInfoEntity.getPackageName();
        this.version = packageInfoEntity.getVersion();
        this.dependencies = new ArrayList<>();
    }
}
