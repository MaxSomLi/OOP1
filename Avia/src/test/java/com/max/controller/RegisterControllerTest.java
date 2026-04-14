package com.max.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RegisterControllerTest {

    @Test
    void shouldForwardToRegisterJsp() throws Exception {
        RegisterController controller = new RegisterController();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/register.jsp")).thenReturn(dispatcher);
        controller.doGet(req, resp);
        verify(req).getRequestDispatcher("/WEB-INF/register.jsp");
        verify(dispatcher).forward(req, resp);
    }
}