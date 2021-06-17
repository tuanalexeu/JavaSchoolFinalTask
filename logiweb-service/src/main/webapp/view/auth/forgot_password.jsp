<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="background: #f8f9fc;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Forgotten Password - Logiweb</title>

    <link rel="icon" href="<c:url value="/assets/img/icons/route.png"/>">

    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome-all.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome5-overrides.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/untitled-1.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/untitled-2.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/untitled.css"/>">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
</head>

<body class="bg-gradient-primary" style="background: #f8f9fc; min-width: max-content">
<div class="container" id="logincontainer">
    <div class="card shadow-lg o-hidden border-0 my-5" style="border-radius: 20px">
        <div class="p-5" style="background: rgb(226,0,116);border-radius: 20px;">
            <div class="text-center">
                <h4 class="text-white mb-2" style="color: rgb(248,249,252);"><strong>Forgot Your Password?</strong></h4>
                <p class="text-white mb-4" style="color: rgb(248,249,252);">Please, enter your email below and we will send you a letter</p>
            </div>
            <form class="user">
                <div class="form-group">
                    <input class="form-control form-control-user" type="email" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Enter Email Address..." name="email">
                </div>
                <button class="btn btn-primary btn-block text-white btn-user" id="loginbutton" type="submit" style="background: rgb(226,0,116);border-color: #f8f9fc;">Reset Password</button>
            </form>
            <div class="text-center">
                <hr><a class="small" href="${pageContext.request.contextPath}/register" style="color: rgb(248,249,252);">Create an Account!</a>
            </div>
            <div class="text-center">
                <a class="small" href="${pageContext.request.contextPath}/login" style="color: rgb(248,249,252);">Already have an account?</a>
            </div>
        </div>
    </div>
</div>
<script src="<c:out value="/assets/js/jquery.min.js"/>"></script>
<script src="<c:out value="/assets/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:out value="/assets/js/theme.js"/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
<script src="<c:out value="/assets/js/chart.min.js"/>"></script>
<script src="<c:out value="/assets/js/bs-init.js"/>"></script>
</body>

</html>