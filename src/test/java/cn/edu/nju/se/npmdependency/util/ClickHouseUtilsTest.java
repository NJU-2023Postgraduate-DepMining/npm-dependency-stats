package cn.edu.nju.se.npmdependency.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClickHouseUtilsTest {

    @Autowired
    ClickHouseUtils clickHouseUtils;

    @Test
    void execute() {


        clickHouseUtils.execute("select month,count(*) as count from stat " +
                "where package_name='a' and version='1' and depended_time_stamp>=0 and depended_time_stamp<='2926-12-04 13:18:30' " +
                "group by month");


    }
}