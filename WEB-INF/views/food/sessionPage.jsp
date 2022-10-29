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
        <link href="${pageContext.request.contextPath}/css/views/food/sessionPage.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Cơm Mẹ Nấu</title>
    </head>
    <body>
        <div class="main" id="showFood">
            <div class="food-title">
                <h2 class="food-title-content">
                    DANH SÁCH SESSION
                </h2>
                <div class="food-title-bottom"></div>
                <button class="plus-btn">
                    <a href="<c:url value="/food/newSession.do"/>"
                       class="text-decoration-none plus-link">
                        <i class="fa-solid fa-circle-plus" style="font-size: 30px;padding-right: 8px;"></i>
                        Tạo Session Mới
                    </a>
                </button>
            </div>
            <div class="grid">
                <div class="timeline">
                    <ul class="timeline-list">
                        <li class="timeline-item"><button value="future" class="timeline-btn" onclick="loadSession(this)">Tương Lai</button>
                        <li class="timeline-item"><button value="now" class="timeline-btn" onclick="loadSession(this)">Hiện Tại</button>
                        <li class="timeline-item"><button value="pass" class="timeline-btn" onclick="loadSession(this)">Quá Khứ</button></ul>
                </div>
                <div id="content">
                    <table class="table table-striped table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>No</th>
                                <th>ID</th>
                                <th>Khung Giờ</th>
                                <th>Ngày</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody id="showSession">
                            <c:forEach items="${sessionList}" var="s" varStatus="loop">

                                <tr style="background: #fff;">
                                    <td>
                                        ${loop.count}
                                    </td>
                                    <td>
                                        ${s.code}
                                    </td>
                                    <td>
                                        ${s.fromto}
                                    </td>
                                    <td>
                                        ${s.day}
                                    </td>
                                    <td>
                                        <button value="${s.code}" class="view-btn"><a class="text-decoration-none" href="<c:url value="/food/sessionDetail.do?code=${s.code}"/>">View</a></button> | <a class="text-decoration-none" href="<c:url value="/food/editSession.do?code=${s.code}"/>">Edit</a>
                                    </td>
                                    <td style="padding: 5px 0 0 0;">
                                        <button class="trash-btn" onclick="deleteSession(this)" value="${s.code}">
                                            <i class="fa-solid fa-trash-can"></i>
                                        </button>
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
                                            function loadSession(param) {
                                                var time = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/food/loadSessionPage.do",
                                                    type: "get",
                                                    data: {
                                                        time: time
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("showSession");
                                                        row.innerHTML = data;
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
                                            function deleteSession(param) {
                                                var code = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/food/deleteSession.do",
                                                    type: "get",
                                                    data: {
                                                        code: code
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("showSession");
                                                        row.innerHTML = data;
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
        </script>
    </body>
</html>