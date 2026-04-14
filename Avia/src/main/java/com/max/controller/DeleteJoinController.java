package com.max.controller;


import com.max.dao.JoinDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteJoin")
public class DeleteJoinController extends HttpServlet {

    private JoinDAO dao;

    public DeleteJoinController() throws Exception {
        this.dao = new JoinDAO();
    }

    public DeleteJoinController(JoinDAO dao) {
        this.dao = dao;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id1 = Integer.parseInt(req.getParameter("id1")), id2 = Integer.parseInt(req.getParameter("id2"));
        try {
            dao.removeFromFlight(id1, id2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/assign");
    }
}