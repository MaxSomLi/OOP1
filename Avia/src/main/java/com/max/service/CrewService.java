package com.max.service;

import com.max.dao.CrewDAO;
import com.max.model.CrewMember;

import java.sql.SQLException;
import java.util.List;

public class CrewService {

    private final CrewDAO crewDAO = new CrewDAO();

    public CrewService() throws Exception {}


    public List<CrewMember> getAllMembers() throws SQLException {
        return crewDAO.findAll();
    }
}