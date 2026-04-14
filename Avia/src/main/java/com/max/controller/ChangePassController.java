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

    private CrewDAO dao;

    public ChangePassController() throws Exception {
        this.dao = new CrewDAO();
    }

    public ChangePassController(CrewDAO dao) {
        this.dao = dao;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pass.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String old = req.getParameter("old"), newP = req.getParameter("new"), con = req.getParameter("con"),name = String.valueOf(req.getSession().getAttribute("user"));
        try {
            String co = dao.findPassword(name);
            if (co.equals(old) && con.equals(newP)) {
                dao.updatePassword(newP, name);
            } else {
                resp.sendRedirect(req.getContextPath() + "/changePass?error=true");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/assign.jsp").forward(req, resp);
    }
}
