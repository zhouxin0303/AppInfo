package cn.appsys.service;

import cn.appsys.pojo.AppCategory;

import java.util.List;

public interface AppCategoryService {
    List<AppCategory> getAll(Integer parentId);
}
