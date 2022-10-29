<%-- 
    Document   : newMeal
    Created on : Oct 14, 2022, 2:51:36 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/views/food/newSession.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main">
            <h1 class="title">Tạo Ra Session Của Bạn</h1>   
            <div class="divider-under"></div>
            <div class="d-flex">
                <h3 class="newest-title">
                    Session Mới Nhất Hiện Tại
                </h3>
                <table class="table table-striped table-hover">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Khung Giờ</th>
                            <th>Ngày Tháng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr style="background: #fff;">
                            <td>
                                ${lastSession.code}
                            </td>
                            <td>
                                ${lastSession.fromto}
                            </td>
                            <td>
                                ${lastSession.day}
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <form action="<c:url value="/food/createSession.do"/>" class="form" > 
                <div class="inputbox" style="margin-top: 25px;">
                    <label for="from" class="label"> Từ</label>
                    <input class="form-control" type="number" min="0" max="24" name="from" id="timeline" value="${from}" required="">
                    <br/>
                    <label for="to" class="label"> Đến</label>
                    <input class="form-control" type="number"  min="0" max="24" name="to" id="timeline" value="${to}"><br/ required="">
                </div>    
                <div class="inputbox">
                    <label for="day" class="label">Ngày:</label>
                    <input class="form-control" type="date" name="day" id="day"value="${day}" required="">
                    <br/>
                </div>    
                <div class="inputbox d-flex">
                    <div class="label">Mâm Cơm:</div>
                    <div class="card-detail-list">
                        <c:forEach items="${mealList}" var="meal">
                            <div class="d-flex position-relative mb-25">
                                <label for="${meal.id}" class="w-100">
                                    <div class="card-detail-item">
                                        <div class="card-detail-box">
                                            <img src="${pageContext.request.contextPath}/images/${meal.images[0]}" class="card-detail-img">
                                            <div class="card-detail-content">
                                                <h4 class="food-name">${meal.name}</h4>
                                                <div class="d-flex justify-content-around card-detail-info">
                                                    <p class="card-price">
                                                        <span class="card-price-pre">Giá: </span>
                                                        <fmt:formatNumber
                                                            value="${meal.price}"
                                                            pattern="#,### VND"/>
                                                    </p>       
                                                    <p class="card-chef">
                                                        <span class="card-chef-pre">Đầu Bếp: </span>
                                                        ${meal.chefId.name}
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </label>
                                <input type="checkbox" name="meal" value="${meal.id}" id="${meal.id}"
                                       <c:forEach items="${mealId}" var="m">
                                           ${m==meal.id?"checked":""}
                                       </c:forEach>
                                       /> 
                            </div>
                        </c:forEach>
                    </div>    
                    <br/>
                </div>    
                    <div class="btn-box d-flex justify-content-end align-items-center" style="padding-right: 55px">
                    <span class="message">${message}</span>
                    <div>
                    <a class="create-btn" href="<c:url value="/food/sessionPage.do"/>">Quay Về</a>
                    <input type="submit" value="Tạo" class="create-btn">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>