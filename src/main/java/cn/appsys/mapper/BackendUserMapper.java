package cn.appsys.mapper;

import org.apache.ibatis.annotations.Param;
import cn.appsys.pojo.BackendUser;

public interface BackendUserMapper {
BackendUser login(@Param("userCode")String userCode,@Param("userPassword")String userPassword);
}
