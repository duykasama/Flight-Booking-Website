<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
        Traveler by freehtml5.co
        Twitter: http://twitter.com/fh5co
        URL: http://freehtml5.co
-->
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Search Flight</title>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/formstyle.css">

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

            <header id="gtco-header" class="gtco-cover gtco-cover-md long-height " role="banner" style="background-image: url(images/img_bg_2.jpg)">
		
		<div class="booking-form-w3layouts form-margin-top">
			<!-- Form starts here -->
			<form action="#" method="post">
				<h3 class="sub-heading-agileits">Personal Details</h3>
				<div class="main-flex-w3ls-sectns">
					<div class="field-agileinfo-spc form-w3-agile-text1">
						<input type="text"  placeholder="First Name" required="">
					</div>
					
					<div class="field-agileinfo-spc form-w3-agile-text2">
						<input type="text" placeholder="Last Name" required="">
					</div>
				</div>
				
				<div class="field-agileinfo-spc form-w3-agile-text1">
					<input id="datepicker" name="Text" type="text" placeholder="Departure Date" value="" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'mm/dd/yyyy';}"
						required="">
						
				</div>
				<div class="radio-section">
					<h6>Gender</h6>
					<ul class="radio-buttons-w3-agileits">
						<li>
							<input type="radio" id="a-option" name="selector1">
							<label for="a-option">Male</label>
							<div class="check"></div>
						</li>
						<li>
							<input type="radio" id="b-option" name="selector1">
							<label for="b-option">Female</label>
							<div class="check">
								<div class="inside"></div>
							</div>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="main-flex-w3ls-sectns">
					<div class="field-agileinfo-spc form-w3-agile-text1">
						<input type="text"  placeholder="Nationality" required="">
					</div>
					<div class="field-agileinfo-spc form-w3-agile-text1">
						<input type="text"  placeholder="ID" required="">
					</div>
				</div>
				<div class="radio-section">
					<h6>Luggage</h6>
					<ul class="radio-buttons-w3-agileits">
						<li>
							<input type="radio" id="a-option" name="selector1">
							<label for="a-option">15kg/package(200k)</label>
							<div class="check"></div>
						</li>
						<li>
							<input type="radio" id="b-option" name="selector1">
							<label for="a-option">25kg/package(300k)</label>
							<div class="check">
								<div class="inside"></div>
							</div>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
				
				<div class="clear"></div>
				<div class="plane">
					<div class="cockpit">
					</div>
					<div class="exit exit--front fuselage">
					  
					</div>
					<ol class="cabin fuselage">
					  <li class="row row--1">
						<ol class="seats" type="A">
						  <li class="seat">
							<input type="checkbox" id="1A" />
							<label for="1A">1A</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="1B" />
							<label for="1B">1B</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="1C" />
							<label for="1C">1C</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" disabled id="1D" />
							<label for="1D">Occupied</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="1E" />
							<label for="1E">1E</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="1F" />
							<label for="1F">1F</label>
						  </li>
						</ol>
					  </li>
					  <li class="row row--2">
						<ol class="seats" type="A">
						  <li class="seat">
							<input type="checkbox" id="2A" />
							<label for="2A">2A</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="2B" />
							<label for="2B">2B</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="2C" />
							<label for="2C">2C</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="2D" />
							<label for="2D">2D</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="2E" />
							<label for="2E">2E</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="2F" />
							<label for="2F">2F</label>
						  </li>
						</ol>
					  </li>
					  <li class="row row--3">
						<ol class="seats" type="A">
						  <li class="seat">
							<input type="checkbox" id="3A" />
							<label for="3A">3A</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="3B" />
							<label for="3B">3B</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="3C" />
							<label for="3C">3C</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="3D" />
							<label for="3D">3D</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="3E" />
							<label for="3E">3E</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="3F" />
							<label for="3F">3F</label>
						  </li>
						</ol>
					  </li>
					  <li class="row row--4">
						<ol class="seats" type="A">
						  <li class="seat">
							<input type="checkbox" id="4A" />
							<label for="4A">4A</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="4B" />
							<label for="4B">4B</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="4C" />
							<label for="4C">4C</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="4D" />
							<label for="4D">4D</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="4E" />
							<label for="4E">4E</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="4F" />
							<label for="4F">4F</label>
						  </li>
						</ol>
					  </li>
					  <li class="row row--5">
						<ol class="seats" type="A">
						  <li class="seat">
							<input type="checkbox" id="5A" />
							<label for="5A">5A</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="5B" />
							<label for="5B">5B</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="5C" />
							<label for="5C">5C</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="5D" />
							<label for="5D">5D</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="5E" />
							<label for="5E">5E</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="5F" />
							<label for="5F">5F</label>
						  </li>
						</ol>
					  </li>
					  <li class="row row--6">
						<ol class="seats" type="A">
						  <li class="seat">
							<input type="checkbox" id="6A" />
							<label for="6A">6A</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="6B" />
							<label for="6B">6B</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="6C" />
							<label for="6C">6C</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="6D" />
							<label for="6D">6D</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="6E" />
							<label for="6E">6E</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="6F" />
							<label for="6F">6F</label>
						  </li>
						</ol>
					  </li>
					  <li class="row row--7">
						<ol class="seats" type="A">
						  <li class="seat">
							<input type="checkbox" id="7A" />
							<label for="7A">7A</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="7B" />
							<label for="7B">7B</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="7C" />
							<label for="7C">7C</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="7D" />
							<label for="7D">7D</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="7E" />
							<label for="7E">7E</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="7F" />
							<label for="7F">7F</label>
						  </li>
						</ol>
					  </li>
					  <li class="row row--8">
						<ol class="seats" type="A">
						  <li class="seat">
							<input type="checkbox" id="8A" />
							<label for="8A">8A</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="8B" />
							<label for="8B">8B</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="8C" />
							<label for="8C">8C</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="8D" />
							<label for="8D">8D</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="8E" />
							<label for="8E">8E</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="8F" />
							<label for="8F">8F</label>
						  </li>
						</ol>
					  </li>
					  <li class="row row--9">
						<ol class="seats" type="A">
						  <li class="seat">
							<input type="checkbox" id="9A" />
							<label for="9A">9A</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="9B" />
							<label for="9B">9B</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="9C" />
							<label for="9C">9C</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="9D" />
							<label for="9D">9D</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="9E" />
							<label for="9E">9E</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="9F" />
							<label for="9F">9F</label>
						  </li>
						</ol>
					  </li>
					  <li class="row row--10">
						<ol class="seats" type="A">
						  <li class="seat">
							<input type="checkbox" id="10A" />
							<label for="10A">10A</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="10B" />
							<label for="10B">10B</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="10C" />
							<label for="10C">10C</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="10D" />
							<label for="10D">10D</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="10E" />
							<label for="10E">10E</label>
						  </li>
						  <li class="seat">
							<input type="checkbox" id="10F" />
							<label for="10F">10F</label>
						  </li>
						</ol>
					  </li>
					</ol>
					<div class="exit exit--back fuselage">
					  
					</div>
				  </div>
				<input type="submit" disabled ="disabled"value="TOTAL"> 
				
				<div class="clear"></div>
				<input type="submit" value="Submit">
				<input type="reset" value="Clear Form">
				<div class="clear"></div>
				
				
				<div class="clear"></div>
			</form>
		</div>
		
	</header>




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

        </div>

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
        <!--CALENDAR-->
        <script type="text/javascript" src="js/wickedpicker.js"></script>
	<script type="text/javascript">
		$('.timepicker,.timepicker1').wickedpicker({
			twentyFour: false
		});
	</script>
	<!--// Time Js -->
	<!-- Calendar Js -->
	<script src="js/jquery-ui.js"></script>
	<script>
		$(function () {
			$("#datepicker,#datepicker1,#datepicker2,#datepicker3").datepicker();
		});
	</script>

    </body>
</html>

