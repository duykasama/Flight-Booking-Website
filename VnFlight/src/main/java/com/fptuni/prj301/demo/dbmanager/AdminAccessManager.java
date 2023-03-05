package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.AdminSession;
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
    
    public static AdminSession login (String adminname, String password){
        if (adminname.equals("dung")&&password.equals("dung")){
            AdminSession us =  new AdminSession();
            us.setAdminname(adminname);
            us.setLoginDate(new Date());
            
            us.setAccessRight("Admin");
            return us;
        }
        return null;
    }
    
}