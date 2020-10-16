<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 04.10.2020
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://localhost:8080/resource/css/refill.css">
</head>
<body>
<div class="header">
    <div>Refill you account</div>
</div>
<%--<div>Refill you account</div>--%>
<%--<div class="cardBox">--%>
<%--    <div class="line"></div>--%>
<%--    <div class="input">--%>
<%--        <div class="submitDiv>">--%>
<%--            <div class="value">--%>
<%--                Enter the amount to replenish:--%>
<%--                <input name="amount" type="amount">--%>
<%--            </div>--%>
<%--            <div><input class="button" type="submit" value="Replenish">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="card">--%>
<%--            <form class="refillForm" action="refill" method="post">--%>
<%--                <div class="value">--%>
<%--                    Enter your card:--%>
<%--                    <input name="cardName" class="cardName"></div>--%>
<%--                &lt;%&ndash;    <div class="error">&ndash;%&gt;--%>
<%--                &lt;%&ndash;        ${requestScope.errorLogin}&ndash;%&gt;--%>
<%--                &lt;%&ndash;    </div>&ndash;%&gt;--%>
<%--                <div class="value">--%>
<%--                    Enter cards expiration date:--%>
<%--                    <input name="date" type="date">--%>
<%--                </div>--%>
<%--                &lt;%&ndash;    <div class="error">&ndash;%&gt;--%>
<%--                &lt;%&ndash;        ${requestScope.errorPassword}</div>&ndash;%&gt;--%>
<%--                <div class="value">--%>
<%--                    Enter cards CVC:--%>
<%--                    <input name="cvc" type="cvc">--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<div class="main">
    <form class="refillForm" action="refill" method="post">
        <div class="line-wrapper">
            <div class="line"></div>
            <div class="submitDiv>">
                <div class="value-submitDiv">
                    Enter the amount to replenish:
                    <input name="amount" class="amount">
                    <div class="error">
                        ${requestScope.errorAmount}</div>
                </div>
                <div><input class="button" type="submit" value="Replenish">
                </div>
            </div>
            <div class="card">
                <div class="top-padding"></div>
                <div class="value" >
                    Enter your card:
                    <input name="cardName" class="cardName"></div>
                    <div class="error">
                        ${requestScope.errorCardName}
                    </div>
                <div class="value">
                    Enter cards expiration date:
                    <div class="date">
                        <input name="month" class="month">
                        <div class="separator">/</div>
                        <input name="year" class="year">
                </div>
                    <div class="error">
                        ${requestScope.errorDate}</div></div>
                <div class="value">
                    Enter cards CVC:
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
