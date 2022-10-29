<%-- 
    Document   : index
    Created on : Sep 22, 2022, 11:39:58 PM
    Author     : MSI GAMING
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cơm Mẹ Nấu</title>
        <link rel="icon" type="image/png" href="<c:url value="/images/logo-1.jpg"/>"/>
        <link href="${pageContext.request.contextPath}/css/views/user/info.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>      
        <div class="box">
            <h1 class="box-header">Hồ sơ của tôi</h1>
            <div class="box-content">
               <form action="<c:url value="/user/editUser.do"/>" method="POST" class="form">
                   <input type="hidden" name="userId" value="${user.id}" />
                    <div class="inputbox">
                    <label for="name" class="label">Họ Tên:</label>
                    <input class="form-control" type="text" name="name" id="name" value="${user.name}">
                    <br/>
                </div>
                <div class="inputbox">
                    <label for="email" class="label">Email:</label>
                    <input class="form-control" id="email" type="email" name="email" value="${user.email}" disabled="">
                    <br/>
                </div>
                <div class="inputbox">
                    <label for="phone" class="label">Số Điện Thoại:</label>
                    <input class="form-control" id="phone" type="number" name="phone" value="${user.phone}">
                    <br/>
                </div>
                    <div class="inputbox">
                    <label for="address" class="label">Địa Chỉ:</label>
                    <select name="address" class="option-list">
                        <c:forEach items="${buildingList}" var="b">
                            <option value="${b.bId}" ${b.bId==location.bId ? "selected":""} >${b.name}</option>
                        </c:forEach>
                    </select>
                    <br/>
                </div>
                <div class="btn-box">
                    <input type="submit" value="Lưu" class="save-btn">
                </div>
                </form>
            </div>
        </div>
    </body>
</html>