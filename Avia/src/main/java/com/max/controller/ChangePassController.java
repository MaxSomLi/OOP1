package com.max.controller;

import com.max.dao.CrewDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/changePass")
public class ChangePassController extends HttpServlet {


    private final CrewDAO dao = new CrewDAO();

    public ChangePassController() throws Exception {}

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pass = req.getParameter("pass");
        try {
            dao.updatePassword(pass, Integer.parseInt(String.valueOf(req.getSession().getAttribute("user"))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/assign.jsp").forward(req, resp);
    }
}
