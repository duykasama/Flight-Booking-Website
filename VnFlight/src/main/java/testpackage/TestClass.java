/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import com.fptuni.prj301.demo.dbmanager.FlightManager;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI GF63
 */
public class TestClass {
    public static void main(String[] args) {
//        try {
//            Connection conn = DBUtils.getConnection();
//            System.out.println("Connection succeed");
//        } catch (Exception ex) {
//            System.out.println("Connection failed");
//        }
        
//        FlightManger fM = new FlightManger();
//        System.out.println(fM.size());
        System.out.println("what's your name? ");
        System.out.println("my name is: " + new Scanner(System.in).nextLine());
    }
}
