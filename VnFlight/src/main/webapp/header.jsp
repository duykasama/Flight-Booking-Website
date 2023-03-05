<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/search.jsp">Search</a></li>
                            <li><a href="${pageContext.request.contextPath}/#">Account</a></li>
                            <li><a href="${pageContext.request.contextPath}/#">Booking History</a></li>
                            <c:choose>
                                <c:when test="${usersession != null}">
                                    <li><a href="${pageContext.request.contextPath}/Access/logout">Logout</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${pageContext.request.contextPath}/Access/login">Login</a></li>
                                </c:otherwise>
                            </c:choose>
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
        <a href="admin_login.jsp" class="button_top">LOGIN AS ADMIN</a></div>
</div>