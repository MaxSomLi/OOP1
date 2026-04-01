package com.max.controller;

import com.max.dao.FlightDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addFlight")
public class AddFlightController extends HttpServlet {

    private final FlightDAO dao = new FlightDAO();

    public AddFlightController() throws Exception {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String number = req.getParameter("number"), origin = req.getParameter("origin"), destination = req.getParameter("destination"), status = req.getParameter("status");
        try {
            dao.create(number, origin, destination, status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/assign");
    }
}