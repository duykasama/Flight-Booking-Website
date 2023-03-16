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
        String sql = "select [id], [name], [email], [phone] from [user] "
                + " where [name] = ? and [password] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                us = new UserSession();

                us.setId(rs.getInt("id"));
                us.setUsername(rs.getString("name"));
                us.setEmail(rs.getString("email"));
                us.setPhone(rs.getString("phone"));
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
        boolean check = false;
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

    public static void signup(String username, String password, String email, String phone) {

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

    public static void updateUser(String email, String phone, String username) {

        String sql = "UPDATE [user]  \n"
                + "SET [email] = ?, [phone] =? \n"
                + "where [name] = ? \n";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, phone);
            ps.setString(3, username);
            ResultSet rs = ps.executeQuery();
            rs.close();          
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }

    }
    
       public static void updateUserPassword(String password, String username) {

        String sql = "UPDATE [user]  \n"
                + "SET [password] = ? \n"
                + "where [name] = ? \n";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, password);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();
            rs.close();          
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }

    }

    public  int searchByName(String username) {
        int userID = -1;
        String sql = "select id from [user] "
                + " where [name] = ? ";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userID = rs.getInt("id");
                 
                }
            }
        } catch (Exception ex) {
            System.out.println("Error finding user by name: " + ex.getMessage());
        }
        return userID;
    }
}
