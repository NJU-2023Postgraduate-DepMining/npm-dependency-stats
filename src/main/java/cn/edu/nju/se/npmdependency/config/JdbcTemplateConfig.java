package cn.edu.nju.se.npmdependency.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
    @ConfigurationProperties(prefix = "spring.datasource.second-datasource")
    public DataSource clickHouseDataSource(){
        return new HikariDataSource();
    }

}
