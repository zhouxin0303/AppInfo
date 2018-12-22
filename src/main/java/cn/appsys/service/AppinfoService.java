package cn.appsys.service;

import cn.appsys.pojo.AppInfo;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppinfoService {
    List<AppInfo> getAll(String softwareName ,Integer status, Integer categoryLevel1, Integer categoryLevel2,
                         Integer categoryLevel3, Integer flatformId, Integer currentpageNo,Integer pageSize);
    List<AppInfo> getCount(String softwareName ,Integer status, Integer categoryLevel1, Integer categoryLevel2,
                           Integer categoryLevel3, Integer flatformId);
    int add(AppInfo appInfo);     //添加基础信息
    List<AppInfo> Chongming(String APKName);  //重名判断
    AppInfo getAppinfo(Integer id,String APKName);
    boolean deleteLogo(@Param("id")Integer id);   //根据id删除图片
    AppInfo huoquxq(Integer id);          //根据ID获取信息详情
    boolean Update(AppInfo appInfo);   //基础信息修改










    List<AppInfo> bakgetAll(String softwareName, Integer categoryLevel1,Integer categoryLevel2,
                            Integer categoryLevel3, Integer flatformId,Integer devId, Integer currentpageNo,Integer pageSize );
    List<AppInfo> bakgetCount(String softwareName, Integer categoryLevel1,Integer categoryLevel2, Integer categoryLevel3,
                              Integer flatformId,Integer devId);
}
