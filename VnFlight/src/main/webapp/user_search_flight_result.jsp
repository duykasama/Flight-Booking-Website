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
        <title>Flight Result</title>
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
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_popup_form.css">-->
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleLogin.css">-->
        <!-- Modernizr JS -->
        <script src="${pageContext.request.contextPath}/js/modernizr-2.6.2.min.js"></script>
        <!-- FOR IE9 below -->
        <!--[if lt IE 9]>
        <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
        <![endif]-->

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
        <c:if test="${pageNumb >= Math.ceil(flightList.size()/10)}">
            <c:set property="double" var="temp" value="${flightList.size()}"></c:set>
            <c:set var="pageNumb" value="${Math.round(Double.parseDouble(Math.ceil(temp/10)))}"></c:set>
            <c:set var="end" value="${flightList.size()-1}"></c:set>
        </c:if>
        <c:if test="${flightList.size() == 0}">
            <c:set var="pageNumb" value="1"></c:set>
            <c:set var="begin" value="0"></c:set>
            <c:set var="end" value="0"></c:set>
        </c:if>

        <c:url var="urlNext" value="/user_search_flight_result.jsp">
            <c:choose>
                <c:when test="${Math.ceil(flightList.size()/10) == pageNumb || flightList.size()==0}">
                    <c:param name="page" value="${pageNumb}"/>
                </c:when>
                <c:otherwise>
                    <c:param name="page" value="${pageNumb+1}"/>
                </c:otherwise>
            </c:choose>
        </c:url>
        <c:url var="urlPrev" value="/user_search_flight_result.jsp">
            <c:choose>
                <c:when test="${pageNumb == 1}">
                    <c:param name="page" value="1"/>
                </c:when>
                <c:otherwise>
                    <c:param name="page" value="${pageNumb-1}"/>
                </c:otherwise>
            </c:choose>
        </c:url>
        <c:url var="urlDoubleNext" value="/user_search_flight_result.jsp">
            <c:choose>
                <c:when test="${pageNumb <= Math.ceil(flightList.size()/10)-2}">
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
            <%@include file="/user_header.jsp" %>   
            <!--loginpop-->
            <%--<%@include file="/login_popup_form.jsp" %>--%>  
            <header id="gtco-header" class="gtco-cover gtco-cover-md" role="banner" style="background-image: url(images/img_bg_2.jpg)">
                
                <div class="product-status mgtop mg-b-30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="product-status-wrap">
                                    <h4>Flight List</h4>
                                    <table>
                                        <tr>
                                            <th>Flight ID</th>
                                            <th>Airline</th>
                                            <th>From</th>
                                            <th>To</th>
                                            <th>Date</th>
                                            <th>Price</th>
                                            <th>Take-off Time</th>
                                            <th>Landing Time</th>
                                            <th>Seat</th>
                                            <th>Status</th>
                                            <th>Purchase</th>
                                        </tr>
                                        <c:if test="${flightList.size() > 0}">
                                            <c:forEach var="i" begin="${begin}" end="${end}">
                                                <tr><td>${flightList.get(i).getId()}</td>
                                                    <td>${flightList.get(i).getAirlineName()}</td>
                                                    <td>${flightList.get(i).getDeparture()}</td>
                                                    <td>${flightList.get(i).getDestination()}</td>
                                                    <td>${flightList.get(i).getDepartureDate()}</td>
                                                    <td>${flightList.get(i).getPrice()}</td>
                                                    <td>${flightList.get(i).getTakeOffTime()}</td>
                                                    <td>${flightList.get(i).getLandingTime()}</td>
                                                    <td>${flightList.get(i).getNoOfSeats()}</td>

                                                    <c:choose>
                                                        <c:when test="${flightList.get(i).getStatus().equals('Up Coming')}">
                                                            <td><button class="pd-setting" style="cursor: text">${flightList.get(i).getStatus()}</button></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                            <td><button class="ds-setting" style="cursor: text">${flightList.get(i).getStatus()}</button></td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${usersession != null}">
                                                                <c:url var="detailLink" value="${request.contextPath}/UserFlightController/createInvoice"/>
                                                                <form action="${detailLink}" name="" method="POST">
                                                                    <input type="hidden" name="userID" value="${usersession.getId()}"/>
                                                                    <input type="hidden" name="flightID" value="${flightList.get(i).getId()}"/>
                                                                    <input type="hidden" name="bookingDate" id="bookingDate" value=""/>
                                                                    <input type="hidden" name="flightPrice" value="${flightList.get(i).getPrice()}">
                                                                    <script>
                                                                        var today = new Date().toISOString().substr(0, 10);
                                                                        document.getElementById("bookingDate").value = today;
                                                                    </script>
                                                                    <input type="hidden" name="purchaseStatus" value="0"/>
                                                                    <button class="pd-setting" type="submit">Purchase</button>
                                                                </form>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="${pageContext.request.contextPath}/user_login_popup.jsp">Login and Purchase</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
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
<!--<script src="${pageContext.request.contextPath}/js/login_popup_form.js"></script>-->

</body>
</html>

