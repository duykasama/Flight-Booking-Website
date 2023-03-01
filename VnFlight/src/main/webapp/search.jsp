<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <title>Home</title>
        <meta charset="utf-8">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <script type="text/javascript" src="js/jquery-1.4.2.js" ></script>
        <script type="text/javascript" src="js/Myriad_Pro_italic_600.font.js"></script>
        <script type="text/javascript" src="js/Myriad_Pro_italic_400.font.js"></script>
        <script type="text/javascript" src="js/Myriad_Pro_400.font.js"></script>
        <!--[if lt IE 9]>
        <script type="text/javascript" src="js/ie6_script_other.js"></script>
        <script type="text/javascript" src="js/html5.js"></script>
        <![endif]-->
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
                                    <li><a href="index.jsp">Home</a></li>
                                    <li id="menu_active"><a href="search.jsp">Search</a></li>
                                    <li><a href="#">Account</a></li>
                                    <li><a href="#">Booking History</a></li>
                                    <li><a href="login.jsp">Login</a></li>
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
                <a href="login_admin.jsp" class="button_top">LOGIN AS ADMIN</a></div>
        </div>
        <div class="main">
            <section id="content">
                <article class="col1">
                    <div class="pad_1">
                        <h2 class="h2_style">Search Flight</h2>
                        <form role="form">
                            <div class="form-group">
                                <label for="from">From</label>
                                <select class="form-control" id="from">
                                    <option></option>
                                    <option value="BOM">Mumbai - BOM</option>
                                    <option value="DEL">Delhi - DEL</option>
                                    <option value="BLR">Bangalore - BLR</option>
                                    <option value="PUN">Pune - PUN</option>
                                    <option value="KOL">Kolkatta - KOL</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="to">To</label>
                                <select class="form-control" id="to">
                                    <option></option>
                                    <option value="BOM">Mumbai - BOM</option>
                                    <option value="DEL">Delhi - DEL</option>
                                    <option value="BLR">Bangalore - BLR</option>
                                    <option value="PUN">Pune - PUN</option>
                                    <option value="KOL">Kolkatta - KOL</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="ondate">Date</label><input type="text" class="form-control" id="ondate" required>
                            </div>
                            <button type="button" id="search-flights" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </article>
                <div class="col2 pad_left1">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Number</th>
                                <th>Dep.</th>
                                <th>Arr.</th>
                                <th>Price</th>
                                <th>Dep. Time</th>
                            </tr>
                        </thead>
                        <tbody id="flights_info"></tbody>
                    </table>
                </div>
            </section>
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