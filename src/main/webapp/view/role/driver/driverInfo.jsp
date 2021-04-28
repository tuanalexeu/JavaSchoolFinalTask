<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Blank Page - Logiweb</title>
    <link rel="stylesheet" href="<c:url value ="/assets/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value ="/assets/fonts/fontawesome-all.min.css"/>">
    <link rel="stylesheet" href="<c:url value ="/assets/fonts/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value ="/assets/fonts/fontawesome5-overrides.min.css"/>">
    <link rel="stylesheet" href="<c:url value ="/assets/css/untitled-1-acc.css"/>">
    <link rel="stylesheet" href="<c:url value ="/assets/css/untitled-2-acc.css"/>">
    <link rel="stylesheet" href="<c:url value ="/assets/css/untitled-3-acc.css"/>">
    <link rel="stylesheet" href="<c:url value ="/assets/css/untitled-acc.css"/>">
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
                <%--                    <li class="nav-item"><a class="nav-link" href="/driver/order"><i class="fas fa-table"></i><span>My orders</span></a></li>--%>
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
                <h3 class="text-dark mb-1" style="padding: 5px;">My driver info</h3>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>${driver.firstName} ${driver.lastName}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>ID</td>
                            <td>${driver.id}</td>
                        </tr>
                        <tr>
                            <td>Lorry number</td>
                            <td>${driver.lorry.regNum}</td>
                        </tr>
                        <tr>
                            <td>My status</td>
                            <td>
                                <div class="dropdown" style="border-color: rgb(255,103,173);color: rgb(255,103,173);">
                                    <button class="btn btn-primary dropdown-toggle" aria-expanded="false" data-toggle="dropdown" type="button" style="color: rgb(255,103,173);background: rgb(255,255,255);border-color: rgb(255,103,173);">${driver.state} </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item">
                                            <form action="/driver/save" method="post">
                                                <button type="submit" value="DUTY" name="status">Duty</button>
                                            </form>
                                        </a>
                                        <a class="dropdown-item">
                                            <form action="/driver/save" method="post">
                                                <button type="submit" value="DRIVING" name="status">Driving</button>
                                            </form>
                                        </a>
                                        <a class="dropdown-item">
                                            <form action="/driver/save" method="post">
                                                <button type="submit" value="RESTING" name="status">Resting</button>
                                            </form>
                                        </a>
                                        <a class="dropdown-item">
                                            <form action="/driver/save" method="post">
                                                <button type="submit" value="UNLOADING" name="status">Unloading</button>
                                            </form>
                                        </a>
                                    </div>
                                </div>
                            </td>
                        <tr>
                            <td>ID</td>
                            <td>${driver.id}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Order</th>
                            <th>${driver.order.id}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Co-drivers ID list</td>
                            <td>
                                <a href="#codrivers-modal" data-toggle="modal" data-target="#codrivers-modal" style="color: #DC58B8">View</a>
                                <div class="modal fade" role="dialog" tabindex="-1" id="codrivers-modal">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title" style="color: rgb(133, 135, 150);">Drivers</h4>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">x</span></button>
                                            </div>
                                            <div class="modal-body" style="color: #858796;border-color: #dc58b8;">
                                                <ul>
                                                    <c:forEach items="${coDrivers}" var="driver">
                                                        <li>${driver.id} ${driver.firstName} ${driver.lastName}</li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                            <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal" style="border-color: #dc58b8;color: #dc58b8;">Ok</button></div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Route points</td>
                            <td>
                                <a href="#routepoints-modal" data-toggle="modal" data-target="#routepoints-modal" style="color: #DC58B8">View</a>
                                <div class="modal fade" role="dialog" tabindex="-1" id="routepoints-modal">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title" style="color: rgb(133, 135, 150);">Drivers</h4>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">x</span></button>
                                            </div>
                                            <div class="modal-body" style="color: #858796;border-color: #dc58b8;">
                                                <ul>
                                                    <c:forEach items="${driver.order.loads}" var="load">
                                                        <li>${load.city.name}</li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                            <div class="modal-footer">
                                                <button class="btn btn-light" type="button" data-dismiss="modal" style="border-color: #dc58b8;color: #dc58b8;">Ok</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Loads</td>
                            <td>
                                <a href="#loads-modal" data-toggle="modal" data-target="#loads-modal" style="color: #DC58B8">View</a>
                                <div class="modal fade" role="dialog" tabindex="-1" id="loads-modal">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title" style="color: rgb(133, 135, 150);">Drivers</h4>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">x</span></button>
                                            </div>
                                            <form:form action="/driver/save-loads" method="post" modelAttribute="driver">
                                                <div class="modal-body" style="color: #858796;border-color: #dc58b8;">
                                                    <div class="main-container">
                                                        <ul class="columns">
                                                            <li class="column to-do-column">
                                                                <div class="column-header">
                                                                    <h4>Prepared</h4>
                                                                </div>
                                                                <ul class="task-list" id="prepared">
                                                                    <c:forEach items="${driver.order.loads}" var="load" varStatus="loop">
                                                                        <c:if test="${load.status eq 'PREPARED'}">
                                                                            <li class="task">
                                                                                <p>${load.name}</p>
                                                                                <div class="dropdown" style="border-color: rgb(255,103,173);color: rgb(255,103,173);">
                                                                                    <form:select  path="order.loads[${loop.index}]">
                                                                                            <form:option value="DUTY">Duty</form:option>
                                                                                            <form:option value="RESTING">Resting</form:option>
                                                                                            <form:option value="DRIVING">Driving</form:option>
                                                                                            <form:option value="UNLOADING">Unloading</form:option>
                                                                                    </form:select>
                                                                                </div>
                                                                            </li>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </ul>
                                                            </li>

                                                            <li class="column doing-column">
                                                                <div class="column-header">
                                                                    <h4>Sent</h4>
                                                                </div>
                                                                <ul class="task-list" id="sent">
                                                                    <c:forEach items="${driver.order.loads}" var="load">
                                                                        <c:if test="${load.load.status eq 'SENT'}">
                                                                            <li class="task">
                                                                                <p>${load.load.name}</p>
                                                                                <div class="dropdown" style="border-color: rgb(255,103,173);color: rgb(255,103,173);"><button class="btn btn-primary dropdown-toggle" aria-expanded="false" data-toggle="dropdown" type="button" style="color: rgb(255,103,173);background: rgb(255,255,255);border-color: rgb(255,103,173);">${load.load.status}</button>
                                                                                    <div class="dropdown-menu">
                                                                                        <a class="dropdown-item" href="#">PREPARED</a>
                                                                                        <a class="dropdown-item" href="#">SENT</a>
                                                                                        <a class="dropdown-item" href="#">DELIVERED</a>
                                                                                    </div>
                                                                                </div>
                                                                            </li>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </ul>
                                                            </li>
                                                            <li class="column doing-column">
                                                                <div class="column-header">
                                                                    <h4>Delivered</h4>
                                                                </div>
                                                                <ul class="task-list" id="delivered">
                                                                    <c:forEach items="${driver.order.loads}" var="load">
                                                                        <c:if test="${load.load.status eq 'DELIVERED'}">
                                                                            <li class="task">
                                                                                <p>${load.load.name}</p>
                                                                                <div class="dropdown" style="border-color: rgb(255,103,173);color: rgb(255,103,173);"><button class="btn btn-primary dropdown-toggle" aria-expanded="false" data-toggle="dropdown" type="button" style="color: rgb(255,103,173);background: rgb(255,255,255);border-color: rgb(255,103,173);">${load.load.status}</button>
                                                                                    <div class="dropdown-menu">
                                                                                        <a class="dropdown-item" href="#">PREPARED</a>
                                                                                        <a class="dropdown-item" href="#">SENT</a>
                                                                                        <a class="dropdown-item" href="#">DELIVERED</a>
                                                                                    </div>
                                                                                </div>
                                                                            </li>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button class="btn btn-light" type="submit" style="border-color: #dc58b8;color: #dc58b8;">Save</button>
                                                </div>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
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
<%--<script src="<c:out value="/assets/js/dragAndDrop.js"/>"></script>--%>
</body>

</html>