<%-- 
    Document   : newFood
    Created on : Oct 13, 2022, 3:10:15 PM
    Author     : luffy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/views/food/newFood.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main">
            <h1 class="title">Chỉnh Sửa Món Ăn Của Bạn</h1>
            <form action="<c:url value="/food/updateFood.do"/>" class="form" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">
                <input type="hidden" name="foodId" value="${food.id}" />
                <div class="inputbox pt-45">
                    <label for="foodName" class="label">Tên Món Ăn:</label>
                    <input class="form-control" type="text" name="foodName" id="foodName" value="${food.name}">
                    <br/>
                </div>
                <div class="inputbox">
                    <label for="foodCategory" class="label">Danh Mục Món Ăn:</label>
                    <select name="foodCategory" class="option-list" id="foodCategory">
                        <c:forEach items="${categoryList}" var="c">--%>
                            <option value="${c.id}" ${categoryIdOfFood == c.id ? "selected":""}>${c.name}</option>
                        </c:forEach>
                    </select>
                    <br/>
                </div>    
                <div class="inputbox">
                    <label for="foodNutrition" class="label">Dinh Dưỡng Món Ăn:</label>
                    <input class="form-control" type="text" id="foodNutrition" name="foodNutrition" value="${food.nutrition}">
                    <br/>
                </div>
                <div class="inputbox">
                    <label for="foodPic" class="label">Ảnh Món Ăn:</label>
                    <input class="form-control" type="file" id="foodPic" name="foodPic">
                    <br/>
                </div>
                <div class="btn-box d-flex justify-content-end align-items-center">
                    <span class="message">${message}</span>
                    <div>
                    <a class="create-btn" href="<c:url value="/food/foodPage.do"/>">Quay Về</a>
                    <input type="submit" value="Lưu" class="create-btn">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
