<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Login - Logiweb</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome-all.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome5-overrides.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/untitled-2.css"/>">
</head>

<body class="bg-gradient-primary" style="background: #f8f9fc;">
<div class="container">
    <div class="card shadow-lg o-hidden border-0 my-5" style="border-radius: 20px">
        <div class="p-5" style="background: rgb(255,103,173);max-width: 400px;margin: initial;border-radius: 20px;">
            <div class="text-center">
                <h3 class="mb-4" style="color: rgb(255,255,255);">Welcome Back!</h3>
                <c:if test="${errorMessage ne null}">
                    <h5 class="mb-5" style="color: #ea4335">${errorMessage}</h5>
                </c:if>
            </div>
            <form class="user" method="post" action="/login">
                <div class="form-group">
                    <input class="form-control form-control-user" type="text" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Enter Email Address..." name="username">
                </div>
                <div class="form-group">
                    <input class="form-control form-control-user" type="password" id="exampleInputPassword" placeholder="Password" name="password">
                </div>
                <button class="btn btn-primary btn-block text-white btn-user" type="submit" style="background: rgb(220,88,184);border-color: #ced8e6;">Login</button>
                <hr>
                <hr>
            </form>
            <div class="text-center"><a class="small" href="/forgotPassword" style="color: rgb(255,255,255);">Forgot Password?</a></div>
            <div class="text-center" style="color: rgb(255,255,255);"><a class="small" href="/register" style="color: rgb(255,255,255);">Create an Account!</a></div>
        </div>
    </div>
</div>
<script src="<c:out value="/assets/js/jquery.min.js"/>"></script>
<script src="<c:out value="/assets/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:out value="/assets/js/theme.js"/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
</body>

</html>