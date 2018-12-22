<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp" %>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>APP信息管理维护
                    <small><i class="fa fa-user"></i>${devSession.devName}您可以通过搜索或者其他的选项对APP的信息修改、删除等管理操作。</small>
                </h2>
                </li>
                <li><a class="close-link"><i class="fa fa-close"></i></a>
                </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <br/>
                <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
                    <ul>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="softwareName">软件名称
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="softwareName" name="softwareName"
                                           class="form-control col-md-7 col-xs-12" value="${softwareName}">
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="status" class="form-control">
                                        <c:if test="${statusList!=null}">
                                            <option value="">请选择</option>
                                            <c:forEach items="${statusList}" var="sta">
                                                <option
                                                        <c:if test="${sta.valueId==status}">selected="selected"</c:if>
                                                        value="${sta.valueId}">${sta.valueName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="flatformId" class="form-control">
                                        <c:if test="${flatFormList!=null}">
                                            <option value="">请选择</option>
                                            <c:forEach var="flat" items="${flatFormList}">
                                                <option
                                                        <c:if test="${flat.valueId==flatformId}">selected="selected"</c:if>
                                                        value="${flat.valueId}">${flat.valueName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">一级分类</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="categoryLevel1" class="form-control" id="queryCategoryLevel1">
                                        <c:if test="${categoryLevel1List!=null}">
                                            <option value="">请选择</option>
                                            <c:forEach items="${categoryLevel1List}" var="cat1">
                                                <option
                                                        <c:if test="${cat1.id==categoryLevel1}">selected="selected"</c:if>
                                                        value="${cat1.id}">${cat1.categoryName}</option>

                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">二级分类
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="categoryLevel2" class="form-control" id="queryCategoryLevel2">
                                        <c:if test="${categoryLevel2List!=null}">
                                            <option value="">请选择</option>
                                            <c:forEach items="${categoryLevel2List}" var="cat2">
                                                <option
                                                        <c:if test="${cat2.id==categoryLevel2}">selected="selected"</c:if>
                                                        value="${cat2.id}">${cat2.categoryName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">三级分类
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="categoryLevel3" class="form-control" id="queryCategoryLevel3">
                                        <c:if test="${categoryLevel3List!=null}">
                                            <option value="">请选择</option>
                                            <c:forEach items="${categoryLevel3List}" var="cat3">
                                                <option
                                                        <c:if test="${cat3.id==categoryLevel3}">selected="selected"</c:if>
                                                        value="${cat3.id}">${cat3.categoryName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                                <button type="submit" class="btn btn-primary">查询</button>
                        </li>
                    </ul>
                </form>
            </div>
        </div>

    <div class="x_panel">
        <div class="x_content">
            <a href="${pageContext.request.contextPath}/dev/appinfo/addym" class="btn btn-success" >新增APP基础信息</a>
            <table id="datatable" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>软件名称</th>
                    <th>APK名称</th>
                    <th>软件大小(单位:M)</th>
                    <th>所属平台</th>
                    <th>所属分类(一级分类、二级分类、三级分类)</th>
                    <th>状态</th>
                    <th>下载次数</th>
                    <th>最新版本号</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${appinfoList}" var="app">
                    <tr>
                        <td>${app.softwareName}</td>
                        <td>${app.APKName}</td>
                        <td>${app.softwareSize}</td>
                        <td>${app.flatformName}</td>
                        <td>${app.categoryLevel1Name}->${app.categoryLevel2Name}->${app.categoryLevel3Name}</td>
                        <td>${app.statusName}</td>
                        <td>${app.downloads}</td>
                        <td>${app.versionNo}</td>
                        <td>
                            <div class="btn-group">
                                <button type="button" class="btn btn-danger">点击操作</button>
                                <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown"
                                        aria-expanded="false">
                                    <span class="caret"></span>
                                    <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <c:if test="${app.status==2||app.status==5}"> <!--审核通过或者已下架-->
                                    <li><a href="#">上架</a>
                                        </c:if>
                                        <c:if test="${app.status==4}"> <!--已上架-->
                                    <li><a href="#">下架</a>
                                        </c:if>
                                    <li><a href="#">新增版本</a>
                                    </li>
                                    <li><a href="#">修改版本</a>
                                    </li>
                                    <li><a href="#" class="modifyAppInfo" appinfoid="${app.id}"
                                           status="${app.status}" statusName="${app.status}">修改</a>
                                    </li>
                                    <li><a href="#">删除</a>
                                    </li>
                                    <li><a href="#">查看</a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div align="center">
            <a href="${pageContext.request.contextPath}/dev/appinfo/list?pageIndex=1">首页</a>
            <c:if test="${pageBean.pageNo>1 }">
                <a href="${pageContext.request.contextPath}/dev/appinfo/list?pageIndex=${pageBean.pageNo-1}">上一页</a>
            </c:if>
            <c:if test="${pageBean.pageNo<pageBean.totalPageCount}">
                <a href="${pageContext.request.contextPath}/dev/appinfo/list?pageIndex=${pageBean.pageNo+1 }">下一页</a>
            </c:if>
            <a href="${pageContext.request.contextPath}/dev/appinfo/list?pageIndex=${pageBean.totalPageCount}">末页</a>
        </div>
        </div>
        <script src="${pageContext.request.contextPath}/statics/localjs/applist.js"></script>
        <script src="${pageContext.request.contextPath}/statics/localjs/appinfolist.js"></script>
        <%@include file="common/footer.jsp" %>

