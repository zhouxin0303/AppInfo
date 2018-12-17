<%--
  Created by IntelliJ IDEA.
  User: My
  Date: 2018/12/17
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath}/statics/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/statics/css/custom.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body class="login">
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form action="dologin" method="post">
                    <h1>APP开发者登录系统</h1>
                    <div>
                        <input type="text" class="form-control" name="devCode" placeholder="Username" required=""/>
                    </div>
                    <div>
                        <input type="password" class="form-control" name="devPassword" placeholder="Password"
                               required=""/>
                    </div>
                    <span>${error}</span>
                    <div>
                        <button type="submit" class="btn btn-warning" href="index.html">登录</button>
                        <button type="reset" class="btn btn-default submit" href="index.html">重填</button>
                    </div>

                    <div>
                        <p>©2016 All Rights Reserved</p>
                    </div>
        </div>
        </form>
        </section>
    </div>
</div>
</body>
</html>
