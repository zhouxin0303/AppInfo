package cn.appsys.mapper;

import cn.appsys.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppCategoryMapper {
    List<AppCategory>getAll(@Param("parentId")Integer parentId);
}
