<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 14.10.2020
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://localhost:8080/resource/css/CreateService.css">
</head>
<body>
<div class="header">
    <div><a href="http://localhost:8080/service"><img src="http://localhost:8080/resource/image/smallLogo.png"></a></div>
    <div>Create service</div>
    <div class="user">
        <div class="language">
            <a>EN</a>
            <div class="separator">/</div>
            <a>RU</a>
        </div>
        <div><a href="http://localhost:8080/account"><img src="http://localhost:8080/resource/image/person_img.png"></a></div>
    </div>
</div>
<form class="loginForm" action="change_tariff" method="post">
    <div class="mainValue">
        <div>
            <div class="value">
                Enter tariff name:
                <input name="tariffName" class="input" value="${sessionScope.tariff.name}"></div>
            <div class="error">
                ${requestScope.ErrorName}
            </div>
            <div class="value">
                Enter description:
                <textarea name="description" class="input-textarea" cols="25" rows="8">${sessionScope.tariff.description}</textarea>
            </div>
            <div class="error">
                ${requestScope.descriptionError}</div>
            <div class="value">
                Enter price:
                <input name="price"  class="input" value="${sessionScope.tariff.price}">
            </div>
            <div class="error">
                ${requestScope.priceError}</div>
        </div>
        <div class="button"><input class="button" type="submit" value="Create">
        </div>
    </div>
</form>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>
