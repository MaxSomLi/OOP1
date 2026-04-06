package com.max.controller;


import com.max.dao.CrewDAO;
import com.max.model.CrewMember;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@WebServlet("/login")
public class AuthController extends HttpServlet {

    protected final String url = "/WEB-INF/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                return;
            }
            props.load(input);
        } catch (IOException ex) {
            return;
        }
        String adminLogin = props.getProperty("admin.login");
        String adminPassword = props.getProperty("admin.password");
        if (adminLogin != null && adminLogin.equals(login) && adminPassword != null && adminPassword.equals(password)) {
            req.getSession().setAttribute("user", login);
            req.getSession().setAttribute("userRole", 2);
            resp.sendRedirect(req.getContextPath() + "/assign");
        } else {
            boolean found = false;
            try {
                CrewDAO c = new CrewDAO();
                List<CrewMember> m = c.findAll();
                for (CrewMember m1 : m) {
                    if (String.valueOf(m1.getId()).equals(login) && m1.getPassword().equals(password)) {
                        req.getSession().setAttribute("user", login);
                        req.getSession().setAttribute("userRole", (m1.isAdmin() ? 1 : 0));
                        resp.sendRedirect(req.getContextPath() + "/assign");
                        found = true;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (!found) {
                resp.sendRedirect(req.getContextPath() + "/login?error=true");
            }
        }
    }

}