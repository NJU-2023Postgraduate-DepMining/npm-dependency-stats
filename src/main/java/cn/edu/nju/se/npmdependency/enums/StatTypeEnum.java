package cn.edu.nju.se.npmdependency.enums;

/**
 * @author fanyanpeng
 * @date 2023/12/4 4:40
 */
public enum StatTypeEnum {
    NPM("npm"),GITHUB("github"),TEST("stat");

    private String statTable;

    StatTypeEnum(String statTable) {
        this.statTable = statTable;
    }

    public String getStatTable() {
        return statTable;
    }

}
