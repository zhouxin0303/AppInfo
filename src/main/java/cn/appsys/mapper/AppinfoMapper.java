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
                     @Param("currentpageNo")Integer currentpageNo,@Param("pageSize")Integer pageSize );
List<AppInfo> getCount(@Param("softwareName")String softwareName ,@Param("status")Integer status,
                       @Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,
                       @Param("categoryLevel3")Integer categoryLevel3,
                       @Param("flatformId")Integer flatformId);
int add(AppInfo appInfo);
List<AppInfo> Chongming(@Param("APKName")String APKName);
AppInfo getAppinfo(@Param(value = "id")Integer id,@Param(value = "APKName")String APKName);
int deleteLogo(@Param(value = "id")Integer id);
AppInfo huoquxq(Integer id);
int Update(AppInfo appInfo);









List<AppInfo> bakgetAll(@Param("softwareName")String softwareName,
                        @Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,
                        @Param("categoryLevel3")Integer categoryLevel3,
                        @Param("flatformId")Integer flatformId,@Param("devId")Integer devId,
                        @Param("currentpageNo")Integer currentpageNo,@Param("pageSize")Integer pageSize );
List<AppInfo> bakgetCount(@Param("softwareName")String softwareName,
                          @Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,
                          @Param("categoryLevel3")Integer categoryLevel3,
                          @Param("flatformId")Integer flatformId,@Param("devId")Integer devId);
}
