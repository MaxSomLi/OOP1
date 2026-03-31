package com.max.controller;

import com.max.dao.FlightDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteFlight")
public class DeleteFlightController extends HttpServlet {

    private FlightDAO dao = new FlightDAO();

    public DeleteFlightController() throws Exception {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        dao.delete(id);
        resp.sendRedirect(req.getContextPath() + "/assign");
    }
}