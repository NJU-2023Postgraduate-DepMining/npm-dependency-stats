package cn.edu.nju.se.npmdependency.enums;

/**
 * @author fanyanpeng
 * @date 2023/12/4 4:39
 */
public enum TimeUnitEnum {
    DAY("day"),
    MONTH("month"),
    YEAR("year");

    private String timeUnit;

    TimeUnitEnum(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getTimeUnit() {
        return timeUnit;
    }
}
