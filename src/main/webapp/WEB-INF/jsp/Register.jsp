<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06.10.2020
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
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
    <div><img src="http://localhost:8080/resource/image/smallLogo.png"></div>
    <div>Registration</div>
</div>
<form class="loginForm" action="register" method="post">
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
        <div class="buttonRegist"><input class="button" type="submit" value="Registration">
        </div>
    </div>
</form>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>