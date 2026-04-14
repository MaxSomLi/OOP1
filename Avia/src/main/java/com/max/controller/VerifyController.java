package com.max.controller;


import com.max.dao.CrewDAO;
import com.max.dao.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/verify")
public class VerifyController extends HttpServlet {

    private UserDAO dao;
    private CrewDAO cd;

    public VerifyController() throws Exception {
        this.dao = new UserDAO();
        this.cd = new CrewDAO();
    }

    public VerifyController(UserDAO dao, CrewDAO cd) {
        this.dao = dao;
        this.cd = cd;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String isAdminParam = req.getParameter("is_admin");
        boolean isAdmin = (isAdminParam != null && isAdminParam.equals("on"));
        String verifyParam = req.getParameter("verify");
        boolean isVerify = (verifyParam != null && verifyParam.equals("on"));
        try {
            if (isVerify) {
                cd.verify(id, isAdmin);
            }
            dao.removeUser(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/assign");
    }
}
