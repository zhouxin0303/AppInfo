package cn.appsys.service.impl;

import cn.appsys.mapper.AppinfoMapper;
import cn.appsys.pojo.AppInfo;
import cn.appsys.service.AppinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AppinfoServiceimpl implements AppinfoService{
    @Autowired
private AppinfoMapper appinfoMapper;

    @Override
    public List<AppInfo> getAll(String softwareName, Integer status, Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3, Integer flatformId, Integer currentpageNo, Integer pageSize) {
        return    appinfoMapper.getAll(softwareName,status,categoryLevel1,categoryLevel2,
                    categoryLevel3,flatformId,(currentpageNo-1)*pageSize,pageSize);
    }

    @Override
    public List<AppInfo> getCount(String softwareName, Integer status, Integer categoryLevel1, Integer categoryLevel2,
                                  Integer categoryLevel3, Integer flatformId) {
        return appinfoMapper.getCount(softwareName,status,categoryLevel1,categoryLevel2, categoryLevel3,flatformId);
    }

    @Override
    public int add(AppInfo appInfo) {
        return appinfoMapper.add(appInfo);
    }

    @Override
    public List<AppInfo> Chongming(String APKName) {
        return appinfoMapper.Chongming(APKName);
    }

    @Override
    public AppInfo getAppinfo(Integer id, String APKName) {
        return appinfoMapper.getAppinfo(id,APKName);
    }

    @Override
    public boolean deleteLogo(Integer id) {
        boolean flag=false;
        if(appinfoMapper.deleteLogo(id)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public AppInfo huoquxq(Integer id) {
        return appinfoMapper.huoquxq(id);
    }

    @Override
    public boolean Update(AppInfo appInfo) {
        boolean flag=false;
        if(appinfoMapper.Update(appInfo)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<AppInfo> bakgetAll(String softwareName, Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3, Integer flatformId, Integer devId, Integer currentpageNo, Integer pageSize) {
        return appinfoMapper.bakgetAll(softwareName,categoryLevel1,
                categoryLevel2,categoryLevel3,flatformId,devId,currentpageNo,pageSize);
    }

    @Override
    public List<AppInfo> bakgetCount(String softwareName, Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3, Integer flatformId, Integer devId) {
        return appinfoMapper.bakgetCount(softwareName,categoryLevel1,categoryLevel2,
                categoryLevel3,flatformId,devId);
    }


}
