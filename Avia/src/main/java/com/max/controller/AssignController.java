package com.max.controller;

import com.max.dao.CrewDAO;
import com.max.dao.FlightDAO;
import com.max.dao.JoinDAO;
import com.max.dao.UserDAO;
import com.max.model.CrewMember;
import com.max.model.Flight;
import com.max.model.Join;
import com.max.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/assign")
public class AssignController extends HttpServlet {

    private final FlightDAO flightDAO = new FlightDAO();
    private final CrewDAO crewDAO = new CrewDAO();
    private final JoinDAO joinDAO = new JoinDAO();
    private final UserDAO dao = new UserDAO();

    public AssignController() throws Exception {}

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Flight> flights = flightDAO.findAll();
            req.setAttribute("flights", flights);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            List<CrewMember> crewMembers = crewDAO.findAll();
            req.setAttribute("crewMembers", crewMembers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            List<Join> joins = joinDAO.findAll();
            req.setAttribute("flightCrews", joins);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            List<User> users = dao.allUsers();
            req.setAttribute("users", users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/assign.jsp").forward(req, resp);
    }

}