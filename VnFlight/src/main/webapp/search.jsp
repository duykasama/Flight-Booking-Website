<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <title>Search Flight</title>
        <%@include file="css.jsp"%>
    </head>
    <body id="page1">
        <!-- Header -->
        <%@include file="/header.jsp" %>
        <!--Main-->
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
        <!-- Footer -->
        <%@include file="/footer.jsp" %>
    </body>
</html>