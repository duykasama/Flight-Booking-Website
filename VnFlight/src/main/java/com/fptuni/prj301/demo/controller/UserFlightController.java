/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.UserAirportManager;
import com.fptuni.prj301.demo.dbmanager.UserFlightManager;
import com.fptuni.prj301.demo.dbmanager.UserInvoiceManager;
import com.fptuni.prj301.demo.dbmanager.UserSeatManager;
import com.fptuni.prj301.demo.dbmanager.UserTicketManager;
import com.fptuni.prj301.demo.model.Invoice;
import com.fptuni.prj301.demo.model.Ticket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        PrintWriter out = response.getWriter();
        if (path.equals("/search")) {
            UserAirportManager aManager = new UserAirportManager();
            request.getSession().setAttribute("airportList", aManager.loadAirport());
            response.sendRedirect(request.getContextPath() + "/user_search_flight.jsp");
        } else if (path.equals("/searchResult")) {
            String departure = request.getParameter("departure");
            String destination = request.getParameter("destination");
            String departure_date = request.getParameter("departure_date");
            request.getSession().setAttribute("flightList", UserFlightManager.searchFlight(departure, destination, departure_date));
            response.sendRedirect(request.getContextPath() + "/user_search_flight_result.jsp");
        } else if (path.equals("/createInvoice")) {
            String userID = request.getParameter("userID");
            String flightID = request.getParameter("flightID");
            String bookingDate = request.getParameter("bookingDate");
            String flightPrice = request.getParameter("flightPrice");
            String purchaseStatus = request.getParameter("purchaseStatus");
            Invoice iTemp = UserInvoiceManager.createInvoiceTemp(userID, flightID, bookingDate, purchaseStatus);

            request.getSession().setAttribute("invoice", iTemp);
//            request.getSession().setAttribute("invoiceId", UserInvoiceManager.insertReturnInvoiceID(iTemp));

            request.getSession().setAttribute("tempTicketList", new ArrayList<>());
            request.getSession().setAttribute("quantityOfSeats", UserFlightManager.getQuantityOfSeats(Integer.parseInt(flightID)));
            request.getSession().setAttribute("choosenflightID", flightID);
            request.getSession().setAttribute("flightPrice", flightPrice);
            response.sendRedirect(request.getContextPath() + "/user_search_flight_detail.jsp");

        } else if (path.equals("/save")) {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String luggageWeight = request.getParameter("luggageWeight");
            String cardID = request.getParameter("cardId");
            String gender = request.getParameter("gender");
            String nationality = request.getParameter("nationality");
            String dob = request.getParameter("dob");
            String seatNumber = request.getParameter("seatNumber");

            String flightID = (String) request.getSession().getAttribute("choosenflightID");
            UserSeatManager usManager = new UserSeatManager();
            if (usManager.checkSeat(seatNumber, flightID) == false) {
                ArrayList<Ticket> tListTemp = (ArrayList<Ticket>) request.getSession().getAttribute("tempTicketList");
                tListTemp.add(new Ticket(firstname, lastname, luggageWeight, cardID, gender, nationality, dob, seatNumber));
                request.getSession().setAttribute("tempTicketList", tListTemp);
                request.setAttribute("ticket_msg", "Ticket Saved");

                RequestDispatcher rd = request.getRequestDispatcher("/user_search_flight_detail.jsp");
                rd.forward(request, response);

            } else {
                request.setAttribute("ticket_msg", "Ticket is has currently been booked");
                RequestDispatcher rd = request.getRequestDispatcher("/user_search_flight_detail.jsp");
                rd.forward(request, response);
            }

        } else if (path.equals("/addToCart")) {
            int invoiceId = UserInvoiceManager.insertReturnInvoiceID((Invoice) request.getSession().getAttribute("invoice"));
            ArrayList<Ticket> tList = (ArrayList<Ticket>) request.getSession().getAttribute("tempTicketList");
            for (Ticket ticket : tList) {
                ticket.setInvoiceId(invoiceId);
            }
            UserTicketManager.insertTicketList(tList);
            response.sendRedirect(request.getContextPath() + "/BookingHistoryController/history");
        } else if (path.equals("/purchase")) {
            int invoiceId = UserInvoiceManager.insertReturnInvoiceID((Invoice) request.getSession().getAttribute("invoice"));
            ArrayList<Ticket> tList = (ArrayList<Ticket>) request.getSession().getAttribute("tempTicketList");
            for (Ticket ticket : tList) {
                ticket.setInvoiceId(invoiceId);
            }
            String flightID = (String) request.getSession().getAttribute("choosenflightID");
            // ...............
            UserTicketManager.insertTicketList(tList);
            ArrayList<Ticket> ticketIDList = (ArrayList<Ticket>) UserTicketManager.getTicketID(invoiceId);
            float flightPrice = Float.parseFloat((String) request.getSession().getAttribute("flightPrice"));
            float total_price = flightPrice;
            for (Ticket x : ticketIDList) {
                float luggage_price = 0;
                out.print("ticket id: " + x.getId());
                out.print("seat_number: " + x.getSeatNumber());
                out.print("luggage_weight: " + x.getLuggageWeight());
                if (x.getLuggageWeight() == 15) {
                    luggage_price = 200000;
                } else if (x.getLuggageWeight() == 25) {
                    luggage_price = 300000;

                }
                total_price += luggage_price;
                new UserSeatManager().AddSeat(x.getSeatNumber(), flightID, x.getId());
            }
            UserInvoiceManager.updateInvoiceTotalPrice(invoiceId, total_price);
            UserInvoiceManager.updateInvoicePurchaseStatus(invoiceId);
//            UserSeatManager usManager = new UserSeatManager();
//
//            for (Ticket ticket : newTicketList) {
//                out.println(ticket.getCardId() + "<br>");
//                out.println(ticket.getDob() + "<br>");
//                out.println(ticket.getFirstName() + "<br>");
//                out.println(ticket.getGender() + "<br>");
//                out.println(ticket.getId() + "<br>");
//                out.println(ticket.getInvoiceId() + "<br>");
//                out.println(ticket.getLastName() + "<br>");
//                out.println(ticket.getLuggageWeight() + "<br>");
//                out.println(ticket.getNationality() + "<br>");
//                out.println(ticket.getSeatNumber() + "<br>");
//
////
//            }
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
