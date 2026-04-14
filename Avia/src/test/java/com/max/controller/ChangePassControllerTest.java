package com.max.controller;

import com.max.dao.CrewDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ChangePassControllerTest {

    @Test
    void shouldForwardToPasswordPage() throws Exception {
        ChangePassController controller = new ChangePassController();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/pass.jsp")).thenReturn(dispatcher);
        controller.doGet(req, resp);
        verify(dispatcher).forward(req, resp);
    }

}
