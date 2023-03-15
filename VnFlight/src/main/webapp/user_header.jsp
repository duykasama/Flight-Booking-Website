<!-- <div class="page-inner"> -->
<nav class="gtco-nav" role="navigation">
    <div class="gtco-container">

        <div class="row">
            <div class="col-sm-4 col-xs-12">
                <div id="gtco-logo"><a href="${pageContext.request.contextPath}/user_home.jsp"><em>VNFLIGHT </em></a></div>
            </div>
            <div class="col-xs-8 text-right menu-1">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/user_home.jsp">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/UserFlightController/search">Search Flight</a></li>
                    <c:choose>
                        <c:when test="${usersession != null}">
                            <li><a href="${pageContext.request.contextPath}/user_account.jsp">Account</a></li>
                            <li><a href="${pageContext.request.contextPath}/BookingHistoryController/history">Booking History</a></li>
                            <li><a href="${pageContext.request.contextPath}/UserAccessController/logout">Logout</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/user_login.jsp">Login</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>	
            </div>
        </div>
    </div>
</nav>