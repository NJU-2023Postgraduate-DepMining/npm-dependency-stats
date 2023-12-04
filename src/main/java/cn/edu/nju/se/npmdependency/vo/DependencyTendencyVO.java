package cn.edu.nju.se.npmdependency.vo;

import cn.edu.nju.se.npmdependency.enums.StatTypeEnum;
import cn.edu.nju.se.npmdependency.enums.TimeUnitEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @author fanyanpeng
 * @date 2023/12/4 4:44
 */
@Getter
@Builder
public class DependencyTendencyVO {

    // 统计的表格，NPM or GITHUB
    private StatTypeEnum statTypeEnum;

    // 时间单位，
    //    DAY("day"),
    //    MONTH("month"),
    //    YEAR("year");
    private TimeUnitEnum timeUnitEnum;

    // 依赖的时间间隔计数，例如：timeUnitEnum为day，unitCount为10，表示十天内的依赖变化。
    private Integer unitCount;


    // 按照时间顺序的依赖变化
    List<TendencyUnitVO> tendencyUnitVOList;

}
