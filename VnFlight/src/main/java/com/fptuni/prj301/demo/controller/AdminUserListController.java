/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fptuni.prj301.demo.dbmanager.AdminFlightManager;
import com.fptuni.prj301.demo.dbmanager.UserAccessManager;
import com.fptuni.prj301.demo.dbmanager.AdminUserListManager;
import com.fptuni.prj301.demo.model.User;
import com.fptuni.prj301.demo.model.UserSession;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MSI GF63
 */
public class AdminUserListController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if(action == null){
            action = "view";
        }
        if(action.equalsIgnoreCase("view")){
            if (request.getSession().getAttribute("uList") == null) {
                request.getSession().setAttribute("uList", new AdminUserListManager());
            }
            response.sendRedirect("admin_user_list.jsp");
        }else if(action.equalsIgnoreCase("delete")){
            String userId = request.getParameter("userId");
            new AdminUserListManager().deleteUser(userId);
            request.getSession().setAttribute("uList", new AdminUserListManager());
            response.sendRedirect("admin_user_list.jsp");
        }else if(action.equalsIgnoreCase("edit")){
            String userId = request.getParameter("userId");
            User user = new AdminUserListManager().loadUser(userId);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("edit_user_form.jsp");
        }else if(action.equalsIgnoreCase("confirmEdit")){

            String userId = request.getParameter("userId");
            String username = request.getParameter("username");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            new AdminUserListManager().updateUser(userId, username, phone, email);
            request.getSession().setAttribute("uList", new AdminUserListManager());
//            request.getRequestDispatcher("AdminFlightController?action=view").forward(request, response);
            response.sendRedirect("admin_user_list.jsp");
        }else if(action.equalsIgnoreCase("cancelEdit")){
            request.getSession().removeAttribute("user");
            response.sendRedirect("admin_user_list.jsp");
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
