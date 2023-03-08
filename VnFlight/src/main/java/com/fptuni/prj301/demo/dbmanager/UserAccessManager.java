package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.User;
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

    public static void updateUser(String password, String email, String phone, String username) {

        String sql = "UPDATE [user]  \n"
                + "SET [password] = ?, [email] = ?, [phone] =? \n"
                + "where [name] = ? \n";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, password);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, username);
            ResultSet rs = ps.executeQuery();
            rs.close();          
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }

    }

//    public static User searchByName(String username) {
//        User user = null;
//        String sql = "select * from [user] "
//                + " where [name] ";
//        try (Connection conn = DBUtils.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, username);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    user = new User();
//                    user.setUserName(rs.getString("username"));
//                    user.setEmail(rs.getString("email"));
//                    user.setPhone(rs.getString("phone"));
//                    // add more fields as needed
//                }
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error finding user by name: " + ex.getMessage());
//        }
//        return user;
//    }
}
