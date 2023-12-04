package cn.edu.nju.se.npmdependency.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("npm")
public class PackageInfoEntity {

    @Field("package_name")
    private String packageName;

    private String version;

    private PackageInfoEntity[] dependencies;
}
