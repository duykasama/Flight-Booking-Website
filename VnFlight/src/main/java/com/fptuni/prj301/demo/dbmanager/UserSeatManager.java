/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.Seat;
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
public class UserSeatManager extends ArrayList<Seat> {

    public UserSeatManager loadSeat(int flightID) {
        String sql = "SELECT seat_id, seat_number, passenger_ticket_id, seat_status FROM [seat] WHERE flightID = ?";
        UserSeatManager seats = new UserSeatManager();
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

    public void AddSeat(String seat_number, String flight_id, int ticket_id) {
        String sql = "insert into [seat] values "
                + " (?, ?, ?,1)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, seat_number);
            stm.setString(2, flight_id);
            stm.setInt(3, ticket_id);
            stm.executeUpdate();

            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
    }

    public boolean checkSeat(String seatNumber, String flightID) {
        String sql = "select * from seat where seat_number = ? and flight_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, seatNumber);
            ps.setString(2, flightID);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
        }
        return false;
    }

    public List<String> seatNumberList(String flightid) {
        String sql = "select seat_number from seat where flight_id = ?";
        List<String> list = new ArrayList();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, flightid);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String numberSeat = rs.getString("seat_number");
                list.add(numberSeat);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
//        System.out.println(new UserSeatManager().AddSeat("1a", 6, 14));
//        System.out.println(new UserSeatManager().AddSeat("1D", "7", 0));
//        System.out.println(new UserSeatManager().checkSeat("1C", "7"));
//        System.out.println(new UserSeatManager().checkSeat("1d", "1"));
        System.out.println(new UserSeatManager().seatNumberList("6"));
        System.out.println(new UserSeatManager().seatNumberList("6").contains("2G"));
    }
}
