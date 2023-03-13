<%-- 
    Document   : add_flight_form
    Created on : Mar 13, 2023, 10:27:26 AM
    Author     : MSI GF63
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
