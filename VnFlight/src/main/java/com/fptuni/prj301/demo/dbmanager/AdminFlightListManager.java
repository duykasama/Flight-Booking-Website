/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.Flight;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI GF63
 */
public class AdminFlightListManager extends ArrayList<Flight> {

    public AdminFlightListManager() {
        loadFlight();
    }

    public boolean addFlight(String takeOffTime, String landingTime, String depDate, String price, String airlineName, String numOfSeat, String depID, String desID) {
        String sql = "insert into flight values (Parse( ? as Time),Parse( ? as Time),Parse( ? as Date),?,?,?,?,?,0)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, takeOffTime);
            ps.setString(2, landingTime);
            ps.setString(3, depDate);
            ps.setString(4, price);
            ps.setString(5, airlineName);
            ps.setString(6, numOfSeat);
            ps.setString(7, depID);
            ps.setString(8, desID);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private void loadFlight() {
        String sql = "select fl.id, fl.takeoff_time, fl.landing_time, fl.departure_date, fl.price, fl.airline_name, fl.no_of_seats, ap1.name as 'departure', ap2.name as 'destination', fl.status \n"
                + "from flight fl join airport ap1 on fl.departure_id = ap1.id "
                + "join airport ap2 on fl.destination_id = ap2.id";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt(1));
                flight.setTakeOffTime(rs.getTime(2));
                flight.setLandingTime(rs.getTime(3));
                flight.setDepartureDate(rs.getDate(4));
                flight.setPrice(rs.getInt(5));
                flight.setAirlineName(rs.getString(6));
                flight.setNoOfSeats(rs.getInt(7));
                flight.setDeparture(rs.getNString(8));
                flight.setDestination(rs.getNString(9));
                flight.setStatus(rs.getInt(10));
                this.add(flight);
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AdminFlightListManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
