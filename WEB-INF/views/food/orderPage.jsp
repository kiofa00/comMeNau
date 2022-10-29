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
        <link href="${pageContext.request.contextPath}/css/views/food/orderPage.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Cơm Mẹ Nấu</title>
    </head>
    <body>
        <div class="main" id="showFood">
            <div class="food-title">
                <h2 class="food-title-content">
                    DANH SÁCH ĐƠN HÀNG
                </h2>
                <div class="food-title-bottom"></div>
            </div>
            <div class="grid">
                <div class="timeline">
                    <ul class="timeline-list">
                        <li class="timeline-item"><button value="2" class="timeline-btn" onclick="showOrderByStatus(this)">Đã Hoàn Thành</button>
                        <li class="timeline-item"><button value="1" class="timeline-btn" onclick="showOrderByStatus(this)">Đang Xử Lý</button>
                        <li class="timeline-item"><button value="0" class="timeline-btn" onclick="showOrderByStatus(this)">Đơn Đã Huỷ</button>
                    </ul>
                </div>
                <div id="content">
                    <div class="sort">
                        <span class="sort-bar-label">Lựa Chọn Thời Gian: </span>
                        <select class="sort-option" name="fromTo" id="fromTo">
                            <c:forEach items="${timelines}" var="t">
                                <option value="${t}">${t}</option>
                            </c:forEach>
                        </select>
                        <select class="sort-option" name="day" id="day">
                            <c:forEach items="${days}" var="d">
                                <option value="${d}">${d}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Tìm Kiếm" class="search-btn" onclick="searchOrderbySession()" />
                    </div> 
                    <table class="table table-striped table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>ID</th>
                                <th>Tên Khách Hàng</th>
                                <th>Địa Chỉ</th>
                                <th>Khung Giờ</th>
                                <th>Ngày</th>
                                <th>Đơn Giá</th>
                                <th>Phương Thức</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody id="showOrder">
                            <c:forEach items="${orderList}" var="order">
                                <tr style="background: #fff;">
                                    <td>
                                        ${order.id}
                                    </td>
                                    <td>
                                        ${order.cusName}
                                    </td>
                                    <td>
                                        ${bulding[order.id].name}
                                    </td>
                                    <td>
                                        ${session[order.id].fromto}
                                    </td>
                                    <td>
                                        ${session[order.id].day}
                                    </td>
                                    <td>
                                        <fmt:formatNumber
                                            value="${order.price}"
                                            pattern="#,### "/>VND
                                    </td>
                                    <td>
                                        ${payment[order.id].type}
                                    </td>
                                    <td>
                                        <button value="${order.id}" class="text-decoration-none func-btn" onclick="acceptOrder(this)">Accept</button> | 
                                        <button value="${order.id}" class="text-decoration-none func-btn" onclick="denyOrder(this)">Deny</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            var timelines = document.querySelectorAll(".timeline-btn");
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
                                            function searchOrderbySession() {
                                                var fromTo = document.getElementById("fromTo").value;
                                                var day = document.getElementById("day").value;
                                                $.ajax({
                                                    url: "/comMeNau/food/searchOrder.do",
                                                    type: "get",
                                                    data: {
                                                        fromTo: fromTo,
                                                        day: day
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("showOrder");
                                                        row.innerHTML = data;

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
                                            function showOrderByStatus(param) {
                                                var status = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/food/showOrderByStatus.do",
                                                    type: "get",
                                                    data: {
                                                        status: status
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("content");
                                                        row.innerHTML = data;
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
                                            function acceptOrder(param) {
                                                var orderId = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/food/acceptOrder.do",
                                                    type: "get",
                                                    data: {
                                                        orderId: orderId
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("showOrder");
                                                        row.innerHTML = data;
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
                                            function denyOrder(param) {
                                                var orderId = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/food/denyOrder.do",
                                                    type: "get",
                                                    data: {
                                                        orderId: orderId
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("showOrder");
                                                        row.innerHTML = data;
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
        </script>
    </body>
</html>