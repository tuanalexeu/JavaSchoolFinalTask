<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Drivers - Logiweb</title>
    <link rel="icon" href="<c:url value="/assets/img/icons/route.png"/>">
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
        <div class="container-fluid d-flex flex-column p-0"><a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="/homePage">
            <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-laugh-wink"></i></div>
            <div class="sidebar-brand-text mx-3"><span>Logiweb</span></div>
        </a>
            <hr class="sidebar-divider my-0">
            <ul class="navbar-nav text-light" id="accordionSidebar">
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/employee/orders"><i class="fas fa-table"></i><span>Orders</span></a></li>
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/employee/drivers"><i class="fas fa-table"></i><span>Drivers</span></a></li>
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/employee/lorries"><i class="fas fa-table"></i><span>Lorries</span></a></li>
            </ul>
            <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
        </div>
    </nav>
    <div class="d-flex flex-column" id="content-wrapper">
        <div id="content">
            <nav class="navbar navbar-light navbar-expand shadow mb-4 topbar static-top" style="background: #ffffff;">
                <ul class="navbar-nav ml-auto flex-nowrap">
                    <li class="nav-item" style="padding: 5px">
                        <form action="${pageContext.request.contextPath}/profile">
                            <button class="btn btn-primary" type="submit" style="background: #ffffff; color: #dc58b8; border-color: #dc58b8;">Profile</button>
                        </form>
                    </li>
                    <li class="nav-item" style="padding: 5px">
                        <form action="${pageContext.request.contextPath}/logout">
                            <button class="btn btn-primary" type="submit" style="background: #ffffff; color: #dc58b8; border-color: #dc58b8;">Log out</button>
                        </form>
                    </li>
                </ul>
            </nav>
            <div class="container-fluid">
                <h3 class="text-dark mb-4">Drivers</h3>
                <div class="card shadow">
                    <div class="card-header py-3">
                        <p class="text-primary m-0 font-weight-bold">Drivers list</p>
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
                                <button class="btn btn-primary btn-block btn-user" id="loginbutton" type="submit" style="background: rgb(255,255,255);color: rgb(220,88,184);border-color: rgb(220,88,184);" data-toggle="modal" data-target="#newDriver-modal">New driver</button>
                                <div class="modal fade" id="newDriver-modal" role="dialog" tabindex="-1" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title">New driver</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">x</span></button>
                                            </div>
                                            <form:form action="/employee/add-driver" method="post" modelAttribute="newDriver">
                                                <div class="modal-body">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <h3 style="margin: 10px;">First name</h3>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <form:input id="firstName" path="firstName" required="required" cssErrorClass="errorBox" cssStyle="background: #ffffff; color: #dc58b8; border-color: #dc58b8; border-radius: 5px; margin: 8px"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <h3 style="margin: 10px;">Last name</h3>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <form:input id="lastName" required="required" path="lastName" cssErrorClass="errorBox" cssStyle="background: #ffffff; color: #dc58b8; border-color: #dc58b8; border-radius: 5px; margin: 8px"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <h3 style="margin: 10px;">Hours worked</h3>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <form:input id="hoursWorked" type="number" required="required" min="0" max="176" path="hoursWorked" cssErrorClass="errorBox" cssStyle="background: #ffffff; color: #dc58b8; border-color: #dc58b8; border-radius: 5px; margin: 8px"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <form:hidden path="state" value="RESTING" cssErrorClass="errorBox"/>
<%--                                                    <div class="container">--%>
<%--                                                        <div class="row">--%>
<%--                                                            <div class="col-md-6">--%>
<%--                                                                <h3 style="margin: 10px;">Status</h3>--%>
<%--                                                            </div>--%>
<%--                                                            <div class="col-md-6">--%>
<%--                                                                <div class="dropdown">--%>
<%--                                                                    <form:select id="status" path="state" cssErrorClass="errorBox"  cssStyle="background: #ffffff; color: #dc58b8; border-color: #dc58b8; border-radius: 5px; margin: 8px">--%>
<%--                                                                        <form:option value="RESTING">Resting</form:option>--%>
<%--                                                                        <form:option value="DUTY">Duty</form:option>--%>
<%--                                                                        <form:option value="DRIVING">Driving</form:option>--%>
<%--                                                                        <form:option value="UNLOADING">Unloading</form:option>--%>
<%--                                                                    </form:select>--%>
<%--                                                                </div>--%>
<%--                                                            </div>--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <h3 style="margin: 10px;">City</h3>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="dropdown">
                                                                    <form:select  path="city.name"  cssStyle="background: #ffffff; color: #dc58b8; border-color: #dc58b8; border-radius: 5px; margin: 8px">
                                                                        <c:forEach items="${cities}" var="city">
                                                                            <form:option value="${city}">${city}</form:option>
                                                                        </c:forEach>
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button class="btn btn-light" type="button" data-dismiss="modal" style="color: rgb(255,103,173);background: rgb(255,255,255);border-color: rgb(255,103,173);">Close</button>
                                                    <button class="btn btn-primary" type="submit" style="background: rgb(255,255,255);border-color: rgb(255,103,173);color: rgb(255,103,173);">Save</button>
                                                </div>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                            <table class="table my-0" id="dataTable">
                                <thead>
                                <tr>
<%--                                    <th>Driver ID</th>--%>
                                    <th>Name</th>
                                    <th>Hours worked</th>
                                    <th>State</th>
                                    <th>City</th>
                                    <th>Lorry</th>
                                    <th>Order id</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${drivers}" var="user">
                                    <tr>
<%--                                        <td>${driver.id}</td>--%>
                                        <td>
                                            <form action="/employee/edit-driver/${user.id}">
                                                <button class="btn btn-primary btn-block btn-user" id="editDriver" type="submit" style="background: rgb(255,255,255);color: rgb(220,88,184);border-color: rgb(220,88,184);">${user.firstName} ${user.lastName}</button>
                                            </form>

                                        </td>
                                        <td>${user.hoursWorked}</td>
                                        <td>${user.state}</td>
                                        <td>${user.city.name}</td>
                                        <td>${user.lorry.regNum}</td>
                                        <td>${user.order.id}</td>
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
                                        <li class="page-item disabled"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true"><<</span></a></li>
                                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                                        <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">>></span></a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
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
<script src="<c:out value="/assets/js/jquery.min.js"/>"></script>
<script src="<c:out value="/assets/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:out value="/assets/js/theme.js"/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
<script src="<c:out value="/assets/js/chart.min.js"/>"></script>
<script src="<c:out value="/assets/js/bs-init.js"/>"></script>
</body>

</html>