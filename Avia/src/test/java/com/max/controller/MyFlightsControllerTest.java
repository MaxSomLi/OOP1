package com.max.controller;

import com.max.dao.FlightDAO;
import com.max.model.Flight;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MyFlightsControllerTest {

    @Test
    void shouldLoadMyFlights_andForwardToJsp() throws Exception {
        FlightDAO dao = mock(FlightDAO.class);
        MyFlightsController controller = new MyFlightsController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Flight flight = mock(Flight.class);
        when(session.getAttribute("user")).thenReturn("N");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/flights.jsp")).thenReturn(dispatcher);
        when(dao.findCrewFlights("N")).thenReturn(List.of(flight));
        controller.doGet(req, resp);
        verify(dao).findCrewFlights("N");
        verify(req).setAttribute(eq("myFlights"), anyList());
        verify(dispatcher).forward(req, resp);
    }

    @Test
    void shouldThrowRuntimeException_whenDaoFails() throws Exception {
        FlightDAO dao = mock(FlightDAO.class);
        MyFlightsController controller = new MyFlightsController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn("n");
        when(req.getSession()).thenReturn(session);
        when(dao.findCrewFlights("n")).thenThrow(new SQLException());
        assertThrows(RuntimeException.class, () -> {
            controller.doGet(req, resp);
        });
        verify(dao).findCrewFlights("n");
    }

    @Test
    void shouldHandleNullUserSession() throws Exception {
        FlightDAO dao = mock(FlightDAO.class);
        MyFlightsController controller = new MyFlightsController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);
        when(req.getRequestDispatcher("/WEB-INF/flights.jsp")).thenReturn(dispatcher);
        when(dao.findCrewFlights("null")).thenReturn(List.of());
        controller.doGet(req, resp);
        verify(dao).findCrewFlights("null");
        verify(dispatcher).forward(req, resp);
    }

}
