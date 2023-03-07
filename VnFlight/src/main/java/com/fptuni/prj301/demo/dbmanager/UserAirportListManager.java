/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.Airport;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI GF63
 */
public class UserAirportListManager extends ArrayList<Airport> {

        public static UserAirportListManager loadAirport() {
        UserAirportListManager a = null;
        String sql = "select id, name \n"
                + "from airport \n";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            a = new UserAirportListManager();
            while (rs.next()) {
                Airport airport = new Airport();
                airport.setId(rs.getInt(1));
                airport.setName(rs.getNString(2));
                a.add(airport);
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(UserAirportListManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
}
