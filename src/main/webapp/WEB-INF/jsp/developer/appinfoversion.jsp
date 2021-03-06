<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="common/header.jsp" %>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>新增APP版本信息 <i class="fa fa-user"></i>
                    <small>${devSession.devName}</small>
                </h2>
                </li>
                <li><a class="close-link"><i class="fa fa-close"></i></a>
                </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_panel">
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
                        <c:forEach items="${ver}" var="v">
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
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>新增版本信息</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form action="${pageContext.request.contextPath}/dev/appinfo/appinfoadd" class="form-horizontal form-label-left" method="post" enctype="multipart/form-data" >
                                          <input type="hidden" name="appid" value="${appId}">
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">版本号<span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type="text" id="versionNo" name="versionNo" class="form-control col-md-7 col-xs-12">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">版本大小<span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type="text" id="versionSize" name="versionSize" required="required" class="form-control col-md-7 col-xs-12">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="control-label col-md-3 col-sm-3 col-xs-12">发布状态</label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input class="form-control col-md-7 col-xs-12" type="hidden" name="publishStatus">预发布
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">版本简介 <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <textarea id="versionInfo" name="versionInfo" class="form-control col-md-7 col-xs-12"></textarea>
                                    </div>
                                </div>
                                <div class="item form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">apk文件<span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type="file"  name="apkFileNames" class="form-control col-md-7 col-xs-12"></input>
                                    </div>
                                    <div>
                                        ${uploadFileError}
                                    </div>
                                </div>
                                <div class="ln_solid"></div>
                                <div class="form-group">
                                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                        <button type="submit" class="btn btn-primary">保存</button>
                                        <button type="submit" class="btn btn-success">返回</button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>

            <%@include file="common/footer.jsp" %>

