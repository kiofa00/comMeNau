<%-- 
    Document   : homepage
    Created on : Jun 12, 2022, 2:41:33 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="${pageContext.request.contextPath}/css/views/home/homePage.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Cơm Mẹ Nấu</title>
    </head>
    <body>
        <div class="body">
            <c:if test="${not empty MealHot}">
                        <div class="grid">
                            <div class="row mt-16 w-100">
                                <div class="best-seller">
                                    <h2 class="best-seller-content">
                                        BEST SELLER
                                    </h2>
                                    <div class="best-seller-bottom"></div>
                                </div>
                                <div class="col l-8 pl-144">
                                    <img class="preview-img-food" src="${pageContext.request.contextPath}/images/${MealHot.images[0]}" alt=""/>
                                    <div class="d-flex justify-content-between">
                                        <h2 class="preview-intro-price">Chỉ
                                            <span class="preview-price"><fmt:formatNumber
                value="${MealHot.price}"
                pattern="#,### "/>VND</span>
    </h2>
    <button class="btn btn-outline-success cart-btn-top">
        <a href="<c:url value="/food/mealDetail.do?mealId=${MealHot.id}&topTimeline=${topFromTo}&topDay=${topDay}"/>"
                                   class="text-decoration-none cart-link-top">
            Xem Thông tin
        </a>
    </button>
</div>
</div>
                                   
