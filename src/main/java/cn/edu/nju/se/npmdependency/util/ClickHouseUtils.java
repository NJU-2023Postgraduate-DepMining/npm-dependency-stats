package cn.edu.nju.se.npmdependency.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fanyanpeng
 * @date 2023/12/4 5:04
 */
@Component
public class ClickHouseUtils {

    @Qualifier(value = "clickHouseJdbcTemplate")
    @Autowired
    JdbcTemplate jdbcTemplate;



    public String getNewestDay(String tableName){
        List<Map<String,Object>> mapList = jdbcTemplate.queryForList("select max(depended_time_stamp) as newest from " + tableName);
        if(mapList.isEmpty()){
            return DateUtils.today();
        }
        Map<String,Object> map = mapList.get(0);
        return DateUtils.timeStamp2String(((Date)map.get("newest")).getTime());
    }



    /**
     * 用于clickhouse调用
     * @param sql 自构建sql查询
     * @return java.util.HashMap<java.lang.String,T>
     *     [ package_id=a1,
     *     package_name=a,
     *     version=1,
     *     depended_time_stamp=2023-11-04 13:18:30.0,
     *     day=2023-11-04,
     *     month=2023-11,
     *     year=2023,
     *     depended_count=1,
     *     createdAt=2023-08-04 13:19:10.0,
     *     updatedAt=2023-12-04 13:19:20.0}]
     */
    public List<Map<String,Object>> execute(String sql){
        List<Map<String,Object>> mapList = jdbcTemplate.queryForList(sql);
        System.out.println(mapList);
        return mapList;
    }


}
