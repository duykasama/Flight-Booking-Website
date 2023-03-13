/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.InvoiceListManager;
import com.fptuni.prj301.demo.dbmanager.SeatManager;
import com.fptuni.prj301.demo.dbmanager.TicketManager;
import com.fptuni.prj301.demo.model.Seat;
import com.fptuni.prj301.demo.model.UserSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "SeatController", urlPatterns = {"/SeatController"})
public class SeatController extends HttpServlet {

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
        String action = request.getParameter("action");
        System.out.println(action);
        if (action.equals("/add")) {

            String seatNumber = request.getParameter("seatName");
            String flightIDString = request.getParameter("flightID");
            int flightID = Integer.parseInt(flightIDString);
            HttpSession session = request.getSession();
            int userID = (int) session.getAttribute("userID");

            String invoiceIDString = (String) session.getAttribute("invoiceID");
            int invoiceID = Integer.parseInt(invoiceIDString);
            SeatManager manager = new SeatManager();
            List<Seat> seats = manager.loadSeat(flightID);
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String luggageWeightString = request.getParameter("luggageWeight");
            float luggageWeight = Float.parseFloat(luggageWeightString);
            String luggagePriceString = request.getParameter("luggagePrice");
            long luggagePrice = Long.parseLong(luggagePriceString);
            String cardID = request.getParameter("cardID");
            String gender = request.getParameter("gender");
            String nationality = request.getParameter("nationality");
            String dobString = request.getParameter("dob");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = null;
            try {
                dob = sdf.parse(dobString);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            session.setAttribute("date", dob);
            
            //add new invoice
            InvoiceListManager iManager = new InvoiceListManager();
            if (session.getAttribute("invoiceID") == null) {
                iManager.insertInvoice(userID, flightID, dob);
                session.setAttribute("invoiceID", iManager.search(userID, flightID));
            }

            boolean seatFound = false;
            for (Seat seat : seats) {
                if (seat.getSeatNumber().equals(seatNumber)) {
                    seatFound = true;
                    break;
                }
            }

            if (seatFound) {
                // Seat is already taken, show error message
                request.setAttribute("booking_msg", "Seat " + seatNumber + " is already taken");
                RequestDispatcher rd = request.getRequestDispatcher("/user_search_flight_detail.jsp");
                rd.forward(request, response);
            } else {
                //total price
                long totalPrice = (long) session.getAttribute("totalPrice");
                totalPrice = luggagePrice+ totalPrice +200;
                session.setAttribute("totalPrice", totalPrice );
                //check passenger cardID 
                String sessionCardID = (String) session.getAttribute("cardID");
                if (sessionCardID == null || !sessionCardID.equals(cardID)) {
                    request.setAttribute("cardID_error", "Card ID must match the one used for booking");
                    RequestDispatcher rd = request.getRequestDispatcher("/user_search_flight_detail.jsp");
                    rd.forward(request, response);
                    return;
                } else {
                    session.setAttribute("cardID", cardID);
                }
                // add new passenger ticket
                TicketManager tmanager = new TicketManager();
                tmanager.insertPassengerTicket(invoiceID, firstname, lastname, luggageWeight, luggagePrice, cardID, gender, nationality, dob);
                int passengerID = tmanager.findPassengerID(invoiceID, cardID);
                 
                //check seat for mutilple booking off the same user
                List<Seat> bookedSeats = (List<Seat>) session.getAttribute("bookedSeats");
                if (bookedSeats == null) {
                    bookedSeats = new ArrayList<>();
                }
                for (Seat seat : bookedSeats) {
                    if (seat.getSeatNumber().equals(seatNumber)) {
                        request.setAttribute("booking_msg", "Seat " + seatNumber + " is already booked");
                        RequestDispatcher rd = request.getRequestDispatcher("/booking.jsp");
                        rd.forward(request, response);
                        return;
                    }
                }
                //add new seat
                manager.AddSeat(seatNumber, flightID, passengerID, 1);
                Seat bookedSeat = new Seat();
                bookedSeat.setSeatNumber(seatNumber);
                bookedSeats.add(bookedSeat);
                session.setAttribute("bookedSeats", bookedSeats);
                RequestDispatcher rd = request.getRequestDispatcher("/user_search_flight_detail.jsp");
                rd.forward(request, response);
            }

        } else if (action.equals("/submit")) {

            HttpSession ss = request.getSession();
            InvoiceListManager iManager = new InvoiceListManager();
            int id = (int) ss.getAttribute("invoiceID");
            Date bookingDate=(Date) ss.getAttribute("date");
            int totalPrice = (int) ss.getAttribute("totalPrice");
            iManager.updateInvoice(id, bookingDate, totalPrice, 1);
            ss.invalidate();
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
