<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.09.2020
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Wolf provider</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/resource/css/login.css">
</head>
<body>
<div class="header">
    <div>Login</div>
</div>
<div class="mainForm">
    <div class="Logo"><img src="http://localhost:8080/resource/image/bigLogo.png"></div>
    <form class="loginForm" action="login" method="post">
        <div class="value">
            Enter your email:
            <input name="email" class="email"></div>
        <div class="error">
                ${requestScope.errorLogin}
        </div>
        <div class="value">
            Enter password:
            <input name="password" type="password" class="password">
        </div>
        <div class="error">
                ${requestScope.errorPassword}</div>
        <div><input class="button" type="submit" value="Login">
        </div>
        <div class="link"><a href="http://localhost:8080/registerPage">Registration</a></div>
    </form>
</div>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>