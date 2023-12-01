package cn.edu.nju.se.npmdependency.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author fanyanpeng
 * @description
 * 该类配置了ClickHouse的JdbcTemplate，使用时需要在需要使用的类中使用@Autowired注入
 *     @Qualifier(value = "clickHouseJdbcTemplate")
 *     @Autowired
 *     JdbcTemplate jdbcTemplate;
 */
@Slf4j
@Configuration
public class JdbcTemplateConfig {

    @Bean(name = "clickHouseJdbcTemplate")
    public JdbcTemplate clickHouseJdbcTemplate(@Qualifier("secondDatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondDatasource")
    public DataSource clickHouseDataSource(){
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:clickhouse://172.29.4.74:30012/test");    // 在test数据库下进行测试
        hikariConfig.setUsername("admin");
        hikariConfig.setPassword("password");
        hikariConfig.setDriverClassName("ru.yandex.clickhouse.ClickHouseDriver");
        hikariConfig.setConnectionTimeout(100000);
        return new HikariDataSource(hikariConfig);
    }

}
