<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" href="<c:url value="/images/logo.jpg"/>"/>
        <link href="${pageContext.request.contextPath}/css/views/user/login.css" rel="stylesheet" type="text/css" />
        <title>Cơm Mẹ Nấu</title>
    </head>
    <body>
        <section class="login">
            <div class="login__box">
                <div class="left">
                    <div class="top__link">
                        <a class="top__content" href="${pageContext.request.contextPath}/home/homePage.do">
                            <i class="fa-regular fa-circle-left top__icon"></i>
                            Return Home
                        </a>
                    </div>
                    <div class="contact">
                        <form action="<c:url value="/user/checkLogin.do" />" method="post" style="width: 75%">
                            <h1 class="left__title">Login</h1>
                            <div class="input__box">
                                <input type="text" name="account" id="account" value="${account}" required="required">
                                <span class="input__content">Username</span>
                                <i class="small__content"></i>
                            </div>
                            <div class="input__box">
                                <input type="password" name="password" id="pass" required="required">
                                <span class="input__content">Password</span>
                                <i class="small__content"></i>
                            </div>
                            <input type="submit" value="Login" class="submit">
                            <div class="divider-under"></div>
                            <a class="submit" style="margin-top: 35px" href="<c:url value="/user/register.do"/>">Register</a>
                        </form>
                    </div>
                        <div class="message">${message} </div>
                </div>
                <div class="right">
                    <img class="right__img" src="${pageContext.request.contextPath}/images/food5.jpg"/>
                    <div class="right__text">
                        <h2 class="right__title">CƠM MẸ NẤU</h2>
                        <h5 class="right__des">Một nơi bạn có thể tìm được cảm giác quê hương <br/> ngay trong lòng thành phố</h5>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>