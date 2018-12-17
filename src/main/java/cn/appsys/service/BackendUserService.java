package cn.appsys.service;


import cn.appsys.pojo.BackendUser;

public interface BackendUserService {
	BackendUser login(String userCode,String userPassword);
}
