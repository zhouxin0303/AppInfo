<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp"%>
<div class="x_content">
    <form action="${pageContext.request.contextPath}/dev/appinfo/update" class="form-horizontal form-label-left" novalidate method="post" enctype="multipart/form-data">
        <span class="section">修改APP基础信息<i class="fa fa-user"></i>${devSession.devName}</span>
       <input type="hidden" id="id" name="id" value="${appInfo.id}">
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="softwareName" name="softwareName" value="${appInfo.softwareName}" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2"  placeholder="请输入软件名称" required="required" >
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >APK名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="APKName" name="APKName" value="${appInfo.APKName}" readonly="readonly" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >支持ROM<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text"  id="supportROM" name="supportROM" value="${appInfo.supportROM}" placeholder="请输入支持的ROM" data-validate-linked="email" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >界面语言 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="interfaceLanguage" name="interfaceLanguage" value="${appInfo.interfaceLanguage}"  placeholder="请输入软件支持的界面语言" required="required" data-validate-minmax="10,100" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >软件大小<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input  type="text" id="softwareSize" name="softwareSize" value="${appInfo.softwareSize}" required="required" placeholder="请输入软件大小,单位为Mb" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">下载次数 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="downloads" type="text" name="downloads" value="${appInfo.downloads}" placeholder="请输入下载次数" data-validate-length-range="5,20" class="optional form-control col-md-7 col-xs-12">
            </div>
        </div>


        <div class="item form-group">
            <input type="hidden" id="fid" name="fid" value="${appInfo.flatformId}">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >所属平台<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select id="flatformId" name="flatformId" class="form-control col-md-7 col-xs-12">

                </select>
            </div>
        </div>
        <div class="item form-group">
            <input type="hidden" id="cl1" name="fid" value="${appInfo.categoryLevel1}">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >一级分类<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select id="categoryLevel1" name="categoryLevel1" class="form-control col-md-7 col-xs-12"></select>
            </div>
        </div>
        <div class="item form-group">
            <input type="hidden" id="cl2" name="fid" value="${appInfo.categoryLevel2}">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >二级分类<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select id="categoryLevel2" name="categoryLevel2" class="form-control col-md-7 col-xs-12"></select>
            </div>
        </div>
        <div class="item form-group">
            <input type="hidden" id="cl3" name="fid" value="${appInfo.categoryLevel3}">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >三级分类<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select id="categoryLevel3" name="categoryLevel3" class="form-control col-md-7 col-xs-12"></select>
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >APP状态<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="statusName" type="text" class="form-control col-md-7 col-xs-12"
                       name="statusName" value="${appInfo.statusName}" readonly="readonly">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >应用简介<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <textarea id="appInfo" name="appInfo" class="form-control col-md-7 col-xs-12">${appInfo.appInfo}</textarea>
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">LOGO图片<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="hidden" name="logoPicPath" value="${appInfo.logoPicPath}" id="logoPicPath">
                <input type="hidden" name="logoLocPath" value="${appInfo.logoLocPath}" id="logoLocPath">
                <div id="uploadfile" style="display: none">
                    <input id="attach"  type="file" class="form-control col-md-7 col-xs-12" name="attach">
                    <p><span style="color:red;font-weight: bold;">*注：1、大小不得超过500k.2、图片格式：jpg、png、jpeg、pneg</span></p>
                </div>
              <div id="logoFile"></div>
            </div>
        </div>
        <div class="ln_solid"></div>
        <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
                <c:if test="${appInfo.status == 3}">
                    <button  type="submit" name="status" value="1" class="btn btn-success">保存并再次提交审核</button>
                </c:if>
                <button type="submit" class="btn btn-primary" name="status"  value="${appInfo.status}">保存</button>
                <button id="send" type="submit" class="btn btn-success">返回</button>
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/statics/localjs/appinfoadd.js"></script>
<script src="${pageContext.request.contextPath}/statics/localjs/appinfomodify.js"></script>
<%@include file="common/footer.jsp"%>