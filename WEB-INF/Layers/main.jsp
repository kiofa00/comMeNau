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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="<c:url value="/images/logo1.jpg"/>"/>
        <link href="${pageContext.request.contextPath}/css/Layers/main.css" rel="stylesheet" type="text/css"/>
        <jsp:include page="/WEB-INF/Layers/header.jsp"></jsp:include>

        </head>
        <body>
            <div class="header">
                <a class="logo-link" href="${pageContext.request.contextPath}/home/homePage.do">
                <img class="logo" src="${pageContext.request.contextPath}/images/logo-vertical.jpg"/>
            </a>
            <form role="search" class="header-form">
                <c:if test="${empty user }">
                    <a href="<c:url value="/home/checkOrderPage.do"/>" class="btn-header">Tra Cứu Đơn Hàng</a>
                    <a href="<c:url value="/user/login.do"/>" class="btn-header">Đăng Nhập</a>
                    <a href="<c:url value="/user/register.do"/>"
                       class="btn-header btn-last">Đăng Ký</a>
                </c:if>
                <c:if test="${not empty user }">
                    <a href="<c:url value="/home/checkOrderPage.do"/>" class="btn-header">Tra Cứu Đơn Hàng</a>
                    <a class="btn-header" style="cursor: pointer; text-transform: uppercase"
                       href="<c:url value="/user/info.do"/>">${user.name}</a>
                    <a href="<c:url value="/user/logout.do"/>" class="btn-header btn-last">Đăng Xuất</a>
                </c:if>  
            </form>
        </div>
        <div id="carouselExampleIndicators" class="carousel slide slider" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                        aria-current="true" aria-label="Slide 1"></button>

                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                        aria-label="Slide 2" class=""></button>

                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                        aria-label="Slide 3" class=""></button>

                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3"
                        aria-label="Slide 4" class=""></button>
            </div>
            <div class="carousel-inner slider-list">
                <div class="carousel-item active slider-item">
                    <img class="slider-img" src=" <c:url value="/images/food.jpg"/>">

                </div>
                <div class="carousel-item slider-item">
                    <img class="slider-img" src=" <c:url value="/images/food1.jpg"/>">

                </div>
                <div class="carousel-item slider-item">
                    <img class="slider-img" src=" <c:url value="/images/food2.jpg"/>">

                </div>
                <div class="carousel-item slider-item">
                    <img class="slider-img" src=" <c:url value="/images/food3.jpg"/>">

                </div>
            </div>
            <div class="slider-left" type="button" data-bs-target="#carouselExampleIndicators"
                 data-bs-slide="prev">
                <i class="fa-solid fa-angle-left icon-left"></i>
            </div>
            <div class="slider-right" type="button" data-bs-target="#carouselExampleIndicators"
                 data-bs-slide="next">
                <i class="fa-solid fa-angle-right icon-right"></i>
            </div>
        </div>

        <div class="main-body">
            <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp"/>
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
        <script>
            document.body.onload = ev => {
                initClock()
                var myCarousel = document.querySelector('#carouselExampleIndicators')
                var carousel = new bootstrap.Carousel(myCarousel, {
                    interval: 1000,
                    wrap: false
                })
                const dropdownElementList = document.querySelectorAll('.dropdown-toggle')
                console.log(dropdownElementList)
                const dropdownList = [...dropdownElementList].map(dropdownToggleEl => new bootstrap.Dropdown(dropdownToggleEl))
            }
            
            function updateClock() {
                var now = new Date();
                var hou = now.getHours(),
                        min = now.getMinutes(),
                        sec = now.getSeconds()
                Number.prototype.pad = function (digits) {
                    for (var n = this.toString(); n.length < digits; n = 0 + n)
                        ;
                    return n;
                }
                var ids = ["hour", "minutes"];
                var values = [hou.pad(2), min.pad(2)];
                for (var i = 0; i < ids.length; i++)
                    document.getElementById(ids[i]).firstChild.nodeValue = values[i];
            }
            function initClock() {
                updateClock();
                window.setInterval("updateClock()", 1);
            }
        </script>
    </body>
</html>