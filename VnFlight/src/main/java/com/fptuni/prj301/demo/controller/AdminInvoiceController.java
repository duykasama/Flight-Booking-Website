/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.AdminInvoiceManager;
import com.fptuni.prj301.demo.model.Invoice;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MSI GF63
 */
public class AdminInvoiceController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        out.print(action);
        request.getSession().setAttribute("totalPassengers", new AdminInvoiceManager().getTotalPassengers());
        request.getSession().setAttribute("totalFlights", new AdminInvoiceManager().getTotalFlights());
        if (action.equalsIgnoreCase("view")) {
            request.getSession().setAttribute("iList", new AdminInvoiceManager());
//            request.getSession().setAttribute("totalPassengers", new AdminInvoiceManager().getTotalPassengers());
            response.sendRedirect("admin_invoice.jsp");
        } else if (action.equalsIgnoreCase("sort")) {
            request.getSession().removeAttribute("iList");

            String cate = request.getParameter("cate");
            String value = request.getParameter("value");
            request.getSession().setAttribute("iList", new AdminInvoiceManager().sortInvoice(cate, value));
//            request.getSession().setAttribute("totalPassengers", new AdminInvoiceManager().getTotalPassengers());

            out.print(cate);
            out.print(value);
            response.sendRedirect("admin_invoice.jsp");
        } else if (action.equalsIgnoreCase("delete")) {
            String invoiceId = request.getParameter("invoiceId");
            new AdminInvoiceManager().deleteInvoice(invoiceId);
            request.getSession().setAttribute("iList", new AdminInvoiceManager());
//            request.getSession().setAttribute("totalPassengers", new AdminInvoiceManager().getTotalPassengers());
            response.sendRedirect("admin_invoice.jsp");
        }else if(action.equalsIgnoreCase("viewByTime")){
            String from = request.getParameter("since");
            String to = request.getParameter("to");
//            response.getWriter().print(from + " " + to);
            request.getSession().setAttribute("totalFlights", new AdminInvoiceManager().getTotalFlights(from, to));
            request.getSession().setAttribute("totalPassengers", new AdminInvoiceManager().getTotalPassengers(from, to));
            request.getSession().setAttribute("iList", new AdminInvoiceManager().getInvoiceInterval(from, to));
            response.sendRedirect("admin_invoice.jsp");
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
