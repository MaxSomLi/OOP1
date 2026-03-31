package com.max.controller;

import com.max.model.CrewMember;
import com.max.model.Flight;
import com.max.model.Join;
import com.max.service.CrewService;
import com.max.service.FlightService;
import com.max.service.JoinService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/assign")
public class AssignController extends HttpServlet {

    private final FlightService flightService = new FlightService();
    private final CrewService crewService = new CrewService();
    private final JoinService joinService = new JoinService();

    public AssignController() throws Exception {}

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Flight> flights = null;
        try {
            flights = flightService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("flights", flights);
        List<CrewMember> crewMembers = null;
        try {
            crewMembers = crewService.getAllMembers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("crewMembers", crewMembers);
        List<Join> joins = null;
        try {
            joins = joinService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("flightCrews", joins);
        req.getRequestDispatcher("/WEB-INF/assign.jsp").forward(req, resp);
    }

    public void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String number = req.getParameter("flightNumber");
        String origin = req.getParameter("origin");
        String dest = req.getParameter("destination");
        String status = req.getParameter("status");
        flightService.create(number, origin, dest, status);
        //resp.sendRedirect("/flights");
    }

}