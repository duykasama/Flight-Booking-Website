

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="plane">
        <div class="cockpit">
        </div>
        <div class="exit exit--front fuselage">

        </div>
       
        <c:set var="numb" value="${quantityOfSeats/6}"></c:set>
        <ol class="cabin fuselage">
            <c:forEach var="i" begin="1" end="${numb}">
                <li class="row row--${i}">
                    
                    <ol class="seats" type="A">
                        <c:set var ="xA" value="${i}A" />
                        <li class="seat" >
                            <c:if test="${seatNumberList.contains(xA)}">
                                <input name="seatNumber" type="checkbox" id="${i}A" value="${i}A" disabled/>
                                <label for="${i}A">${i}A</label>
                            </c:if>
                            <c:if test="${seatNumberList.contains(xA) == false}">
                                <input name="seatNumber" type="checkbox" id="${i}A" value="${i}A" />
                                <label for="${i}A">${i}A</label>
                            </c:if>
                        </li>
                        <li class="seat">
                            <c:set var ="xB" value="${i}B" />
                            <c:if test="${seatNumberList.contains(xB)}">
                                <input name="seatNumber" type="checkbox" id="${i}B" value="${i}B" disabled/>
                                <label for="${i}B">${i}B</label>
                            </c:if>
                            <c:if test="${seatNumberList.contains(xB) == false}">
                                <input name="seatNumber" type="checkbox" id="${i}B" value="${i}B" />
                                <label for="${i}B">${i}B</label>
                            </c:if>
                        </li>
                        <li class="seat">
                            <c:set var ="xC" value="${i}C" />
                            <c:if test="${seatNumberList.contains(xC)}">
                                <input name="seatNumber" type="checkbox" id="${i}C" value="${i}C" disabled/>
                                <label for="${i}C">${i}C</label>
                            </c:if>
                            <c:if test="${seatNumberList.contains(xC) == false}">
                                <input name="seatNumber" type="checkbox" id="${i}C" value="${i}C" />
                                <label for="${i}C">${i}C</label>
                            </c:if>
                        </li>
                        <li class="seat">
                            <c:set var ="xD" value="${i}D" />
                            <c:if test="${seatNumberList.contains(xD)}">
                                <input name="seatNumber" type="checkbox" id="${i}D" value="${i}D" disabled/>
                                <label for="${i}D">${i}D</label>
                            </c:if>
                            <c:if test="${seatNumberList.contains(xD) == false}">
                                <input name="seatNumber" type="checkbox" id="${i}D" value="${i}D" />
                                <label for="${i}D">${i}D</label>
                            </c:if>
                        </li>
                        <li class="seat">
                            <c:set var ="xE" value="${i}E" />
                            <c:if test="${seatNumberList.contains(xE)}">
                                <input name="seatNumber" type="checkbox" id="${i}E" value="${i}E" disabled/>
                                <label for="${i}E">${i}E</label>
                            </c:if>
                            <c:if test="${seatNumberList.contains(xE) == false}">
                                <input name="seatNumber" type="checkbox" id="${i}E" value="${i}E" />
                                <label for="${i}E">${i}E</label>
                            </c:if>
                        </li>
                        <li class="seat">
                             <c:set var ="xF" value="${i}F" />
                            <c:if test="${seatNumberList.contains(xF)}">
                                <input name="seatNumber" type="checkbox" id="${i}F" value="${i}F" disabled/>
                                <label for="${i}F">${i}F</label>
                            </c:if>
                            <c:if test="${seatNumberList.contains(xF) == false}">
                                <input name="seatNumber" type="checkbox" id="${i}F" value="${i}F" />
                                <label for="${i}F">${i}F</label>
                            </c:if>
                        </li>
                    </ol>
                </li>
            </c:forEach>
        </ol>
        <div class="exit exit--back fuselage">
        </div>
    </div>


