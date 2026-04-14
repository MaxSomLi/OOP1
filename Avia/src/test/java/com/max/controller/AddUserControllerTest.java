package com.max.controller;

import com.max.dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AddUserControllerTest {

    @Test
    void shouldRegisterUser_andRedirect() throws Exception {
        UserDAO dao = mock(UserDAO.class);
        AddUserController controller = new AddUserController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("N");
        when(req.getParameter("password")).thenReturn("1234");
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(dao).registerUser("N", "1234");
        verify(resp).sendRedirect("/login");
    }

    @Test
    void shouldHandleNullParameters() throws Exception {
        UserDAO dao = mock(UserDAO.class);
        AddUserController controller = new AddUserController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn(null);
        when(req.getParameter("password")).thenReturn(null);
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(dao).registerUser(null, null);
        verify(resp).sendRedirect("/login");
    }

    @Test
    void shouldThrowRuntimeException_whenDaoFails() throws Exception {
        UserDAO dao = mock(UserDAO.class);
        AddUserController controller = new AddUserController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("N");
        when(req.getParameter("password")).thenReturn("1234");
        doThrow(new SQLException()).when(dao).registerUser("N", "1234");
        assertThrows(RuntimeException.class, () -> {
            controller.doPost(req, resp);
        });
        verify(dao).registerUser("N", "1234");
    }
}