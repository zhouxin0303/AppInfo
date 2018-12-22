package cn.appsys.controller;

import cn.appsys.mapper.AppCategoryMapper;
import cn.appsys.mapper.AppinfoMapper;
import cn.appsys.mapper.DataDictionaryMapper;
import cn.appsys.pojo.*;
import cn.appsys.tools.Constants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/dev/appinfo")
public class AppinfoController {
    @Autowired
    private AppinfoMapper appinfoMapper;
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;
    @Autowired
    private AppCategoryMapper appCategoryMapper;

    @RequestMapping(value = "/list")
    public String list(AppInfo appInfo, HttpServletRequest request,
                       HttpSession session, @RequestParam(value = "softwareName", required = false) String softwareName,   //软件名称
                       @RequestParam(value = "status", required = false) String status,        //APP状态
                       @RequestParam(value = "categoryLevel1", required = false) String categoryLevel1,    //一级分类
                       @RequestParam(value = "categoryLevel2", required = false) String categoryLevel2,    //二级分类
                       @RequestParam(value = "categoryLevel3", required = false) String categoryLevel3,    //三级分类
                       @RequestParam(value = "flatformId", required = false) String flatformId,    //所属平台
                       @RequestParam(value = "pageIndex", required = false) String pageIndex) {

        Integer devId = ((DevUser) session.getAttribute(Constants.DEV_SESSION)).getId();  //获取存在session登录用户的id
        List<AppInfo> appinfoList = null;
        List<DataDictionary> statusList = null;
        List<DataDictionary> flatFormList = null;
        List<AppCategory> categoryLevel1List = null;
        List<AppCategory> categoryLevel2List = null;
        List<AppCategory> categoryLevel3List = null;
        PageBean pageBean = new PageBean();
        Integer _status = null;
        if (status != null && !status.equals("")) {
            _status = Integer.parseInt(status);
        }
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
        appinfoList = appinfoMapper.getAll(softwareName, _status, _categoryLevel1,
                _categoryLevel2, _categoryLevel3,
                _flatformId, (pageBean.getPageNo() - 1) * pageBean.getPageSize(), pageBean.getPageSize());

        statusList = this.getDataDictionaryList("APP_STATUS");
        flatFormList = this.getDataDictionaryList("APP_FLATFORM");
        categoryLevel1List = appCategoryMapper.getAll(null);

        pageBean.setTotalCount(appinfoMapper.getCount(softwareName, _status, _categoryLevel1,
                _categoryLevel2, _categoryLevel3,
                _flatformId).size());
        pageBean.setAppinfolist(appinfoList);
        request.setAttribute("appinfoList", appinfoList);
        request.setAttribute("statusList", statusList);
        request.setAttribute("flatFormList", flatFormList);
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("categoryLevel1List", categoryLevel1List);
        request.setAttribute("softwareName", softwareName);
        request.setAttribute("status", _status);
        request.setAttribute("categoryLevel1", _categoryLevel1);
        request.setAttribute("categoryLevel2", _categoryLevel2);
        request.setAttribute("categoryLevel3", _categoryLevel3);
        request.setAttribute("flatformId", _flatformId);

        //二级分类列表和三级分类列表---回显
        if (categoryLevel2 != null && !categoryLevel2.equals("")) {
            categoryLevel2List = getCategoryList(categoryLevel1.toString());
            request.setAttribute("categoryLevel2List", categoryLevel2List);
        }
        if (categoryLevel3 != null && !categoryLevel3.equals("")) {
            categoryLevel3List = getCategoryList(categoryLevel2.toString());
            request.setAttribute("categoryLevel3List", categoryLevel3List);
        }
        return "developer/appinfolist";
    }

    //根据类型编码查询所属平台
    public List<DataDictionary> getDataDictionaryList(String typeCode) {
        List<DataDictionary> dataDictionarylist = null;
        dataDictionarylist = dataDictionaryMapper.getDataDictionaryList(typeCode);
        return dataDictionarylist;
    }

    @RequestMapping(value = "/datadictionarylist.json", method = RequestMethod.GET)
    @ResponseBody
    public List<DataDictionary> getDatapingtai(@RequestParam String tcode) {
        System.out.println(tcode);
        System.out.println(getDataDictionaryList(tcode).size());
        return getDataDictionaryList(tcode);
    }

    //一二三级分类
    public List<AppCategory> getCategoryList(String pid) {
        List<AppCategory> Categorylist = null;
        if (pid == null) {
            Categorylist = appCategoryMapper.getAll(null);
        } else {
            Categorylist = appCategoryMapper.getAll(Integer.parseInt(pid));
        }
        return Categorylist;
    }

    @RequestMapping(value = "/categorylevellist.json", method = RequestMethod.GET)
    @ResponseBody
    public List<AppCategory> getAppCategoryList(@RequestParam String pid) {
        if (("").equals(pid)) pid = null;
        return getCategoryList(pid);
    }


    //进入新增页面
    @RequestMapping(value = "/addym", method = RequestMethod.GET)
    public String addym() {

        return "developer/appinfoadd";
    }

