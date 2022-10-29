<%-- 
    Document   : newMeal
    Created on : Oct 14, 2022, 2:51:36 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/views/food/newMeal.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main" style="padding-top: 3rem">
            <h1 class="title">Thêm Địa Chỉ Mới</h1>
            <form action="<c:url value="/Admin/newBuilding.do"/>" class="form" style="margin-top: 3rem">
                <div class="inputbox pt-45">
                    <label for="buildingName" class="label">Nhập Tên Địa Chỉ Mới:</label>
                    <input class="form-control" type="text" name="buildingName" id="buildingName" value="${buildingName}" required="">
                    <br/>
                </div>  
                    <div class="btn-box d-flex justify-content-end align-items-center" style="padding-right: 62px">
                    <span class="message">${message}</span>
                    <div>
                    <a class="create-btn" href="<c:url value="/home/buildingPage.do"/>">Quay Về</a>
                    <input type="submit" value="Thêm" class="create-btn">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>