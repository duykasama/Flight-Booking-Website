<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
        Traveler by freehtml5.co
        Twitter: http://twitter.com/fh5co
        URL: http://freehtml5.co
-->
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Ticket Info</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
        <meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
        <meta name="author" content="FreeHTML5.co" />

        <!-- Facebook and Twitter integration -->
        <meta property="og:title" content=""/>
        <meta property="og:image" content=""/>
        <meta property="og:url" content=""/>
        <meta property="og:site_name" content=""/>
        <meta property="og:description" content=""/>
        <meta name="twitter:title" content="" />
        <meta name="twitter:image" content="" />
        <meta name="twitter:url" content="" />
        <meta name="twitter:card" content="" />

        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700" rel="stylesheet">

        <!-- Animate.css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
        <!-- Icomoon Icon Fonts-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/icomoon.css">
        <!-- Themify Icons-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/themify-icons.css">
        <!-- Bootstrap  -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

        <!-- Magnific Popup -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magnific-popup.css">

        <!-- Magnific Popup -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker.min.css">

        <!-- Owl Carousel  -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.default.min.css">

        <!-- Theme style  -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquerry-ui.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/place.css">

        <!-- Modernizr JS -->
        <script src="${pageContext.request.contextPath}/js/modernizr-2.6.2.min.js"></script>
        <!-- FOR IE9 below -->
        <!--[if lt IE 9]>
        <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>

        <div class="gtco-loader"></div>

        <div id="page">

            <%@include file="/user_header.jsp" %>

            <header id="gtco-header" class="gtco-cover-special gtco-cover-md" role="banner" style="background-image: url(${pageContext.request.contextPath}/images/img_bg_2.jpg)">
                <div class="overlay"></div>
                <div class="gtco-container">
                    <div class="row">
                        <div class="col-md-12 col-md-offset-0 text-left">


                            <div class="row row-mt-10em">

                                <div class="col-md-10 col-md-push-1 animate-box" data-animate-effect="fadeInRight" style="margin-bottom: 50px">
                                    <div class="form-wrap">
                                        <div class="tab">
                                            <div class="tab-content">
                                                <div class="col-md-12">
                                                    <div class="col-md-10"></div>
                                                    <div class="col-md-2" style="color: red">${ticket_msg}</div>
                                                </div>
                                                <div class="tab-content-inner active" data-content="signup">
                                                    <h3>Ticket Passenger Information</h3>
                                                    <c:url var="saveLink" value="${request.contextPath}/UserFlightController/save"/>
                                                    <form action="${saveLink}" name="" method="GET">
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <label for="firstname">First Name</label>
                                                                <input class="form-control" id="firstname" name="firstname" type="text" required>
                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <label for="lastname">Last Name</label>
                                                                <input class="form-control" id="lastname" name="lastname" type="text" required>
                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <label for="dob">Date of Birth</label>
                                                                <input class="form-control" id="dob" name="dob" type="date" required>
                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <label for="gender" required>Gender</label>
                                                                <ul style="list-style:none">
                                                                    <li>
                                                                        <input type="radio" id="a-option" name="gender" value="Male">
                                                                        <label for="a-option">Male</label>
                                                                    </li>
                                                                    <li>
                                                                        <input type="radio" id="b-option" name="gender" value="Female">
                                                                        <label for="b-option">Female</label>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <label for="nationality">Nationality</label>
                                                                <input class="form-control" id="nationality" name="nationality" type="text" required>
                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <label for="card_id">Card ID</label>
                                                                <input class="form-control" id="card_id" name="cardId" required>
                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <label for="luggage">Luggage</label>
                                                                <ul style="list-style:none">
                                                                    <li>
                                                                        <input type="checkbox" id="a-option" name="luggageWeight" value="15">
                                                                        <label for="a-option">15kg (200,000 VND)</label>
                                                                    </li>
                                                                    <li>
                                                                        <input type="checkbox" id="b-option" name="luggageWeight" value="25">
                                                                        <label for="b-option">25kg (300,000 VND)</label>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <script>
                                                            const checkboxes = document.querySelectorAll('input[type="checkbox"]');

                                                            checkboxes.forEach((checkbox) => {
                                                                checkbox.addEventListener('click', () => {
                                                                    checkboxes.forEach((otherCheckbox) => {
                                                                        if (otherCheckbox !== checkbox) {
                                                                            otherCheckbox.checked = false;
                                                                        }
                                                                    });
                                                                });
                                                            });
                                                        </script>
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <label for="seat">Select Seat</label>
                                                                <%@include file="/plane_60_seats.jsp" %>
                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <div class="col-md-10"></div>
                                                                <div class="col-md-2">
                                                                    <button type="submit" class="btn btn-primary btn-block" value="save">SAVE</button>

                                                                </div>
                                                            </div>
                                                        </div>                                                        
                                                    </form>	
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-11">
                                        <div class="col-md-9"></div>
                                        <div class="col-md-3"><button type="submit" class="btn btn-primary btn-block" value="add">ADD TICKET</button></div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-11">
                                        <div class="col-md-6"></div>
                                        <div class="col-md-3"><button type="submit" class="btn btn-primary btn-block" value="cart">ADD TO CART</button></div>
                                        <div class="col-md-3"><button type="submit" class="btn btn-primary btn-block" value="purchase">PURCHASE</button></div>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </header>
        </div>

        <footer id="gtco-footer" role="contentinfo">
            <div class="gtco-container">
                <div class="row row-p	b-md">

                    <div class="col-md-3">
                        <div class="gtco-widget">
                            <h3>About Us</h3>
                            <p>VnFlight is a website that provides the service for booking domestic flight tickets.</p>
                        </div>
                    </div>

                    <div class="col-md-3 col-md-push-1">
                        <h3>Member</h3>
                        <ul class="gtco-footer-links">
                            <li><a href="#">Nguyen Hung Hai</a></li>
                            <li><a href="#">Nguyen Ba Huy</a></li>
                            <li><a href="#">Nguyen Thanh Duy</a></li>
                            <li><a href="#">Le Trung Duc</a></li>
                        </ul>
                    </div>



                    <div class="col-md-3 col-md-push-1">
                        <div class="gtco-widget">
                            <h3>Get In Touch</h3>
                            <ul class="gtco-quick-contact">
                                <li><a href="#"><i class="icon-phone"></i> +01 234 567 89</a></li>
                                <li><a href="#"><i class="icon-mail2"></i> vnflight@prj301.com</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-3 col-md-push-1">
                        <div class="gtco-widget">
                            <h3>Media</h3>
                            <ul class="gtco-quick-contact">
                                <li><a href="#"><i class="icon-facebook"></i> vnflight.fb.com</a></li>
                                <li><a href="#"><i class="icon-twitter"></i> vnflight@twt.com</a></li>
                            </ul>
                        </div>
                    </div>

                </div>



            </div>
        </footer>
        <!-- </div> -->

        <div class="gototop js-top">
            <a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
        </div>

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <!-- jQuery Easing -->
        <script src="${pageContext.request.contextPath}/js/jquery.easing.1.3.js"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <!-- Waypoints -->
        <script src="${pageContext.request.contextPath}/js/jquery.waypoints.min.js"></script>
        <!-- Carousel -->
        <script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
        <!-- countTo -->
        <script src="${pageContext.request.contextPath}/js/jquery.countTo.js"></script>

        <!-- Stellar Parallax -->
        <script src="${pageContext.request.contextPath}/js/jquery.stellar.min.js"></script>

        <!-- Magnific Popup -->
        <script src="${pageContext.request.contextPath}/js/jquery.magnific-popup.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/magnific-popup-options.js"></script>

        <!-- Datepicker -->
        <script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>


        <!-- Main -->
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>

