/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.AdminFlightManager;
import com.fptuni.prj301.demo.dbmanager.UserAirportManager;
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
        if (action.equalsIgnoreCase("view")) {
            UserAirportManager aManager = new UserAirportManager();
            request.getSession().setAttribute("airportList", aManager.loadAirport());
            if (request.getSession().getAttribute("fList") == null) {
                request.getSession().setAttribute("fList", new AdminFlightManager());
            }
            response.sendRedirect("admin_flight.jsp");
        } else if (action.equalsIgnoreCase("addflight")) {
            out.print(action);
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
