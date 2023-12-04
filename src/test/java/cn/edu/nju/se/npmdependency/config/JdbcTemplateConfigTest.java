package cn.edu.nju.se.npmdependency.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcTemplateConfigTest {

    @Autowired
    MongoTemplate mongoTemplate;


    @Qualifier(value = "clickHouseJdbcTemplate")
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    void mongoDBTest(){
        System.out.println(mongoTemplate.estimatedCount("test"));

    }


    @Test
    void clickHouseTest(){
        String sql="select count(*) from npm_dependency_stats";
        System.out.println(jdbcTemplate.queryForList(sql));
    }

}