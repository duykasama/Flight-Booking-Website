/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Ticket {

    private int id;
    private int invoiceId;
    private String firstName;
    private String lastName;
    private float luggageWeight;
    private String cardId;
    private String gender;
    private String nationality;
    private Date dob;
    private String seatNumber;

    public Ticket() {
    }

    public Ticket(int id, String seatNumber, float luggageWeight) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.luggageWeight = luggageWeight;
    }

    public Ticket(String firstName, String lastName, String luggageWeight, String cardId, String gender, String nationality, String dob, String seatNumber) {
        this.setCardId(cardId);
        this.setFirstName(firstName);
        this.setGender(gender);
        this.setLastName(lastName);
        this.setNationality(nationality);
        this.setLuggageWeight(Float.parseFloat(luggageWeight));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.setDob(dateFormat.parse(dob));
        } catch (ParseException ex) {
            Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setSeatNumber(seatNumber);
    }

    public Ticket(int invoiceId, String firstName, String lastName, String luggageWeight, String cardId, String gender, String nationality, String dob, String seatNumber) {
        this.setInvoiceId(invoiceId);
        this.setCardId(cardId);
        this.setFirstName(firstName);
        this.setGender(gender);
        this.setLastName(lastName);
        this.setNationality(nationality);
        this.setLuggageWeight(Float.parseFloat(luggageWeight));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.setDob(dateFormat.parse(dob));
        } catch (ParseException ex) {
            Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setSeatNumber(seatNumber);
    }

    public Ticket(int invoiceId, String firstName, String lastName, float luggageWeight, String cardId, String gender, String nationality, Date dob, String seatNumber) {
        this.invoiceId = invoiceId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.luggageWeight = luggageWeight;
        this.cardId = cardId;
        this.gender = gender;
        this.nationality = nationality;
        this.dob = dob;
        this.seatNumber = seatNumber;
    }

    public Ticket(String firstName, String lastName, float luggageWeight, String cardId, String gender, String nationality, Date dob, String seatNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.luggageWeight = luggageWeight;
        this.cardId = cardId;
        this.gender = gender;
        this.nationality = nationality;
        this.dob = dob;
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(float luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
