package com.max.controller;

import com.max.dao.FlightDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AddFlightControllerTest {

    @Test
    void shouldCreateFlightAndRedirect() throws Exception {
        FlightDAO dao = mock(FlightDAO.class);
        AddFlightController controller = new AddFlightController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("number")).thenReturn("FL123");
        when(req.getParameter("origin")).thenReturn("o");
        when(req.getParameter("destination")).thenReturn("d");
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(dao).create("FL123", "o", "d");
        verify(resp).sendRedirect("/assign");
    }

    @Test
    void shouldHandleEmptyParameters() throws Exception {
        FlightDAO dao = mock(FlightDAO.class);
        AddFlightController controller = new AddFlightController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("number")).thenReturn(null);
        when(req.getParameter("origin")).thenReturn(null);
        when(req.getParameter("destination")).thenReturn(null);
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(dao).create(null, null, null);
        verify(resp).sendRedirect("/assign");
    }

}