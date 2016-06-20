<%--
  Created by IntelliJ IDEA.
  User: Didoy
  Date: 6/9/2016
  Time: 7:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="resources/JS/jquery-2.2.2.js"></script>
    <link href="resources/plugins/bootstrap-3.3.6/dist/css/bootstrap.css" rel="stylesheet">
    <script src="resources/plugins/bootstrap-3.3.6/dist/js/bootstrap.js"></script>

    <link href="resources/CSS/indexStyle.css" rel="stylesheet">
    <script src="resources/JS/index.js"></script>
    <script src="resources/JS/orvilleJS.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
    <title>Login</title>
</head>


<body>


<div class="header">
    <div class="logo-container">
        <p class="logo-text"> Recordie</p>
    </div>

    <button type="button" id="login" class="button-simple">Login</button>
    <button type="button" id="sign-up" class="button-simple">Sign Up</button>
</div>

<div class="main-div">
    <div class="welcome-container">
        <p class="welcome-big"> Welcome to Recordie</p>
        <p class="welcome-small"> Recordie is a simple online grading management record website for every teachers in
            the world. </p>
        <span class="small-border"></span>

        <div class="list-container features-container">
            <p class="features-text list-text"> Record grades through online, <br>Track your records,<br> Identify
                failing students. </p>
            <span class="bar"></span>

        </div>

        <div class="features-container time-container">
            <p class="features-text list-text"> Record grades through online, <br>Track your records,<br> Identify
                failing students. </p>
            <span class="bar"></span>

        </div>

        <div class="features-container user-friendly-container">
            <p class="features-text list-text"> Record grades through online, <br>Track your records,<br> Identify
                failing students. </p>
            <span class="bar"></span>
        </div>

    </div>

    <div id="modal-signup">
        <div class="modal-content">
            <div class="modal-header">
                <p class="white-text"> Sign Up for Free</p>
            </div>

            <div class="modal-form">
                <form id="signup-form" method="post" action="Register">
                    <div class="group">
                        <input id="name-field" type="text" placeholder="Full Name" name="name" value="${requestScope.name}"
                               data-toggle="popover" data-trigger="focus" data-content=""  data-placement="left">
                        <span class="bar"></span>
                    </div>
                    <div class="group">
                        <input id="email-field" type="text" placeholder="Email" name="email" value="${requestScope.email}"
                               data-toggle="popover" data-trigger="focus" data-content="" data-placement="left">
                        <span class="bar"></span>
                    </div>
                    <div class="group">
                        <input id="password-field" type="password" placeholder="Password" name="password" value="${requestScope.password}"
                               data-toggle="popover" data-trigger="focus" data-content=""  data-placement="left">
                        <span class="bar"></span>
                    </div>
                    <div class="group">
                        <input id="cpassword-field" type="password" placeholder="Confirm  Password" name="confirmpassword" value="${requestScope.cpassword}"
                               data-toggle="popover" data-trigger="focus" data-content="" data-placement="left">
                        <span class="bar"></span>
                    </div>
                    <label>
                        <input type="radio" name="gender" value="Male" checked  > Male
                    </label>
                    <label>
                        <input type="radio" name="gender" value="Female" > Female
                    </label>
                    <input type="submit" value="Sign Up" class="btn btn-primary" id="signup-Btn">
                </form>

            </div>

            <div class="modal-footer"></div>

        </div>
    </div>
</div>

<div id="modal-login">
    <div class="modal-Login">
        <div class="login-header">
            <p class="white-text">Login</p>
        </div>
        <div class="modal-form">
            <form method="post" action="">
                <div class="group">
                    <input type="text" name="username" placeholder="Username">
                    <span class="bar"></span>
                </div>
                <div class="group">
                    <input type="password" name="Password" placeholder="Password">
                    <span class="bar"></span>
                </div>
                <input type="submit" name="login-login" value="Login" class="btn btn-primary">
            </form>
        </div>

    </div>
</div>

<div class="footer">
    <div class="social-sites">
        <p class="white-text-sm"> About the developer</p>
        <div id="fb-icon"></div>
        <div id="twitter-icon"></div>
    </div>
</div>


<script>

<c:set var="errorAttribute" value="${requestScope.error}"/>
<c:set var="clear" value="${sessionScope.clear}"/>
alert("clear is: " + ${clear})

<c:if test="${clear == true}">
$(window).load(function() {
            console.log("reset form");
            $("#signup-form").clearForm();
            $("#signup-form").find("input:radio").first().prop("checked", true); // select Male in gender radio Button
        });
</c:if>

<c:choose>

        <c:when test="${errorAttribute == true}">
            var signModal = $("#modal-signup");
            $(signModal).css("display", "block");

            <c:set var="name" value="${requestScope.name_empty}"/>
                <c:if test="${name == true}">
                $("#name-field").attr("data-content", "The name was empty").popover('show');
                closePopOverWhenFocus("#name-field");
                </c:if>

            <c:set var="email" value="${requestScope.email_empty}"/>
                <c:if test="${email == true}">
                $("#email-field").attr("data-content", "The Email was empty").popover('show');
                closePopOverWhenFocus("#email-field");
                </c:if>

            <c:set var="password" value="${requestScope.password_empty}"/>
                <c:if test="${password == true}">
                $("#password-field").attr("data-content", "The Password was empty").popover('show');
                closePopOverWhenFocus("#password-field");
                </c:if>

            <c:set var="cpassword" value="${requestScope.cpassword_empty}"/>
                <c:if test="${cpassword == true}">
                $("#cpassword-field").attr("data-content", "The Confirmat password was empty").popover('show');
                closePopOverWhenFocus("#cpassword-field");
                </c:if>

            <c:set var="invalidName" value="${requestScope.invalid_name}"/>
                <c:if test="${invalidName == true}">
                $("#name-field").attr("data-content", "The name is invalid").popover('show');
                closePopOverWhenFocus("#name-field");
                </c:if>

            <c:set var="invalidMail" value="${requestScope.invalid_email}"/>
                <c:if test="${invalidMail == true}">
                $("#email-field").attr("data-content", "The Email is invalid. valid email e.g Juan@yahoo.com").popover('show');
                closePopOverWhenFocus("#email-field");
                </c:if>

            <c:set var="invalidPassword" value="${requestScope.invalid_password}"/>
                <c:if test="${invalidPassword == true}">
                $("#password-field").attr("data-content", "The password is invalid").popover('show');
                closePopOverWhenFocus("#password-field");
                </c:if>

            <c:set var="passwordnotmatch" value="${requestScope.password_not_match}"/>
                <c:if test="${passwordnotmatch == true}">
                $("#password-field").attr("data-content", "The password Did not match from confirm password").popover('show');
                closePopOverWhenFocus("#password-field");
                </c:if>

            <c:set var="email_taken" value="${requestScope.email_taken}"/>
                <c:if test="${email_taken == true}">
                $("#email-field").attr("data-content", "The email is already taken").popover('show');
                closePopOverWhenFocus("#email-field");
                </c:if>
        </c:when>
</c:choose>

    </script>

</body>
</html>