<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.09.2020
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Wolf provider</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resource/css/login.css">
</head>
<body>
<div class="header">
    <div><fmt:message key="login.submit" /></div>
    <div class="language">
        <a href="?locale=en">EN</a>
        <div class="separator">/</div>
        <a href="?locale=ru">RU</a>
    </div>
</div>
<div class="mainForm">
    <div class="Logo"><img src="/resource/image/bigLogo.png"></div>
    <form class="loginForm" action="login" method="post">
        <div class="value">
            <fmt:message key="login.enterLogin" />
            <input name="email" class="email"></div>
        <div class="error">
                ${requestScope.errorLogin}
        </div>
        <div class="value">
            <fmt:message key="login.enterPassword" />
            <input name="password" type="password" class="password">
        </div>
        <div class="error">
                ${requestScope.errorPassword}</div>
        <div><input class="button" type="submit" value="<fmt:message key="login.submit" />">
        </div>
        <div class="link"><a href="http://localhost:8080/register"><fmt:message key="login.registration" /></a></div>
    </form>
</div>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>