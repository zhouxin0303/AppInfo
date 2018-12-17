package cn.appsys.mapper;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.DevUser;

public interface DevUserMapper {
DevUser login(@Param("devCode")String devCode,@Param("devPassword")String devPassword);
}
