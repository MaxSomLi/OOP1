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

    private CrewDAO dao;

    public AddCrewController() throws Exception {
        dao = new CrewDAO();
    }

    public AddCrewController(CrewDAO dao) {
        this.dao = dao;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name"), password = req.getParameter("password");
        String isAdminParam = req.getParameter("is_admin");
        boolean isAdmin = (isAdminParam != null && isAdminParam.equals("on"));
        try {
            dao.create(name, isAdmin, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/assign");
    }
}