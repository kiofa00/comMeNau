<%-- 
    Document   : HistoryShop
    Created on : Sep 28, 2022, 5:20:01 PM
    Author     : MSI GAMING
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cơm Mẹ Nấu</title>
        <link rel="icon" type="image/png" href="<c:url value="/images/logo.jpg"/>"/>
        <link href="${pageContext.request.contextPath}/css/views/user/history.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="box">
            <h1 class="box-header">Lịch Sử Đơn Hàng</h1>
            <div class="box-content">
                <c:forEach items="${orderList}" var="order">
                    <div class="row">
                        <div class="card-detail-item">
                            <div class="card-detail-box row m-0">
                                <img src="${pageContext.request.contextPath}/images/${meal[order.id].images[0]}" class="card-detail-img col l-4 p-0">
                                <div class="card-detail-content col l-8 p-0">
                                    <h4 class="food-name">${order.id}</h4>

                                    <div class="row card-detail-info">
                                        <p class="card-meal-name col l-6">${meal[order.id].name}</p>
                                        <p class="card-price col l-6">
                                            <span class="card-pre">Giá: </span>
                                            <fmt:formatNumber
                                                value="${order.price}"
                                                pattern="#,### VND"/>
                                        </p>       
                                    </div>
                                    <div class="row card-detail-info">
                                        <p class="card-date col l-6">
                                            ${timeline[order.id]}
                                        </p>
                                        <p class="card-address col l-6">
                                            <span class="card-pre">Địa Chỉ:</span> ${address[order.id]}
                                        </p> 
                                    </div>
                                    <div class="row card-detail-info">
                                        <p class="col l-7" style="color:red">
                                            <span class="card-pre">Tình Trạng:</span> ${status[order.id]}
                                        </p>
                                        <c:set var="processing" value="Đang xử lí"/>
                                        <c:if test="${status[order.id] == processing}">
                                            <div class="col l-4 text-center" style="padding-bottom: 15px">
                                            <form action="<c:url value="/user/cancelOrder.do" />">
                                                    <input type="hidden" name="orderId" value="${order.id}" />
                                                    <button class="btn-box" > 
                                                        Huỷ
                                                    </button>
                                                </form>
                                            </div>
                                        </c:if>
                                        
                                    </div>
                                </div>
                            </div>
                        </div> 
                    </div>
                </c:forEach>
            </div>
    </body>
</html>