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
    </head>
    <body>
        <div class="main" id="showFood">
            <div class="food-title">
                <h2 class="food-title-content">
                    DANH SÁCH KHÁCH HÀNG
                </h2>
                <div class="food-title-bottom"></div>
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
                                <th></th>
                            </tr>
                        </thead>
                        <tbody id="show">
                            <c:forEach items="${customerList}" var="c" varStatus="loop">
                                <tr style="background: #fff;">
                                    <td>
                                        ${loop.count}
                                    </td>
                                    <td>
                                        ${c.name}
                                    </td>
                                    <td>
                                        ${c.phone}
                                    </td>
                                    <td>
                                        ${buildingList[c.id].name}
                                    </td>
                                    <td>
                                        ${c.email}
                                    </td>
                                    <td>
                                        <a href="<c:url value="/user/editCustomer.do?customerId=${c.id}"/>" class="text-decoration-none">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </body>
</html>