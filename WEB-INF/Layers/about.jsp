<%-- 
    Document   : main
    Created on : Jun 12, 2022, 2:05:06 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/Layers/about.css" rel="stylesheet" type="text/css" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" type="text/css" />
        <link rel="icon" type="image/png" href="<c:url value="/images/logo1.jpg"/>"/>
        <link href="${pageContext.request.contextPath}/css/Layers/main.css" rel="stylesheet" type="text/css"/>
        <title>Cơm Mẹ Nấu</title>
        <jsp:include page="/WEB-INF/Layers/header.jsp"></jsp:include>

        </head>
        <body>
            <div class="header">
                <a class="logo-link" href="${pageContext.request.contextPath}/home/homePage.do">
                <img class="logo" src="${pageContext.request.contextPath}/images/logo-vertical.jpg"/>
            </a>
            <form role="search" class="header-form">
                <c:if test="${empty user }">
                    <a href="<c:url value="/user/login.do"/>" class="btn-header">Đăng Nhập</a>
                    <a href="<c:url value="/user/register.do"/>"
                       class="btn-header btn-last">Đăng Ký</a>
                </c:if>
                <c:if test="${not empty user }">
                    <a class="btn-header" style="cursor: pointer; text-transform: uppercase"
                       href="<c:url value="/user/info.do"/>">${user.name}</a>
                    <a href="<c:url value="/user/logout.do"/>" class="btn-header btn-last">Đăng Xuất</a>
                </c:if>
            </form>
        </div>
        <div class="row w-100 m-0">
            <nav class="col l-3 dashboard" style="height: 78.43vh; border-right: solid 1px; padding: 0">
                <ul class="dashboard-list">
                    <li class="dashboard-item">
                        <div class="dashboard-logo">
                            <img class="dashboard-pic" src="${pageContext.request.contextPath}/images/logo.png" alt="">
                        </div>
                    </li>
                    <li class="dashboard-item"><a class="dashboard-link" href="<c:url value="/user/info.do"/>">
                            <i class="fa-solid fa-user dashboard-icon"></i>
                            <span class="dashboard-name">Tài Khoản Của Tôi</span>
                        </a></li>
                    <li class="dashboard-item"><a class="dashboard-link" href="<c:url value="/user/history.do"/>">
                            <i class="fas fa-calendar-alt dashboard-icon"></i>
                            <span class="dashboard-name">Lịch Sử Đơn Hàng</span>
                        </a></li>
                </ul>
            </nav>
            <div class="main-body col l-9 d-flex-center">
                <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp"/>
            </div>
        </div>
        <div class="footer">
            <img class="footer-img" src="${pageContext.request.contextPath}/images/logo-vertical.jpg" alt=""/>
            <span class="footer-contact"> Company Profile | Follow Us 
                <i class="fa-brands fa-facebook"></i>
                <i class="fa-brands fa-instagram"></i>
                <i class="fa-brands fa-youtube"></i></span>
            <span class="footer-info">Copyrights &COPY; 2022 By Com Me Nau. All Rights Reserved.</span>
        </div>

        <jsp:include page="/WEB-INF/Layers/footer_script.jsp"></jsp:include>
    </body>
</html>