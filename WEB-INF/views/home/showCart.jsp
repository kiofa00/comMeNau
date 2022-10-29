<%-- 
    Document   : showCart
    Created on : Jun 21, 2022, 8:50:40 PM
    Author     : Acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="${pageContext.request.contextPath}/css/views/home/showCart.css" rel="stylesheet" type="text/css"/>
        <title>Cơm Mẹ Nấu</title>
    </head>
    <body>
        <div class="container mt-3">
            <div class="row">
                <div class="col-md-6 p-2 pr-4">
                    <h2 class="title">
                        Thông tin giao hàng
                    </h2>
                    <form action="<c:url value="/home/checkout.do"/>" class="form" id="checkout">
                        <input type="hidden" name="topDay" value="${topDay}" />
                        <input type="hidden" name="topTimeline" value="${topTimeline}" />
                        <div class="inputbox pt-35">
                            <label class="label" for="name">Họ tên:</label>
                            <input id="name"
                                   class="form-control" type="text" value="${user.name}" placeholder="Họ tên" name="name"
                                   required=""/>
                        </div>
                        <input type="hidden" name="mealId" value="${meal.id}"/>
                        <div class="inputbox">
                            <label class="label">Số điện thoại:</label>
                            <input class="form-control" type="text" value="${user.phone}" placeholder="Số điện thoại"
                                   name="phone"
                                   required=""/>
                        </div>
                        <div class="inputbox">
                            <label class="label">Email:</label>
                            <input class="form-control" type="text" value="${user.email}" placeholder="Email"
                                   name="email"
                                   required=""/>
                        </div>
                        <div class="inputbox">
                            <label class="label">Địa chỉ:</label>
                            <select name="location" class="option-list">
                                <c:forEach items="${buildList}" var="b">
                                    <option value="${b.bId}" ${user.address == b.bId ? "selected":""}>${b.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="inputbox">
                            <label class="label">Thanh toán</label>
                            <select id="isTitles" name="payment" class="option-list">
                                <c:forEach items="${payList}" var="pay">
                                    <option value="${pay.id}">${pay.type}</option>
                                </c:forEach>
                            </select>

                        </div>           
                        <div class="inputbox">
                            <label class="label">Ghi chú:</label>
                            <input class="form-control" type="text" value="${note}" placeholder="Ghi chú..."
                                   name="note"/>
                        </div>
                    </form>
                </div>
                <div class=" col-md-6 p-2">
                    <div class="meal-title">
                        <h2 class="title">
                            Mâm Cơm Của Bạn
                        </h2>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>Tên Mâm Cơm</th>
                                <th>Đầu Bếp</th>
                                <th>Đơn Giá</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr style="background: #e8e8e8;">
                                <td>
                                    <p>${meal.name}</p>
                                </td>
                                <td>
                                    <p>${meal.chefId.name}</p>
                                </td>
                                <td>
                                    <p><fmt:formatNumber value="${meal.price}" pattern="#,### VND"/></p>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot style="height: 50px;">
                            <tr class="table-light">
                                <td class="fw-bold">Tổng tiền</td>
                                <td>
                                </td>
                                <td class="fw-bold">
                                    <span style="overflow: auto;
                                          white-space: nowrap;"><fmt:formatNumber value="${meal.price}" pattern="#,### VND"/></span>
                                </td>

                            </tr>
                        </tfoot>
                    </table>
                    <div class="submit">
                        <input id="checkout" type="submit" class="submit-btn" form="checkout" value="Xác nhận đơn hàng"/>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>