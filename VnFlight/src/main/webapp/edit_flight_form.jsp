<%-- 
    Document   : edit_flight_form
    Created on : Mar 19, 2023, 7:43:04 PM
    Author     : MSI GF63
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    </head>
    <style>
        body{
            position: relative;
            background-color: #81a6f0;
        }
        .form {
            position: absolute;
            top: 200px;
            left: 25%;
            transition: transform 0.3s ease 0s;
            z-index: 2;
            background-color:grey; 
            width: 50%; 
            padding: 20px 30px;
            border-radius: 10px;

        }
        label,.form-title{
            color: goldenrod;
        }
    </style>
    <body>
        <c:url var = "addFlightLink" value="${request.contextPath}/AdminFlightController" />
        <!-- Form starts here -->
        <form id="form" class="form" action="${addFlightLink}" method="post">
            <input type="hidden" name="flightID" value="${flight.getId()}">
            <h2 class="form-title">EDIT FLIGHT </h2>
            <div class="row form-group">
                <div class="col-md-6">
                    <label >TAKE OFF TIME</label>
                    <input name="takeOffTime" type="time" class="form-control" required="" value="${flight.getTakeOffTime()}">
                </div>
                <div class="col-md-6">
                    <label >LANDING TIME</label>
                    <input name="landingTime" type="time" class="form-control" required="" value="${flight.getLandingTime()}">
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <label>DEPARTURE Date</label>
                    <input name="depDate" type="date" id="fullname" class="form-control" value="${flight.getDepartureDate()}">
                </div>
                <div class="col-md-6">
                    <label>STATUS</label>
                    <select name="status" class="form-control" id="from" >
                        <option selected hidden value="${flight.getStatus()}">${flight.getStatus()}</option>
                        <option value="0">Up Coming</option>
                        <option value="1">Taken off</option>

                    </select>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <label >PRICE</label>
                    <input name="price" type="number" id="fullname" class="form-control" value="${flight.getPrice()}" >
                </div>
                <div class="col-md-4">
                    <label >AIRLINE NAME</label>
                    <select class="form-control" id="from" value="${flight.getAirlineName()}" name="airlineName">
                        <option hidden selected value="${flight.getAirlineName()}">${flight.getAirlineName()}</option>
                        <option value="Vietnam Airline">Vietnam Airline</option>
                        <option value="Vietjet Air">Vietjet Air</option>
                        <option value="Bamboo Airways">Bamboo Airways</option>

                    </select>
                </div>
                <div class="col-md-4">
                    <label >NUMBER OF SEAT</label>
                    <select class="form-control" id="from" name="numOfSeat">
                        <option hidden selected value="${flight.getNoOfSeats()}">${flight.getNoOfSeats()} seats</option>
                        <option value="60">60 seats</option>
                        <option value="90">90 seats</option>
                        <option value="120">120 seats</option>

                    </select>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <label for="from">FROM</label>
                    <select class="form-control" id="from" name="depID">
                        <option hidden selected value="${flight.getDeparture()}">${flight.getDeparture()}</option>
                        <c:forEach var="i" begin="0" end="22">
                            <option value="${airportList.get(i).getId()}">${airportList.get(i).getName()}</option>
                        </c:forEach>/option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="to">TO</label>
                    <select class="form-control" id="to" name="desID">
                        <option hidden selected value="${flight.getDestination()}">${flight.getDestination()}</option>
                        <c:forEach var="i" begin="0" end="22">
                            <option value="${airportList.get(i).getId()}">${airportList.get(i).getName()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>



            <div class="row form-group">
                <div class="col-md-12">
                    <button name="action" type="submit" class="btn btn-primary btn-block" value="confirmEdit">Save</button>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <button name="action" type="submit" class="btn btn-primary btn-block" value="cancelEdit">Cancel</button>
                </div>
            </div>

        </form>
    </body>
</html>
