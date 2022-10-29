<%-- 
    Document   : thankyou
    Created on : Jul 20, 2022, 3:45:02 PM
    Author     : luffy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/css/views/home/thankyou.css"/>" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="body">
            <div class="ty-top">
                <i class="fa-regular fa-heart ty-icon"></i>
                <h1 class="ty-title">Cảm ơn vì đã lựa chọn Cơm Mẹ Nấu!</h1>
                <h4 class="ty-content">Cơm Mẹ Nấu luôn luôn cung cấp chất lượng trong từng món ăn! Bạn sẽ cảm nhận được hương vị quê nhà khi thưởng thức tại Cơm Mẹ Nấu!</h4>
            </div>
            <div class="ty-body">
                <c:if test="${empty sessionScope.user }"><h4 class="ty-email">Thông tin đơn hàng đã được gửi qua email! <a class="text-decoration-none" target="_blank" href="https://mail.google.com/">Kiểm tra Ngay!</a></h4>
                </c:if>
                <p class="ty-back">Tiếp tục chọn món? Quay về lại trang chủ!<a class="ty-btn" href="<c:url value="/home/homePage.do"/>">Home</a></p> 
            </div>
        </div>
    </body> <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script></html>
