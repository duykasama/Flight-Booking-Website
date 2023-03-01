<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <title>Home</title>
        <meta charset="utf-8">
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
                                    <li id="menu_active"><a href="index.jsp">Home</a></li>
                                    <li><a href="aircrafts.jsp">Account</a></li>
                                    <li><a href="safety.jsp">Booking History</a></li>
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
                        <form id="form_1" action="#" method="post">
                            <!--          <div class="wrapper pad_bot1">
                                        <div class="radio marg_right1">
                                          <input type="radio" name="name1">
                                          Round Trip<br>
                                          <input type="radio" name="name1">
                                          One Way </div>
                                        <div class="radio">
                                          <input type="radio" name="name1">
                                          Empty-Leg<br>
                                          <input type="radio" name="name1">
                                          Multi-Leg </div>
                                      </div>-->
                            <div class="wrapper"> From:
                                <div class="bg">
                                    <input type="text" class="input input1" value="Enter City or Airport Code" onBlur="if (this.value == '')
                          this.value = 'Enter City or Airport Code'" onFocus="if (this.value == 'Enter City or Airport Code')
                                      this.value = ''">
                                </div>
                            </div>
                            <div class="wrapper"> To:
                                <div class="bg">
                                    <input type="text" class="input input1" value="Enter City or Airport Code" onBlur="if (this.value == '')
                          this.value = 'Enter City or Airport Code'" onFocus="if (this.value == 'Enter City or Airport Code')
                                      this.value = ''">
                                </div>
                            </div>
                            <div class="wrapper"> Departure Date:
                                <div class="wrapper">
                                    <div class="bg left">
                                        <input type="text" class="input input2" value="mm/dd/yyyy " onBlur="if (this.value == '')
                            this.value = 'mm/dd/yyyy '" onFocus="if (this.value == 'mm/dd/yyyy ')
                                        this.value = ''">
                                    </div>
                                    <!--              <div class="bg right">
                                                    <input type="text" class="input input2" value="12:00am" onBlur="if(this.value=='') this.value='12:00am'" onFocus="if(this.value =='12:00am' ) this.value=''">
                                                  </div>-->
                                </div>
                            </div>
                            <!--          <div class="wrapper"> Return Date and Time:
                                        <div class="wrapper">
                                          <div class="bg left">
                                            <input type="text" class="input input2" value="mm/dd/yyyy " onBlur="if(this.value=='') this.value='mm/dd/yyyy '" onFocus="if(this.value =='mm/dd/yyyy ' ) this.value=''">
                                          </div>
                                          <div class="bg right">
                                            <input type="text" class="input input2" value="12:00am" onBlur="if(this.value=='') this.value='12:00am'" onFocus="if(this.value =='12:00am' ) this.value=''">
                                          </div>
                                        </div>
                                      </div>-->
                            <div class="wrapper">
                                <!--            <p>Passenger(s):</p>
                                            <div class="bg left">
                                              <input type="text" class="input input2" value="# passengers" onBlur="if(this.value=='') this.value='# passengers'" onFocus="if(this.value =='# passengers' ) this.value=''">
                                            </div>-->
                                <a href="#" class="button2 searchButton">Search</a> </div>
                        </form>
                        <!--        <h2 class="h2_style">Recent News</h2>
                                <p class="under"><a href="#" class="link1">Nemo enim ipsam voluptatem quia</a><br>
                                  November 5, 2010</p>
                                <p class="under"><a href="#" class="link1">Voluptas aspernatur autoditaut fjugit</a><br>
                                  November 1, 2010</p>
                                <p><a href="#" class="link1">Sed quia consequuntur magni</a><br>
                                  October 23, 2010</p>-->
                    </div>
                </article>
                <article class="col2 pad_left1">
                    <h2 class="h2_style">Welcome to our Website!</h2>
                    <p class="color1">Aliquefauctor ac nulla ant vestibulum ris sed tincidunt pede ut faucibus. Necpellus vel orci pede justo eros elit eget tellus ero nunc. Hendnibh pretium nec lorem tor tempus vel nibh urna quis morbi.</p>
                    <p class="color1">Maecenatoquetamet tempus arcu adipis duis partur convallisi sed eget et inte. Fauctormassa ligula por nam velit id eros et curabitudin dolor id.</p>
                    <div class="marker">
                        <div class="wrapper">
                            <p class="pad_bot2"><strong>Aircraft</strong></p>
                            <p class="pad_bot2">Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione.</p>
                        </div>
                    </div>
                    <div class="wrapper pad_bot2"><a href="#" class="button1">Read More</a></div>
                    <div class="marker">
                        <div class="wrapper">
                            <p class="pad_bot2"><strong>Charters</strong></p>
                            <p class="pad_bot2">Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.</p>
                        </div>
                    </div>
                    <div class="wrapper pad_bot2"> <a href="#" class="button1">Reservation</a> <a href="#" class="button2">Fleet</a> </div>
                    <div class="wrapper">
                        <article class="cols">
                            <h2 class="h2_style">Our Clients</h2>
                            <p><strong>Avero eoset</strong> accusamus et iusto odio dig- nissimos ducimus qui blanditiis praesentium voluptatum deleniti.</p>
                            <p>Atque corrupti quos dolores et quas moles- tias excepturi sint <a href="#">occaecati cupiditate</a> non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.</p>
                        </article>
                        <div class="box1">
                            <div class="pad_1">
                                <div class="wrapper">
                                    <p class="pad_bot2">Lorem ipsum dolor sit amet, consectetur adip- isicing elit, sed do eius- mod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis </p>
                                    <p><span class="right">Mr. Thomas Lloyd</span>&nbsp;<br>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </article>
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