<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp"%>
<div class="x_content">
    <div class="form-horizontal form-label-left" >
        <span class="section">查看APP信息<i class="fa fa-user"></i>${devSession.devName}</span>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="softwareName" name="softwareName" value="${appInfo.softwareName}" class="form-control col-md-7 col-xs-12" readonly="readonly" >
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
                <input type="text"  id="supportROM" name="supportROM" value="${appInfo.supportROM}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >界面语言 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="interfaceLanguage" name="interfaceLanguage" value="${appInfo.interfaceLanguage}"  readonly="readonly" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >软件大小<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input  type="text" id="softwareSize" name="softwareSize" value="${appInfo.softwareSize}" readonly="readonly" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">下载次数 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="downloads" type="text" name="downloads" value="${appInfo.downloads}" readonly="readonly" class="optional form-control col-md-7 col-xs-12">
            </div>
        </div>


        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >所属平台<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="flatformId" readonly="readonly" type="text" name="flatformId" value="${appInfo.flatformName}" class="optional form-control col-md-7 col-xs-12">

            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >所属分类<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="categoryLevel" readonly="readonly" type="text" name="categoryLevel" value="${appInfo.categoryLevel1Name}->${appInfo.categoryLevel2Name}->${appInfo.categoryLevel3Name}" class="optional form-control col-md-7 col-xs-12">

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
                <textarea id="appInfo" name="appInfo" class="form-control col-md-7 col-xs-12" readonly="readonly">${appInfo.appInfo}</textarea>
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">LOGO图片<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
               <img src="${appInfo.logoPicPath}" width="100px" height="50px">
            </div>
        </div>
        <div class="ln_solid"></div>
        <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
                <button id="back"  class="btn btn-success">返回</button>
            </div>
        </div>
    </div>

    <div class="x_content">
        <p>历史版本信息</p>
        <table id="datatable" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>软件名称</th>
                <th>版本号</th>
                <th>软件大小(单位:M)</th>
                <th>发布状态</th>
                <th>APK文件下载</th>
                <th>最近更新时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${appVersionList}" var="v">
                <tr>
                    <td>${v.appName}</td>
                    <td>${v.versionNo}</td>
                    <td>${v.versionSize}</td>
                    <td>${v.publishStatusName}</td>
                    <td>${v.downloadLink}</td>
                    <td><fmt:formatDate value="${v.creationDate}"></fmt:formatDate></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/localjs/appinfoview.js"></script>

<%@include file="common/footer.jsp"%>