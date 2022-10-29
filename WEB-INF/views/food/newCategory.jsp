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
            <h1 class="title">Thêm Danh Mục Mới</h1>
            <form action="<c:url value="/food/createCategory.do"/>" class="form">
                <div class="inputbox pt-45">
                    <label for="categoryName" class="label">Tên:</label>
                    <input class="form-control" type="text" name="categoryName" id="categoryName" value="${categoryName}" required=""/>
                    <br/>
                </div>  
                <div class="inputbox pt-45">
                    <label for="categoryType" class="label">Độ Ưu Tiên:</label>
                    <input class="form-control" type="number" name="categoryType" id="categoryType" value="${categoryType}" required=""/>
                    <br/>
                </div>      
                <div class="btn-box d-flex justify-content-end align-items-center" style="padding-right: 62px">
                    <span class="message">${message}</span>
                    <div>
                    <a class="create-btn" href="<c:url value="/food/categoryPage.do"/>">Quay Về</a>
                    <input type="submit" value="Thêm" class="create-btn">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>