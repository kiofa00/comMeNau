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
        <div class="main">
            <h1 class="title">Tạo Ra Mâm Cơm Của Riêng Bạn</h1>
            <form action="<c:url value="/food/createMeal.do"/>" class="form" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">
                <div class="inputbox pt-45">
                    <label for="mealName" class="label">Tên Mâm Cơm:</label>
                    <input class="form-control" type="text" name="mealName" id="mealName" value="${mealName}" required="">
                    <br/>
                </div>  
                <div class="inputbox">
                    <label for="mealPrice" class="label">Giá Mâm Cơm:</label>
                    <input class="form-control" type="number" name="mealPrice" id="mealPrice" value="${mealPrice}" required="">
                    <br/>
                </div>    
                <div class="inputbox">
                    <label for="mealDescribe" class="label">Mô Tả Mâm Cơm:</label>
                    <input class="form-control" type="text" name="mealDescribe" id="mealDescribe"value="${mealDescribe}" required="">
                    <br/>
                </div>
                <div class="inputbox">
                    <label for="chef" class="label">Đầu Bếp Mâm Cơm:</label>
                    <select name="chef" class="option-list">
                        <c:forEach items="${chefList}" var="c">
                            <option value="${c.id}" ${c.id == chefId ? "selected":""}>${c.name}</option>
                        </c:forEach>
                    </select>
                    <br/>
                </div>    
                <div class="inputbox d-flex">
                    <div class="label">Món Ăn Mâm Cơm:</div>
                    <div class="card-detail-list">
                        <c:forEach items="${foodList}" var="food">
                            <div class="d-flex position-relative mb-25">
                                <label for="${food.id}" >   
                                    <div class="card-detail-item">
                                        <div class="card-detail-box">
                                            <img src="<c:url value="/images/${food.images[0]}"/>" class="card-detail-img">
                                            <div class="card-detail-content" >
                                                <h4 class="food-name">${food.name}</h4>
                                                <p class="food-des">
                                                    ${food.nutrition}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </label>
                                <input type="checkbox" name="mealFood" id="${food.id}" value="${food.id}"
                                       <c:forEach items="${foodId}" var="f">
                                           ${f==food.id?"checked":""}
                                       </c:forEach>
                                       /> 
                            </div>
                        </c:forEach>
                    </div>    
                    <br/>
                </div>    
                <div class="inputbox">
                    <label for="mealPic" class="label">Ảnh Mâm Cơm:</label>
                    <input class="form-control" type="file" name="mealPic" value="${mealPic}" required="">
                    <br/>
                </div>
                <div class="btn-box d-flex justify-content-end align-items-center">
                    <span class="message">${message}</span>
                    <div>
                    <a class="create-btn" href="<c:url value="/food/mealPage.do"/>">Quay Về</a>
                    <input type="submit" value="Tạo" class="create-btn">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>