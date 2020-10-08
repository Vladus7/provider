<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 05.10.2020
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://localhost:8080/resource/css/service.css">
</head>
<body>
<div class="header">
    <div><img src="http://localhost:8080/resource/image/smallLogo.png"></div>
    <div>Service</div>
    <div class="user">
    <div class="language">
        <a>EN</a>
        <div class="separator">/</div>
        <a>RU</a>
    </div>
    <div><img src="http://localhost:8080/resource/image/person_img.png"></div>
    </div>
</div>
<div class="list-Tile">
    <c:forEach items="${services}" var="service">
        <a href="http://localhost:8080/tariff?id=${service.id}&page=1">
            <div class="item">
                <div class="item_image"><img src="${service.image}" width=150"></div>
                <div class="item_container">
                    <div class="item_name">${service.name}</div>
                    <div class="item_description">${service.description}</div>
                </div>
            </div>
        </a>
    </c:forEach>
</div>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>