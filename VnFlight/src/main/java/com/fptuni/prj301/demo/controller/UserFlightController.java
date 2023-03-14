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
import com.fptuni.prj301.demo.dbmanager.UserFlightManager;
import com.fptuni.prj301.demo.dbmanager.UserAirportManager;
import com.fptuni.prj301.demo.dbmanager.UserInvoiceManager;
import com.fptuni.prj301.demo.dbmanager.UserTicketManager;
import com.fptuni.prj301.demo.model.Invoice;
import com.fptuni.prj301.demo.model.Ticket;

/**
 *
 * @author MSI GF63
 */
public class UserFlightController extends HttpServlet {

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
        String path = request.getPathInfo();
        System.out.println(path);
        if (path.equals("/search")) {
            UserAirportManager aManager = new UserAirportManager();
            request.getSession().setAttribute("airportList", aManager.loadAirport());
            response.sendRedirect(request.getContextPath() + "/user_search_flight.jsp");
        } else if (path.equals("/searchResult")) {
            String departure = request.getParameter("departure");
            String destination = request.getParameter("destination");
            String departure_date = request.getParameter("departure_date");
            UserFlightManager fManager = new UserFlightManager();
            request.getSession().setAttribute("flightList", fManager.searchFlight(departure, destination, departure_date));
            response.sendRedirect(request.getContextPath() + "/user_search_flight_result.jsp");
        } else if (path.equals("/createInvoice")) {
            
            String userID = request.getParameter("userID");
            String flightID = request.getParameter("flightID");
            String bookingDate = request.getParameter("bookingDate");
            String purchaseStatus = request.getParameter("purchaseStatus");
            
            UserInvoiceManager iManager = new UserInvoiceManager();
            Invoice iTem = iManager.createInvoiceTemp(userID, flightID, bookingDate, purchaseStatus);
            request.getSession().setAttribute("tempInvoice", iTem);

            response.sendRedirect(request.getContextPath() + "/user_search_flight_detail.jsp");
            
        } else if (path.equals("/save")) {
            
            int invoiceID = UserInvoiceManager.insertReturnInvoiceID((Invoice) request.getSession().getAttribute("tempInvoice"));
            
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String luggageWeight = request.getParameter("luggageWeight");
            String cardID = request.getParameter("cardID");
            String gender = request.getParameter("gender");
            String nationality = request.getParameter("nationality");
            String dob = request.getParameter("dob");
            
            UserTicketManager tManager = new UserTicketManager();
            Ticket tTem = tManager.createTicketTemp(invoiceID, firstname, lastname, luggageWeight, cardID, gender, nationality, dob);
            request.getSession().setAttribute("tempTicket", tTem);
            
            UserTicketManager.insertTicket((Ticket) request.getSession().getAttribute("tempTicket"));
            
            response.sendRedirect(request.getContextPath() + "/user_search_flight_detail.jsp");
        } else if (path.equals("/addToCart")) {
            
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
