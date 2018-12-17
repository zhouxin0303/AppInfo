package cn.appsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.mapper.BackendUserMapper;
import cn.appsys.pojo.BackendUser;
import cn.appsys.service.BackendUserService;
@Service
@Transactional
public class BackendUserServiceimpl implements BackendUserService {
@Autowired
private BackendUserMapper backendUserMapper;
	@Override
	public BackendUser login(String userCode, String userPassword) {
		return backendUserMapper.login(userCode, userPassword);
	}

}
