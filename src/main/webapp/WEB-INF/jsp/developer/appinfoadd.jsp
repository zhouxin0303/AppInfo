<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/header.jsp"%>
<div class="x_content">
    <form action="${pageContext.request.contextPath}/dev/appinfo/add" class="form-horizontal form-label-left" novalidate method="post" enctype="multipart/form-data">
        <span class="section">新增APP基础信息<i class="fa fa-user"></i>${devSession.devName}</span>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="softwareName" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" name="softwareName" placeholder="请输入软件名称" required="required" type="text">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >APK名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="APKName" name="APKName" placeholder="请输入AKP名称" required="required" class="form-control col-md-7 col-xs-12">
                <span id="APKNames"></span>
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >支持ROM<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text"  id="supportROM" name="supportROM" placeholder="请输入支持的ROM" data-validate-linked="email" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >界面语言 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="interfaceLanguage" name="interfaceLanguage"placeholder="请输入软件支持的界面语言" required="required" data-validate-minmax="10,100" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >软件大小<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input  type="text" id="softwareSize" name="softwareSize" required="required" placeholder="请输入软件大小,单位为Mb" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">下载次数 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="downloads" type="text" name="downloads" placeholder="请输入下载次数" data-validate-length-range="5,20" class="optional form-control col-md-7 col-xs-12">
            </div>
        </div>


        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >所属平台<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select id="flatformId" name="flatformId" class="form-control col-md-7 col-xs-12"></select>
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >一级分类<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select id="categoryLevel1" name="categoryLevel1" class="form-control col-md-7 col-xs-12"></select>
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >二级分类<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select id="categoryLevel2" name="categoryLevel2" class="form-control col-md-7 col-xs-12"></select>
            </div>
        </div>
        <div class="item form-group">
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
                <input type="hidden" id="status"  name="status" value="1" >待审核
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" >应用简介<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <textarea id="appInfo" required="required" name="appInfo" class="form-control col-md-7 col-xs-12"></textarea>
            </div>
        </div>
        <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">LOGO图片<span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="file"  name="logoPicPath" class="form-control col-md-7 col-xs-12"></input>
            </div>
        </div>
        <div class="ln_solid"></div>
        <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
                <button type="submit" class="btn btn-primary" id="btn_sh">保存并再次提交审核</button>
                <button type="submit" class="btn btn-primary" id="btn_id">保存</button>
                <button id="send" type="submit" class="btn btn-success">返回</button>
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/statics/localjs/appinfoadd.js"></script>
<%@include file="common/footer.jsp"%>