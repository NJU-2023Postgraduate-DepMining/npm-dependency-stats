package cn.edu.nju.se.npmdependency.controller;

import cn.edu.nju.se.npmdependency.enums.StatTypeEnum;
import cn.edu.nju.se.npmdependency.enums.TimeUnitEnum;
import cn.edu.nju.se.npmdependency.service.PackageService;
import cn.edu.nju.se.npmdependency.vo.DependencyTendencyVO;
import cn.edu.nju.se.npmdependency.vo.PackageVO;
import cn.edu.nju.se.npmdependency.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping("/{packageName}")
    public ResultVO<List<PackageVO>> getPackagesByName(@PathVariable String packageName) {
        return packageService.getPackagesByName(packageName);
    }

    @GetMapping("/{packageName}/versions/{version}")
    public ResultVO<PackageVO> getPackageDetail(@PathVariable String packageName, @PathVariable String version) {
        return packageService.getPackageDetail(packageName, version);
    }

    @GetMapping("/{packageName}/versions/{version}/dependency-tree")
    public ResultVO<PackageVO> getPackageDependencyTree(@PathVariable String packageName, @PathVariable String version) {
        return packageService.getPackageDependencyTree(packageName, version);
    }


    /**
     *
     * @author   fanyanpeng
     * @date 2023/12/4 4:57
     * @param timeUnitEnum  时间单位，DAY, MONTH, YEAR。默认为DAY
     * @param statTypeEnum  统计的表格，NPM or GITHUB。默认为NPM
     * @param packageName 包名称，若为空则返回所有包的趋势
     * @param version 包版本，若为空则返回所有版本的趋势
     * @param start 起始时间，若为空则：
     *              对于时间单位为day，则为当前时间前7天；
     *              对于时间单位为month，则为当前时间前12个月；
     *              对于时间单位为year，则为当前时间前3年
     * @param end 结束时间，若为空则为当前时间
     * @return cn.edu.nju.se.npmdependency.vo.ResultVO<cn.edu.nju.se.npmdependency.vo.PackageVO>
     */
    @GetMapping("/tendency")
    public ResultVO<DependencyTendencyVO> getPackageTendency(
            @RequestParam @DefaultValue("DAY") TimeUnitEnum timeUnitEnum,
            @RequestParam @DefaultValue("GITHUB") StatTypeEnum statTypeEnum,@RequestParam String packageName,
            @RequestParam String version,
            @RequestParam String start,
            @RequestParam String end
    ) {


        return ResultVO.buildSuccess(null);
    }



}
