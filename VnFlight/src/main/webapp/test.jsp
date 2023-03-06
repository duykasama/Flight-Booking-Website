<%-- 
    Document   : test
    Created on : Mar 6, 2023, 5:13:04 PM
    Author     : MSI GF63
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>TODO write content</div>
    <c:forEach var="i" begin="0" end="1">
        <tr>

            <td>${iList.get(i).getId()}</td>
            <td>${iList.get(i).getUserId()}</td>
            <td>${iList.get(i).getFlightId()}</td>
            <td>${iList.get(i).getBookingDate()}</td>
            <td>${iList.get(i).getTotalPrice()}</td>
        <c:choose>
            <c:when test="${iList.get(i).getPurchaseStatus().equals('Purchasing')}">
                <td><button class="pd-setting">${iList.get(i).getPurchaseStatus()}</button></td>
            </c:when>
            <c:when test="${iList.get(i).getPurchaseStatus().equals('Purchased')}">
                <td><button class="ds-setting">${iList.get(i).getPurchaseStatus()}</button></td>
            </c:when>
        </c:choose>
        <td><a href="">edit</a></td>
        <td><a href=""><i class="icon-delete"></i></a></td>


    </tr>
</c:forEach>
</body>
</html>