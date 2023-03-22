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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author MSI GF63
 */
public class AdminInvoiceManager extends ArrayList<Invoice> {

    public AdminInvoiceManager() {
        loadInvoice();
    }

    private void loadInvoice() {
        String sql = "select i.id, u.id as \'user_id\', f.id as \'flight_id\', i.booking_date, i.total_price, i.purchase_status\n"
                + "from invoice i join [user] u on i.[user_id] = u.id join flight f on i.flight_id = f.id";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setUserId(rs.getInt("user_id"));
                invoice.setFlightId(rs.getInt("flight_id"));
                invoice.setBookingDate(rs.getDate("booking_date"));
                invoice.setTotalPrice(rs.getInt("total_price"));
                invoice.setPurchaseStatus(rs.getInt("purchase_status"));
                this.add(invoice);
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
    }

    public String getRevenue() {
//        String sql = "select sum(total_price) as 'revenue' from invoice";
//        int revenue = 0;
//        try {
//            Connection conn = DBUtils.getConnection();
//            PreparedStatement stm = conn.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                revenue = rs.getInt("revenue");
//            }
//            rs.close();
//            stm.close();
//            conn.close();
//        } catch (Exception ex) {
//        }
        int revenue = 0;
        for (Invoice i : this) {
            if(i.getPurchaseStatus() == 1){
                revenue += i.getTotalPrice();
            }
        }
        String output = NumberFormat.getCurrencyInstance(new Locale("jp", "JP")).format(revenue);
        return output.substring(4) + " vnd";
    }

    public int getTotalFlights() {
        int quantity = 0;
        String sql = "select count(id) as 'quantity' from flight";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt("quantity");
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
        return quantity;
    }
    
    public Object getTotalFlights(String from, String to) {
        int quantity = 0;
        String sql = "select count(id) as 'quantity' from flight where departure_date between parse(? as date) and parse(? as date)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, from);
            stm.setString(2, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt("quantity");
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
        return quantity;
    }

    public void insertInvoice(int userId, int flightId, Date bookingDate) {
        String sql = "INSERT INTO invoice(user_id, flight_id, booking_date) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, flightId);
            ps.setDate(3, new java.sql.Date(bookingDate.getTime()));

            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Insert invoice error: " + ex.getMessage());
        }
    }

    public int search(int userId, int flightId) {
        int invoiceId = -1;
        String sql = "SELECT id FROM invoice WHERE user_id=? AND flight_id=?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, flightId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    invoiceId = rs.getInt("id");

                }
            }
        } catch (Exception ex) {
            System.out.println("Error finding user by name: " + ex.getMessage());
        }
        return invoiceId;
    }

    public void updateInvoice(int id, Date bookingDate, long totalPrice, int purchaseStatus) {
        String sql = "UPDATE invoice SET booking_date=?, total_price=?, purchase_status=? WHERE id=?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(bookingDate.getTime()));
            ps.setLong(2, totalPrice);
            ps.setInt(3, purchaseStatus);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List sortInvoice(String cate, String value) {
        this.clear();
        String sql = "select i.id, u.id as \'user_id\', f.id as \'flight_id\', i.booking_date, i.total_price, i.purchase_status\n"
                + "from invoice i join [user] u on i.[user_id] = u.id join flight f on i.flight_id = f.id ";
        String orderBySql = "";
        switch (cate) {
            case "bookingDate":
                orderBySql = "order by i.booking_date " + value;
                break;
            case "amount":
                orderBySql = "order by i.total_price " + value;
                break;
        }
        sql += orderBySql;
        System.out.println(sql);
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setUserId(rs.getInt("user_id"));
                invoice.setFlightId(rs.getInt("flight_id"));
                invoice.setBookingDate(rs.getDate("booking_date"));
                invoice.setTotalPrice(rs.getInt("total_price"));
                invoice.setPurchaseStatus(rs.getInt("purchase_status"));
                this.add(invoice);
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
        return this;
    }

    public boolean deleteInvoice(String invoiceId) {
//        String sqlSetNull = "update passenger_ticket set invoice_id = null where invoice_id = ?";
//        String sqlDelete = "delete from invoice where id = ?";
//        try{
//            Connection conn = DBUtils.getConnection();
//            PreparedStatement stm = conn.prepareStatement(sqlSetNull);
//            stm.setString(1, invoiceId);
//            stm.executeUpdate();
//            stm = conn.prepareStatement(sqlDelete);
//            stm.setString(1, invoiceId);
//            return (stm.executeUpdate() > 0);
//        } catch (Exception ex) {
//        }
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
            stm.setString(1, invoiceId);
            stm1.setString(1, invoiceId);
            stm2.setString(1, invoiceId);
            stm3.setString(1, invoiceId);
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
    
    public int getTotalPassengers(){
        int quantity = 0;
        String sql = "select count(id) as 'quantity' from passenger_ticket";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt("quantity");
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
        return quantity;
    }
    
    public int getTotalPassengers(String since, String to){
        int quantity = 0;
        String sql = "select count(p.id) as 'quantity' from invoice i join passenger_ticket p on i.id = p.invoice_id where i.booking_date between parse( ? as date ) and parse( ? as date )";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, since);
            stm.setString(2, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt("quantity");
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
        return quantity;
    }

    public ArrayList<Invoice> getInvoiceInterval(String from, String to) {
        this.clear();
        String sql = "select i.id, u.id as \'user_id\', f.id as \'flight_id\', i.booking_date, i.total_price, i.purchase_status\n"
                + "from invoice i join [user] u on i.[user_id] = u.id join flight f on i.flight_id = f.id "
                + "where booking_date between parse(? as date) and parse(? as date)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, from);
            stm.setString(2, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setUserId(rs.getInt("user_id"));
                invoice.setFlightId(rs.getInt("flight_id"));
                invoice.setBookingDate(rs.getDate("booking_date"));
                invoice.setTotalPrice(rs.getInt("total_price"));
                invoice.setPurchaseStatus(rs.getInt("purchase_status"));
                this.add(invoice);
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
        return this;
    }
    
    public static void main(String[] args) {
        System.out.println(new AdminInvoiceManager().deleteInvoice("4"));;
    }

}
