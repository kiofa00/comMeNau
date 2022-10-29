<%-- 
    Document   : mealDetail
    Created on : Oct 8, 2022, 9:39:24 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cơm Mẹ Nấu</title>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/views/food/mealDetail.css" rel="stylesheet" type="text/css"/>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="main">
            <div class="container-fluid">
                <div class="row pt-30">
                    <div class="col-sm-4 col-md-4 col-lg-6 pr-40">
                        <div class="card">
                            <h1 class="meal-name" >${meal.name}</h1>
                            <img class="meal-img" src="${pageContext.request.contextPath}/images/${meal.images[0]}">
                        </div>
                        <div class="describe">
                            <p class="meal-info">${meal.detail}</p>
                        </div>
                    </div>
                    <div class="col-sm-4 col-md-4 col-lg-6">
                        <div class="card-detail">
                            <h2 class="card-detail-title">Món ăn</h2>
                            <div class="card-detail-list">
                                <c:forEach items="${meal.foods}" var="food">
                                    <div class="card-detail-item" style="cursor: default">
                                        <div class="card-detail-box">
                                            <img src="<c:url value="/images/${food.images[0]}"/>" class="card-detail-img">
                                            <div class="card-detail-content" >
                                                <h4 class="food-name">${food.name}</h4>
                                                <p class="food-des">
                                                    ${food.nutrition}
                                                </p>
                                            </div>
                                        </div>
                                    </div> 
                                </c:forEach>
                            </div>
                        </div>
                        <div class="price">
                            <span class="price-left"><fmt:formatNumber
                                    value="${meal.price}"
                                    pattern="#,### "/>VND</span>
                            <a href="<c:url value="/home/showCart.do?mealId=${meal.id}&topTimeline=${topTimeline}&topDay=${topDay}"/>" class="price-btn">
                                <i class="fa-solid fa-circle-plus icon-plus"></i><span class="price-right">Mua Ngay</span>
                            </a>  
                        </div>
                            <h1>${message}</h1>
                    </div>
                </div>
                <div>
                    <div class="other-meal">
                        <h1 class="other-meal-content">
                            Các Mâm Cơm Khác
                        </h1>
                        <div class="other-meal-bottom"></div>
                    </div>
                    <div class="row">
                        <c:if test="${empty mealList}"><h1 class="message">Khung giờ này đã hết mâm cơm. Mong quý khách thông cảm</h1></c:if>
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
    </body>
</html>