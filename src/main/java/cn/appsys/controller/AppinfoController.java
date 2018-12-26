package cn.appsys.controller;

import cn.appsys.mapper.AppCategoryMapper;
import cn.appsys.mapper.AppinfoMapper;
import cn.appsys.mapper.AppinfoVersionMapper;
import cn.appsys.mapper.DataDictionaryMapper;
import cn.appsys.pojo.*;
import cn.appsys.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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
    @Autowired
    private AppinfoVersionMapper appinfoVersionMapper;

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

    public AppInfo Chongming(String APKName) {
        AppInfo appInfo = null;
        appInfo = appinfoMapper.Chongming(APKName);
        return appInfo;
    }

    //APKName重名判断
    @RequestMapping(value = "/apkexist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object apkexist(@RequestParam String APKName) {
        HashMap<String, String> map = new HashMap<String, String>();
        if (APKName == null || APKName == "") {
            map.put("APKName", "empty");
        } else {
            if (appinfoMapper.Chongming(APKName) == null) {
                map.put("APKName", "noexist");//可用
            } else {
                map.put("APKName", "exist");//不可用
            }
        }
        return JSONArray.toJSONString(map);

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
        String logoLocPath = null;
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
            logoPicPaths = request.getContextPath() + "/statics/uploadfiles/" + fileName;
            logoLocPath = path + File.separator + fileName;
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
        appInfo.setCreationDate(new Date());
        appInfo.setStatus(1);
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
                    if (appinfoMapper.deleteLogo(Integer.parseInt(id)) > 0) {
                        map.put("result", "success");
                    }
                }
            }

        }
        return JSONArray.toJSON(map);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String Update(AppInfo appInfo, HttpSession session, HttpServletRequest request,
                         @RequestParam(value = "attach", required = false) MultipartFile attach) {
        String logoPicPaths = null;
        String logoLocPath = null;
        String status=request.getParameter("status");
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
            logoPicPaths = request.getContextPath() + "/statics/uploadfiles/" + fileName;
            logoLocPath = path + File.separator + fileName;
        }
        appInfo.setLogoPicPath(logoPicPaths);
        appInfo.setLogoLocPath(logoLocPath);
        appInfo.setModifyBy(devId);
        appInfo.setStatus(Integer.parseInt(status));
        if (appinfoMapper.Update(appInfo) > 0) {
            return "redirect:/dev/appinfo/list";
        }
        return "developer/appinfomodify";
    }

    @RequestMapping(value = "/banben", method = RequestMethod.GET)
    public String banben(AppVersion appVersion, HttpServletRequest request) {
        List<AppVersion> versionList = null;
        String appId = request.getParameter("appId");
        versionList = appinfoVersionMapper.getAll(Integer.parseInt(appId));
        System.out.println(versionList.toString());
        request.setAttribute("appId", appId);
        request.setAttribute("ver", versionList);
        return "developer/appinfoversion";
    }

    @RequestMapping(value = "/appinfoadd", method = RequestMethod.POST)
    public String addVersion(HttpServletRequest request, @RequestParam(value = "apkFileNames", required = false) MultipartFile att, HttpSession session) {
        AppVersion appVersion = new AppVersion();
        String appid = request.getParameter("appid");
        String versionNo = request.getParameter("versionNo");
        String versionSize = request.getParameter("versionSize");
        String versionInfo = request.getParameter("versionInfo");
        String downloadLink = null;
        String apkFileName = null;
        String apkLocPath = null;
        if (!att.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics/uploadfiles" + File.separator);
            String oldFileName = att.getOriginalFilename(); //原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            if (prefix.equalsIgnoreCase("apk")) {
                apkFileName = System.currentTimeMillis() + ".apk";
                File file = new File(path, apkFileName);
                if (!file.exists()) {
                    file.mkdirs();
                }
                try {
                    att.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                downloadLink = request.getContextPath() + "/statics/uploadfiles/" + apkFileName;
                apkLocPath = path + File.separator + apkFileName;
            } else {
                request.setAttribute("uploadFileError", "*上传文件格式不正确");
                return "developer/appinfoversion";
            }
        }
        Integer devId = ((DevUser) session.getAttribute(Constants.DEV_SESSION)).getId();  //获取存在session登录用户的id
        appVersion.setAppId(Integer.parseInt(appid));
        appVersion.setVersionNo(versionNo);
        appVersion.setVersionSize(new BigDecimal(versionSize));
        appVersion.setVersionInfo(versionInfo);
        appVersion.setPublishStatus(3);
        appVersion.setApkFileName(apkFileName);
        appVersion.setApkLocPath(apkLocPath);
        appVersion.setCreatedBy(devId);
        appVersion.setCreationDate(new Date());
        appVersion.setDownloadLink(downloadLink);
        int result = appinfoVersionMapper.add(appVersion);
        if (result > 0) {
            int versionid = appVersion.getId();
            appinfoMapper.banbenUpdate(versionid, Integer.parseInt(appid));
            return "redirect:/dev/appinfo/list";
        }
        return "redirect:/dev/appinfo/banben?id=\"+appVersion.getAppId()";
    }


    @RequestMapping(value = "/appversionmodify", method = RequestMethod.GET)
    public String getBanben(HttpServletRequest request) {
        String vid = request.getParameter("vid");
        String aid = request.getParameter("aid");
        List<AppVersion> versionList = null;
        AppVersion version = new AppVersion();
        versionList = appinfoVersionMapper.getAll(Integer.parseInt(aid));
        version = appinfoVersionMapper.getBanben(Integer.parseInt(vid));
        request.setAttribute("versionList", versionList);
        request.setAttribute("version", version);
        return "developer/appersionUpdate";
    }

    @RequestMapping(value = "/banbenUpdate", method = RequestMethod.POST)
    public String banbenUpdate(AppVersion version, HttpServletRequest request, HttpSession session, @RequestParam(value = "attach", required = false) MultipartFile att) {
        Integer devId = ((DevUser) session.getAttribute(Constants.DEV_SESSION)).getId();  //获取存在session登录用户的id

        String downloadLink = null;
        String apkFileName = null;
        String apkLocPath = null;
        if (!att.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics/uploadfiles" + File.separator);
            String oldFileName = att.getOriginalFilename(); //原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            if (prefix.equalsIgnoreCase("apk")) {
                apkFileName = System.currentTimeMillis() + ".apk";
                File file = new File(path, apkFileName);
                if (!file.exists()) {
                    file.mkdirs();
                }
                try {
                    att.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                downloadLink = request.getContextPath() + "/statics/uploadfiles/" + apkFileName;
                apkLocPath = path + File.separator + apkFileName;
            } else {
                request.setAttribute("uploadFileError", "*上传文件格式不正确");
                return "developer/appinfoversion";
            }
        }

        version.setPublishStatus(3);
        version.setModifyDate(new Date());
        version.setModifyBy(devId);
        version.setApkLocPath(apkLocPath);
        version.setApkFileName(apkFileName);
        version.setDownloadLink(downloadLink);
        int result = appinfoVersionMapper.Update(version);
        if (result > 0) {
            return "redirect:/dev/appinfo/list";
        }
        return "developer/appersionUpdate";
    }

    @RequestMapping(value = "delfiles.json",method = RequestMethod.GET)
    @ResponseBody
    public Object delfiles(@RequestParam(value = "id", required = false) String id) {
        HashMap<String, String> map = new HashMap<String, String>();
        String fileLoadPath = null;
        if (id == null || ("").equals(id)) {
            map.put("result", "failed");
        } else {
            fileLoadPath = (appinfoVersionMapper.getBanben(Integer.parseInt(id))).getApkLocPath();
            File file = new File(fileLoadPath);
            if (file.exists()) {
                if (file.delete()) {
                    if (appinfoVersionMapper.delete(Integer.parseInt(id))>0) {
                        map.put("result", "success");
                    }
                }
            }
        }
        return JSONArray.toJSON(map);
    }
    @RequestMapping(value = "/chakan",method = RequestMethod.GET)
    public String chakan(HttpServletRequest request){
        String  id=request.getParameter("id");
        AppInfo appInfo=new AppInfo();
        List<AppVersion> appVersionList=null;
        appInfo=appinfoMapper.getAppinfo(Integer.parseInt(id),null);
        appVersionList=appinfoVersionMapper.getAll(Integer.parseInt(id));
        request.setAttribute("appInfo",appInfo);
        request.setAttribute("appVersionList",appVersionList);
        return "developer/appinfochakan";
    }
    @RequestMapping(value = "delapp.json",method = RequestMethod.GET)
    @ResponseBody
    public String delapp(@RequestParam(value = "id",required = false)String id){
        HashMap<String,String> map=new HashMap<String, String>();
        int result=appinfoMapper.deletexinxi(Integer.parseInt(id));
        if(result>0){
            appinfoVersionMapper.deletebanben(Integer.parseInt(id));
            map.put("delResult","true");
        }else {
            map.put("delResult","false");
        }
        return  JSONArray.toJSONString(map);
    }

    @RequestMapping(value = "/{appId}/sale.json/{saleSwitch}", method = RequestMethod.PUT)
    @ResponseBody
    public String sxjia(@PathVariable String appId, @PathVariable String saleSwitch, HttpSession session) {
        HashMap<String, String> map = new HashMap<String, String>();
        int devid = ((DevUser) session.getAttribute(Constants.DEV_SESSION)).getId();  //获取存在session登录用户的id
        AppInfo appInfo= appinfoMapper.getAppinfo(Integer.parseInt(appId), null);
        int versionNo=appInfo.getVersionId();
        AppVersion appVersion = appinfoVersionMapper.getBanben(versionNo);
        AppInfo app=new AppInfo();
        if (saleSwitch.equals("open")) {
            app.setId(Integer.parseInt(appId));
            app.setOffSaleDate(new Date());
            app.setModifyBy(devid);
            app.setStatus(4);
            appVersion.setPublishStatus(2);
        } else if (saleSwitch.equals("close")) {
            app.setId(Integer.parseInt(appId));
            app.setOffSaleDate(new Date());
            app.setModifyBy(devid);
            app.setStatus(5);
            appVersion.setPublishStatus(3);
        }
        if(saleSwitch.equals("open")) {
           int result=appinfoMapper.sxjiaUpdate(app);
            if (appinfoMapper.sxjiaUpdate(app) > 0) {
                if (appinfoVersionMapper.sxjiaUpdate(appVersion) > 0) {
                    map.put("resultMsg", "success");
                }
            }
        }else if(saleSwitch.equals("close")){
            if(appinfoMapper.sxjiaUpdate(app)>0){
                if(appinfoVersionMapper.sxjiaUpdate(appVersion)>0){
                    map.put("resultMsg", "success");
                }
            }
        } else {
            map.put("resultMsg", "failed");
        }
        return JSONArray.toJSONString(map);
    }
}
