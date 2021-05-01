<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <div class="container-fluid d-flex flex-column p-0"><a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="/homePage">
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
                <h3 class="text-dark mb-4">Edit driver</h3>
                <form:form action="/employee/edit-driver" method="post" modelAttribute="editDriver" id="editDriverForm">
                    <form:hidden path="id" value="${editDriver.id}"/>
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <h3 style="margin: 10px;">First name</h3>
                                </div>
                                <div class="col-md-6">
                                    <form:input id="firstName" path="firstName" cssErrorClass="errorBox"/>
                                </div>
                            </div>
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <h3 style="margin: 10px;">Last name</h3>
                                </div>
                                <div class="col-md-6">
                                    <form:input id="lastName" path="lastName" cssErrorClass="errorBox"/>
                                </div>
                            </div>
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <h3 style="margin: 10px;">Hours worked</h3>
                                </div>
                                <div class="col-md-6">
                                    <form:input id="hoursWorked" path="hoursWorked" cssErrorClass="errorBox"/>
                                </div>
                            </div>
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <h3 style="margin: 10px;">Status</h3>
                                </div>
                                <div class="col-md-6">
                                    <div class="dropdown">
                                        <form:select id="status" path="state" cssErrorClass="errorBox">
                                            <form:option value="DUTY">Duty</form:option>
                                            <form:option value="DRIVING">Driving</form:option>
                                            <form:option value="RESTING">Resting</form:option>
                                            <form:option value="UNLOADING">Unloading</form:option>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <h3 style="margin: 10px;">City</h3>
                                </div>
                                <div class="col-md-6">
                                    <div class="dropdown">
                                        <form:select  path="city.name">
                                            <c:forEach items="${cities}" var="city">
                                                <form:option value="${city}">${city}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <c:if test="${not empty editDriver.lorry}">
                            <form:hidden id="lorry" path="lorry.regNum" value="${editDriver.lorry.regNum}" cssErrorClass="errorBox"/>
                        </c:if>

                        <c:if test="${not empty editDriver.order}">
                            <form:hidden id="order" path="order.id" value="${editDriver.order.id}" cssErrorClass="errorBox"/>
                        </c:if>

                        <form:hidden id="email" path="user.email" value="${editDriver.user.email}" cssErrorClass="errorBox"/>
                        <form:hidden id="password" path="user.password" value="${editDriver.user.password}" cssErrorClass="errorBox"/>
                    </div>
                </form:form>
                <div class="modal-footer">
                    <form action="/employee/drivers">
                        <button class="btn btn-light" href="/employee/drivers" style="color: rgb(255,103,173);background: rgb(255,255,255);border-color: rgb(255,103,173);">Back</button>
                    </form>
                    <form action="/employee/delete-driver/${editDriver.id}">
                        <button class="btn btn-light" type="submit" style="color: rgb(255,103,173);background: rgb(255,255,255);border-color: rgb(255,103,173);">Delete</button>
                    </form>
                    <button form="editDriverForm" class="btn btn-primary" type="submit" style="background: rgb(255,255,255);border-color: rgb(255,103,173);color: rgb(255,103,173);">Save</button>
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