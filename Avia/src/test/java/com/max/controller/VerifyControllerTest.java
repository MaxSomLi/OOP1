package com.max.controller;

import com.max.dao.CrewDAO;
import com.max.dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class VerifyControllerTest {

    @Test
    void shouldVerifyAndRemoveUser_whenVerifyIsOn() throws Exception {
        UserDAO userDAO = mock(UserDAO.class);
        CrewDAO crewDAO = mock(CrewDAO.class);
        VerifyController controller = new VerifyController(userDAO, crewDAO);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("10");
        when(req.getParameter("is_admin")).thenReturn("on");
        when(req.getParameter("verify")).thenReturn("on");
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(crewDAO).verify(10, true);
        verify(userDAO).removeUser(10);
        verify(resp).sendRedirect("/assign");
    }

    @Test
    void shouldOnlyRemoveUser_whenVerifyIsOff() throws Exception {
        UserDAO userDAO = mock(UserDAO.class);
        CrewDAO crewDAO = mock(CrewDAO.class);
        VerifyController controller = new VerifyController(userDAO, crewDAO);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("5");
        when(req.getParameter("verify")).thenReturn(null);
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(crewDAO, never()).verify(anyInt(), anyBoolean());
        verify(userDAO).removeUser(5);
        verify(resp).sendRedirect("/assign");
    }

    @Test
    void shouldVerifyAsNonAdmin_whenIsAdminOff() throws Exception {
        UserDAO userDAO = mock(UserDAO.class);
        CrewDAO crewDAO = mock(CrewDAO.class);
        VerifyController controller = new VerifyController(userDAO, crewDAO);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("7");
        when(req.getParameter("is_admin")).thenReturn(null);
        when(req.getParameter("verify")).thenReturn("on");
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(crewDAO).verify(7, false);
        verify(userDAO).removeUser(7);
    }

    @Test
    void shouldThrowException_whenIdInvalid() {
        UserDAO userDAO = mock(UserDAO.class);
        CrewDAO crewDAO = mock(CrewDAO.class);
        VerifyController controller = new VerifyController(userDAO, crewDAO);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("abc");
        assertThrows(NumberFormatException.class, () -> {
            controller.doPost(req, resp);
        });
        verifyNoInteractions(userDAO);
        verifyNoInteractions(crewDAO);
    }

}
