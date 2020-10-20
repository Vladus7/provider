<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 16.10.2020
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="http://localhost:8080/resource/css/error.css">

</head>
<body>
<div class="page">
<div class="error-code">${requestScope.error}</div>
<div class="error-inf">${requestScope.errorMessages}</div>
</div>
</body>
</html>
