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
                                <button class="btn btn-primary btn-block btn-user" id="loginbutton" type="submit" style="background: rgb(255,255,255);color: rgb(220,88,184);border-color: rgb(220,88,184);" data-toggle="modal" data-target="#newPoint-modal">New route point</button>
                                <div class="modal fade" id="newPoint-modal" role="dialog" tabindex="-1" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title">New point</h4>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">x</span></button>
                                            </div>
                                            <form:form action="/employee/add-load" method="post" modelAttribute="newLoad">
                                                <div class="modal-body">
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <h3 style="margin: 10px;">City (Loading)</h3>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="dropdown">
                                                                    <form:select  path="cityLoad.name">
                                                                        <c:forEach items="${cityNames}" var="name">
                                                                            <form:option value="${name}">${name}</form:option>
                                                                        </c:forEach>
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <h3 style="margin: 10px;">City (Unloading)</h3>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="dropdown">
                                                                    <form:select  path="cityUnload.name">
                                                                        <c:forEach items="${cityNames}" var="name">
                                                                            <form:option value="${name}">${name}</form:option>
                                                                        </c:forEach>
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <h3 style="margin: 10px;">Load description</h3>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <form:input id="loadDescription" path="name" cssErrorClass="errorBox"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <h3 style="margin: 10px;">Load weight</h3>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <form:input id="weight" path="weight" cssErrorClass="errorBox"/>
                                                                <form:hidden id="status" path="status" value="PREPARED" cssErrorClass="errorBox"/>
                                                                <form:hidden path="order.id" value="${order.id}" cssErrorClass="errorBox"/>
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
                                    <th>Load id</th>
                                    <th>City (Loading)</th>
                                    <th>City (Unloading)</th>
                                    <th>Load info</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${order.loads}" var="load">
                                    <tr>
                                        <td>
                                            <form action="/employee/edit-load/${load.id}">
                                                <button class="btn btn-primary btn-block btn-user" id="editPoint" type="submit" style="background: rgb(255,255,255);color: rgb(220,88,184);border-color: rgb(220,88,184);">${load.id}</button>
                                            </form>
                                        </td>
                                        <td>${load.cityLoad.name}</td>
                                        <td>${load.cityUnload.name}</td>
                                        <td>
                                            <a href="#load-modal" data-toggle="modal" data-target="#load-modal" style="color: #DC58B8">Info</a>
                                            <div class="modal fade" role="dialog" tabindex="-1" id="load-modal">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" style="color: rgb(133, 135, 150);">Load info</h4>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">x</span></button>
                                                        </div>
                                                        <div class="modal-body" style="color: #858796;border-color: #dc58b8;">
                                                            <ul>
                                                                <li>
                                                                    <form action="/employee/edit-routePoint/${load.id}">
                                                                        <button class="btn btn-primary btn-block btn-user" id="editDriver" type="submit" style="background: rgb(255,255,255);color: rgb(220,88,184);border-color: rgb(220,88,184);">${load.city.name}</button>
                                                                    </form>
                                                                </li>
                                                            </ul>
                                                            <ul>
                                                                <li>${load.name}</li>
                                                                <li>${load.weight}</li>
                                                                <li>${load.status}</li>
                                                            </ul>
                                                        </div>
                                                        <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal" style="border-color: #dc58b8;color: #dc58b8;">OK</button></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
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

                <form:form name="saveOrderForm" action="/employee/checkPoints" method="post" modelAttribute="order">
                    <div class="container" style="margin-top: 10px;margin-bottom: 10px;width: 600px;">
                        <div class="card shadow">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-6 text-nowrap">
                                        <p class="m-0 font-weight-bold" style="padding: 10px;color: rgb(90,92,105);">Choose available truck:</p>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="dropdown">
                                            <button class="btn btn-primary dropdown-toggle" aria-expanded="false" data-toggle="dropdown" type="button" style="padding: 10px;margin: 10px;"></button>
                                            <div class="dropdown-menu">
                                                <form:select  path="lorry.regNum">
                                                    <c:forEach items="${suitableLorries}" var="lorry">
                                                        <form:option value="${lorry.regNum}">${lorry.regNum}</form:option>
                                                        <input:hidden path="lorry.capacity" value="${lorry.capacity}"/>
                                                        <input:hidden path="lorry.shiftTime" value="${lorry.shiftTime}"/>
                                                        <input:hidden path="lorry.city.name" value="${lorry.city.name}"/>
                                                        <input:hidden path="lorry.broken" value="${lorry.broken}"/>
                                                        <input:hidden path="lorry.order" value="${lorry.order}"/>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                        </div>
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
                                            <div class="col" style="padding: 10px;">
                                                <p class="m-0 font-weight-bold" style="padding: 10px;color: rgb(90,92,105);">Driver 1:</p>
                                            </div>
                                            <div class="col">
                                                <div class="dropdown" style="padding: 10px;"><button class="btn btn-primary dropdown-toggle" aria-expanded="false" data-toggle="dropdown" type="button" style="padding: 10px;">Dropdown </button>
                                                    <div class="dropdown-menu">
                                                        <a class="dropdown-item" href="#">First Item</a>
                                                        <a class="dropdown-item" href="#">Second Item</a>
                                                        <a class="dropdown-item" href="#">Third Item</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col" style="padding: 10px;">
                                                <p class="m-0 font-weight-bold" style="padding: 10px;color: rgb(90,92,105);">Driver 2:</p>
                                            </div>
                                            <div class="col">
                                                <div class="dropdown" style="padding: 10px;"><button class="btn btn-primary dropdown-toggle" aria-expanded="false" data-toggle="dropdown" type="button" style="padding: 10px;">Dropdown </button>
                                                    <div class="dropdown-menu">
                                                        <a class="dropdown-item" href="#">First Item</a>
                                                        <a class="dropdown-item" href="#">Second Item</a>
                                                        <a class="dropdown-item" href="#">Third Item</a>
                                                    </div>
                                                </div>
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
                    <button form="saveOrderForm" class="btn btn-primary" type="button" style="margin-bottom: 10px; background: rgb(255,255,255);color: rgb(220,88,184);border-color: rgb(220,88,184);">Save</button>
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