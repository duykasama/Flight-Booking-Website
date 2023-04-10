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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
        ServletContext context = getServletContext();
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

            // F001,1A
            // purchase/add to cart -> list.remove("F001,1A")
            // save -> add list
            // save -> list.contain("flightID"+"seatnumber")
            // -> list.add("flightID"+"seatnumber")
            // bookinh history -> dele list.remove("F001,1A")
            // admin dele -> list.remove("F001,1A")
            request.getSession().setAttribute("tempTicketList", new ArrayList<>());
            request.getSession().setAttribute("quantityOfSeats", UserFlightManager.getQuantityOfSeats(Integer.parseInt(flightID)));
            request.getSession().setAttribute("choosenflightID", flightID);
            request.getSession().setAttribute("flightPrice", flightPrice);
            request.getSession().setAttribute("seatNumberList", new UserSeatManager().seatNumberList(flightID));
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

            List list;
            if (context.getAttribute("flightID_seatNumberList") == null) {
                list = new ArrayList<String>();
            } else {
                list = (List) context.getAttribute("flightID_seatNumberList");
            }
            // check vong ngoai
            if (list.contains(flightID + "," + seatNumber)) {
                request.setAttribute("ticket_msg", "Seat has currently been booked");
                RequestDispatcher rd = request.getRequestDispatcher("/user_search_flight_detail.jsp");
                rd.forward(request, response);
            } else {

                ArrayList<Ticket> tListTemp = (ArrayList<Ticket>) request.getSession().getAttribute("tempTicketList");
                boolean doubleCheck = false;
                // check chung 1 invoice
                for (Ticket x : tListTemp) {
                    if (x.getSeatNumber().equalsIgnoreCase(seatNumber)) {
                        doubleCheck = true;
                    }
                }
                if (usManager.checkSeat(seatNumber, flightID) == false && !doubleCheck) {

                    tListTemp.add(new Ticket(firstname, lastname, luggageWeight, cardID, gender, nationality, dob, seatNumber));
                    request.getSession().setAttribute("tempTicketList", tListTemp);
                    request.setAttribute("ticket_msg", "Ticket Saved");
                    list.add(flightID + "," + seatNumber);
                    context.setAttribute("flightID_seatNumberList", list);
                    RequestDispatcher rd = request.getRequestDispatcher("/user_search_flight_detail.jsp");
                    rd.forward(request, response);

                } else {
                    request.setAttribute("ticket_msg", "Seat has currently been booked");
                    RequestDispatcher rd = request.getRequestDispatcher("/user_search_flight_detail.jsp");
                    rd.forward(request, response);
                }
            }

        } else if (path.equals("/addToCart")) {
            int invoiceId = UserInvoiceManager.insertReturnInvoiceID((Invoice) request.getSession().getAttribute("invoice"));
            ArrayList<Ticket> tList = (ArrayList<Ticket>) request.getSession().getAttribute("tempTicketList");
            for (Ticket ticket : tList) {
                ticket.setInvoiceId(invoiceId);
            }
            String flightID = (String) request.getSession().getAttribute("choosenflightID");
            List list = (List) context.getAttribute("flightID_seatNumberList");
            // ...............
            UserTicketManager.insertTicketList(tList);
            ArrayList<Ticket> ticketIDList = (ArrayList<Ticket>) UserTicketManager.getTicketID(invoiceId);
            float flightPrice = Float.parseFloat((String) request.getSession().getAttribute("flightPrice"));
            float total_price = 0;
            for (Ticket x : ticketIDList) {
                float luggage_price = 0;
//                out.print("ticket id: " + x.getId());
//                out.print("seat_number: " + x.getSeatNumber());
//                out.print("luggage_weight: " + x.getLuggageWeight());
                if (x.getLuggageWeight() == 15) {
                    luggage_price = 200000;
                } else if (x.getLuggageWeight() == 25) {
                    luggage_price = 300000;

                }
                total_price += luggage_price + flightPrice;
                new UserSeatManager().AddSeat(x.getSeatNumber(), flightID, x.getId());
                list.remove(flightID + "," + x.getSeatNumber());
            }
            context.setAttribute("flightID_seatNumberList", list);
            UserInvoiceManager.updateInvoiceTotalPrice(invoiceId, total_price);
            response.sendRedirect(request.getContextPath() + "/BookingHistoryController/history");
        } else if (path.equals("/purchase")) {
            int invoiceId = UserInvoiceManager.insertReturnInvoiceID((Invoice) request.getSession().getAttribute("invoice"));
            ArrayList<Ticket> tList = (ArrayList<Ticket>) request.getSession().getAttribute("tempTicketList");
            String flightID = (String) request.getSession().getAttribute("choosenflightID");

            List<String> seatNumberList = new UserSeatManager().seatNumberList(flightID);
            List list = (List) context.getAttribute("flightID_seatNumberList");

            for (Ticket ticket : tList) {
                ticket.setInvoiceId(invoiceId);
            }
            // ...............
            UserTicketManager.insertTicketList(tList);
            ArrayList<Ticket> ticketIDList = (ArrayList<Ticket>) UserTicketManager.getTicketID(invoiceId);
            float flightPrice = Float.parseFloat((String) request.getSession().getAttribute("flightPrice"));
            float total_price = 0;
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
                total_price += luggage_price + flightPrice;
                new UserSeatManager().AddSeat(x.getSeatNumber(), flightID, x.getId());
                list.remove(flightID + "," + x.getSeatNumber());

            }
            context.setAttribute("flightID_seatNumberList", list);

            UserInvoiceManager.updateInvoiceTotalPrice(invoiceId, total_price);
            UserInvoiceManager.updateInvoicePurchaseStatus(invoiceId);
            response.sendRedirect(request.getContextPath() + "/BookingHistoryController/history");

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
