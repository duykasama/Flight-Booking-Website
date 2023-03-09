<nav class="gtco-nav" role="navigation">
    <div class="gtco-container">

        <div class="row">
            <div class="col-sm-4 col-xs-12">
                <div id="gtco-logo"><a href="admin_home.jsp"><em>VNFLIGHT</em></a></div>
            </div>
            <div class="col-xs-8 text-right menu-1">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/AdminFlightController">FLIGHT</a></li>
                    <li class="has-dropdown">
                        <a href="#">MANAGE</a>
                        <ul class="dropdown">

                            <li><a href="${pageContext.request.contextPath}/AdminUserListController">USER</a></li>
                            <li><a href="${pageContext.request.contextPath}/AdminInvoiceController">INVOICE</a></li>
                        </ul>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/AdminAccessController/logout">LOG OUT</a></li>

                </ul>	
            </div>
        </div>

    </div>
</nav>