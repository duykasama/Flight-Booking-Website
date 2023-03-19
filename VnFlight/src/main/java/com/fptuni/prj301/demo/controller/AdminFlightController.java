/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.AdminFlightManager;
import com.fptuni.prj301.demo.dbmanager.UserAirportManager;
import com.fptuni.prj301.demo.model.Flight;
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
public class AdminFlightController extends HttpServlet {

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
        PrintWriter out;
        out = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("view") || action == null) {
            UserAirportManager aManager = new UserAirportManager();
            request.getSession().setAttribute("airportList", aManager.loadAirport());
            if (request.getSession().getAttribute("fList") == null) {
                request.getSession().setAttribute("fList", new AdminFlightManager().loadFlight());
            }
            response.sendRedirect("admin_flight.jsp");
        } else if (action.equalsIgnoreCase("addflight")) {
            String takeOffTimeStr = request.getParameter("takeOffTime");
            String landingTimeStr = request.getParameter("landingTime");
            String depDate = request.getParameter("depDate");
            String numOfSeat = request.getParameter("numOfSeat");
            String depID = request.getParameter("depID");
            String desID = request.getParameter("desID");
            String airlineName = request.getParameter("airlineName");
            String price = request.getParameter("price");
            try {
                AdminFlightManager adManager = new AdminFlightManager();
                Boolean check = adManager.addFlight(takeOffTimeStr, landingTimeStr, depDate, price, airlineName, numOfSeat, depID, desID);
                if (check) {
                    request.getSession().setAttribute("flight_msg", "Added Successfully");
                } else {
                    request.getSession().setAttribute("flight_msg", "Added Failed");
                }
                response.sendRedirect("admin_flight.jsp");

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (action.equalsIgnoreCase("filter")) {
//            out.print("hello");
            request.getSession().removeAttribute("fList");

            String cate = request.getParameter("cate");
            String value = request.getParameter("value");
            UserAirportManager aManager = new UserAirportManager();
            request.getSession().setAttribute("airportList", aManager.loadAirport());
            if (request.getSession().getAttribute("fList") == null) {
                request.getSession().setAttribute("fList", new AdminFlightManager().loadFlight(cate, value));
            }
//            out.print(((List) request.getSession().getAttribute("fList")).size());
            response.sendRedirect("admin_flight.jsp");

        } else if (action.equalsIgnoreCase("sort")) {
            request.getSession().removeAttribute("fList");

            String cate = request.getParameter("cate");
            String value = request.getParameter("value");
            UserAirportManager aManager = new UserAirportManager();
            request.getSession().setAttribute("airportList", aManager.loadAirport());
            if (request.getSession().getAttribute("fList") == null) {
                request.getSession().setAttribute("fList", new AdminFlightManager().sortFlight(cate, value));
            }
//            out.print(((List) request.getSession().getAttribute("fList")).size());
            response.sendRedirect("admin_flight.jsp");
        } else if (action.equalsIgnoreCase("delete")) {
            String flightID = request.getParameter("flightID");
            new AdminFlightManager().deleteFlight(flightID);
            request.getSession().setAttribute("fList", new AdminFlightManager().loadFlight());
//            RequestDispatcher rd = request.getRequestDispatcher("./AdminFlightController?action=view");
//            rd.forward(request, response);
            response.sendRedirect("./AdminFlightController?action=view");
        } else if(action.equalsIgnoreCase("edit")){
            String flightID = request.getParameter("flightID");
            Flight flight = new AdminFlightManager().getFlight(flightID);
            request.getSession().setAttribute("flight", flight);
            response.sendRedirect("edit_flight_form.jsp");
        } else if(action.equalsIgnoreCase("confirmEdit")){
            
            String flightID = request.getParameter("flightID");
            String takeOffTimeStr = request.getParameter("takeOffTime");
            String landingTimeStr = request.getParameter("landingTime");
            String depDate = request.getParameter("depDate");
            String numOfSeat = request.getParameter("numOfSeat");
            String depID = request.getParameter("depID");
            String desID = request.getParameter("desID");
            String airlineName = request.getParameter("airlineName");
            String price = request.getParameter("price");
            new AdminFlightManager().editFlight(flightID, takeOffTimeStr, landingTimeStr, depDate, price, airlineName, numOfSeat, depID, desID);
            request.getSession().setAttribute("fList", new AdminFlightManager().loadFlight());
            request.getRequestDispatcher("AdminFlightController?action=view").forward(request, response);
            
        } else if(action.equalsIgnoreCase("cancelEdit")){
            request.getSession().removeAttribute("flight");
            response.sendRedirect("admin_flight.jsp");
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
