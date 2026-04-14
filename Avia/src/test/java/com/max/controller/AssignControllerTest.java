package com.max.controller;

import com.max.dao.*;
import com.max.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.*;

class AssignControllerTest {

    @Test
    void shouldLoadAllData_andForwardToJsp() throws Exception {
        FlightDAO flightDAO = mock(FlightDAO.class);
        CrewDAO crewDAO = mock(CrewDAO.class);
        JoinDAO joinDAO = mock(JoinDAO.class);
        UserDAO userDAO = mock(UserDAO.class);
        AssignController controller = new AssignController(flightDAO, crewDAO, joinDAO, userDAO);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        List<Flight> flights = List.of(mock(Flight.class));
        List<CrewMember> crew = List.of(mock(CrewMember.class));
        List<Join> joins = List.of(mock(Join.class));
        List<User> users = List.of(mock(User.class));
        when(flightDAO.findAll()).thenReturn(flights);
        when(crewDAO.findAll()).thenReturn(crew);
        when(joinDAO.findAll()).thenReturn(joins);
        when(userDAO.allUsers()).thenReturn(users);
        when(req.getRequestDispatcher("/WEB-INF/assign.jsp")).thenReturn(dispatcher);
        controller.doGet(req, resp);
        verify(req).setAttribute("flights", flights);
        verify(req).setAttribute("crewMembers", crew);
        verify(req).setAttribute("flightCrews", joins);
        verify(req).setAttribute("users", users);
        verify(dispatcher).forward(req, resp);
    }

    @Test
    void shouldThrowRuntimeException_whenAnyDaoFails() throws Exception {
        FlightDAO flightDAO = mock(FlightDAO.class);
        CrewDAO crewDAO = mock(CrewDAO.class);
        JoinDAO joinDAO = mock(JoinDAO.class);
        UserDAO userDAO = mock(UserDAO.class);
        AssignController controller = new AssignController(flightDAO, crewDAO, joinDAO, userDAO);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(flightDAO.findAll()).thenThrow(new SQLException());
        try {
            controller.doGet(req, resp);
        } catch (RuntimeException e) {
            verify(flightDAO).findAll();
            return;
        }
        throw new AssertionError("Expected RuntimeException");
    }
}