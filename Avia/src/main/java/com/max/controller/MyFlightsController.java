package com.max.controller;


import com.max.dao.FlightDAO;
import com.max.model.Flight;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/flights")
public class MyFlightsController extends HttpServlet {

    private final FlightDAO dao = new FlightDAO();

    public MyFlightsController() throws Exception {}

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Flight> flights = dao.findCrewFlights(Integer.parseInt(String.valueOf(req.getSession().getAttribute("user"))));
            req.setAttribute("myFlights", flights);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/flights.jsp").forward(req, resp);
    }

}