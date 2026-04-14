package com.max.controller;

import com.max.dao.FlightDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteFlightControllerTest {

    @Test
    void shouldDeleteFlight_andRedirect() throws Exception {
        FlightDAO dao = mock(FlightDAO.class);
        DeleteFlightController controller = new DeleteFlightController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("3");
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(dao).delete(3);
        verify(resp).sendRedirect("/assign");
    }

    @Test
    void shouldThrowException_whenIdIsInvalid() {
        FlightDAO dao = mock(FlightDAO.class);
        DeleteFlightController controller = new DeleteFlightController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("abc");
        assertThrows(NumberFormatException.class, () -> {
            controller.doPost(req, resp);
        });
        verifyNoInteractions(dao);
    }

    @Test
    void shouldThrowRuntimeException_whenDaoFails() throws Exception {
        FlightDAO dao = mock(FlightDAO.class);
        DeleteFlightController controller = new DeleteFlightController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("10");
        doThrow(new SQLException()).when(dao).delete(10);
        assertThrows(RuntimeException.class, () -> {
            controller.doPost(req, resp);
        });
        verify(dao).delete(10);
    }

    @Test
    void shouldThrowException_whenIdIsNull() {
        FlightDAO dao = mock(FlightDAO.class);
        DeleteFlightController controller = new DeleteFlightController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(null);
        assertThrows(NumberFormatException.class, () -> {
            controller.doPost(req, resp);
        });
        verifyNoInteractions(dao);
    }

}
