<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="<c:url value="/images/logo.jpg"/>"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/views/user/register.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Cơm Mẹ Nấu</title>
    </head>

    <body>
        <section class="register">
            <div class="register__box">
                <div class="register__top">
                    <a class="top__content" href="${pageContext.request.contextPath}/home/homePage.do">
                        <i class="fa-regular fa-circle-left top__icon"></i>
                        Return Home
                    </a>
                    <div class="register__title">Registration</div>
                </div>
                <form action="<c:url value="/user/checkRegister.do"/>">
                    <div class="user__detail">
                        <div class="input__box">
                            <input type="text" name="name" id="name" value="${name}" required="required">
                            <span class="input__content">Full Name</span>
                            <i class="small__content"></i>
                        </div>

                        <div class="input__box">
                            <input type="text" name="account" id="account" value="${account}" required="required">
                            <span class="input__content">Username</span>
                            <i class="small__content"></i>
                        </div>

                        <div class="input__box">
                            <input type="email" name="email" id="email" value="${email}" required="required"
                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
                            <span class="input__content">Email</span>
                            <i class="small__content"></i>
                        </div>

                        <div class="input__box">
                            <input type="phone" name="phone" id="phone" value="${phone}" required="required"
                                   pattern="^0\d{9}$">
                            <span class="input__content">Phone Number</span>
                            <i class="small__content"></i>
                        </div>

                        <div class="input__box">
                            <input type="password" name="password" value="${password}" required="required">
                            <span class="input__content">Password</span>
                            <i class="small__content"></i>
                        </div>

                        <div class="input__box">
                            <input type="password" name="checkPassword" id="pass" required="required">
                            <span class="input__content">Confirm Password</span>
                            <i class="small__content"></i>
                        </div>
                    </div>
                    <div class="address">
                        <label class="address__title" for="address__input">Address</label>
                        <div class="address__select">
                            <select class="address__options" id="address__options" name="location">
                                <c:forEach items="${buildList}" var="b">
                                    <option value="${b.bId}">${b.name}</option>
                                </c:forEach>
                            </select>
                            <span class="address__focus"></span>
                        </div>
                        <div class="message"> ${message}</div>
                    </div>
                    <div class="register__button">
                        <input class="register__input" type="submit" value="Register">
                    </div>
                    <div class="divider-under"></div>
                </form>
                <a class="login__button" href="<c:url value="/user/login.do"/>">
                    Login
                </a>
            </div>
        </section>

    </body>

</html>