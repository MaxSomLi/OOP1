package com.max.controller;

import com.max.dao.JoinDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteJoinControllerTest {

    @Test
    void shouldRemoveJoin_andRedirect() throws Exception {
        JoinDAO dao = mock(JoinDAO.class);
        DeleteJoinController controller = new DeleteJoinController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id1")).thenReturn("1");
        when(req.getParameter("id2")).thenReturn("2");
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(dao).removeFromFlight(1, 2);
        verify(resp).sendRedirect("/assign");
    }

    @Test
    void shouldThrowException_whenId1Invalid() {
        JoinDAO dao = mock(JoinDAO.class);
        DeleteJoinController controller = new DeleteJoinController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id1")).thenReturn("abc");
        when(req.getParameter("id2")).thenReturn("2");
        assertThrows(NumberFormatException.class, () -> {
            controller.doPost(req, resp);
        });
        verifyNoInteractions(dao);
    }

    @Test
    void shouldThrowException_whenId2Invalid() {
        JoinDAO dao = mock(JoinDAO.class);
        DeleteJoinController controller = new DeleteJoinController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id1")).thenReturn("1");
        when(req.getParameter("id2")).thenReturn("xyz");
        assertThrows(NumberFormatException.class, () -> {
            controller.doPost(req, resp);
        });
        verifyNoInteractions(dao);
    }

    @Test
    void shouldThrowException_whenParamsAreNull() {
        JoinDAO dao = mock(JoinDAO.class);
        DeleteJoinController controller = new DeleteJoinController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id1")).thenReturn(null);
        when(req.getParameter("id2")).thenReturn(null);
        assertThrows(NumberFormatException.class, () -> {
            controller.doPost(req, resp);
        });
        verifyNoInteractions(dao);
    }

}
