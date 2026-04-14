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

    private FlightDAO flightDAO;
    private CrewDAO crewDAO;
    private JoinDAO joinDAO;
    private UserDAO dao;

    public AssignController() throws Exception {
        flightDAO = new FlightDAO();
        crewDAO = new CrewDAO();
        joinDAO = new JoinDAO();
        dao = new UserDAO();
    }

    public AssignController(FlightDAO flightDAO, CrewDAO crewDAO, JoinDAO joinDAO, UserDAO dao) {
        this.flightDAO = flightDAO;
        this.crewDAO = crewDAO;
        this.joinDAO = joinDAO;
        this.dao = dao;
    }

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