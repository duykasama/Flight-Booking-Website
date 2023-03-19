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
                    <li class="seat" >
                        <input name="seatNumber" type="checkbox" id="${i}A" value="${i}A" />
                        <label for="${i}A">${i}A</label>
                    </li>
                    <li class="seat">
                        <input name="seatNumber" type="checkbox" id="${i}B" value="${i}B"/>
                        <label for="${i}B">${i}B</label>
                    </li>
                    <li class="seat">
                        <input name="seatNumber" type="checkbox" id="${i}C" value="${i}C"/>
                        <label for="${i}C">${i}C</label>
                    </li>
                    <li class="seat">
                        <input name="seatNumber" type="checkbox" id="${i}D" value="${i}D"/>
                        <label for="${i}D">${i}D</label>
                    </li>
                    <li class="seat">
                        <input name="seatNumber" type="checkbox" id="${i}E" value="${i}E"/>
                        <label for="${i}E">${i}E</label>
                    </li>
                    <li class="seat">
                        <input name="seatNumber" type="checkbox" id="${i}F" value="${i}F"/>
                        <label for="${i}F">${i}F</label>
                    </li>
                </ol>
            </li>
        </c:forEach>
    </ol>
    <div class="exit exit--back fuselage">
    </div>
</div>

