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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserInvoiceManager {

    public static Invoice createInvoiceTemp(String user_id, String flight_id, String booking_date, String purchase_status) {
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

    public static boolean deleteInvoice(int invoiceId) {
        String sql = "select id from passenger_ticket where invoice_id = ?";
        String sqlDeleteSeat = "delete seat where passenger_ticket_id = ?";
        String sqlDeleteTicket = "delete passenger_ticket where invoice_id = ?";
        String sqlDeleteInvoice = "delete from invoice where id = ?";
        try{
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm1 = conn.prepareStatement(sqlDeleteSeat);
            PreparedStatement stm2 = conn.prepareStatement(sqlDeleteTicket);
            PreparedStatement stm3 = conn.prepareStatement(sqlDeleteInvoice);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, invoiceId);
            stm1.setInt(1, invoiceId);
            stm2.setInt(1, invoiceId);
            stm3.setInt(1, invoiceId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                stm1.setString(1, rs.getString("id"));
                stm1.executeUpdate();
            }
            stm2.executeUpdate();
            int affectedRows = stm3.executeUpdate();
            return (affectedRows == 1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List<Invoice> getInvoiceHistory(int userId) {
        List<Invoice> invoice = new ArrayList<>();
        String sql = " SELECT id, flight_id, booking_date, total_price,purchase_status "
                + " from invoice "
                + " WHERE user_id = ? ";

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

//    public static void autoDeleteNullInvoice(int invoiceId) {
//        String sql = " delete"
//                + " from invoice "
//                + " WHERE id = ? ";
//
//        try (Connection conn = DBUtils.getConnection();
//                PreparedStatement statement = conn.prepareStatement(sql)) {
//            statement.setInt(1, invoiceId);
//            ResultSet rs = statement.executeQuery();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public Invoice getDetailInvoice(int invoiceId) {
        Invoice i = new Invoice();
        String sql = "SELECT id, flight_id, booking_date, total_price,purchase_status "
                + " from invoice "
                + " WHERE id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, invoiceId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                i.setId(rs.getInt(1));
                i.setFlightId(rs.getInt(2));
                i.setBookingDate(rs.getDate(3));
                i.setTotalPrice(rs.getInt(4));
                i.setPurchaseStatus(rs.getInt(5));
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void updateInvoicePurchaseStatus(int invoiceId) {
        String query = " UPDATE [invoice] "
                + " SET purchase_status = 1 "
                + " FROM [invoice]  "
                + " WHERE id = ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, invoiceId);
            ps.executeUpdate();
//            ResultSet rs = ps.executeQuery();
//            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateInvoiceTotalPrice(int invoiceID, float totalprice) {

        String sql = "update [dbo].[invoice] set total_price = ? from [dbo].[invoice] where id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setFloat(1, totalprice);
            ps.setInt(2, invoiceID);
            ps.executeUpdate();
//            ResultSet rs = ps.executeQuery();
//            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
