/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.UserAccessManager;
import com.fptuni.prj301.demo.model.User;
import com.fptuni.prj301.demo.model.UserSession;
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
public class UserAccessController extends HttpServlet {

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

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null || password == null) {
                request.setAttribute("login_msg", "Please Enter Username and Password");
                RequestDispatcher rd = request.getRequestDispatcher("/user_login.jsp");
                rd.forward(request, response);
            } else {
                UserAccessManager manager = new UserAccessManager();
                UserSession us = manager.login(username, password);
                HttpSession ss = request.getSession(true);
                //success
                if (us != null) {
                    ss.setAttribute("usersession", us);
                    ss.setAttribute("username", us.getUsername());
                    response.sendRedirect(request.getContextPath() + "/user_home.jsp");
                } else {
                    //unsuccessful
                    request.setAttribute("login_msg", "Wrong username or password");
                    ss.removeAttribute("usersession");
                    RequestDispatcher rd = request.getRequestDispatcher("/user_login.jsp");
                    rd.forward(request, response);
                }
            }

        } else if (path.equals("/logout")) {
            HttpSession ss = request.getSession();
            ss.removeAttribute("usersession");
            ss.removeAttribute("username");
            request.setAttribute("login-msg", "Log out");
            response.sendRedirect(request.getContextPath() + "/user_home.jsp");
        } else if (path.equals("/signup")) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            if (username == null || password == null) {
                request.setAttribute("signup_msg", "Please Enter Username and Password");
                RequestDispatcher rd = request.getRequestDispatcher("/user_login.jsp");
                rd.forward(request, response);
            } else {

                UserAccessManager manager = new UserAccessManager();
                if (manager.isUserExist(username)) {
                    request.setAttribute("signup_msg", "Username Exists, Signup Fails");
                    RequestDispatcher rd = request.getRequestDispatcher("/user_login.jsp");
                    rd.forward(request, response);
                } else {
                    manager.signup(username, password, email, phone);
                    request.setAttribute("signup_msg", "Signup Succeeds");
                    RequestDispatcher rd = request.getRequestDispatcher("/user_login.jsp");
                    rd.forward(request, response);
                }
            }
        } else if (path.equals("/change")) {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            if (username == null || password == null || email == null || phone == null) {
                response.sendRedirect(request.getContextPath() + "/user_account.jsp");

            }

            UserAccessManager manager = new UserAccessManager();
            manager.updateUser(password, email, phone, username);
            RequestDispatcher rd = request.getRequestDispatcher("/user_home.jsp");
            rd.forward(request, response);
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
