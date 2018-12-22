package cn.appsys.service.impl;

import cn.appsys.mapper.DataDictionaryMapper;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class DataDictionaryServiceimpl implements DataDictionaryService {
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;
    @Override
    public List<DataDictionary> getDataDictionaryList(String typeCode) {
        return dataDictionaryMapper.getDataDictionaryList(typeCode);
    }
}
