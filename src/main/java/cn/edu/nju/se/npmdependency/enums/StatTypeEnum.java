package cn.edu.nju.se.npmdependency.enums;

/**
 * @author fanyanpeng
 * @date 2023/12/4 4:40
 */
public enum StatTypeEnum {
    NPM("npm_dependency_stats"),GITHUB("github_dependency_stats"),TEST("npm_dependency_stats");

    private String statTable;

    StatTypeEnum(String statTable) {
        this.statTable = statTable;
    }

    public String getStatTable() {
        return statTable;
    }

}
