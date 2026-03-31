package com.max.service;


import com.max.dao.JoinDAO;

import java.sql.SQLException;
import java.util.List;

public class JoinService {

    private final JoinDAO dao = new JoinDAO();

    public JoinService() throws Exception {}

    public List<com.max.model.Join> getAll() throws SQLException {
        return dao.findAll();
    }

}