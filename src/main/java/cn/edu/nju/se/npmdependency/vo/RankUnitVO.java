package cn.edu.nju.se.npmdependency.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankUnitVO {
    String packageName;
    String version;
    Long count;
}
