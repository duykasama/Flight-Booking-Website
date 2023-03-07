package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.AdminSession;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DUNGHUYNH
 */
public class AdminAccessManager {

    public static AdminSession login(String adminname, String password) {

        AdminSession as = null;
        String sql = "select [name] from [admin] "
                + " where [name] = ? and [password] = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, adminname);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                as = new AdminSession();

                as.setAdminname(rs.getString("name"));
                as.setLoginDate(new Date());
                as.setAccessRight("User");

            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return as;
    }
}
