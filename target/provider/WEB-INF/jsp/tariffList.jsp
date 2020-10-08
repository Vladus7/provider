<jsp:useBean id="user" scope="session" type="com.vlad.model.dao.entity.User"/>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 29.09.2020
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://localhost:8080/resource/css/tariff.css">
</head>
<body>
<div class="header">
    <div><img src="http://localhost:8080/resource/image/smallLogo.png"></div>
    <div>Tariffs</div>
    <div class="language">
        <a>EN</a>
        <div class="separator">/</div>
        <a>RU</a>
    </div>
</div>
<div class="page">
<div class="list-Tile">
    <c:forEach items="${tariffs}" var="tariff">
        <a href="http://localhost:8080/tariff?id=${tariff.id}&page=1">
            <div class="item">
                <div class="item_container">
                    <div class="item_name">${tariff.name}</div>
                    <div class="item_description">${tariff.description}</div>
                </div>
                <div class="item_prise">$ ${tariff.price}</div>
            </div>
        </a>
    </c:forEach>
</div>
<div class="user">
    <div><img src="http://localhost:8080/resource/image/person_img.png"></div>
    <div>
        ${user.bill}</div>
</div>
</div>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>
