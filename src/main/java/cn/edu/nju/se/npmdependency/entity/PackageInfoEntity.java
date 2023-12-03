package cn.edu.nju.se.npmdependency.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("package_info")
@Data
public class PackageInfoEntity {

    private String id;

    @Field("package_name")
    private String packageName;

    private String version;

    private String[] dependencies;
}
