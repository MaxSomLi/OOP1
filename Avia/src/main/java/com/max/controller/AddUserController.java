package com.max.controller;


import com.max.dao.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addUser")
public class AddUserController extends HttpServlet {

    private UserDAO dao;

    public AddUserController() throws Exception {
        this.dao = new UserDAO();
    }

    public AddUserController(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name"), password = req.getParameter("password");
        try {
            dao.registerUser(name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
