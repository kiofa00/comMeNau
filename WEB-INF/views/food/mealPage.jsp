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
        <link href="${pageContext.request.contextPath}/css/views/food/mealPage.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Cơm Mẹ Nấu</title>
    </head>
    <body>
        <div id="jump" class="jump"></div>
        <div class="main" id="showFood">
            <div class="food-title">
                <h2 class="food-title-content">
                    DANH SÁCH MÂM CƠM
                </h2>
                <div class="food-title-bottom"></div>
                <button class="plus-btn">
                    <a href="<c:url value="/food/newMeal.do"/>"
                       class="text-decoration-none plus-link">
                        <i class="fa-solid fa-circle-plus" style="font-size: 30px;padding-right: 8px;"></i>
                        Tạo Mâm Cơm Mới
                    </a>
                </button>
            </div>
            <div class="grid">
                <div class="row d-flex m-0" id="content">
                    <c:forEach items="${mealList}" var="meal">
                        <div class="row">
                            <a class="col l-9 card-detail-item" href="<c:url value="/food/editMeal.do?mealId=${meal.id}"/>">
                                <div class="card-detail-box">
                                    <img src="${pageContext.request.contextPath}/images/${meal.images[0]}" class="card-detail-img">
                                    <div class="card-detail-content">
                                        <h4 class="food-name">${meal.name}</h4>
                                        <div class="d-flex justify-content-around card-detail-info">
                                            <p class="card-price">
                                                <span class="card-price-pre">Giá: </span>
                                                <fmt:formatNumber
                                                    value="${meal.price}"
                                                    pattern="#,### VND"/>
                                            </p>       
                                            <p class="card-chef">
                                                <span class="card-chef-pre">Đầu Bếp: </span>
                                                ${meal.chefId.name}
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </a> 
                            <div class="col l-2">        
                                <button class="trash-btn" value="${meal.id}" onclick="deleteMeal(this)">
                                    <i class="fa-solid fa-trash-can" style="font-size: 45px"></i>
                                </button> 
                            </div> 
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <a href="#jump" class="to-header-link shadow-sm text-decoration-none text-center">
            <i id="up" class="fa-solid fa-arrow-up display-none"></i>
            <i id="down" class="fa-solid fa-arrow-down"></i>
        </a>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            function searchByName(param) {
                var txtSearch = param.value;
                $.ajax({
                    url: "/comMeNau/food/searchAjax.do",
                    type: "get",
                    data: {
                        Search: txtSearch
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;
                        var page = document.getElementById("pagination");
                        page.style.display = "none";
                    },
                    error: function () {
                    }
                });
            }
            function showFood(param) {
                var category = param.value;
                $.ajax({
                    url: "/comMeNau/food/foodPageAjax.do",
                    type: "get",
                    data: {
                        categoryName: category
                    },
                    success: function (data) {
                        var row = document.getElementById("showFood");
                        row.innerHTML = data;
                    },
                    error: function () {
                    }
                });
            }
            function deleteMeal(param) {
                var mealId = param.value;
                $.ajax({
                    url: "/comMeNau/food/deleteMeal.do",
                    type: "get",
                    data: {
                        mealId: mealId
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
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
                if (window.pageYOffset >= 88) {
                    downBtn.classList.add('display-none')
                    upBtn.classList.remove('display-none')
                } else {
                    downBtn.classList.remove('display-none')
                    upBtn.classList.add('display-none')
                }
            })
        </script>
    </body>
</html>