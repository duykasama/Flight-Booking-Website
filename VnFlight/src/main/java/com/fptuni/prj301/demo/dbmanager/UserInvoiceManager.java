/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.BookingHistory;
import com.fptuni.prj301.demo.model.Invoice;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserInvoiceManager {

    public Invoice createInvoiceTemp(String user_id, String flight_id, String booking_date, String purchase_status) {
        Invoice invoice = new Invoice();
        invoice.setUserId(Integer.parseInt(user_id));
        invoice.setFlightId(Integer.parseInt(flight_id));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            invoice.setBookingDate(dateFormat.parse(booking_date));
        } catch (ParseException ex) {
            Logger.getLogger(UserInvoiceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        invoice.setPurchaseStatus(Integer.parseInt(purchase_status));
        return invoice;
    }

    //insert invoice information (not include invoice_id) into database and return invoice_id
    public static int insertReturnInvoiceID(Invoice invoice) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO invoice (user_id, flight_id, booking_date, purchase_status) "
                + "VALUES (?, ?, ?, ?)";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, invoice.getUserId());
            ps.setInt(2, invoice.getFlightId());
            ps.setDate(3, new java.sql.Date(invoice.getBookingDate().getTime()));
            ps.setInt(4, invoice.getPurchaseStatus());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting invoice failed, no rows affected.");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return id;
            } else {
                throw new SQLException("Inserting invoice failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            };
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            };
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            };
        }
        return -1;
    }

    public List<Invoice> getInvoiceHistory(int userId) {
        List<Invoice> invoice = new ArrayList<>();
        String sql = "SELECT id, flight_id, booking_date, total_price,purchase_status "
                + "from invoice "
                + "WHERE user_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Invoice i = new Invoice();
                i.setId(rs.getInt(1));
                i.setFlightId(rs.getInt(2));
                i.setBookingDate(rs.getDate(3));
                i.setTotalPrice(rs.getInt(4));
                i.setPurchaseStatus(rs.getInt(5));
                invoice.add(i);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    public void updateInvoicePurchaseStatus(int invoiceId) {
        String query = "UPDATE [invoice] "
                + "SET purchase_status = 1 "
                + "FROM [invoice]  "
                + "WHERE id = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, invoiceId);
            ResultSet rs = ps.executeQuery();
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Invoice> getDetailHistory(int invoiceId) {
        List<Invoice> bookings = new ArrayList<>();
        String sql = "SELECT i.id AS invoice_id, "
                + "f.id AS flight_id, "
                + "f.airline_name AS airline_name, "
                + "dep.name AS departure, "
                + "dest.name AS destination, "
                + "f.takeoff_time, "
                + "f.departure_date, "
                + "s.seat_number, "
                + "i.total_price, "
                + "i.purchase_status "
                + "invoice i  "
                + "JOIN flight f ON i.flight_id = f.id "
                + "JOIN airport dep ON f.departure_id = dep.id "
                + "JOIN airport dest ON f.destination_id = dest.id "
                + "JOIN passenger_ticket pt ON i.id = pt.invoice_id "
                + "JOIN seat s ON pt.id = s.passenger_ticket_id "
                + "WHERE i.id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, invoiceId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BookingHistory booking = new BookingHistory();
                booking.setInvoiceId(resultSet.getInt(1));
                booking.setFlightId(resultSet.getInt(2));
                booking.setAirlineName(resultSet.getString(3));
                booking.setDeparture(resultSet.getString(4));
                booking.setDestination(resultSet.getString(5));
                booking.setTakeOffTime(resultSet.getTime(6));
                booking.setDepartureDate(resultSet.getDate(7));
                booking.setSeatNumber(resultSet.getString(8));
                booking.setTotalPrice(resultSet.getLong(9));
                booking.setPurchaseStatus(resultSet.getInt(10));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

//    public static void main(String[] args) {
//        Invoice invoice = new Invoice();
//        invoice.setUserId(1);
//        invoice.setFlightId(1);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            invoice.setInvoiceDate(dateFormat.parse("2023-03-14"));
//        } catch (ParseException ex) {
//            Logger.getLogger(UserInvoiceManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        invoice.setPurchaseStatus(0);
//        System.out.println(insertReturnInvoiceID(invoice));
//    }
}
