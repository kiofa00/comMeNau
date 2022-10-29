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
        <link href="${pageContext.request.contextPath}/css/views/food/categoryPage.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Cơm Mẹ Nấu</title>
    </head>
    <body>
        <div class="main" id="showFood">
            <div class="food-title">
                <h2 class="food-title-content">
                    DANH SÁCH DANH MỤC
                </h2>
                <div class="food-title-bottom"></div>
                <button class="plus-btn">
                    <a href="<c:url value="/food/newCategory.do"/>"
                       class="text-decoration-none plus-link">
                        <i class="fa-solid fa-circle-plus" style="font-size: 30px;padding-right: 8px;"></i>
                        Tạo Danh Mục Mới
                    </a>
                </button>
            </div>
            <div class="grid">
                <div id="content">
                    <table class="table table-striped table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>No</th>
                                <th>Tên Danh Mục</th>
                                <th>Mức Độ Ưu Tiên</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody id="show">
                            <c:forEach items="${categoryList}" var="c" varStatus="loop">
                                <tr style="background: #fff;">
                                    <td>
                                        ${loop.count}
                                    </td>
                                    <td>
                                        ${c.name}
                                    </td>
                                    <td>
                                        ${c.type}
                                    </td>
                                    <td>
                                        <a class="text-decoration-none" href="<c:url value="/food/updateCategoryPage.do?categoryId=${c.id}"/>">Edit</a>
                                    </td>
                                    <td style="padding: 5px 0 0 0;">
                                        <button class="trash-btn" value="${c.id}" onclick="deleteCategory(this)" >
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
                                            function showCategoryDetail(param) {
                                                var categoryId = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/food/categoryDetail.do",
                                                    type: "get",
                                                    data: {
                                                        categoryId: categoryId
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("myModal");
                                                        row.innerHTML = data;
                                                        document.getElementById("myModal").style.display = "block";
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
                                            function deleteCategory(param) {
                                                var categoryId = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/food/deleteCategory.do",
                                                    type: "get",
                                                    data: {
                                                        categoryId: categoryId
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("show");
                                                        row.innerHTML = data;
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
        </script>
    </body>
</html>