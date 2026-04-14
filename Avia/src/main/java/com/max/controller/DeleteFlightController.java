package com.max.controller;

import com.max.dao.FlightDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteFlight")
public class DeleteFlightController extends HttpServlet {

    private FlightDAO dao;

    public DeleteFlightController() throws Exception {
        this.dao = new FlightDAO();
    }

    public DeleteFlightController(FlightDAO dao) {
        this.dao = dao;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            dao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/assign");
    }
}