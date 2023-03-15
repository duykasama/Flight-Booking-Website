/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.BookingHistory;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class BookingHistoryManager {
     public List<BookingHistory> getBookingHistory(int userId) {
        List<BookingHistory> bookings = new ArrayList<>();
        String sql = "SELECT i.id AS invoice_id, " +
                "f.id AS flight_id, " +
                "f.airline_name AS airline_name, " +
                "dep.name AS departure, " +
                "dest.name AS destination, " +
                "f.takeoff_time, " +
                "f.departure_date, " +
                "s.seat_number, " +
                "i.total_price, " +
                "i.purchase_status " +
                "FROM [user] u "+
                "JOIN invoice i ON u.id = i.user_id " +
                "JOIN flight f ON i.flight_id = f.id " +
                "JOIN airport dep ON f.departure_id = dep.id " +
                "JOIN airport dest ON f.destination_id = dest.id " +
                "JOIN passenger_ticket pt ON i.id = pt.invoice_id " +
                "JOIN seat s ON pt.id = s.passenger_ticket_id " +
                "WHERE u.id = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
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
                booking.setTotalPrice( resultSet.getLong(9));
                booking.setPurchaseStatus(resultSet.getInt(10));
                bookings.add(booking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
