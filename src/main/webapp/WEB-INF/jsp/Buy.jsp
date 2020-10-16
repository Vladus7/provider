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
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://localhost:8080/resource/css/buy.css">
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
<div class="main">
    ${sessionScope.massage}
</div>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>
