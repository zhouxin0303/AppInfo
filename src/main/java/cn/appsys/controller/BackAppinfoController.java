package cn.appsys.controller;

import cn.appsys.mapper.AppCategoryMapper;
import cn.appsys.mapper.AppinfoMapper;
import cn.appsys.mapper.DataDictionaryMapper;
import cn.appsys.pojo.*;
import cn.appsys.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/bac/appinfo")
public class BackAppinfoController {
    @Autowired
    private AppinfoMapper appinfoMapper;
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;
    @Autowired
    private AppCategoryMapper appCategoryMapper;

    @RequestMapping(value = "/list")
    public String list(AppInfo appInfo, HttpServletRequest request,
                       HttpSession session, @RequestParam(value = "softwareName", required = false) String softwareName,   //软件名称
                       @RequestParam(value = "categoryLevel1", required = false) String categoryLevel1,    //一级分类
                       @RequestParam(value = "categoryLevel2", required = false) String categoryLevel2,    //二级分类
                       @RequestParam(value = "categoryLevel3", required = false) String categoryLevel3,    //三级分类
                       @RequestParam(value = "flatformId", required = false) String flatformId,    //所属平台
                       @RequestParam(value = "pageIndex", required = false) String pageIndex) {

        Integer devId = ((BackendUser) session.getAttribute(Constants.bak_SESSION)).getId();  //获取存在session登录用户的id
        List<AppInfo> appinfoList = null;
        List<DataDictionary> statusList = null;
        List<DataDictionary> flatFormList = null;
        List<AppCategory> categoryLevel1List = null;
        List<AppCategory> categoryLevel2List = null;
        List<AppCategory> categoryLevel3List = null;
        PageBean pageBean = new PageBean();

        Integer _categoryLevel1 = null;
        if (categoryLevel1 != null && !categoryLevel1.equals("")) {
            _categoryLevel1 = Integer.parseInt(categoryLevel1);
        }
        Integer _categoryLevel2 = null;
        if (categoryLevel2 != null && !categoryLevel2.equals("")) {
            _categoryLevel2 = Integer.parseInt(categoryLevel2);
        }
        Integer _categoryLevel3 = null;
        if (categoryLevel3 != null && !categoryLevel3.equals("")) {
            _categoryLevel3 = Integer.parseInt(categoryLevel3);
        }
        Integer _flatformId = null;
        if (flatformId != null && !flatformId.equals("")) {
            _flatformId = Integer.parseInt(flatformId);
        }

        if (pageIndex != null && pageIndex != "") {
            pageBean.setPageNo(Integer.parseInt(pageIndex));
        } else {
            pageBean.setPageNo(1);
        }
        pageBean.setPageSize(5);
        appinfoList = appinfoMapper.bakgetAll(softwareName, _categoryLevel1,
                _categoryLevel2, _categoryLevel3,
                _flatformId, devId, (pageBean.getPageNo() - 1) * pageBean.getPageSize(), pageBean.getPageSize());

        statusList = this.getDataDictionaryList("APP_STATUS");
        flatFormList = this.getDataDictionaryList("APP_FLATFORM");
        categoryLevel1List = appCategoryMapper.getAll(null);

        pageBean.setTotalCount(appinfoMapper.bakgetCount(softwareName,  _categoryLevel1,
                _categoryLevel2, _categoryLevel3,
                _flatformId, devId).size());
        pageBean.setAppinfolist(appinfoList);
        request.setAttribute("appinfoList", appinfoList);
        request.setAttribute("statusList", statusList);
        request.setAttribute("flatFormList", flatFormList);
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("categoryLevel1List", categoryLevel1List);
        request.setAttribute("softwareName", softwareName);
        request.setAttribute("categoryLevel1", _categoryLevel1);
        request.setAttribute("categoryLevel2", _categoryLevel2);
        request.setAttribute("categoryLevel3", _categoryLevel3);
        request.setAttribute("flatformId", _flatformId);

        //二级分类列表和三级分类列表---回显
        if(categoryLevel2 != null && !categoryLevel2.equals("")){
            categoryLevel2List = getCategoryList(categoryLevel1.toString());
            request.setAttribute("categoryLevel2List", categoryLevel2List);
        }
        if(categoryLevel3 != null && !categoryLevel3.equals("")){
            categoryLevel3List = getCategoryList(categoryLevel2.toString());
            request.setAttribute("categoryLevel3List", categoryLevel3List);
        }

        return "backend/bakapplist";
    }

    public List<DataDictionary> getDataDictionaryList(String typeCode) {
        List<DataDictionary> dataDictionarylist = null;
        dataDictionarylist = dataDictionaryMapper.getDataDictionaryList(typeCode);
        return dataDictionarylist;
    }

    @RequestMapping(value = "/categorylevellist.json", method = RequestMethod.GET)
    @ResponseBody
    public List<AppCategory> getAppCategoryList(@RequestParam String pid) {
        if (("").equals(pid)) pid=null;
        return getCategoryList(pid);
    }
    public List<AppCategory> getCategoryList(String pid){
        List<AppCategory> Categorylist=null;
        if(pid==null) {
            Categorylist = appCategoryMapper.getAll(null);
        }else{
            Categorylist = appCategoryMapper.getAll(Integer.parseInt(pid));
        }
        return Categorylist;
    }
}