    public List<AppInfo> Chongming(String APKName) {
        List<AppInfo> appInfo=null;
        appInfo = appinfoMapper.Chongming(APKName);
        return appInfo;
    }

    //APKName重名判断
    @RequestMapping(value = "/apkexist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object apkexist(@RequestParam String APKName) {
        if(APKName==null){
            return  JSON.toJSONString("empty");
        }
        if(appinfoMapper.Chongming(APKName)==null){
            return JSON.toJSONString ("noexist");//可用
        }else{
            return JSON.toJSONString("exist");//不可用
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request, HttpSession session, @RequestParam(value = "logoPicPath", required = false) MultipartFile mult) {
        AppInfo appInfo = new AppInfo();
        String softwareName = request.getParameter("softwareName");
        String APKName = request.getParameter("APKName");
        String supportROM = request.getParameter("supportROM");
        String interfaceLanguage = request.getParameter("interfaceLanguage");
        String softwareSize = request.getParameter("softwareSize");
        String downloads = request.getParameter("downloads");
        String flatformId = request.getParameter("flatformId");
        String categoryLevel1 = request.getParameter("categoryLevel1");
        String categoryLevel2 = request.getParameter("categoryLevel2");
        String categoryLevel3 = request.getParameter("categoryLevel3");
        String appInfos = request.getParameter("appInfo");
        Integer devId = ((DevUser) session.getAttribute(Constants.DEV_SESSION)).getId();  //获取存在session登录用户的id

        String logoPicPaths = null;
        String logoLocPath=null;
        if (!mult.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics/uploadfiles" + File.separator);
            String fileName = UUID.randomUUID() + ".jpg";
            File file = new File(path, fileName);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                mult.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logoPicPaths = request.getContextPath()+"/statics/uploadfiles/"+ fileName;
            logoLocPath=path+File.separator+fileName;
        }

        appInfo.setSoftwareName(softwareName);
        appInfo.setAPKName(APKName);
        appInfo.setSupportROM(supportROM);
        appInfo.setInterfaceLanguage(interfaceLanguage);
        appInfo.setSoftwareSize(new BigDecimal(softwareSize));
        appInfo.setDownloads(Integer.parseInt(downloads));
        appInfo.setFlatformId(Integer.parseInt(flatformId));
        appInfo.setCategoryLevel1(Integer.parseInt(categoryLevel1));
        appInfo.setCategoryLevel2(Integer.parseInt(categoryLevel2));
        appInfo.setCategoryLevel3(Integer.parseInt(categoryLevel3));
        appInfo.setAppInfo(appInfos);
        appInfo.setCreatedBy(devId);
        appInfo.setLogoPicPath(logoPicPaths);
        appInfo.setLogoLocPath(logoLocPath);
        int result = appinfoMapper.add(appInfo);
        if (result > 0) {
            return "redirect:/dev/appinfo/list";
        }
        return "developer/appinfoadd";
    }


    @RequestMapping(value = "/appinfomodify", method = RequestMethod.GET)
    public String huoquxq(@RequestParam(value = "id", required = false) String id, HttpServletRequest request) {
        System.out.println(id);
        AppInfo appInfo = new AppInfo();
        appInfo = appinfoMapper.huoquxq(Integer.parseInt(id));
        request.setAttribute("appInfo", appInfo);
        System.out.println(appInfo.getStatusName());
        return "developer/appupdate";
    }

    @RequestMapping(value = "delfile", method = RequestMethod.GET)
    @ResponseBody
    public Object delfile(@RequestParam(value = "id", required = false) String id) {
        HashMap<String, String> map = new HashMap<String, String>();
        String fileLoadPath = null;
        if (id == null || ("").equals(id)) {
            map.put("result", "failed");
        } else {
            fileLoadPath = (appinfoMapper.getAppinfo(Integer.parseInt(id), null)).getLogoLocPath();
            File file = new File(fileLoadPath);
            if (file.exists()) {
                if (file.delete()) {
                    if (appinfoMapper.deleteLogo(Integer.parseInt(id))>0) {
                        map.put("result", "success");
                    }
                }
            }

        }
        return JSONArray.toJSON(map);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String Update(AppInfo appInfo,HttpSession session,HttpServletRequest request,
                         @RequestParam(value="attach",required= false) MultipartFile attach){
        String logoPicPaths = null;
        String logoLocPath=null;
        Integer devId = ((DevUser) session.getAttribute(Constants.DEV_SESSION)).getId();  //获取存在session登录用户的id
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics/uploadfiles" + File.separator);
            String fileName = UUID.randomUUID() + ".jpg";
            File file = new File(path, fileName);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                attach.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logoPicPaths = request.getContextPath()+"/statics/uploadfiles/"+fileName;
            logoLocPath=path+File.separator+fileName;
        }
        appInfo.setLogoPicPath(logoPicPaths);
        appInfo.setLogoLocPath(logoLocPath);
        appInfo.setModifyBy(devId);
        if(appinfoMapper.Update(appInfo)>0){
            return "redirect:/dev/appinfo/list";
        }
       return "developer/appinfomodify";
    }
}