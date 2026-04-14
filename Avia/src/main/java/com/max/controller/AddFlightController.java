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

    private FlightDAO dao;

    public AddFlightController() throws Exception {
        this.dao = new FlightDAO();
    }

    public AddFlightController(FlightDAO dao) {
        this.dao = dao;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String number = req.getParameter("number"), origin = req.getParameter("origin"), destination = req.getParameter("destination");
        try {
            dao.create(number, origin, destination);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/assign");
    }
}