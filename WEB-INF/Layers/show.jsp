<%-- 
    Document   : show
    Created on : Jun 18, 2022, 8:18:11 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/Layers/show.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" href="<c:url value="/images/logo1.jpg"/>"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" type="text/css"/>
        <jsp:include page="header.jsp"></jsp:include>
            <title>Cơm Mẹ Nấu</title>
        </head>

        <body>
                    <div class="header">
                        <a class="logo-link" href="${pageContext.request.contextPath}/home/homePage.do">
                        <img class="logo" src="${pageContext.request.contextPath}/images/logo-vertical.jpg"/>
                    </a>
                </div>

        <div class="body">
            <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp"/>
        </div>

        <div class="footer">
            <img class="footer-img" src="${pageContext.request.contextPath}/images/logo-vertical.jpg" alt=""/>
            <span class="footer-contact"> Company Profile | Follow Us 
                <i class="fa-brands fa-facebook"></i>
                <i class="fa-brands fa-instagram"></i>
                <i class="fa-brands fa-youtube"></i></span>
            <span class="footer-info">Copyrights &COPY; 2022 By Com Me Nau. All Rights Reserved.</span>
        </div>

        <jsp:include page="footer_script.jsp"/>
    </body>

</html>