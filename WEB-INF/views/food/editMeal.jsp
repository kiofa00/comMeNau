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
        <link href="${pageContext.request.contextPath}/css/views/food/newMeal.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main">
            <h1 class="title">Chỉnh Sửa Mâm Cơm Của Bạn</h1>
            <form action="<c:url value="/food/updateMeal.do"/>" class="form" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">
                <div class="inputbox pt-45">
                    <input type="hidden" name="mealId" value="${meal.id}" />
                    <label for="mealName" class="label">Tên Mâm Cơm:</label>
                    <input class="form-control" type="text" name="mealName" id="mealName" value="${meal.name}">
                    <br/>
                </div>  
                <div class="inputbox">
                    <label for="mealPrice" class="label">Giá Mâm Cơm:</label>
                    <input class="form-control" type="number" name="mealPrice" id="mealPrice" value="${meal.price}">
                    <br/>
                </div>    
                <div class="inputbox">
                    <label for="quantity" class="label">Số Lượng đã bán:</label>
                    <input class="form-control" type="number" name="quantity" id="quantity"value="${quantity}" disabled="">
                    <br/>
                </div>
                <div class="inputbox">
                    <label for="mealDescribe" class="label">Mô Tả Mâm Cơm:</label>
                    <input class="form-control" type="text" name="mealDescribe" id="mealDescribe"value="${meal.detail}">
                    <br/>
                </div>
                <div class="inputbox">
                    <label class="label">Đầu Bếp</label>
                    <select name="chef" class="option-list">
                        <c:forEach items="${chefList}" var="c">
                            <option value="${c.id}" ${c.id==meal.chefId.id ? "selected":""}>${c.name}</option>
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
                                       <c:forEach items="${meal.foods}" var="f">
                                           ${f.id==food.id?"checked":""}
                                       </c:forEach>
                                       />  
                            </div>
                        </c:forEach>
                    </div>    
                    <br/>
                </div>    
                <div class="inputbox">
                    <label for="mealPic" class="label">Ảnh Mâm Cơm:</label>
                    <input class="form-control" type="file" name="mealPic" value="${mealPic}">
                    <br/>
                </div>
                <div class="btn-box">
                    <a class="create-btn" href="<c:url value="/food/mealPage.do"/>">Quay Về</a>
                    <input type="submit" value="Lưu" class="create-btn">
                </div>
            </form>
        </div>
    </body>
</html>
