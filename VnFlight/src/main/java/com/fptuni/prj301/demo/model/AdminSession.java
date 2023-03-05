/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.prj301.demo.model;

import java.util.Date;

/**
 *
 * @author DUNGHUYNH
 */
public class AdminSession {
    
    private String adminname;
    private String accessRight;

    public Date getLoginDate() {
        return loginDate;
    }

    public String getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(String accessRight) {
        this.accessRight = accessRight;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    private Date loginDate;

    /**
     * Get the value of adminname
     *
     * @return the value of adminname
     */
    public String getAdminname() {
        return adminname;
    }

    /**
     * Set the value of adminname
     *
     * @param adminname new value of adminname
     */
    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

}
