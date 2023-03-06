/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;


import com.fptuni.prj301.demo.model.Flight;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fptuni.prj301.demo.utils.DBUtils;

/**
 *
 * @author MSI GF63
 */
public class AdminFlightListManager extends ArrayList<Flight> {

    public AdminFlightListManager() {
        loadFlight();
    }

    private void loadFlight() {
        String sql = "select fl.id, fl.takeoff_time, fl.landing_time, fl.departure_date, fl.price, fl.airline_name, fl.no_of_seats, ap1.airport_name as 'departure', ap2.airport_name as 'destination', fl.status \n"
                + "from flight fl join airport ap1 on fl.departure_id = ap1.id "
                + "join airport ap2 on fl.destination_id = ap2.id";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setTakeOffTime(rs.getTime("takeoff_time"));
                flight.setLandingTime(rs.getTime("landing_time"));
                flight.setDepartureDate(rs.getDate("departure_date"));
                flight.setPrice(rs.getInt("price"));
                flight.setAirlineName(rs.getString("airline_name"));
                flight.setNoOfSeats(rs.getInt("no_of_seats"));
                flight.setDeparture(rs.getNString("departure"));
                flight.setDestination(rs.getNString("destination"));
                flight.setStatus(rs.getInt("status"));
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
