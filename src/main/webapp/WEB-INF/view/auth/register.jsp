<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Register - Logiweb</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<c:url value="assets/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="assets/fonts/fontawesome-all.min.css"/>">
    <link rel="stylesheet" href="<c:url value="assets/fonts/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="assets/fonts/fontawesome5-overrides.min.css"/>">
    <link rel="stylesheet" href="<c:url value="assets/css/untitled-1.css"/>">
    <link rel="stylesheet" href="<c:url value="assets/css/untitled.css"/>">
</head>

<body class="bg-gradient-primary" style="background: #f8f9fc;border-color: rgb(255,103,173);">
    <div class="container" style="background: #f8f9fc; border-radius: 20px">
        <div class="card shadow-lg o-hidden border-0 my-5" style="border-radius: 20px">
            <div class="card-body p-0" style="border-radius: 20px"></div>
            <div class="p-5" style="border-color: rgb(255,255,255);margin: initial;background: rgb(255,103,173);border-radius: 20px;">
                <div class="text-center">
                    <h4 class="mb-4" style="color: rgb(255,255,255);">Create an Account!</h4>
                </div>
                <form class="user">
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0"><input class="form-control form-control-user" type="text" id="exampleFirstName" placeholder="First Name" name="first_name"></div>
                        <div class="col-sm-6"><input class="form-control form-control-user" type="text" id="exampleFirstName" placeholder="Last Name" name="last_name"></div>
                    </div>
                    <div class="form-group"><input class="form-control form-control-user" type="email" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Email Address" name="email"></div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0"><input class="form-control form-control-user" type="password" id="examplePasswordInput" placeholder="Password" name="password"></div>
                        <div class="col-sm-6"><input class="form-control form-control-user" type="password" id="exampleRepeatPasswordInput" placeholder="Repeat Password" name="password_repeat"></div>
                    </div><button class="btn btn-primary btn-block text-white btn-user" id="register_button" type="submit" style="background: rgb(220,88,184);border-color: #ced8e6;">Register Account</button>
                    <hr>
                    <hr>
                </form>
                <div class="text-center"><a class="small" href="forgot_password.jsp" style="border-color: rgb(255,255,255);color: rgb(255,255,255);">Forgot Password?</a></div>
                <div class="text-center"><a class="small" href="login.jsp" style="color: rgb(255,255,255);">Already have an account? Login!</a></div>
            </div>
        </div>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>