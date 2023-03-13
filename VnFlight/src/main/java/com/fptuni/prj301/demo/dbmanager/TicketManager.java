/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class TicketManager {

    public void insertPassengerTicket(int invoice_id, String firstname, String lastname, float luggageWeight,
            long luggagePrice, String cardID, String gender, String nationality, Date dob) {

        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO passenger_ticket (invoice_id, firstname, lastname, luggage_weight, luggage_price, card_id, gender, nationality, dob) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, invoice_id);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setFloat(4, luggageWeight);
            ps.setLong(5, luggagePrice);
            ps.setString(6, cardID);
            ps.setString(7, gender);
            ps.setString(8, nationality);
            ps.setDate(9, new java.sql.Date(dob.getTime()));

            ps.executeUpdate();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int findPassengerID(int invoiceID,String cardID) {
        int passengerID = 0;
        String sql = "SELECT id FROM passenger WHERE invoice_id = ? and card_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, invoiceID);
            stmt.setString(2, cardID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    passengerID = rs.getInt("id");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error finding passenger ID by card ID: " + ex.getMessage());
        }
        return passengerID;
    }
}
