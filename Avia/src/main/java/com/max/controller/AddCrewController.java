package com.max.controller;

import com.max.dao.CrewDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addCrew")
public class AddCrewController extends HttpServlet {

    private final CrewDAO dao = new CrewDAO();

    public AddCrewController() throws Exception {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name"), role = req.getParameter("role");
        try {
            dao.create(name, role);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/assign");
    }
}