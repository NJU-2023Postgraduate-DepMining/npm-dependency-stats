package cn.edu.nju.se.npmdependency.controller;

import cn.edu.nju.se.npmdependency.service.PackageService;
import cn.edu.nju.se.npmdependency.vo.PackageVO;
import cn.edu.nju.se.npmdependency.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping("api/packages/{packageName}")
    public ResultVO<List<PackageVO>> getPackagesByName(@PathVariable String packageName) {
        return packageService.getPackagesByName(packageName);
    }

    @GetMapping("api/packages/{packageName}/versions/{version}")
    public ResultVO<PackageVO> getPackageDetail(@PathVariable String packageName, @PathVariable String version) {
        return packageService.getPackageDetail(packageName, version);
    }

    @GetMapping("api/packages/{packageName}/versions/{version}/dependency-tree")
    public ResultVO<PackageVO> getPackageDependencyTree(@PathVariable String packageName, @PathVariable String version) {
        return packageService.getPackageDependencyTree(packageName, version);
    }
}
