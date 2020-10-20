<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.10.2020
  Time: 10:19
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
    <link rel="stylesheet" href="/resource/css/userList.css">
</head>
<body>
<div class="header">
    <div><a href="/service"><img src="/resource/image/smallLogo.png"></a>
    </div>
    <div><fmt:message key="userList.Users"/></div>
    <div class="user">
        <div class="language">
            <a>EN</a>
            <div class="separator">/</div>
            <a>RU</a>
        </div>
        <div><a href="/account"><img src="/resource/image/person_img.png"></a>
        </div>
        <div class="value-name">$ ${user.bill}</div>
    </div>
</div>
<div class="page">
    <div class="list-Tile">
        <c:forEach items="${users}" var="user">
            <div class="item">
                <div class="item_container">
                    <div class="item_name">${user.login}</div>
                </div>
                <c:choose>
                    <c:when test="${user.blocking}">
                        <form action="banned" method="POST">
                            <input type="hidden" value="${user.id}" name="id"/>
                            <input type="hidden" value="unbanned" name="command"/>
                            <button class="button-banned"><fmt:message key="userList.Unbanned"/></button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="banned" method="POST">
                            <input type="hidden" value="${user.id}" name="id"/>
                            <input type="hidden" value="banned" name="command"/>
                            <button class="button-banned"><fmt:message key="userList.Banned"/></button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
    </div>

    <div class="admin-panel">
        <div class="admin_buttons">
            <div class="button"><a href="/download?elem=user"><fmt:message key="userList.DownloadUsers"/></a></div>
            <a class="icon" href="/create_account"> <img
                    src="https://www.pinclipart.com/picdir/big/23-230423_faa-registration-add-to-contact-icon-clipart.png" width="150"></a>
        </div>
    </div>
</div>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>
