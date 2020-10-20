<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cities</title>
</head>
<body>
<h2>Cities</h2>
<img alt="Qries" src="https://www.freepnglogos.com/uploads/logo-internet-png/logo-internet-chemiphase-updated-website-goes-live-chemiphase-ltd-12.png"
     width=150">
<img alt="Qries" src="https://p1.hiclipart.com/preview/678/589/306/metrostation-tv-icon-logo-png-clipart-thumbnail.jpg"
     width=150">
<img alt="Qries" src="https://www.pngitem.com/pimgs/m/11-117523_blue-telephone-png-blue-phone-logo-png-transparent.png"
     width=150">
<img alt="Qries" src="https://thumbs.dreamstime.com/b/iptv-badge-icon-logo-vector-stock-illustration-180182437.jpg"
     width=150">
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Population</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${services}" var="service">
        <tr>
            <td>${service.id}</td>
            <td>${service.name}</td>
            <td>${service.description}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>