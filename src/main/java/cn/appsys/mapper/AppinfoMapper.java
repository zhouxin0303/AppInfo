package cn.appsys.mapper;

import cn.appsys.pojo.AppInfo;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppinfoMapper {
List<AppInfo> getAll(@Param("softwareName")String softwareName ,@Param("status")Integer status,
                     @Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,
                     @Param("categoryLevel3")Integer categoryLevel3,
                     @Param("flatformId")Integer flatformId,
                     @Param("currentpageNo")Integer currentpageNo,@Param("pageSize")Integer pageSize );   //分页查询基础信息
List<AppInfo> getCount(@Param("softwareName")String softwareName ,@Param("status")Integer status,
                       @Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,
                       @Param("categoryLevel3")Integer categoryLevel3,
                       @Param("flatformId")Integer flatformId);
int add(AppInfo appInfo);   //添加基础信息
AppInfo Chongming(@Param("APKName")String APKName);//判断重名
AppInfo getAppinfo(@Param(value = "id")Integer id,@Param(value = "APKName")String APKName);  //查询
int deleteLogo(@Param(value = "id")Integer id);
AppInfo huoquxq(@Param(value = "id") Integer id);
int Update(AppInfo appInfo);

int banbenUpdate(@Param(value="versionId")Integer versionId,@Param(value="id")Integer appId);  //修改版本信息

int deletexinxi(@Param(value = "id") Integer id);  //根据ID删除信息

int sxjiaUpdate(AppInfo appInfo);   //上下架







List<AppInfo> bakgetAll(@Param("softwareName")String softwareName,
                        @Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,
                        @Param("categoryLevel3")Integer categoryLevel3,
                        @Param("flatformId")Integer flatformId,@Param("devId")Integer devId,
                        @Param("currentpageNo")Integer currentpageNo,@Param("pageSize")Integer pageSize );
List<AppInfo> bakgetCount(@Param("softwareName")String softwareName,
                          @Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,
                          @Param("categoryLevel3")Integer categoryLevel3,
                          @Param("flatformId")Integer flatformId,@Param("devId")Integer devId);
int ShengheUpdate(AppInfo appInfo);
}