<div class="col l-4 ml-16">
<h1 class="preview-title">
            ${MealHot.name}
        </h1>
        <hr class="preview-border"/>
            <c:forEach items="${MealHot.foods}" var="food">
                <div class="preview-food-list">
                    <img class="preview-img-small" src="${pageContext.request.contextPath}/images/${food.images[0]}" alt=""/>
                    <span class="preview-food">${food.name}</span><br/>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
        </c:if>
            <div class="preview">
                <div class="preview-content">
                    <h1 class="preview-heading">
                        Cơm Mẹ Nấu
                    </h1>
                    <div class="preview-description">
                        Một nơi bạn có thể tìm được cảm giác quê hương ngay trong lòng thành phố
                    </div>
                </div>
                <img class="preview-img" src="${pageContext.request.contextPath}/images/food4.jpg" alt=""/>
            </div>
            <div id="jump" class="jump"></div>
            <form action="<c:url value="/home/search.do"/>" class="search" id="search">
                <div class="search-info">
                    <input oninput="searchAjax(this)" class="search-bar"  placeholder="Nhập thông tin mâm cơm.." type="search" name="Search"
                           value="${Search}"/>
                    <span class="search-logo"> <i class="fa fa-search"></i></span>
                </div>
            </form>
            <div class="grid wide">
                <div class="row">
                    <div class="col l-12 m-6 c-12">
                        <div class="row">
                            <div class="timeline">
                                <ul class="timeline-list">
                                    <li class="timeline-item"><button value="${dayOfWeek['Thu 2']}" onclick="getMealByDay(this)" class="timeline-btn">Thứ 2<br/>${dayOfWeek['Thu 2']}</button>
                                    <li class="timeline-item"><button value="${dayOfWeek['Thu 3']}" onclick="getMealByDay(this)" class="timeline-btn">Thứ 3<br/>${dayOfWeek['Thu 3']}</button>
                                    <li class="timeline-item"><button value="${dayOfWeek['Thu 4']}" onclick="getMealByDay(this)" class="timeline-btn">Thứ 4<br/>${dayOfWeek['Thu 4']}</button>
                                    <li class="timeline-item"><button value="${dayOfWeek['Thu 5']}" onclick="getMealByDay(this)"  class="timeline-btn">Thứ 5<br/>${dayOfWeek['Thu 5']}</button>
                                    <li class="timeline-item"><button value="${dayOfWeek['Thu 6']}" onclick="getMealByDay(this)" class="timeline-btn">Thứ 6<br/>${dayOfWeek['Thu 6']}</button>
                                    <li class="timeline-item"><button value="${dayOfWeek['Thu 7']}" onclick="getMealByDay(this)" class="timeline-btn">Thứ 7<br/>${dayOfWeek['Thu 7']}</button>
                                    <li class="timeline-item"><button value="${dayOfWeek['chu nhat']}" onclick="getMealByDay(this)" class="timeline-btn">Chủ nhật<br/>${dayOfWeek['chu nhat']}</button></li>
                                </ul>
                            </div>
                            <div id="show2" class="row">

                                <div class="sort">
                                    <span class="sort-bar-label">Sắp Xếp Theo</span>
                                    <c:forEach items="${session.timeline}" var="time">
                                        <button value="${time.from}-${time.to}" class="sort-option" onclick="getMealBySession(this)">${time.from}-${time.to}</button>
                                    </c:forEach>
                                </div>
                                <div id="show1" class="row">
                                    <c:if test="${empty mealList}">
                                        <h1 class="message">${message}</h1>
                                    </c:if>
                                    <c:forEach items="${mealList}" var="meal">
                                        <div class="col l-4 c-12 m-6">
                                            <a class="card" href="<c:url value="/food/mealDetail.do?mealId=${meal.id}"/>">
                                                <div class="card-top">
                                                    <img src="${pageContext.request.contextPath}/images/${meal.images[0]}" class="card-img-big">
                                                    <h5 class="card-title">${meal.name}</h5>
                                                </div>
                                                <div class="card-body row">
                                                    <p class="card-price">
                                                        <span class="card-price-pre">Từ</span>
                                                        <fmt:formatNumber
                                                            value="${meal.price}"
                                                            pattern="#,### VND"/>
                                                    </p>       
                                                    <p class="card-chef">
                                                        <span class="card-chef-pre">Đầu Bếp: </span>
                                                        ${meal.chefId.name}
                                                    </p>           
                                                </div>
                                            </a> 
                                        </div>                  
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="time-show shadow-sm text-decoration-none text-center">
            <span id="hour">00</span> : <span id="minutes">00</span>
        </div>
        <a href="#jump" class="to-header-link shadow-sm text-decoration-none text-center ">
            <i id="up" class="fa-solid fa-arrow-up display-none"></i>
            <i id="down" class="fa-solid fa-arrow-down"></i>
        </a>   

    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
                                            var timelines = document.querySelectorAll(".timeline-btn");
                                            var sortoptions = document.querySelectorAll(".sort-option");
                                            for (const timeline of timelines) {
                                                timeline.addEventListener("click", function () {
                                                    timelines.forEach(timeline => timeline.classList.remove("mystyle"))
                                                    timeline.classList.add("mystyle")
                                                })
                                            }
                                            for (const sortoption of sortoptions) {
                                                sortoption.addEventListener("click", function () {
                                                    sortoptions.forEach(sortoption => sortoption.classList.remove("mystyle"))
                                                    sortoption.classList.add("mystyle")
                                                })
                                            }
                                            function getMealBySession(param) {
                                                timeLine = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/home/showMealByTimelineAjax.do",
                                                    type: "get",
                                                    data: {
                                                        timeline: timeLine
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("show1");
                                                        row.innerHTML = data;
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
                                            function getMealByDay(param) {
                                                day1 = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/home/showMealbyDay.do",
                                                    type: "get",
                                                    data: {
                                                        day1: day1
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("show2");
                                                        row.innerHTML = data;
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
                                            function searchAjax(param) {
                                                txtSearch = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/home/searchAjax.do",
                                                    type: "get",
                                                    data: {
                                                        Search: txtSearch
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("show1");
                                                        row.innerHTML = data;
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
                                            function addToCart(param) {
                                                mealId = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/home/addToCart.do",
                                                    type: "get",
                                                    data: {
                                                        mealId: mealId
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("quantity");
                                                        row.innerHTML = data;
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
                                            const upBtn = document.getElementById("up");
                                            const downBtn = document.getElementById("down");
                                            downBtn.addEventListener("click", function () {
                                                if (downBtn.classList.contains("display-none")) {
                                                    downBtn.classList.remove('display-none')
                                                    upBtn.classList.add('display-none')
                                                }
                                            })
                                            window.addEventListener("scroll", function () {
                                                if (window.pageYOffset >= 1439.199951171875) {
                                                    downBtn.classList.add('display-none')
                                                    upBtn.classList.remove('display-none')
                                                } else {
                                                    downBtn.classList.remove('display-none')
                                                    upBtn.classList.add('display-none')
                                                }
                                            })
    </script>
</html>