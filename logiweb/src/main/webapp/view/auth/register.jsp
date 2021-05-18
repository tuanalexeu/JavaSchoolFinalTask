<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Register - Logiweb</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome-all.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome5-overrides.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/untitled-1.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/untitled.css"/>">
</head>

<body class="bg-gradient-primary" style="background: #f8f9fc;border-color: rgb(255,103,173);">
    <div class="container" style="background: #f8f9fc; border-radius: 20px">
        <div class="card shadow-lg o-hidden border-0 my-5" style="border-radius: 20px">
            <div class="card-body p-0" style="border-radius: 20px"></div>
            <div class="p-5" style="border-color: rgb(255,255,255);margin: initial;background: rgb(255,103,173);border-radius: 20px;">
                <div class="text-center">
                    <h4 class="mb-4" style="color: rgb(255,255,255);">Create an Account!</h4>
                    <c:if test="${not empty message}">
                        <h5 class="mb-5" style="color: rgb(152,59,59);">${message}</h5>
                    </c:if>
                </div>
                <form:form action="/reg-process" method="post" modelAttribute="user">
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <form:input class="form-control form-control-user" required="required" type="text" id="exampleFirstName" placeholder="First Name"  path="firstName" name="first_name"/>
                        </div>
                        <div class="col-sm-6">
                            <form:input class="form-control form-control-user" required="required" type="text" id="exampleLastName" placeholder="Last Name" path="lastName" name="last_name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:input class="form-control form-control-user" required="required" type="email" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Email Address" path="email" name="email"/>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <form:input class="form-control form-control-user" required="required" type="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" id="examplePasswordInput" placeholder="Password" path="password" name="password"/>
                        </div>
                        <div class="col-sm-6">
                            <form:input class="form-control form-control-user" required="required" type="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" id="exampleRepeatPasswordInput" placeholder="Repeat Password" path="matchingPassword" name="password_repeat"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                            <form:select class="form-control form-control-user" placeholder="Role" path="role" name="role"  cssStyle="background: #ffffff; color: #dc58b8; border-color: #dc58b8; border-radius: 5px; margin: 8px">
                                <form:option value="ROLE_DRIVER">Driver</form:option>
                                <form:option value="ROLE_EMPLOYEE">Employee</form:option>
                            </form:select>
                        </div>
                    </div>
                    <button class="btn btn-primary btn-block text-white btn-user" id="register_button" type="submit" style="background: rgb(220,88,184);border-color: #ced8e6;">Register Account</button>
                    <hr>
                    <hr>
                </form:form>

                <div class="text-center"><a class="small" href="${pageContext.request.contextPath}/forgotPassword" style="border-color: rgb(255,255,255);color: rgb(255,255,255);">Forgot Password?</a></div>
                <div class="text-center"><a class="small" href="${pageContext.request.contextPath}/login" style="color: rgb(255,255,255);">Already have an account? Login!</a></div>
            </div>
        </div>
    </div>
    <script src="<c:out value="/assets/js/jquery.min.js"/>"></script>
    <script src="<c:out value="/assets/bootstrap/js/bootstrap.min.js"/>"></script>
    <script src="<c:out value="/assets/js/theme.js"/>"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
</body>

</html>