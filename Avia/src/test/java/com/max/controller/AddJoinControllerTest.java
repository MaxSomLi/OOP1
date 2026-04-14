package com.max.controller;

import com.max.dao.JoinDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AddJoinControllerTest {

    @Test
    void shouldAssignCrewToFlight_andRedirect() throws Exception {
        JoinDAO dao = mock(JoinDAO.class);
        AddJoinController controller = new AddJoinController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id1")).thenReturn("1");
        when(req.getParameter("id2")).thenReturn("10");
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(dao).assignToFlight(1, 10);
        verify(resp).sendRedirect("/assign");
    }

    @Test
    void shouldThrowNumberFormatException_whenInvalidIds() throws Exception {
        JoinDAO dao = mock(JoinDAO.class);
        AddJoinController controller = new AddJoinController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id1")).thenReturn("abc");
        when(req.getParameter("id2")).thenReturn("10");
        assertThrows(NumberFormatException.class, () -> {
            controller.doPost(req, resp);
        });
        verifyNoInteractions(dao);
    }

    @Test
    void shouldThrowRuntimeException_whenDaoFails() throws Exception {
        JoinDAO dao = mock(JoinDAO.class);
        AddJoinController controller = new AddJoinController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id1")).thenReturn("2");
        when(req.getParameter("id2")).thenReturn("5");
        doThrow(new SQLException()).when(dao).assignToFlight(2, 5);
        assertThrows(RuntimeException.class, () -> {
            controller.doPost(req, resp);
        });
        verify(dao).assignToFlight(2, 5);
    }

    @Test
    void shouldHandleZeroOrNegativeIds() throws Exception {
        JoinDAO dao = mock(JoinDAO.class);
        AddJoinController controller = new AddJoinController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id1")).thenReturn("0");
        when(req.getParameter("id2")).thenReturn("-1");
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(dao).assignToFlight(0, -1);
        verify(resp).sendRedirect("/assign");
    }
}