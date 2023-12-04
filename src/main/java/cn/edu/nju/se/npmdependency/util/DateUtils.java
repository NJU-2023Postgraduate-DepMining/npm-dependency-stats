package cn.edu.nju.se.npmdependency.util;

import cn.edu.nju.se.npmdependency.enums.TimeUnitEnum;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author fanyanpeng
 * @date 2023/12/4 6:12
 */
public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String[] dateFormats = {"yyyy-MM-dd", "yyyy-MM", "yyyy"};




    private static LocalDate parseDate(String dateString) {
        for (String dateFormat : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // 尝试下一个格式
            }
        }
        // 如果所有格式都不匹配，抛出异常或返回 null，根据需要进行处理
        throw new IllegalArgumentException("Date string does not match any of the provided formats");
    }


    /**
     * 将日期字符串转换为时间戳
     * @return
     */
    public static Long string2Timestamp(String localDateStr) {
        LocalDate localDate = parseDate(localDateStr);
        Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.ofHours(8));
        // 转换为时间戳（Epoch秒数）
        return instant.getEpochSecond() * 1000;
    }


    private static LocalDate ago(LocalDate date, TimeUnitEnum unitEnum, int unitCount) {
        switch (unitEnum) {
            case DAY:
                return date.minusDays(unitCount);
            case MONTH:
                return date.minusMonths(unitCount);
            case YEAR:
                return date.minusYears(unitCount);
            default:
                return date.minusDays(unitCount);
        }
    }

    /**
     * 获取指定时间之前的日期字符串
     * @param dateStr
     * @param unitEnum
     * @param unitCount
     * @return
     */
    public static String  ago(String dateStr, TimeUnitEnum unitEnum, int unitCount) {
        LocalDate date = parseDate(dateStr);
        LocalDate ago = ago(date, unitEnum, unitCount);
        return ago.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }


    /**
     * 获取今天的日期字符串
     * @return
     */
    public static String today() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String timeStamp2String(Long timestamp) {
        long epochDay = timestamp / 1000 / 86400;
        return LocalDate.ofEpochDay(epochDay).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }












}
