/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.Flight;
import com.fptuni.prj301.demo.model.Ticket;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class UserTicketManager extends ArrayList<Ticket> {

    public UserTicketManager() {
    }

    public static void insertTicketList(ArrayList<Ticket> list) {

        for (Ticket ticket : list) {
            Connection conn = null;
            PreparedStatement ps = null;

            String sql = " INSERT INTO passenger_ticket (invoice_id, firstname, lastname, luggage_weight, card_id, gender, nationality, dob, seat_number) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

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
                ps.setString(9, ticket.getSeatNumber());

                ps.executeUpdate();
                ps.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public static List<Ticket> getTicketsForInvoice(int invoiceId) {
        List<Ticket> passengers = new ArrayList<>();
//        String sql = " SELECT pt.id, pt.firstname, pt.lastname, pt.card_id, pt.gender, pt.nationality, pt.luggage_weight, pt.dob, s.seat_number "
        String sql = " SELECT pt.id, pt.firstname, pt.lastname, pt.card_id, pt.gender, pt.nationality, pt.luggage_weight, pt.dob, pt.seat_number "
                + " FROM passenger_ticket pt "
                + " JOIN invoice i ON i.id = pt.invoice_id "
                //                + " JOIN seat s ON pt.id = s.passenger_ticket_id "
                + " WHERE i.id = ? ";

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
                passenger.setSeatNumber(rs.getString("seat_number"));
                passengers.add(passenger);
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return passengers;
    }

//    public static void main(String[] args) {
//        System.out.println(getTicketsForInvoice(19));
//    }
    public Flight getDetailFlight(int invoiceId) {
        Flight flight = null;
        String sql = " select fl.id, fl.takeoff_time, fl.landing_time, fl.departure_date, fl.airline_name,ap1.name as 'departure', ap2.name as 'destination', fl.price "
                + " from invoice i join flight fl on i.flight_id = fl.id "
                + " join airport ap1 on fl.departure_id = ap1.id "
                + " join airport ap2 on fl.destination_id = ap2.id "
                + " where i.id = ? ";

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
                flight.setPrice(rs.getInt(8));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flight;
    }

    public static List<Ticket> getTicketID(int invoiceID) {
        List<Ticket> ticketIDList = new ArrayList();
        String sql = "select id,seat_number,luggage_weight from [dbo].[passenger_ticket] where invoice_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, invoiceID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ticketID = rs.getInt("id");
                String seatNumber = rs.getString("seat_number");
                float luggage_weight = rs.getFloat("luggage_weight");
                ticketIDList.add(new Ticket(ticketID, seatNumber, luggage_weight));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ticketIDList;
    }

//    public static List<Ticket> insertReturnTicketList(List<Ticket> tickets) {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String sql = "INSERT INTO ticket (invoice_id, firstname, lastname, luggage_weight,card_id,gender,nationality,dob,seat_number) "
//                + "VALUES (?, ?, ?, ?,?,?,?,?,?)";
//
//        try {
//            conn = DBUtils.getConnection();
//            ps = conn.prepareStatement(sql);
//
//            List<Ticket> ticketObjectList = new ArrayList<>();
//
//            for (Ticket ticket : tickets) {
//                ps.setInt(1, ticket.getInvoiceId());
//                ps.setString(2, ticket.getFirstName());
//                ps.setString(3, ticket.getLastName());
//                ps.setFloat(4, ticket.getLuggageWeight());
//                ps.setString(5, ticket.getCardId());
//                ps.setString(6, ticket.getGender());
//                ps.setString(7, ticket.getNationality());
//                ps.setDate(8, new java.sql.Date(ticket.getDob().getTime()));
//                ps.setString(9, ticket.getSeatNumber());
//
//                int affectedRows = ps.executeUpdate();
//
//
//            }
//
//            return ticketObjectList;
//        } catch (SQLException ex) {
//            System.out.println("Query Student error!" + ex.getMessage());
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (Exception e) {
//            };
//            try {
//                if (ps != null) {
//                    ps.close();
//                }
//            } catch (Exception e) {
//            };
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (Exception e) {
//            };
//        }
//        return null;
//    }
}
//    public static int findTicketID(int invoiceID, String cardID) {
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

