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
        <link href="${pageContext.request.contextPath}/css/views/food/foodPage.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Cơm Mẹ Nấu</title>
    </head>
    <body>
        <div class="container" >
            <div class="grid">
                <div class="row">
                    <div class="col l-1"></div>
                    <div class="col l-3 category p-0" >
                        <ul class="category-list">
                            <form action="<c:url value="/food/search.do"/>" class="search" id="search">
                                <div class="search-info">
                                    <span class="search-logo"> <i class="fa fa-search"></i></span>
                                    <input  oninput="searchByName(this)"class="search-bar"  placeholder="Nhập thông tin.." type="search" name="Search"
                                            value="${Search}"/>
                                </div>
                            </form>
                            <li class="category-item category-title">DANH MỤC</li>
                            <li class="divider-under"></li>
                                <c:forEach items="${categoryList}" var="category">
                                <button class="category-name category-name-link" onclick="showFood(this)" value="${category.id}">
                                    ${category.name}
                                </button>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="col l-8 p-0" >
                        <div class="food-title">
                            <h2 class="food-title-content">
                                DANH SÁCH MÓN ĂN
                            </h2>
                            <div class="food-title-bottom"></div>
                            <button class="plus-btn">
                                <a href="<c:url value="/food/newFood.do"/>"
                                   class="text-decoration-none plus-link">
                                    <i class="fa-solid fa-circle-plus" style="font-size: 30px;padding-right: 8px;"></i>
                                    Tạo Món Ăn Mới
                                </a>
                            </button>
                        </div>
                            <div class="grid">
                            <div class="row d-flex" id="content">
                                <c:forEach items="${foodList}" var="food">
                                    <div class="row">
                                        <a class="col l-9 card-detail-item"href="<c:url value="/food/editFood.do?foodId=${food.id}"/>">
                                            <div class="card-detail-box">
                                                <img src="<c:url value="/images/${food.images[0]}"/>" class="card-detail-img">
                                                <div class="card-detail-content" >
                                                    <h4 class="food-name">${food.name}</h4>
                                                    <p class="food-des">
                                                        ${food.nutrition}
                                                    </p>
                                                </div>
                                            </div>
                                        </a> 
                                        <div class="col l-2">         
                                            <button class="trash-btn" value="${food.id}" onclick="deleteFood(this)">
                                                    <i class="fa-solid fa-trash-can" style="font-size: 45px"></i>
                                            </button> 
                                        </div>     
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            var timelines = document.querySelectorAll(".category-name");
                                            for (const timeline of timelines) {
                                                timeline.addEventListener("click", function () {
                                                    timelines.forEach(timeline => timeline.classList.remove("mystyle"))
                                                    timeline.classList.add("mystyle")
                                                })
                                            }
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
                                                categoryId: category
                                            },
                                            success: function (data) {
                                                var row = document.getElementById("content");
                                                row.innerHTML = data;
                                            },
                                            error: function () {
                                            }
                                        });
                                    }
                                    function deleteFood(param) {
                                        var foodId = param.value;
                                        $.ajax({
                                            url: "/comMeNau/food/deleteFood.do",
                                            type: "get",
                                            data: {
                                                foodId: foodId
                                            },
                                            success: function (data) {
                                                var row = document.getElementById("content");
                                                row.innerHTML = data;
                                            },
                                            error: function () {
                                            }
                                        });
                                    }
        </script>
    </body>
</html>