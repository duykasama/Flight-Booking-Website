<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <title>Admin Home</title>
        <meta charset="utf-8">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <script type="text/javascript" src="js/jquery-1.4.2.js" ></script>
        <script type="text/javascript" src="js/Myriad_Pro_italic_600.font.js"></script>
        <script type="text/javascript" src="js/Myriad_Pro_italic_400.font.js"></script>
        <script type="text/javascript" src="js/Myriad_Pro_400.font.js"></script>
    </head>
    <body id="page1">
        <!-- START PAGE SOURCE -->
        <div class="body1">
            <div class="main">
                <header>
                    <div class="wrapper">
                        <h1 class="header-logo"><a href="index.jsp" id="logo">VnFlight</a><span id="slogan">Domestic Flight Tickets</span></h1>
                        <div class="right">
                            <nav>
                                <ul id="top_nav">
                                    <li><a href="#"><img src="images/img1.gif" alt=""></a></li>
                                    <li><a href="#"><img src="images/img2.gif" alt=""></a></li>
                                    <li class="bg_none"><a href="#"><img src="images/img3.gif" alt=""></a></li>
                                </ul>
                            </nav>
                            <nav>
                                <ul id="menu">
                                    <li id="menu_active"><a href="admin_home.jsp">Admin Home</a></li>
                                    <li><a href="admin_flight.jsp">Flight</a></li>
                                    <li><a href="admin_user.jsp">User</a></li>
                                    <li><a href="admin_invoice.jsp">Invoice</a></li>
                                    <li><a href="#">Logout</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </header>
            </div>
        </div>
        <div class="main">
            <div id="banner">
                <div class="text1">COMFORT<span>Guaranteed</span>
                    <p>A website for booking domestic flight tickets!</p>
                </div>
            </div>
        </div>
        <div class="body2">
            <div class="main">
                <footer>
                    <div class="footerlink">
                        <p class="lf">Copyright &copy; 2010 <a href="#">Domain Name</a> - All Rights Reserved</p>
                        <p class="rf">Design by <a href="http://www.templatemonster.com/">TemplateMonster</a></p>
                        <div style="clear:both;"></div>
                    </div>
                </footer>
            </div>
        </div>
        <!-- END PAGE SOURCE -->
    </body>
</html>