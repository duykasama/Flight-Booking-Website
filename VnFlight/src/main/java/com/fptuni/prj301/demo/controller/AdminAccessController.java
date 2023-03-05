/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.AdminAccessManager;
import com.fptuni.prj301.demo.model.AdminSession;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DUNGHUYNH
 */
public class AdminAccessController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();
        System.out.println(path);
        if (path.equals("/login")) {

            String adminname = request.getParameter("adminname");
            String password = request.getParameter("password");

            if (adminname == null || password == null) {
                request.setAttribute("login_msg", "Please Enter Username and Password");
                RequestDispatcher rd = request.getRequestDispatcher("/admin_login.jsp");
                rd.forward(request, response);
            } else {
                AdminAccessManager manager = new AdminAccessManager();
                AdminSession us = manager.login(adminname, password);
                HttpSession ss = request.getSession(true);
                if (us != null) {
                    ss.setAttribute("adminsession", us);
                    response.sendRedirect(request.getContextPath() + "/admin_home.jsp");
                } else {
                    request.setAttribute("login_msg", "Wrong adminname or password");
                    ss.removeAttribute("adminsession");
                    RequestDispatcher rd = request.getRequestDispatcher("/admin_login.jsp");
                    rd.forward(request, response);
                }
            }

        } else if (path.equals("/logout")) {
            HttpSession ss = request.getSession();
            ss.removeAttribute("adminsession");
            request.setAttribute("login-msg", "Log out");
            response.sendRedirect(request.getContextPath() + "/user_home.jsp");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
