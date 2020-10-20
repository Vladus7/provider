<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 15.10.2020
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="user" scope="session" type="com.vlad.model.dao.entity.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html">
<head>
    <title>Wolf provider</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resource/css/regist.css">
</head>
<body>
<div class="header">
    <div><a href="/service"><img src="/resource/image/smallLogo.png"></a>
    </div>
    <div><fmt:message key="createAccount.CreateAccount" /></div>
    <div class="user">
        <div><a href="/account"><img src="/resource/image/person_img.png"></a>
        </div>
    </div>
</div>
<form class="loginForm" action="create_account" method="post">
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
        <div class="buttonRegist"><input class="button" type="submit" value="<fmt:message key="createAccount.CreateAccount" />">
        </div>
    </div>
</form>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>
