<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Client Service</title>
    <link rel="icon" href="<c:url value="/assets/img/icons/route.png"/>">
    <link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"/>">
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
            <nav class="navbar navbar-light navbar-expand shadow mb-4 topbar static-top" id="top-navbar" style="background: #e20074;">
                <div class="container">
                    <h1 style="color: rgb(255,255,255); margin-top: 2px">Client Service</h1>
                    <ul class="navbar-nav ml-auto flex-nowrap">
                        <li class="nav-item" style="padding: 5px">
                            <form action="${pageContext.request.contextPath}/oauth2/authorization/github">
                                <button class="btn btn-primary" type="submit" style="color: #ffffff; background: #e20074;border-color: #ffffff;">Log in with Github</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container" style="alignment: center; height: 800px">
                <h1 align="center">Check order status:</h1>

                <div class="container" align="center">
                    <form action="${pageContext.request.contextPath}/find-order">
                        <input required="required" type="text" name="orderToken" style="width: 400px;height: 40px;border-radius: 10px; border-color: #e20074" />
                        <button class="btn btn-primary" type="submit" style="margin-left: 10px; color: #e20074; background: #ffffff;border-color: #e20074;">Find</button>
                    </form>
                </div>

                <c:choose>
                    <c:when test="${order ne null}">
                        <div class="container-fluid" style="margin-top: 30px">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>Client ID</th>
                                        <th>${order.clientId}</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>City load</td>
                                        <td>${order.cityLoad}</td>
                                    </tr>
                                    <tr>
                                        <td>City unload</td>
                                        <td>${order.cityUnload}</td>
                                    </tr>
                                    <tr>
                                        <td>Name</td>
                                        <td>${order.name}</td>
                                    </tr>
                                    <tr>
                                        <td>Weight</td>
                                        <td>${order.weight}</td>
                                    </tr>
                                    <tr>
                                        <td>Status</td>
                                        <td>${order.status}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${not empty error}">
                                <div align="center">
                                    <h1 style="margin-top: 20px;">${error}</h1>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div align="center">
                                    <h1 style="margin-top: 20px;">No order selected</h1>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <footer class="bg-white sticky-footer" style="background: #043880;">
            <div class="container my-auto">
                <div class="text-center my-auto copyright"><span>Logiweb 2021</span></div>
            </div>
        </footer>
    </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
</div>
<script src="<c:out value="/assets/js/jquery.min.js"/>"></script>
<script src="<c:out value="/assets/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:out value="/assets/js/theme.js"/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/js-cookie/js.cookie.js"></script>
</body>

</html>