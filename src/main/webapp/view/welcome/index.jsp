<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Dashboard - Logiweb</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome-all.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome5-overrides.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/untitled-1-pt.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/untitled-2-pt.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/untitled-3-pt.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/untitled-pt.css"/>">
</head>

<body id="page-top">
    <div id="wrapper">
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <nav class="navbar navbar-light navbar-expand shadow mb-4 topbar static-top" id="top-navbar" style="background: #FF67AD;">
                    <div class="container">
                        <ul class="navbar-nav ml-auto flex-nowrap">
                            <li class="nav-item" style="padding: 5px">
                                <form action="/login">
                                    <button class="btn btn-primary" type="submit" style="background: #dc58b8;border-color: #ced8e6;">Sign in</button>
                                </form>
                            </li>
                            <li class="nav-item" style="padding: 5px">
                                <form action="/register">
                                    <button class="btn btn-primary" type="submit" style="background: #dc58b8;border-color: #ced8e6;">Sign up</button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </nav>
                <div class="container" style="height: 800px;">
                    <div class="row">
                        <div class="col-md-6">
                            <h1>Logiweb website</h1>
                            <p>Welcome to our page.<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br>Bla-bla-bla<br></p>
                        </div>
                        <div class="col-md-6"><img src="<c:url value="/assets/img/dogs/image1.jpeg"/>" style="width: 300px;"></div>
                    </div>
                </div>
            </div>
            <footer class="bg-white sticky-footer" style="background: #043880;">
                <div class="container my-auto">
                    <div class="text-center my-auto copyright"><span>Copyright © Logiweb 2021</span></div>
                </div>
            </footer>
        </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
    </div>
    <script src="<c:out value="/assets/js/jquery.min.js"/>"></script>
    <script src="<c:out value="/assets/bootstrap/js/bootstrap.min.js"/>"></script>
    <script src="<c:out value="/assets/js/theme.js"/>"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
</body>

</html>