<jsp:useBean id="user" scope="session" type="com.vlad.model.dao.entity.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "ex" uri="/WEB-INF/custom.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Wolf provider</title>
    <link rel="stylesheet" href="/resource/css/tariff.css">
</head>
<body>
<div class="header">
    <div><a href="/service"><img src="/resource/image/smallLogo.png"></a>
    </div>
    <div><fmt:message key="tariff.Tariffs"/></div>
    <div class="user">
        <div><a href="/account"><img src="/resource/image/person_img.png"></a>
        </div>
        <div class="value-name">$ ${user.bill}</div>
    </div>
</div>
<div class="page">
    <div class="list-Tile">
        <ex:EmptyCheck message = "Sorry, no tariffs" list="${tariffs}">
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
                            <button class="button-buy"><fmt:message key="tariff.Buy"/></button>
                        </form>
                </div>
                <c:choose>
                    <c:when test="${sessionScope.user.permissions =='admin'}">
                        <div class="admin_buttons">
                            <a href="/create_tariff?id=${requestScope.id}"> <img src="https://image.flaticon.com/icons/png/512/1263/1263914.png" width="45"></a>
                            <a href="/change_tariff?id=${tariff.id}"><img src="https://cdn4.iconfinder.com/data/icons/social-messaging-ui-coloricon-1/21/31-512.png" width="45"></a>
                            <a href="/delete_tariff?id=${tariff.id}"> <img src="https://cdn.iconscout.com/icon/premium/png-256-thumb/delete-759-552068.png" width="45"></a>
                        </div>
                    </c:when>
                </c:choose>
                </div>
            </div>
        </c:forEach>
        </ex:EmptyCheck>
    </div>
    <div class="panels">
    <div class="settings-panel">
        <div class="button"><a href="/tariff?id=${requestScope.id}&page=1&sorting=name"><fmt:message key="tariff.Name"/></a>
        </div>
        <div class="button"><a href="/tariff?id=${requestScope.id}&page=1&sorting=cost"><fmt:message key="tariff.Cost"/></a>
        </div>
        <div class="button"><a href="/tariff?id=${requestScope.id}&page=1&sorting=id"><fmt:message key="tariff.Id"/></a></div>
        <div class="button"><a href="/tariff?id=${requestScope.id}&page=1&sorting=name_revers"><fmt:message key="tariff.NameRevers"/></a></div>
        <div class="button"><a href="/download?elem=tariff&id=${requestScope.id}"><fmt:message key="tariff.Download"/></a></div>
    </div>
        <c:choose>
            <c:when test="${sessionScope.user.permissions =='admin'}">
            <div class="admin-panel">
                <div class="admin_buttons">
                    <a class="icon" href="/accountList"> <img src="https://www.pinclipart.com/picdir/big/23-230423_faa-registration-add-to-contact-icon-clipart.png" width="150"></a>
                    <div class="button"><a href="/download?elem=user"><fmt:message key="tariff.DownloadUsers"/></a></div>
                    <a class="icon" href="/create_tariff?id=${requestScope.id}"> <img src="https://image.flaticon.com/icons/png/512/1263/1263914.png" width="150"></a>
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
