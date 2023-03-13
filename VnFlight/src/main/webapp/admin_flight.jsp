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
        <title>Flight Management</title>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productlist.css">

        <!-- Modernizr JS -->
        <script src="${pageContext.request.contextPath}/js/modernizr-2.6.2.min.js"></script>
        <!-- FOR IE9 below -->
        <!--[if lt IE 9]>
        <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
        <![endif]-->
        <style>    
            body{
                position: relative;
            }
            .add-product a{
                cursor: pointer;
            }
            .form {
                position: absolute;
                top: 20%;
                left: 25%;
                transition: transform 0.3s ease 0s;
                display: none;
                z-index: 2;
               background-color:grey; 
                width: 50%; 
                padding: 20px 30px;
              
                
            }
            .form-show {
                display: block; /* this class is added when the form button is clicked, making the form appear */
            }
           
            .open-form-button:hover {
                cursor: pointer;
            }
            .form-background {
                display: none; /* starts out with display of none so it can be changed to block on click */
                position: fixed; /* stays on screen despite scroll */
                width: 100%; /* covers entire width of screen */
                height: 100%; /* covers entire height of screen */
                z-index: 1; /* will be displayed behind background */
            }
            label,.form-title{
                color: goldenrod;
            }
        </style>
    </head>
    <body>
        <c:choose>
            <c:when test="${param.page != null}">
                <c:set var="pageNumb" value="${param.page}"></c:set>
            </c:when>
            <c:otherwise>
                <c:set var="pageNumb" value="1"></c:set>
            </c:otherwise>
        </c:choose>
        <c:set var="begin" value="${(pageNumb-1)*10}"></c:set>
        <c:set var="end" value="${pageNumb*10-1}"></c:set>
        <c:if test="${pageNumb >= Math.ceil(fList.size()/10)}">
            <c:set var="pageNumb" value="${Math.round(fList.size()/10)}"></c:set>
            <c:set var="end" value="${fList.size()-1}"></c:set>
        </c:if>
        <c:if test="${fList.size() == 0}">
            <c:set var="pageNumb" value="1"></c:set>
            <c:set var="begin" value="0"></c:set>
            <c:set var="end" value="0"></c:set>
        </c:if>

        <c:url var="urlNext" value="/admin_flight.jsp">
            <c:choose>
                <c:when test="${Math.ceil(fList.size()/10) == pageNumb || fList.size()==0}">
                    <c:param name="page" value="${pageNumb}"/>
                </c:when>
                <c:otherwise>
                    <c:param name="page" value="${pageNumb+1}"/>
                </c:otherwise>
            </c:choose>
        </c:url>
        <c:url var="urlPrev" value="/admin_flight.jsp">
            <c:choose>
                <c:when test="${pageNumb == 1}">
                    <c:param name="page" value="1"/>
                </c:when>
                <c:otherwise>
                    <c:param name="page" value="${pageNumb-1}"/>
                </c:otherwise>
            </c:choose>
        </c:url>
        <c:url var="urlDoubleNext" value="/admin_flight.jsp">
            <c:choose>
                <c:when test="${pageNumb <= Math.ceil(fList.size()/10)-2}">
                    <c:param name="page" value="${pageNumb+2}"/>
                </c:when>
                <c:otherwise>
                    <c:param name="page" value="${pageNumb}"/>
                </c:otherwise>
            </c:choose>
        </c:url>

        <div class="gtco-loader"></div>
        <div id="page">


            <!-- <div class="page-inner"> -->
            <%@include file="/admin_header.jsp" %>                
            <!--<form id="form" class="form" action="" method="post">-->
           
           <c:url var = "addFlightLink" value="${request.contextPath}/AdminFlightController" />
            <!-- Form starts here -->
            <form id="form" class="form" action="${addFlightLink}" method="post">
                <input type="hidden" name="action" value="addflight">
                <h2 class="form-title">ADD FLIGHT </h2>
                <div class="row form-group">
                    <div class="col-md-6">
                        <label >TAKE OFF TIME</label>
                        <input name="takeOffTime" type="time" class="form-control" required="" value="00:00">
                    </div>
                    <div class="col-md-6">
                        <label >LANDING TIME</label>
                        <input name="landingTime" type="time" class="form-control" required="" value="00:00">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-6">
                        <label>DEPARTURE Date</label>
                        <input name="depDate" type="date" id="fullname" class="form-control" placeholder="departure_date">
                    </div>
                    <div class="col-md-6">
                        <label>STATUS</label>
                        <select name="status" class="form-control" id="from" placeholder="status" >
                            <option value="0">Up Coming</option>
                            <option value="1">Taken off</option>
                            
                        </select>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <label >PRICE</label>
                        <input name="price" type="number" id="fullname" class="form-control" placeholder="PRICE" >
                    </div>
                    <div class="col-md-4">
                        <label >AIRLINE NAME</label>
                        <select class="form-control" id="from" placeholder="departure" name="airlineName">
                            <option value="Vietnam Airline">Vietnam Airline</option>
                            <option value="Vietjet Air">Vietjet Air</option>
                            <option value="Bamboo Airways">Bamboo Airways</option>
                            
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label >NUMBER OF SEAT</label>
                        <select class="form-control" id="from" placeholder="departure" name="numOfSeat">
                            <option value="80">80 seats</option>
                            <option value="100">100 seats</option>
                            <option value="120">120 seats</option>
                            
                        </select>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-6">
                        <label for="from">FROM</label>
                        <select class="form-control" id="from" placeholder="departure" name="depID">
                            <c:forEach var="i" begin="0" end="22">
                                <option value="${airportList.get(i).getId()}">${airportList.get(i).getName()}</option>
                            </c:forEach>/option>
                        </select>
                    </div>
                     <div class="col-md-6">
                        <label for="to">TO</label>
                        <select class="form-control" id="to" placeholder="destination" name="desID">
                            <c:forEach var="i" begin="0" end="22">
                                <option value="${airportList.get(i).getId()}">${airportList.get(i).getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                
                
                <div class="row form-group">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary btn-block" value="ADDFLIGHT">ADD FLIGHT</button>
                    </div>
                </div>
               
            </form>

               

            <div id="form-bg" class="form-background" onclick="closeForm()"></div>
            
            <header id="gtco-header" class="gtco-cover gtco-cover-md" role="banner" style="background-image: url(images/img_bg_2.jpg)">
                <div class="product-status mgtop mg-b-30">
                    
                    <div class="container-fluid">
                        <p class="text-danger">${response}</p>
                      
                        <div class="row">
                            
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="product-status-wrap">
                                    <h4>Flight List</h4>
                                    <div class="add-product">
                                        <a id="button" class="open-form-button" onclick="openForm()">Add Flight</a>
                                    </div>
                                    <table>
                                        <tr>
                                            <th>Flight ID</th>
                                            <th>Airline</th>
                                            <th>From</th>
                                            <th>To</th>
                                            <th>Date</th>
                                            <th>Dep.Time</th>
                                            <th>Seat</th>
                                            <th>Status</th>
                                            <th>Edit</th>
                                            <th>Delete</th>
                                        </tr>
                                        <c:if test="${fList.size() > 0}">
                                            <c:forEach var="i" begin="${begin}" end="${end}">
                                                <tr><td>${fList.get(i).getId()}</td>
                                                    <td>${fList.get(i).getAirlineName()}</td>
                                                    <td>${fList.get(i).getDeparture()}</td>
                                                    <td>${fList.get(i).getDestination()}</td>
                                                    <td>${fList.get(i).getDepartureDate()}</td>
                                                    <td>${fList.get(i).getTakeOffTime()}</td>
                                                    <td>${fList.get(i).getNoOfSeats()}</td>
                                                    <c:choose>
                                                        <c:when test="${fList.get(i).getStatus().equals('Up Coming')}">
                                                            <td><button class="pd-setting">${fList.get(i).getStatus()}</button></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                            <td><button class="ds-setting">${fList.get(i).getStatus()}</button></td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    <td><a href="">edit</a></td>
                                                    <td><a href=""><i class="icon-delete"></i></a></td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </table>
                                    <div class="custom-pagination">
                                        <ul class="pagination">
                                            <li class="page-item"><a class="page-link" href="${urlPrev}">Previous</a></li>
                                            <li class="page-item"><a class="page-link" href="#">${pageNumb}</a></li>
                                            <li class="page-item"><a class="page-link" href="${urlNext}">${pageNumb+1}</a></li>
                                            <li class="page-item"><a class="page-link" href="${urlDoubleNext}">${pageNumb+2}</a></li>
                                            <li class="page-item"><a class="page-link" href="${urlNext}">Next</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

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

</body>
</html>
<script>
                                            function openForm() {
                                                document.getElementById("form").classList.toggle("form-show");
                                                document.getElementById("form-bg").style.display = "block";
                                            }

                                            function closeForm() {
                                                document.getElementById("form").classList.toggle("form-show");
                                                document.getElementById("form-bg").style.display = "none";
                                            }
</script>

