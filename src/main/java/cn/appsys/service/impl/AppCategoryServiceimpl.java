package cn.appsys.service.impl;

import cn.appsys.mapper.AppCategoryMapper;
import cn.appsys.pojo.AppCategory;
import cn.appsys.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AppCategoryServiceimpl implements AppCategoryService {
    @Autowired
    private AppCategoryMapper appCategoryMapper;
    @Override
    public List<AppCategory> getAll(Integer parentId) {
        return appCategoryMapper.getAll(parentId);
    }
}
