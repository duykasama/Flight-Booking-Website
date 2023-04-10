/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.UserInvoiceManager;
import com.fptuni.prj301.demo.dbmanager.UserTicketManager;
import com.fptuni.prj301.demo.model.Ticket;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class BookingHistoryController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getPathInfo();
        System.out.println(path);
        if (path.equals("/history")) {
            UserInvoiceManager usm = new UserInvoiceManager();
            request.getSession().setAttribute("BookingHistory", usm.getInvoiceHistory((int) request.getSession().getAttribute("userID")));
            response.sendRedirect(request.getContextPath() + "/user_booking_history.jsp");

        } else if (path.equals("/detail")) {
            UserTicketManager tsm = new UserTicketManager();
            UserInvoiceManager ism = new UserInvoiceManager();
            int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
//            PrintWriter out = response.getWriter();
//            out.print(invoiceId);
            request.getSession().setAttribute("invoice", ism.getDetailInvoice(invoiceId));
            request.getSession().setAttribute("flight", tsm.getDetailFlight(invoiceId));
            request.getSession().setAttribute("ticket", (ArrayList<Ticket>) UserTicketManager.getTicketsForInvoice(invoiceId));
            response.sendRedirect(request.getContextPath() + "/user_booking_history_detail.jsp");

        } else if (path.equals("/finish")) {
            UserInvoiceManager usm = new UserInvoiceManager();
            int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
            UserInvoiceManager.updateInvoicePurchaseStatus(invoiceId);
            request.getSession().setAttribute("BookingHistory", usm.getInvoiceHistory((int) request.getSession().getAttribute("userID")));
            response.sendRedirect(request.getContextPath() + "/user_booking_history.jsp");

        } else if (path.equals("/delete")) {
            UserInvoiceManager usm = new UserInvoiceManager();
            int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
            UserInvoiceManager.deleteInvoice(invoiceId);

            request.getSession().setAttribute("BookingHistory", usm.getInvoiceHistory((int) request.getSession().getAttribute("userID")));
            response.sendRedirect(request.getContextPath() + "/user_booking_history.jsp");
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
