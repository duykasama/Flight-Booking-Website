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
import java.util.ArrayList;

/**
 *
 * @author MSI GF63
 */
public class InvoiceListManager extends ArrayList<Invoice> {

    public InvoiceListManager() {
        loadInvoice();
    }

    private void loadInvoice() {
        String sql = "select i.id, u.id, f.id, i.booking_date, i.total_price, i.purchase_status\n"
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
                invoice.setPurchaseStatus(rs.getInt("status"));
                this.add(invoice);
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
    }
    
}
