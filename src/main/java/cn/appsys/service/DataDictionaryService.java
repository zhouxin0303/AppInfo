package cn.appsys.service;

import cn.appsys.pojo.DataDictionary;

import java.util.List;

public interface DataDictionaryService {
    List<DataDictionary> getDataDictionaryList(String typeCode);
}
