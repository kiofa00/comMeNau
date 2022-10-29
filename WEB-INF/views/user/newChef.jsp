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
            <h1 class="title">Thêm Đầu Bếp Mới</h1>
            <form action="<c:url value="/Admin/newChef.do"/>" class="form" >   
                <div class="inputbox pt-45">
                    <label for="chefName" class="label">Tên:</label>
                    <input class="form-control" type="text" name="chefName" id="chefName" value="${chefName}" required="">
                    <br/>
                </div>  
                <div class="inputbox">
                    <label for="chefPhone" class="label">Số Điện Thoại:</label>
                    <input class="form-control" type="number" name="chefPhone" id="chefPhone" value="${chefPhone}" required="">
                    <br/>
                </div>    
                <div class="inputbox">
                    <label class="label">Địa Chỉ:</label>
                    <select name="address" class="option-list">
                        <c:forEach items="${buildingList}" var="b">
                            <option value="${b.bId}" ${b.bId==location.bId ? "selected":""} >${b.name}</option>
                        </c:forEach>
                    </select>
                    <br/>
                </div>
                <div class="inputbox">
                    <label for="chefEmail" class="label">Email:</label>
                    <input class="form-control" type="email" name="chefEmail" id="chefEmail" value="${chefEmail}" required="">
                    <br/>
                </div>        
                <div class="inputbox">
                    <label for="chefSalary" class="label">Lương:</label>
                    <input class="form-control" type="number" name="chefSalary" value="${chefSalary}" required="">
                    <br/>
                </div>
                    <div class="btn-box d-flex justify-content-end align-items-center" style="padding-right: 58px">
                    <span class="message">${message}</span>
                    <div>
                    <a class="create-btn" href="<c:url value="/user/chef.do"/>">Quay Về</a>
                    <input type="submit" value="Thêm" class="create-btn">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>