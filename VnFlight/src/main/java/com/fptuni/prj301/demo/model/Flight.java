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
 * @author MSI GF63
 */
public class Flight {

    private int id;
    private Time takeOffTime;
    private Time landingTime;
    private Date departureDate;
    private int price;
    private String airlineName;
    private int noOfSeats;
    private String departure;
    private String destination;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Time getTakeOffTime() {
        return takeOffTime;
    }

    public void setTakeOffTime(Time takeOffTime) {
        this.takeOffTime = takeOffTime;
    }

    public Time getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(Time landingTime) {
        this.landingTime = landingTime;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
