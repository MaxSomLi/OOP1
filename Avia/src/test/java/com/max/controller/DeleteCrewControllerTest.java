package com.max.controller;

import com.max.dao.CrewDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteCrewControllerTest {

    @Test
    void shouldDeleteCrew_andRedirect() throws Exception {
        CrewDAO dao = mock(CrewDAO.class);
        DeleteCrewController controller = new DeleteCrewController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("5");
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(dao).delete(5);
        verify(resp).sendRedirect("/assign");
    }

    @Test
    void shouldThrowException_whenIdIsInvalid() {
        CrewDAO dao = mock(CrewDAO.class);
        DeleteCrewController controller = new DeleteCrewController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("abc");
        assertThrows(NumberFormatException.class, () -> {
            controller.doPost(req, resp);
        });
        verifyNoInteractions(dao);
    }

    @Test
    void shouldThrowException_whenIdIsNull() {
        CrewDAO dao = mock(CrewDAO.class);
        DeleteCrewController controller = new DeleteCrewController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(null);
        assertThrows(NumberFormatException.class, () -> {
            controller.doPost(req, resp);
        });
        verifyNoInteractions(dao);
    }

}
