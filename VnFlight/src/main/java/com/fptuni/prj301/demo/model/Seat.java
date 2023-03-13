/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Seat {
    private int seatId;
    private int flightId;
    private int passengerTicketId;
    private String seatNumber;
    private int status;

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getPassengerTicketId() {
        return passengerTicketId;
    }

    public void setPassengerTicketId(int passengerTicketId) {
        this.passengerTicketId = passengerTicketId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    

}
