package cn.appsys.service.impl;

import cn.appsys.mapper.AppinfoVersionMapper;
import cn.appsys.pojo.AppVersion;
import cn.appsys.service.AppinfoVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppinfoVersionServiceimpl implements AppinfoVersionService {
    @Autowired
    AppinfoVersionMapper appinfoVersionMapper;
    @Override
    public List<AppVersion> getAll(Integer appId) {
        return appinfoVersionMapper.getAll(appId);
    }

    @Override
    public int add(AppVersion appVersion) {
        return appinfoVersionMapper.add(appVersion);
    }

    @Override
    public AppVersion getBanben(Integer id) {
        return appinfoVersionMapper.getBanben(id);
    }

    @Override
    public int Update(AppVersion appVersion) {
        return appinfoVersionMapper.Update(appVersion);
    }

    @Override
    public int delete(Integer id) {
        return appinfoVersionMapper.delete(id);
    }

    @Override
    public int sxjiaUpdate(AppVersion appVersion) {
        return appinfoVersionMapper.sxjiaUpdate(appVersion);
    }
}
