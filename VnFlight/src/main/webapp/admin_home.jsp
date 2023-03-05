<%@page import="com.fptuni.prj301.demo.model.Flight"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.fptuni.prj301.demo.model.FlightManager"%>
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
        <style>
            table {
                border-collapse: separate; 
                border-spacing: 25px 0;
                /*border: solid black 1px;*/
            }
            td {
                padding: 10px;
                width: 100px;
            }
            .center {
                text-align: center;
            }
            form {
                display: inline;
            }
            .page-numb{
                border: black solid 1px;
                padding: 1px 2px 1px 2px;
                margin: 5px;
            }
            .active {
                background-color: cyan;
            }

        </style>
    </head>
    <body id="page1">
        <!-- START PAGE SOURCE -->
        <%
            int pageNumber = 1;
            if (request.getSession().getAttribute("pageNumber") != null) {
                pageNumber = (int) request.getSession().getAttribute("pageNumber");
            }
        %>
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
            <!--            <div id="banner">
                            <div class="text1">COMFORT<span>Guaranteed</span>
                                <p>A website for booking domestic flight tickets!</p>
                            </div>
                        </div>-->
            <table>
                <thead>
                <th>Flight ID</th>
                <th>Airline</th>
                <th>From</th>
                <th>To</th>
                <th>Date</th>
                <th>Departure Time</th>
                <th>Price</th>
                <th>Seats</th>
                <th>Status</th>
                <th>Edit</th>
                <th>Delete</th>
                </thead>
                <tbody>
                    <% FlightManager fManager = new FlightManager();
                        for (int i = 10 * pageNumber - 10; i < 10 * pageNumber; i++) {
                            out.print("<tr>");
                            out.println("<td>" + fManager.get(i).getId() + "</td>");
                            out.println("<td>" + fManager.get(i).getAirlineName() + "</td>");
                            out.println("<td>" + fManager.get(i).getDeparture() + "</td>");
                            out.println("<td>" + fManager.get(i).getDestination() + "</td>");
                            out.println("<td>" + fManager.get(i).getDepartureDate() + "</td>");
                            out.println("<td>" + fManager.get(i).getTakeOffTime() + "</td>");
                            out.println("<td>" + fManager.get(i).getPrice() + "</td>");
                            out.println("<td>" + fManager.get(i).getNoOfSeats() + "</td>");
                            out.println("<td>" + fManager.get(i).getStatus() + "</td>");
                            out.println("<td>Icon</td>");
                            out.println("<td>Icon</td>");
                            out.print("</tr>");
                        }
                    %>
                </tbody>
            </table>
            <div class="center">
                <%
                    out.print("<span><form action=\"./PageController\" method=\"POST\">"
                            + "<input name=\"action\" value=\"decrease\" type=\"hidden\">"
                            + "<input name=\"pageNumber\" value=\"" + pageNumber + "\" type=\"hidden\">"
                            + "<input type=\"submit\" value=\"<<\">"
                            + "</form>"
                            + "</span>");
                    out.print("<span class=\"page-numb active\">" + pageNumber + "</span>");
                    out.print("<span class=\"page-numb\">" + String.valueOf(pageNumber + 1) + "</span>");
                    out.print("<span class=\"page-numb\">" + String.valueOf(pageNumber + 2) + "</span>");
                    out.print("<span><form action=\"./PageController\" method=\"POST\">"
                            + "<input name=\"action\" value=\"increase\" type=\"hidden\">"
                            + "<input name=\"pageNumber\" value=\"" + pageNumber + "\" type=\"hidden\">"
                            + "<input type=\"submit\" value=\">>\">"
                            + "</form>"
                            + "</span>");
                %>

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

        <%@include file="css.jsp"%>
    </head>
<body id="page1">
    <%@include file="/admin_header.jsp" %>
    <%@include file="/footer.jsp" %>

    <!-- END PAGE SOURCE -->
</body>
</html>