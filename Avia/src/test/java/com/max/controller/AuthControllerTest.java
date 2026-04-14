package com.max.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Test
    void shouldForwardToLoginPage() throws Exception {
        AuthController controller = new AuthController();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/login.jsp")).thenReturn(dispatcher);
        controller.doGet(req, resp);
        verify(dispatcher).forward(req, resp);
    }

}
