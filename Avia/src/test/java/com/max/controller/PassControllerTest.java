package com.max.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PassControllerTest {

    @Test
    void shouldForwardToPassJsp() throws Exception {
        PassController controller = new PassController();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/pass.jsp")).thenReturn(dispatcher);
        controller.doGet(req, resp);
        verify(req).getRequestDispatcher("/WEB-INF/pass.jsp");
        verify(dispatcher).forward(req, resp);
    }
}