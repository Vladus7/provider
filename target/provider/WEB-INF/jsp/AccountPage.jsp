<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 08.10.2020
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06.10.2020
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>

<jsp:useBean id="user" scope="session" type="com.vlad.model.dao.entity.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "ex" uri="/WEB-INF/custom.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html ">
<head>
    <title>Wolf provider</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resource/css/account.css">
</head>
<body>
<div class="header">
    <div><a href="/service"><img src="/resource/image/smallLogo.png"></a></div>
    <div><fmt:message key="accountPage.Account"/></div>
    <div class="language">
        <a href="?locale=en">EN</a>
        <div class="separator">/</div>
        <a href="?locale=ru">RU</a>
    </div>
    <div>$ <a href="/refill"> ${user.bill}</a></div>
   <a href="/logout">Logout </a>
</div>
    <div class="mainFormAccount">
        <div class="tariffs">
            <div class="list-Tile">
                <ex:EmptyCheck message = "You have no purchased tariffs." list="${tariffs}">
                <c:forEach items="${tariffs}" var="tariff">
                        <div class="item">
                            <div class="item_container">
                                <div class="item_name">${tariff.name}</div>
                                <div class="item_description">${tariff.description}</div>
                            </div>
                            <div class="item_prise">$ ${tariff.price}</div>
                        </div>
                </c:forEach>
                </ex:EmptyCheck>
            </div>
        </div>
        <div class="mainValue">
            <div>
                <form class="loginForm" action="account" method="post">
                <div class="value-name">
                    <fmt:message key="accountPage.EnterName"/>
                    <input name="name" class="value" value="${user.name}"></div>
                <div class="error">
                    ${requestScope.errorName}
                </div>
                <div class="value-name">
                    <fmt:message key="accountPage.EnterSurname"/>
                    <input name="surname" class="value" value="${user.surname}">
                </div>
                <div class="error">
                    ${requestScope.errorSurname}</div>
                <div class="value-name">
                    <fmt:message key="accountPage.EnterTelephone"/>
                    <input name="telephone" class="value" value="${user.telephone}">
                </div>
                <div class="error">
                    ${requestScope.errorTelephon}</div>
                <div class="buttonRegist"><input class="button" type="submit" value="<fmt:message key="accountPage.SaveChanges"/>">
                </div>
                </form>
            </div>
        </div>
    </div>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>