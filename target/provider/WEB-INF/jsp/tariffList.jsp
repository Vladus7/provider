<jsp:useBean id="user" scope="session" type="com.vlad.model.dao.entity.User"/>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 29.09.2020
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://localhost:8080/resource/css/tariff.css">
</head>
<body>
<div class="header">
    <div><a href="http://localhost:8080/service"><img src="http://localhost:8080/resource/image/smallLogo.png"></a>
    </div>
    <div>Tariffs</div>
    <div class="user">
        <div class="language">
            <a>EN</a>
            <div class="separator">/</div>
            <a>RU</a>
        </div>
        <div><a href="http://localhost:8080/account"><img src="http://localhost:8080/resource/image/person_img.png"></a>
        </div>
        <div class="value-name">$ ${user.bill}</div>
    </div>
</div>
<div class="page">
    <div class="list-Tile">
        <c:forEach items="${tariffs}" var="tariff">
            <div class="item">
                <div class="item_container">
                    <div class="item_name">${tariff.name}</div>
                    <div class="item_description">${tariff.description}</div>
                </div>
                <div class="end-buttons">
                <div class="item_container">
                    <div class="item_prise">$ ${tariff.price}</div>
                        <form action="buy" method="POST">
                            <input type="hidden" value="${tariff.id}" name="buy"/>
                            <button class="button-buy">Buy</button>
                        </form>
                </div>
                <c:choose>
                    <c:when test="${sessionScope.user.permissions =='admin'}">
                        <div class="admin_buttons">
                            <a href="http://localhost:8080/create_tariff?id=${requestScope.id}"> <img src="https://image.flaticon.com/icons/png/512/1263/1263914.png" width="45"></a>
                            <a href="http://localhost:8080/change_tariff?id=${tariff.id}"><img src="https://cdn4.iconfinder.com/data/icons/social-messaging-ui-coloricon-1/21/31-512.png" width="45"></a>
                            <a href="http://localhost:8080/delete_tariff?id=${tariff.id}"> <img src="https://cdn.iconscout.com/icon/premium/png-256-thumb/delete-759-552068.png" width="45"></a>
                        </div>
                    </c:when>
                </c:choose>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="panels">
    <div class="settings-panel">
        <div class="button"><a href="http://localhost:8080/tariff?id=${requestScope.id}&page=1&sorting=name">Name</a>
        </div>
        <div class="button"><a href="http://localhost:8080/tariff?id=${requestScope.id}&page=1&sorting=cost">Cost</a>
        </div>
        <div class="button"><a href="http://localhost:8080/tariff?id=${requestScope.id}&page=1&sorting=id">Id</a></div>
        <div class="button"><a href="http://localhost:8080/tariff?id=${requestScope.id}&page=1&sorting=name_revers">Name
            revers</a></div>
        <div class="button"><a href="">Download</a></div>
    </div>
        <c:choose>
            <c:when test="${sessionScope.user.permissions =='admin'}">
            <div class="admin-panel">
                <div class="admin_buttons">
                    <a class="icon" href="http://localhost:8080/create_account"> <img src="https://www.pinclipart.com/picdir/big/23-230423_faa-registration-add-to-contact-icon-clipart.png" width="150"></a>
                    <div class="button"><a href="http://localhost:8080/download_users">Download users</a></div>
                    <a class="icon" href="http://localhost:8080/create_tariff?id=${requestScope.id}"> <img src="https://image.flaticon.com/icons/png/512/1263/1263914.png" width="150"></a>
                </div>
        </div>
            </c:when>
        </c:choose>

    </div>
</div>
<div class="pagination">
    <div><c:choose>
        <c:when test="${requestScope.previousPage}">
            <a href="${requestScope.previousPageLink}">
                <div class="button-left"><</div>
            </a>
        </c:when>
        <c:otherwise>
            <a>
                <div class="button-left"></div>
            </a>
        </c:otherwise>
    </c:choose></div>
    <div class="current-page">${requestScope.currentPage}</div>
    <div><c:choose>
        <c:when test="${requestScope.nextPage}">
            <a href="${requestScope.nextPageLink}">
                <div class="button-right">></div>
            </a>
        </c:when>
        <c:otherwise>
            <a>
                <div class="button-right"></div>
            </a>
        </c:otherwise>
    </c:choose></div>
</div>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>
