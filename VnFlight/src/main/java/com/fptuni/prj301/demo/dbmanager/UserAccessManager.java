package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.UserSession;
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
public class UserAccessManager {

    public static UserSession login(String username, String password) {
        UserSession us = null;
        String sql = "select [name] from [user] "
                + " where [name] = ? and [password] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                us = new UserSession();

                us.setUsername(rs.getString("name"));
                us.setLoginDate(new Date());
                us.setAccessRight("User");

            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return us;
    }

    public static boolean isUserExist(String username) {
        boolean check =false;
        String sql = "select [name] from [user] "
                + " where [name] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
                check = rs.next();
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return check;
    }

    public static void  signup(String username, String password, String email, String phone) {

            String sql = "insert into [user] values "
                    + " (?, ?, ?, ?)";
            try {
                Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.setString(4, phone);

                ResultSet rs = ps.executeQuery();
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Query Student error!" + ex.getMessage());
            }

        
        
    }

}
