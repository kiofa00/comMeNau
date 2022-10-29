<%-- 
    Document   : newFood
    Created on : Oct 13, 2022, 3:10:15 PM
    Author     : luffy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/views/food/editSession.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main">
            <h1 class="title">Chỉnh Sửa Session Của Bạn</h1>
            <form action="<c:url value="/food/updateSession.do"/>" class="form">
                <div class="inputbox pt-45">
                    <label for="code" class="label">ID:</label>
                    <input class="form-control" type="text" name="" value="${session.code}" disabled="">
                    <input class="form-control" type="hidden" name="codeSession" value="${session.code}" ></div>
                <div class="inputbox">
                    <div class="inputbox">
                    <label for="from" class="label"> Từ</label>
                    <input class="form-control" type="number" min="0" max="24" name="from" id="timeline" value="${from}" required="">
                    <br/>
                    <label for="to" class="label"> Đến</label>
                    <input class="form-control" type="number"  min="0" max="24" name="to" id="timeline" value="${to}" required="">
                    <br/>
                </div>  
                </div>    
                <div class="inputbox">
                    <label for="date" class="label">Date:</label>
                    <input class="form-control" type="date" name="date" id="date" value="${session.day}"/>
                    <br/>
                </div>   
                <div class="inputbox d-flex">
                    <div class="label">Mâm Cơm:</div>
                    <div class="card-detail-list">
                        <c:forEach items="${mealList}" var="meal">
                            <div class="d-flex position-relative mb-25">
                                <label for="${meal.id}" class="w-100">
                                    <div class="card-detail-item row m-0">
                                        <div class="col l-4 p-0 h-100">
                                            <img src="${pageContext.request.contextPath}/images/${meal.images[0]}" class="card-img-big">  
                                        </div>
                                        <div class="col l-8 p-0 h-100">
                                            <h5 class="card-title">${meal.name}</h5>
                                            <div class="card-body">
                                                <span class="card-price">
                                                    <span class="card-price-pre">Từ</span>
                                                    <fmt:formatNumber
                                                        value="${meal.price}"
                                                        pattern="#,### VND"/>
                                                </span>       
                                                <span class="card-chef">
                                                    <span class="card-chef-pre">Đầu Bếp: </span>
                                                    ${meal.chefId.name}
                                                </span>
                                            </div>        
                                        </div>
                                    </div>
                                </label>
                                <input type="checkbox" name="meal" value="${meal.id}" id="${meal.id}"
                                       <c:forEach items="${mealInSession}" var="mealSess">
                                           <c:if test="${meal.id==mealSess.id}">checked</c:if>
                                       </c:forEach>
                                >    
                            </div>
                        </c:forEach>
                    </div>    
                    <br/>
                </div>    
                <div class="btn-box d-flex justify-content-end align-items-center">
                    <span class="message">${message}</span>
                    <div>
                    <a class="create-btn" href="<c:url value="/food/sessionPage.do"/>">Quay Về</a>
                    <input type="submit" value="Lưu" class="create-btn">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
