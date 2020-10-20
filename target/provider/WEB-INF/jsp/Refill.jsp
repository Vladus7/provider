<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 04.10.2020
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resource/css/refill.css">
</head>
<body>
<div class="header">
    <div><fmt:message key="refill.RefillAccount"/></div>
</div>
<div class="main">
    <form class="refillForm" action="refill" method="post">
        <div class="line-wrapper">
            <div class="line"></div>
            <div class="submitDiv>">
                <div class="value-submitDiv">
                    <fmt:message key="refill.EnterAmountReplenish"/>
                    <input name="amount" class="amount">
                    <div class="error">
                        ${requestScope.errorAmount}</div>
                </div>
                <div><input class="button" type="submit" value="<fmt:message key="refill.Replenish"/>">
                </div>
            </div>
            <div class="card">
                <div class="top-padding"></div>
                <div class="value" >
                    <fmt:message key="refill.EnterCard"/>
                    <input name="cardName" class="cardName"></div>
                    <div class="error">
                        ${requestScope.errorCardName}
                    </div>
                <div class="value">
                    <fmt:message key="refill.EnterCardsExpirationDate"/>
                    <div class="date">
                        <input name="month" class="month">
                        <div class="separator">/</div>
                        <input name="year" class="year">
                </div>
                    <div class="error">
                        ${requestScope.errorDate}</div></div>
                <div class="value">
                    <fmt:message key="refill.EnterCardsCVC"/>
                    <input name="cvc" class="cvc">
                    <div class="error">
                        ${requestScope.errorCVC}</div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="information">
    <div>2018-2020</div>
</div>
</body>
</html>
