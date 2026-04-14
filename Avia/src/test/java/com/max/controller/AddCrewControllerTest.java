package com.max.controller;

import com.max.dao.CrewDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AddCrewControllerTest {

    @Test
    void shouldCreateCrewAndRedirect_whenAdminChecked() throws Exception {
        CrewDAO dao = mock(CrewDAO.class);
        AddCrewController controller = new AddCrewController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("Name");
        when(req.getParameter("password")).thenReturn("1234");
        when(req.getParameter("is_admin")).thenReturn("on");
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(dao).create("Name", true, "1234");
        verify(resp).sendRedirect("/assign");
    }

    @Test
    void shouldCreateNonAdmin_whenCheckboxNotChecked() throws Exception {
        CrewDAO dao = mock(CrewDAO.class);
        AddCrewController controller = new AddCrewController(dao);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("New");
        when(req.getParameter("password")).thenReturn("pass");
        when(req.getParameter("is_admin")).thenReturn(null);
        when(req.getContextPath()).thenReturn("");
        controller.doPost(req, resp);
        verify(dao).create("New", false, "pass");
        verify(resp).sendRedirect("/assign");
    }

}