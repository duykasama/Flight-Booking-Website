/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.Invoice;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

//    public static void main(String[] args) {
//        Invoice invoice = new Invoice();
//        invoice.setUserId(1);
//        invoice.setFlightId(1);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            invoice.setBookingDate(dateFormat.parse("2023-03-14"));
//        } catch (ParseException ex) {
//            Logger.getLogger(UserInvoiceManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        invoice.setPurchaseStatus(0);
//        System.out.println(insertReturnInvoiceID(invoice));
//    }

}
