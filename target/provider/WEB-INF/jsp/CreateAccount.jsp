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
<html>
<head>
    <title>Wolf provider</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/resource/css/regist.css">
</head>
<body>
<div class="header">
    <div><a href="http://localhost:8080/service"><img src="http://localhost:8080/resource/image/smallLogo.png"></a>
    </div>
    <div>Create account</div>
    <div class="user">
        <div><a href="http://localhost:8080/account"><img src="http://localhost:8080/resource/image/person_img.png"></a>
        </div>
    </div>
</div>
<form class="loginForm" action="create_account" method="post">
    <div class="mainFormRegist">
        <div class="mainValue">
            <div>
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
                <div class="value">
                    Repeat password:
                    <input name="password2" type="password" class="password">
                </div>
                <div class="error">
                    ${requestScope.errorPassword2}</div>
            </div>
            <div>
                <div class="value">
                    Enter your name:
                    <input name="name" class="email"></div>
                <div class="error">
                    ${requestScope.errorName}
                </div>
                <div class="value">
                    Enter surname:
                    <input name="surname"  class="password">
                </div>
                <div class="error">
                    ${requestScope.errorSurname}</div>
                <div class="value">
                    Enter telephone:
                    <input name="telephone" class="password">
                </div>
                <div class="error">
                    ${requestScope.errorTelephon}</div>
            </div>
        </div>
        <div class="buttonRegist"><input class="button" type="submit" value="Create account">
        </div>
    </div>
</form>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>
