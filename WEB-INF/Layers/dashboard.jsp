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
        <link href="${pageContext.request.contextPath}/css/Layers/dashboard.css" rel="stylesheet" type="text/css" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" type="text/css" />
        <link rel="icon" type="image/png" href="<c:url value="/images/logo1.jpg"/>"/>
        <link href="${pageContext.request.contextPath}/css/Layers/main.css" rel="stylesheet" type="text/css"/>
        <jsp:include page="/WEB-INF/Layers/header.jsp"></jsp:include>

        </head>
        <body>
            <div class="header">
                <a class="logo-link" href="${pageContext.request.contextPath}/food/orderPage.do">
                <img class="logo" src="${pageContext.request.contextPath}/images/logo-vertical.jpg"/>
            </a>
            <form role="search" class="header-form">
                <div class="btn-header" style="cursor: default; text-transform: uppercase">${user.name}</div>
                <a href="<c:url value="/user/logout.do"/>" class="btn-header btn-last">Đăng Xuất</a>
            </form>
        </div>
        <div class="row w-100" style="min-height: 78.4vh;">

            <nav class="col l-2 dashboard p-0">
                <div class="dashboard-logo">
                    <img class="dashboard-pic" src="${pageContext.request.contextPath}/images/logo.png" alt="">
                </div>
                <ul class="dashboard-list">
                    <li class="dashboard-item"><a class="dashboard-link" href="<c:url value="/food/orderPage.do"/>">
                            <i class="fa-solid fa-folder-closed dashboard-icon"></i>
                            <span class="dashboard-name">Order</span>
                        </a></li>   
                    <li class="dashboard-item"><a class="dashboard-link" href="<c:url value="/food/sessionPage.do"/>">
                            <i class="fas fa-calendar-alt dashboard-icon"></i>
                            <span class="dashboard-name">Session</span>
                        </a></li>
                    <li class="dashboard-item"><a class="dashboard-link" href="<c:url value="/food/mealPage.do"/>">
                            <i class="fa-solid fa-bowl-food dashboard-icon"></i>
                            <span class="dashboard-name">Meal</span>
                        </a></li>
                    <li class="dashboard-item"><a class="dashboard-link" href="<c:url value="/food/foodPage.do"/>">
                            <i class="fa-solid fa-utensils dashboard-icon"></i>
                            <span class="dashboard-name">Food</span>
                        </a></li>
                    <div class="dashboard-divide"></div>
                    <li class="dashboard-item"><a class="dashboard-link" href="<c:url value="/user/chef.do"/>">
                            <i class="fa-solid fa-user dashboard-icon"></i>
                            <span class="dashboard-name">Chef</span>
                        </a></li>
                    <li class="dashboard-item"><a class="dashboard-link" href="<c:url value="/user/customer.do"/>">
                            <i class="fa-solid fa-user dashboard-icon"></i>
                            <span class="dashboard-name">Customer</span>
                        </a></li>    
                    <div class="dashboard-divide"></div>
                    <li class="dashboard-item"><a class="dashboard-link" href="<c:url value="/food/categoryPage.do"/>">
                            <i class="fa-solid fa-bars dashboard-icon"></i>
                            <span class="dashboard-name">Category</span>
                        </a></li>
                    <li class="dashboard-item"><a class="dashboard-link" href="<c:url value="/home/buildingPage.do"/>">
                            <i class="fa-solid fa-building dashboard-icon"></i>
                            <span class="dashboard-name">Building</span>
                        </a></li>    
                </ul>
            </nav>
            <div class="main-body col l-10">
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