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
            <h1 class="title">Chỉnh Sửa Thông Tin Đầu Bếp</h1> 
            <form action="<c:url value="/Admin/updateChef.do"/>" class="form" >
                <input type="hidden" name="chefId" value="${chef.id}" />
                <div class="inputbox pt-45">
                    <label for="chefName" class="label">Tên:</label>
                    <input class="form-control" type="text" name="chefName" id="chefName" value="${chef.name}">
                    <br/>
                </div>  
                <div class="inputbox">
                    <label for="chefPhone" class="label">Số Điện Thoại:</label>
                    <input class="form-control" type="number" name="chefPhone" id="chefPhone" value="${chef.phone}">
                    <br/>
                </div>    
                <div class="inputbox">
                    <label class="label">Địa Chỉ:</label>
                    <select name="address" class="option-list">
                        <c:forEach items="${buildingList}" var="b">
                            <option value="${b.bId}" ${b.bId==chef.address ? "selected":""} >${b.name}</option>
                        </c:forEach>
                    </select>
                    <br/>
                </div>
                <div class="inputbox">
                    <label for="chefEmail" class="label">Email:</label>
                    <input class="form-control" type="email" name="chefEmail" id="chefEmail" value="${chef.email}">
                    <br/>
                </div>        
                <div class="inputbox">
                    <label for="chefSalary" class="label">Lương:</label>
                    <input class="form-control" type="number" name="chefSalary" value="${chef.salary}">
                    <br/>
                </div>
                <div class="btn-box d-flex justify-content-end align-items-center" style="padding-right: 59px">
                    <span class="message">${message}</span>
                    <div>
                    <a class="create-btn" href="<c:url value="/user/chef.do"/>">Quay Về</a>
                    <input type="submit" value="Lưu" class="create-btn">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
