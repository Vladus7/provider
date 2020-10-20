<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06.10.2020
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Wolf provider</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resource/css/regist.css">
</head>
<body>
<div class="header">
    <div><img src="/resource/image/smallLogo.png"></div>
    <div><fmt:message key="register.Registration" /></div>
    <div class="language">
        <a href="?locale=en">EN</a>
        <div class="separator">/</div>
        <a href="?locale=ru">RU</a>
    </div>
</div>
<form class="loginForm" action="register" method="post">
    <div class="mainFormRegist">
        <div class="mainValue">
            <div>
                <div class="value">
                    <fmt:message key="register.EnterEmail" />
                    <input name="email" class="email"></div>
                <div class="error">
                    ${requestScope.errorLogin}
                </div>
                <div class="value">
                    <fmt:message key="register.EnterPassword" />
                    <input name="password" type="password" class="password">
                </div>
                <div class="error">
                    ${requestScope.errorPassword}</div>
                <div class="value">
                    <fmt:message key="register.RepeatPassword" />
                    <input name="password2" type="password" class="password">
                </div>
                <div class="error">
                    ${requestScope.errorPassword2}</div>
            </div>
            <div>
                <div class="value">
                    <fmt:message key="register.EnterName" />
                    <input name="name" class="email"></div>
                <div class="error">
                    ${requestScope.errorName}
                </div>
                <div class="value">
                    <fmt:message key="register.EnterSurname" />
                    <input name="surname"  class="password">
                </div>
                <div class="error">
                    ${requestScope.errorSurname}</div>
                <div class="value">
                    <fmt:message key="register.EnterTelephone" />
                    <input name="telephone" class="password">
                </div>
                <div class="error">
                    ${requestScope.errorTelephon}</div>
            </div>
        </div>
        <div class="buttonRegist"><input class="button" type="submit" value="<fmt:message key="register.Registration" />">
        </div>
    </div>
</form>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>