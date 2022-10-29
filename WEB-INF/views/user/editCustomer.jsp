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
        <link href="${pageContext.request.contextPath}/css/views/food/newMeal.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main">
            <h1 class="title">Chỉnh Sửa Thông Tin Khách Hàng</h1>
             <form action="<c:url value="/Admin/updateCustomer.do"/>" class="form" ">
                <input type="hidden" name="userId" value="${customer.id}" />
                <div class="inputbox pt-45">
                    <label for="customerName" class="label">Tên:</label>
                    <input class="form-control" type="text" name="customerName" id="customerName" value="${customer.name}">
                    <br/>
                </div>  
                <div class="inputbox">
                    <label for="customerPhone" class="label">Số Điện Thoại:</label>
                    <input class="form-control" type="number" name="customerPhone" id="customerPhone" value="${customer.phone}">
                    <br/>
                </div>    
                <div class="inputbox">
                    <label class="label">Địa Chỉ:</label>
                    <select name="address" class="option-list">
                        <c:forEach items="${buildingList}" var="b">
                            <option value="${b.bId}" ${b.bId==customer.address ? "selected":""} >${b.name}</option>
                        </c:forEach>
                    </select>
                    <br/>
                </div>
                <div class="inputbox">
                    <label for="customerEmail" class="label">Email:</label>
                    <input class="form-control" type="email" name="customerEmail" id="customerEmail" value="${customer.email}">
                    <br/>
                </div>
                    <div class="btn-box d-flex justify-content-end align-items-center" style="padding-right: 62px">
                    <span class="message">${message}</span>
                    <div>
                    <a class="create-btn" href="<c:url value="/user/customer.do"/>">Quay Về</a>
                    <input type="submit" value="Lưu" class="create-btn">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
