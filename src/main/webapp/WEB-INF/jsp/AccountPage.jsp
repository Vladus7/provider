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
<html>
<head>
    <title>Wolf provider</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/resource/css/account.css">
</head>
<body>
<div class="header">
    <div><a href="http://localhost:8080/service"><img src="http://localhost:8080/resource/image/smallLogo.png"></a></div>
    <div>Account</div>
    <div>$ <a href="http://localhost:8080/refill"> ${user.bill}</a></div>
</div>
    <div class="mainFormAccount">
        <div class="tariffs">
            <div class="list-Tile">
                <c:forEach items="${tariffs}" var="tariff">
                        <div class="item">
                            <div class="item_container">
                                <div class="item_name">${tariff.name}</div>
                                <div class="item_description">${tariff.description}</div>
                            </div>
                            <div class="item_prise">$ ${tariff.price}</div>
                        </div>
                </c:forEach>
            </div>
        </div>
        <div class="mainValue">
            <div>
                <form class="loginForm" action="account" method="post">
                <div class="value-name">
                    Enter your name:
                    <input name="name" class="value" value="${user.name}"></div>
                <div class="error">
                    ${requestScope.errorName}
                </div>
                <div class="value-name">
                    Enter surname:
                    <input name="surname" class="value" value="${user.surname}">
                </div>
                <div class="error">
                    ${requestScope.errorSurname}</div>
                <div class="value-name">
                    Enter telephone:
                    <input name="telephone" class="value" value="${user.telephone}">
                </div>
                <div class="error">
                    ${requestScope.errorTelephon}</div>
                <div class="buttonRegist"><input class="button" type="submit" value="Save changes">
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