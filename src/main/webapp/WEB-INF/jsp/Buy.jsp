<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11.10.2020
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resource/css/buy.css">
</head>
<body>
<div class="header">
    <div><a href="/service"><img src="/resource/image/smallLogo.png"></a>
    </div>
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
<div class="main">
    ${sessionScope.massage}
    <div class="end-date"> <fmt:message key="buy.EndSupport" />  <tags:dateConverter date="${sessionScope.date}" pattern="dd.MM.yyyy"/></div>
</div>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>
