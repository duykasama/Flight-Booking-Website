/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.Flight;
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
import java.util.List;
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

    public List<Ticket> getTicketsForInvoice(int invoiceId) {
        List<Ticket> passengers = new ArrayList<>();
        String sql = "SELECT pt.id, pt.firstname, pt.lastname, pt.card_id, pt.gender, pt.nationality, pt.luggage_weight, pt.dob, s.seat_id "
                + "FROM invoice i "
                + "JOIN passenger_ticket pt ON i.id = pt.invoice_id "
                + "JOIN seat s ON pt.id = s.passenger_ticket_id "
                + "WHERE i.id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, invoiceId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ticket passenger = new Ticket();
                passenger.setId(rs.getInt("id"));
                passenger.setFirstName(rs.getString("firstname"));
                passenger.setLastName(rs.getString("lastname"));
                passenger.setCardId(rs.getString("card_id"));
                passenger.setGender(rs.getString("gender"));
                passenger.setNationality(rs.getString("nationality"));
                passenger.setLuggageWeight(rs.getFloat("luggage_weight"));
                passenger.setDob(rs.getDate("dob"));
                passenger.setId(rs.getInt("seat_id"));
                passengers.add(passenger);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers ;
    }
    


public Flight getDetailFlight(int invoiceId) {
        Flight flight = null;
        String sql = "select fl.id, fl.takeoff_time, fl.landing_time, fl.departure_date, fl.airline_name,ap1.name as 'departure', ap2.name as 'destination'\n"
                + "from invoice i join flight fl on i.flight_id = f1.id"
                + "join airport ap1 on fl.departure_id = ap1.id \n"
                + "join airport ap2 on fl.destination_id = ap2.id \n"
                + "where fl.invoice_ID = ? \n";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, invoiceId);
            ResultSet rs = statement.executeQuery();

             while (rs.next()) {
                flight = new Flight();
                flight.setId(rs.getInt(1));
                flight.setTakeOffTime(rs.getTime(2));
                flight.setLandingTime(rs.getTime(3));
                flight.setDepartureDate(rs.getDate(4));
                flight.setAirlineName(rs.getString(5));
                flight.setDeparture(rs.getNString(6));
                flight.setDestination(rs.getNString(7));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }
}
//    public int findTicketID(int invoiceID, String cardID) {
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

