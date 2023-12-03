package cn.edu.nju.se.npmdependency.service;

import cn.edu.nju.se.npmdependency.vo.PackageVO;
import cn.edu.nju.se.npmdependency.vo.ResultVO;

import java.util.List;

public interface PackageService {
    public ResultVO<PackageVO> getPackageDetail(String packageName, String version);
    public ResultVO<PackageVO> getPackageDependencyTree(String packageName, String version);
    public ResultVO<List<PackageVO>> getPackagesByName(String packageName);
}
