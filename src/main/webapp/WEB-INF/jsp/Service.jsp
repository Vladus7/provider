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
    <div><a href="http://localhost:8080/service"><img src="http://localhost:8080/resource/image/smallLogo.png"></a></div>
    <div>Service</div>
    <div class="user">
    <div class="language">
        <a>EN</a>
        <div class="separator">/</div>
        <a>RU</a>
    </div>
    <div><a href="http://localhost:8080/account"><img src="http://localhost:8080/resource/image/person_img.png"></a></div>
    </div>
</div>
<div class="list-Tile">
    <c:forEach items="${services}" var="service">
        <a class="item_link" href="http://localhost:8080/tariff?id=${service.id}&page=1&sorting=id">
            <div class="item">
                <div class="item_image"><img src="${service.image}" width=150"></div>
                <div class="item_container">
                    <div class="head-item">
                    <div class="item_name">${service.name}</div>
                        <div><c:choose>
                            <c:when test="${sessionScope.user.permissions =='admin'}">
                                <div class="admin_buttons">
                                    <a href="http://localhost:8080/create_service"> <img src="https://image.flaticon.com/icons/png/512/1263/1263914.png" width="45"></a>
                                    <a href="http://localhost:8080/change_service?id=${service.id}"><img src="https://cdn4.iconfinder.com/data/icons/social-messaging-ui-coloricon-1/21/31-512.png" width="45"></a>
                                    <a href="http://localhost:8080/delete_service?id=${service.id}"> <img src="https://cdn.iconscout.com/icon/premium/png-256-thumb/delete-759-552068.png" width="45"></a>
                                </div>
                            </c:when>
                        </c:choose>
                        </div>
                    </div>
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