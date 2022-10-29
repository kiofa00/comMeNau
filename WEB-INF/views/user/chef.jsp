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
        <link href="${pageContext.request.contextPath}/css/views/user/chef.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Cơm Mẹ Nấu</title>
        <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
    </head>
    <body>
        <div class="main" id="showFood">
            <div class="food-title">
                <h2 class="food-title-content">
                    DANH SÁCH ĐẦU BẾP
                </h2>
                <div class="food-title-bottom"></div>
                <button class="plus-btn">
                    <a href="<c:url value="/user/newChef.do"/>"
                       class="text-decoration-none plus-link">
                        <i class="fa-solid fa-circle-plus" style="font-size: 30px;padding-right: 8px;"></i>
                        Thêm Đầu Bếp Mới
                    </a>
                </button>
            </div>
            <div class="grid">
                <div id="content">
                    <table class="table table-striped table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>No</th>
                                <th>Tên</th>
                                <th>Số Điện Thoại</th>
                                <th>Địa Chỉ</th>
                                <th>Email</th>
                                <th>Lương</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody id="showChef">
                            <c:forEach items="${chefList}" var="ch" varStatus="loop">
                                <tr style="background: #fff;">
                                    <td>
                                        ${loop.count}
                                    </td>
                                    <td>
                                        ${ch.name}
                                    </td>
                                    <td>
                                        ${ch.phone}
                                    </td>
                                    <td>
                                        ${bulding[ch.id].name}
                                    </td>
                                    <td>
                                        ${ch.email}
                                    </td>
                                    <td>
                                        <fmt:formatNumber
                                            value="${ch.salary}"
                                            pattern="#,### "/>VND
                                    </td>
                                    <td>
                                        <a href="<c:url value="/user/editChef.do?chefId=${ch.id}"/>" class="text-decoration-none">Edit</a>
                                    </td>
                                    <td style="padding: 5px 0 0 0;">
                                        <button class="trash-btn" value="${ch.id}" onclick="deleteChef(this)" >
                                            <i class="fa-solid fa-trash-can"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            function deleteChef(param) {
                                        var chefId = param.value;
                                        $.ajax({
                                            url: "/comMeNau/Admin/deleteChef.do",
                                            type: "get",
                                            data: {
                                                chefId: chefId
                                            },
                                            success: function (data) {
                                                var row = document.getElementById("showChef");
                                                row.innerHTML = data;
                                            },
                                            error: function () {
                                            }
                                        });
                                    }
        </script>
    </body>
</html>