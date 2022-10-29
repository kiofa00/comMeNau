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
        <link href="${pageContext.request.contextPath}/css/views/food/editCategory.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main">
            <h1 class="title">Chỉnh Sửa Danh Mục</h1>
            <form action="<c:url value="/food/updateCategory.do"/>" class="form" >
                <h2 class="text-center"id="cName">${category.name}</h2>
                <input type="hidden" name="id" value="${category.id}" />
                        <div class="inputbox pt-45">
                            <label class="label" for="name">Nhập Tên Danh Mục Mới:</label>
                            <input id="name"
                                   class="form-control" type="text" value="${name}"  name="name"/>
                            <br/>
                        </div>
                            <div class="inputbox pt-45">
                            <label class="label" for="type">Mức Độ Ưu Tiên Mới:</label>
                            <input id="type"
                                   class="form-control" type="number" value="${type}"  name="type"/>
                            <br/>
                        </div>
                <div class="btn-box d-flex justify-content-end align-items-center">
                    <span class="message">${message}</span>
                    <div>
                    <a class="create-btn" href="<c:url value="/food/categoryPage.do"/>">Quay Về</a>
                    <input type="submit" value="Lưu" class="create-btn">
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
