/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;


import com.fptuni.prj301.demo.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.fptuni.prj301.demo.utils.DBUtils;

/**
 *
 * @author MSI GF63
 */
public class AdminUserListManager extends ArrayList<User>{

    public AdminUserListManager() {
        loadUser();
    }
    
    private void loadUser(){
        String sql = "select id, username, email, phone from [user]";
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
    
}
