<%-- 
    Document   : checkOrder
    Created on : Oct 27, 2022, 2:58:00 PM
    Author     : PC
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/views/home/checkOrder.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/LIB.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main">
            <h1 class="title">Tra Cứu Đơn Hàng</h1>
            <div class="row">
                <div class="col l-7">
                    <form action="<c:url value="/home/CheckOrder.do"/>" class="form">
                        <div class="inputbox pt-45">
                            <label class="label" for="id">Nhập Mã Đơn Hàng:</label>
                            <input id="id"
                                   class="form-control" type="number" value="${orderId}"  name="id"/>
                            <br/>
                        </div>
                        <div class="inputbox pt-45">
                            <label class="label" for="email">Nhập Email:</label>
                            <input id="email"
                                   class="form-control" type="email" value="${email}"  name="email"/>
                            <br/>
                        </div>
                        <div class="btn-box">
                            <a class="create-btn" href="<c:url value="/home/homePage.do"/>">Quay Về</a>
                            <input type="submit" value="Tìm" class="create-btn">
                        </div>
                    </form>
                </div>
                <div class="col l-5">
                    <div class="form">
                        <h3 class="text-center">Thông Tin Đơn Hàng</h3>
                        <p class="message">${message}</p>
                        <c:if test="${not empty meal}">
                            <p class="order-line">
                                <span class="order-span-pre">Tên Khách Hàng: </span>
                                <span class="order-span-after">${order.cusName}</span>
                            </p>
                            <p class="order-line">
                                <span class="order-span-pre">Mâm cơm: </span>
                                <span class="order-span-after">${meal.name}</span>
                            </p>
                            <p class="order-line">
                                <span class="order-span-pre">Khung Giờ: </span>
                                <span class="order-span-after">${session.fromto}</span>
                            </p>
                            <p class="order-line">
                                <span class="order-span-pre">Ngày: </span>
                                <span class="order-span-after">${session.day}</span>
                            </p>
                            <p class="order-line">
                                <span class="order-span-pre">Địa Chỉ: </span>
                                <span class="order-span-after">${build.name}</span>
                            </p>
                            <p class="order-line">
                                <span class="order-span-pre">Giá Tiền: </span>
                                <span class="order-span-after">${order.price}</span>
                            </p>
                            <p class="order-line">
                                <span class="order-span-pre">Phương Thức: </span>
                                <span class="order-span-after">${payMethod.type}</span>
                            </p>
                            <p class="order-line">
                                <span class="order-span-pre"> Tình trạng đơn hàng </span>
                                <span class="order-span-after">${status}</span>
                            </p>

                        </c:if>

                        </div>

                    </div>
                </div>
            </div>
        </body>
    </html>