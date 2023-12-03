package cn.edu.nju.se.npmdependency.enums;

/**
 * @author fanyanpeng
 * @date 2023/12/4 4:40
 */
public enum StatTypeEnum {
    NPM("npm"),GITHUB("github");

    private String statType;

    StatTypeEnum(String statType) {
        this.statType = statType;
    }

}
