/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.Invoice;
import com.fptuni.prj301.demo.model.Seat;
import com.fptuni.prj301.demo.model.User;
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
public class SeatManager  {

    public List<Seat> loadSeat(int flightID) {
        String sql = "SELECT seat_id, seat_number, passenger_ticket_id, seat_status FROM [seat] WHERE flightID = ?";
       List<Seat> seats = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, flightID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Seat seat = new Seat();
                seat.setSeatId(rs.getInt("seat_id"));
                seat.setSeatNumber(rs.getString("seat_number"));
                seat.setPassengerTicketId(rs.getInt("passenger_ticket_id"));
                seat.setStatus(rs.getInt("seat_status"));
                seats.add(seat);
            }
        } catch (Exception ex) {
            // Handle the exception
        }
        return seats;
    }

    public void AddSeat( String seat_number, int flight_id, int passenger_id, int status) {
        String sql = "insert into [seat] values "
                + " (?, ?, ?, ?, ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, seat_number);
            stm.setInt(2, flight_id);
            stm.setInt(3, passenger_id);
            stm.setInt(4, status);

            ResultSet rs = stm.executeQuery();
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
    }
}
