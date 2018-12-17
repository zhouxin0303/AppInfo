package cn.appsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.mapper.DevUserMapper;
import cn.appsys.pojo.DevUser;
import cn.appsys.service.DevUserService;
@Service
@Transactional
public class DevUserServiceimpl implements DevUserService{
@Autowired
private DevUserMapper devUserMapper;
	@Override
	public DevUser login(String devCode, String devPassword) {
		return devUserMapper.login(devCode, devPassword);
	}

}
