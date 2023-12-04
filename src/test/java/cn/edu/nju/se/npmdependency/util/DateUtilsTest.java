package cn.edu.nju.se.npmdependency.util;

import cn.edu.nju.se.npmdependency.enums.TimeUnitEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DateUtilsTest {

    @Test
    void daysAgo() {
        Assertions.assertEquals(DateUtils.ago("2020-12-04", TimeUnitEnum.DAY,5), "2020-11-29");
    }


}