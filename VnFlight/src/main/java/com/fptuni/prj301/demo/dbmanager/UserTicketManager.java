/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.Invoice;
import com.fptuni.prj301.demo.model.Ticket;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserTicketManager {

    public Ticket createTicketTemp(int invoiceID, String firstName, String lastName, String luggageWeight, String cardId, String gender, String nationality, String dob) {
        Ticket ticket = new Ticket();
        ticket.setInvoiceId(invoiceID);
        ticket.setCardId(cardId);
        ticket.setFirstName(firstName);
        ticket.setGender(gender);
        ticket.setLastName(lastName);
        ticket.setNationality(nationality);
        ticket.setLuggageWeight(Float.parseFloat(luggageWeight));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ticket.setDob(dateFormat.parse(dob));
        } catch (ParseException ex) {
            Logger.getLogger(UserInvoiceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ticket;
    }

    public static void insertTicket(Ticket ticket) {

        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO passenger_ticket (invoice_id, firstname, lastname, luggage_weight, card_id, gender, nationality, dob) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, ticket.getInvoiceId());
            ps.setString(2, ticket.getFirstName());
            ps.setString(3, ticket.getLastName());
            ps.setFloat(4, ticket.getLuggageWeight());
            ps.setString(5, ticket.getCardId());
            ps.setString(6, ticket.getGender());
            ps.setString(7, ticket.getNationality());
            ps.setDate(8, new java.sql.Date(ticket.getDob().getTime()));

            ps.executeUpdate();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    public int findPassengerID(int invoiceID, String cardID) {
//        int passengerID = 0;
//        String sql = "SELECT id FROM passenger WHERE invoice_id = ? and card_id = ?";
//        try (Connection conn = DBUtils.getConnection();
//                PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, invoiceID);
//            stmt.setString(2, cardID);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    passengerID = rs.getInt("id");
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println("Error finding passenger ID by card ID: " + ex.getMessage());
//        }
//        return passengerID;
//    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ticket.setInvoiceId(1);
        ticket.setFirstName("a");
        ticket.setLastName("a");
        ticket.setLuggageWeight(15);
        ticket.setCardId("1");
        ticket.setGender("Male");
        ticket.setNationality("a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ticket.setDob(dateFormat.parse("2023-03-14"));
        } catch (ParseException ex) {
            Logger.getLogger(UserTicketManager.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println(createTicketTemp(1, "b", "b", "15", "b", "b", "b", "2022-04-12"));
//        insertTicket(ticket);
//        System.out.println(insertTicket(createTicketTemp(1, "b", "b", "15", "b", "b", "b", "2022-04-12")));
    }
}
