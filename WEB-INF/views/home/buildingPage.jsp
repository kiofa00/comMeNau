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
        <link href="${pageContext.request.contextPath}/css/views/home/buildingPage.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Cơm Mẹ Nấu</title>
    </head>
    <body>
        <div class="main">
            <div class="food-title">
                <h2 class="food-title-content">
                    DANH SÁCH ĐỊA CHỈ
                </h2>
                <div class="food-title-bottom"></div>
                <button class="plus-btn">
                    <a href="<c:url value="/home/newBuilding.do"/>"
                       class="text-decoration-none plus-link">
                        <i class="fa-solid fa-circle-plus" style="font-size: 30px;padding-right: 8px;"></i>
                        Thêm Địa Chỉ Mới
                    </a>
                </button>
            </div>
            <div class="grid">
                <div id="content">
                    <table class="table table-striped table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>No</th>
                                <th>Tên Toà</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody id="show">
                            <c:forEach items="${buildingList}" var="b" varStatus="loop">
                                <tr style="background: #fff;">
                                    <td>
                                        ${loop.count}
                                    </td>
                                    <td>
                                        ${b.name}
                                    </td>
                                    <td>
                                        <a class="text-decoration-none" href="<c:url value="/home/updateBuilding.do?buildingId=${b.bId}"/>" >Edit</a>
                                    </td>
                                    <td style="padding: 5px 0 0 0;">
                                        <button class="trash-btn" value="${b.bId}" onclick="deleteBuilding(this)" >
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
                                            function showBuildingDetail(param) {
                                                var buildingId = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/home/BuildingDetail.do",
                                                    type: "get",
                                                    data: {
                                                        buildingId: buildingId
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
                                            function updateBuilding() {
                                                var buildingName = $('#bName').val();
                                                $.ajax({
                                                    url: "/comMeNau/admin/updateBuilding.do",
                                                    type: "get",
                                                    data: {
                                                        buildingName: buildingName
                                                    },
                                                    success: function (data) {
                                                        var row = document.getElementById("myModal");
                                                        row.innerHTML = data;
                                                    },
                                                    error: function () {
                                                    }
                                                });
                                            }
                                            function deleteBuilding(param) {
                                                var buildingId = param.value;
                                                $.ajax({
                                                    url: "/comMeNau/Admin/deleteBuilding.do",
                                                    type: "get",
                                                    data: {
                                                        buildingId: buildingId
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