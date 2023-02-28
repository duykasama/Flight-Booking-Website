<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <title>AirLines | Contacts</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <script type="text/javascript" src="js/jquery-1.4.2.js" ></script>
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/cufon-replace.js"></script>
        <script type="text/javascript" src="js/Myriad_Pro_italic_600.font.js"></script>
        <script type="text/javascript" src="js/Myriad_Pro_italic_400.font.js"></script>
        <script type="text/javascript" src="js/Myriad_Pro_400.font.js"></script>
        <!--[if lt IE 9]>
        <script type="text/javascript" src="js/ie6_script_other.js"></script>
        <script type="text/javascript" src="js/html5.js"></script>
        <![endif]-->
    </head>
    <body id="page5">
        <!-- START PAGE SOURCE -->
        <div class="body1">
            <div class="main">
                <header>
                    <div class="wrapper">
                        <h1><a href="index.jsp" id="logo">VnFlight</a><span id="slogan">Domestic Flight Tickets</span></h1>
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
                                    <li><a href="aircrafts.jsp">Account</a></li>
                                    <li><a href="safety.jsp">Booking History</a></li>
                                    <li id="menu_active"><a href="login.jsp">Login</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </header>
            </div>
        </div>
        <div class="main">
            <div id="banner">
                <div class="text1"> COMFORT<span>Guaranteed</span>
                </div>
            </div>
        </div>
        <div class="main">
            <section id="content">
                <article class="col1">
                    <div class="pad_1">
                        <h2>Contact Us</h2>
                        <span class="cols"> Country:<br>
                            City:<br>
                            Telephone:<br>
                            Email: </span> USA<br>
                        San Diego<br>
                        +354 5635600<br>
                        <a href="#">businessco@mail.com</a>
                        <h2>Miscellaneous Info</h2>
                        <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia.</p>
                    </div>
                </article>

                <!--Login Part-->
                <article class="col2 pad_left1">
                    <!--<h1>Please login</h1>-->
                    <c:url var="loginLink" value="${request.contextPath}/Access/login"/>


                    <form action="${loginLink}" name="" method="POST">
                        <input name="username" type="text">
                        <input name="password" type="password">
                        <input value="Login" type="submit">
                    </form>
                </article>
                <!--End Login Part-->       
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
        <script type="text/javascript"> Cufon.now();</script>
        <!-- END PAGE SOURCE -->
    </body>
</html>