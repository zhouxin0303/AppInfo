package cn.appsys.service;

import cn.appsys.pojo.AppVersion;

import java.util.List;

public interface AppinfoVersionService {
    List<AppVersion> getAll(Integer appId);  //根据查询所有版本信息
    int add(AppVersion appVersion);  //新增版本信息
    AppVersion getBanben(Integer id);//根据id查询版本信息
    int Update (AppVersion appVersion); //根据Id修改最新版本信息
    int delete(Integer id);  //根据id删除图片

    int sxjiaUpdate(AppVersion appVersion);
}
