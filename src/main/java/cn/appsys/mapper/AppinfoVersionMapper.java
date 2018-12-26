package cn.appsys.mapper;

import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.AppVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppinfoVersionMapper {
    List<AppVersion> getAll(@Param(value = "appId") Integer appId);
    int add(AppVersion appVersion);  //新增版本信息
    AppVersion getBanben(@Param(value ="id") Integer id);//根据id查询版本信息
    int Update (AppVersion appVersion); //根据Id修改最新版本信息

    int delete(@Param(value = "id") Integer id);  //根据id删除图片
    int deletebanben(@Param(value = "appId") Integer appId);
    int sxjiaUpdate(AppVersion appVersion);
}
