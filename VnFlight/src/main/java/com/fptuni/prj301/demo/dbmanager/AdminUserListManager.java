/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.User;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author MSI GF63
 */
public class AdminUserListManager extends ArrayList<User> {

    public AdminUserListManager() {
        loadUser();
    }

    private void loadUser() {
        String sql = "select id,name, email, phone from [user]";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                this.add(user);
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
    }

    public boolean deleteUser(String userId) {
        String sqlSetNull = "update invoice set [user_id] = null where [user_id] = ?";
        String sqlDelete = "delete from [user] where id = ?";
        try{
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sqlSetNull);
            stm.setString(1, userId);
            stm.executeUpdate();
            stm = conn.prepareStatement(sqlDelete);
            stm.setString(1, userId);
            return (stm.executeUpdate() > 0);
        } catch (Exception ex) {
        }
        return false;
    }
    
    public User loadUser(String id){
        User user = null;
        String sql = "select id,name, email, phone from [user] where id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
        return user;
    }
    
    public boolean updateUser(String userId, String username, String phone, String email) {
        String sql = "update [user] set name = ?, email = ?, phone = ? where id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, email);
            stm.setString(3, phone);
            stm.setString(4, userId);
            return (stm.executeUpdate() > 0);
        } catch (Exception ex) {
        }
        return false;
    }
}
