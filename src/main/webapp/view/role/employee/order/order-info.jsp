<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Table - Logiweb</title>
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
                <li class="nav-item"><a class="nav-link active" href="/employee/orders"><i class="fas fa-table"></i><span>Orders</span></a></li>
                <li class="nav-item"><a class="nav-link active" href="/employee/drivers"><i class="fas fa-table"></i><span>Drivers</span></a></li>
                <li class="nav-item"><a class="nav-link active" href="/employee/lorries"><i class="fas fa-table"></i><span>Lorries</span></a></li>
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
                <h3 class="text-dark mb-4">Edit order</h3>
                <div class="card shadow">
                    <div class="card-header py-3">
                        <p class="text-primary m-0 font-weight-bold">Loads</p>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6 text-nowrap">
                                <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable"><label>Show&nbsp;<select class="form-control form-control-sm custom-select custom-select-sm">
                                    <option value="10" selected="">10</option>
                                    <option value="25">25</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                </select>&nbsp;</label></div>
                            </div>
                            <div class="col-md-6">

                            </div>
                        </div>
                        <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                            <table class="table my-0" id="dataTable">
                                <thead>
                                <tr>
                                    <th>Load id</th>
                                    <th>Description</th>
                                    <th>City (Loading)</th>
                                    <th>City (Unloading)</th>
                                    <th>Weight</th>
                                    <th>Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${order.loads}" var="load">
                                    <tr>
                                        <td>${load.id}</td>
                                        <td>${load.name}</td>
                                        <td>${load.cityLoad.name}</td>
                                        <td>${load.cityUnload.name}</td>
                                        <td>${load.weight}</td>
                                        <td>${load.status}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="row">
                            <div class="col-md-6 align-self-center">
                                <p id="dataTable_info" class="dataTables_info" role="status" aria-live="polite">Showing 1 to 10 of 27</p>
                            </div>
                            <div class="col-md-6">
                                <nav class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
                                    <ul class="pagination">
                                        <li class="page-item disabled"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                                        <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>

                <form:form name="saveOrderForm" action="/employee/save-order" method="post" modelAttribute="order">
                    <div class="container" style="margin-top: 10px;margin-bottom: 10px;width: 600px;">
                        <div class="card shadow">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-6 text-nowrap">
                                        <p class="m-0 font-weight-bold" style="padding: 10px;color: rgb(90,92,105);">Choose available truck:</p>
                                    </div>
                                    <div class="col-md-6">
                                        <form:select path="lorry.regNum" cssClass="dropdown" cssStyle="background: #ffffff; color: #dc58b8; border-color: #dc58b8; border-radius: 5px; margin: 8px">
                                            <c:forEach items="${suitableLorries}" var="lorry">
                                                <form:option value="${lorry.regNum}">${lorry.regNum}</form:option>
                                                <input:hidden path="lorry.capacity" value="${lorry.capacity}"/>
                                                <input:hidden path="lorry.shiftTime" value="${lorry.shiftTime}"/>
                                                <input:hidden path="lorry.city.name" value="${lorry.city.name}"/>
                                                <input:hidden path="lorry.broken" value="${lorry.broken}"/>
                                                <input:hidden path="lorry.order.id" value="${lorry.order.id}"/>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-6 text-nowrap">
                                        <p class="m-0 font-weight-bold" style="padding: 10px;color: rgb(90,92,105);">Choose available drivers:</p>
                                    </div>
                                    <div class="col-md-6">
                                        <div></div>
                                        <div class="row">
                                            <div class="col" style="padding: 5px;">
                                                <p class="m-0 font-weight-bold" style="padding: 10px;color: rgb(90,92,105);">Driver 1:</p>
                                            </div>
                                            <div class="col">

                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col" style="padding: 5px;">
                                                <p class="m-0 font-weight-bold" style="padding: 10px;color: rgb(90,92,105);">Driver 2:</p>
                                            </div>
                                            <div class="col">
                                                <form:select  path="drivers[1].firstName" cssClass="dropdown" cssStyle="background: #ffffff; color: #dc58b8; border-color: #dc58b8; border-radius: 5px;">
                                                    <c:forEach items="${suitableDrivers}" var="driver">
                                                        <form:option value="${driver.firstName}">[${driver.id}] ${driver.firstName} ${driver.lastName}</form:option>
                                                        <%--                                                        <input:hidden path="drivers[1].lastName" value="${driver.lastName}"/>--%>
                                                        <%--                                                        <input:hidden path="drivers[1].id" value="${driver.id}"/>--%>
                                                        <%--                                                        <input:hidden path="drivers[1].hoursWorked" value="${driver.hoursWorked}"/>--%>
                                                        <%--                                                        <input:hidden path="drivers[1].state" value="${driver.state}"/>--%>
                                                        <%--                                                        <input:hidden path="drivers[1].city.name" value="${driver.city.name}"/>--%>
                                                        <%--                                                        <input:hidden path="drivers[1].lorry" value="${driver.lorry}"/>--%>
                                                        <%--                                                        <input:hidden path="drivers[1].order.id" value="${order.id}"/>--%>
                                                        <%--                                                        <input:hidden path="drivers[1].user.email" value="${driver.user.email}"/>--%>
                                                        <%--                                                        <input:hidden path="drivers[1].user.password" value="${driver.user.password}"/>--%>
                                                        <%--                                                        <input:hidden path="drivers[1].user.enabled" value="${driver.user.enabled}"/>--%>
                                                        <%--                                                        <input:hidden path="drivers[1].user.role" value="${driver.user.role}"/>--%>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form:hidden name="id" value="${order.id}" path="id"/>
                </form:form>
                <div class="container" style="text-align: center;">
                    <form action="/employee/orders">
                        <button class="btn btn-primary" type="submit" style="margin-bottom: 10px; background: rgb(255,255,255);color: rgb(220,88,184);border-color: rgb(220,88,184);">Back</button>
                    </form>
                </div>
                <div class="container" style="text-align: center;">
                    <form action="/employee/delete-order/${order.id}" method="get">
                        <button class="btn btn-primary" type="submit" style="margin-bottom: 10px; background: rgb(255,255,255);color: rgb(220,88,184);border-color: rgb(220,88,184);">Delete</button>
                    </form>
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