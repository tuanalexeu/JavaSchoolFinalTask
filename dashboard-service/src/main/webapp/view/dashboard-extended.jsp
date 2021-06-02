<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Logiweb</title>
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
                    <h1 style="color: rgb(255,255,255); margin-top: 2px">Logiweb</h1>
                    <ul class="navbar-nav ml-auto flex-nowrap">
                        <li class="nav-item" style="padding: 5px">
                            <div class="container authenticated" style="display:none; color: #ffffff">
                                <span id="user" style="margin-top: 8px"></span>
                            </div>
                        </li>
                        <li class="nav-item" style="padding: 5px">
                            <form action="/logout">
                                <button type="submit" class="btn btn-primary" style="color: #ffffff; background: #e20074;border-color: #ffffff;">Logout</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container" style="height: 800px;">
                <div class="row">
                    <div class="col-md-6">
                        <h1>Check order status:</h1>
                        <input type="text" style="width: 400px;height: 40px;border-radius: 10px;" />
                        <button class="btn btn-primary" type="button" style="margin-left: 10px; color: #e20074; background: #ffffff;border-color: #e20074;">Find</button>
                        <div>
                            <h1 style="margin-top: 20px;">No order selected</h1>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h1>My orders</h1>
                        <ul>
                            <li>Item 1</li>
                            <li>Item 2</li>
                            <li>Item 3</li>
                            <li>Item 4</li>
                        </ul>
                    </div>
                </div>
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
<script type="text/javascript" src="/webjars/js-cookie/js.cookie.js"></script>
<script type="text/javascript">

    $.ajaxSetup({
        beforeSend : function(xhr, settings) {
            if (settings.type == 'POST' || settings.type == 'PUT'
                || settings.type == 'DELETE') {
                if (!(/^http:.*/.test(settings.url) || /^https:.*/
                    .test(settings.url))) {
                    // Only send the token to relative URLs i.e. locally.
                    xhr.setRequestHeader("X-XSRF-TOKEN", Cookies
                        .get('XSRF-TOKEN'));
                }
            }
        }
    });

    $.get("/user", function(data) {
        $("#user").html(data.name);
        $(".authenticated").show();
    });

    $.get("/error", function(data) {
        if (data) {
            $(".error").html(data);
        } else {
            $(".error").html('');
        }
    });
</script>
</body>

</html>