<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Blank Page - Logiweb</title>
    <link rel="stylesheet" href="<c:url value ="assets/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value ="assets/fonts/fontawesome-all.min.css"/>">
    <link rel="stylesheet" href="<c:url value ="assets/fonts/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value ="assets/fonts/fontawesome5-overrides.min.css"/>">
    <link rel="stylesheet" href="<c:url value ="assets/css/untitled-1-acc.css"/>">
    <link rel="stylesheet" href="<c:url value ="assets/css/untitled-2-acc.css"/>">
    <link rel="stylesheet" href="<c:url value ="assets/css/untitled-3-acc.css"/>">
    <link rel="stylesheet" href="<c:url value ="assets/css/untitled-acc.css"/>">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
</head>

<body id="page-top">
    <div id="wrapper">
        <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0" style="background: rgb(255,103,173);">
            <div class="container-fluid d-flex flex-column p-0"><a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                    <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-laugh-wink"></i></div>
                    <div class="sidebar-brand-text mx-3"><span>Logiweb</span></div>
                </a>
                <hr class="sidebar-divider my-0">
                <ul class="navbar-nav text-light" id="accordionSidebar">
                    <li class="nav-item"><a class="nav-link active" href="/driver/info"><i class="fas fa-window-maximize"></i><span>My info</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="/driver/order"><i class="fas fa-table"></i><span>My orders</span></a></li>
                </ul>
                <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
            </div>
        </nav>
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <nav class="navbar navbar-light navbar-expand shadow mb-4 topbar static-top" style="background: #ffffff;">
                    <ul class="navbar-nav ml-auto flex-nowrap">
                        <li class="nav-item" style="padding: 5px">
                            <form action="/profile">
                                <button class="btn btn-primary" type="submit" style="background: #ffffff; color: #dc58b8; border-color: #dc58b8;">Profile</button>
                            </form>
                        </li>
                        <li class="nav-item" style="padding: 5px">
                            <form action="/logout">
                                <button class="btn btn-primary" type="submit" style="background: #ffffff; color: #dc58b8; border-color: #dc58b8;">Log out</button>
                            </form>
                        </li>
                    </ul>
                </nav>
                <div class="container-fluid">
                    <h3 class="text-dark mb-1">My info</h3>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>${driver.firstName + " " + driver.lastName}</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>ID</td>
                                    <td>${driver.id}</td>
                                </tr>
                                <tr>
                                    <td>Lorry reg.number</td>
                                    <td>${driver.lorry.regNum}</td>
                                </tr>
                                <tr>
                                    <td>Order ID</td>
                                    <td>${driver.order.id}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <h3 class="text-dark">Co-drivers IDs:</h3>
                        </div>
                        <div class="col-md-6">
                            <h3 class="text-dark">Route points list:</h3>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <ul>
                                <c:forEach items="${coDrivers}" var="driver">
                                    <li>${driver.id + " " + driver.firstName + " " + driver.lastName}</li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="col-md-6">
                            <ul>
                                <c:forEach items="${driver.order.routePoints}" var="point">
                                    <li>${point.city}</li>
                                </c:forEach>
                            </ul>
                        </div>
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
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>