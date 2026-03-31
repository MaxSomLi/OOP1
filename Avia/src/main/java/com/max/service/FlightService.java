package com.max.service;

import com.max.dao.FlightDAO;

import java.sql.SQLException;
import java.util.List;

public class FlightService {

    private final FlightDAO dao = new FlightDAO();

    public FlightService() throws Exception {}

    public List<com.max.model.Flight> getAll() throws SQLException {
        return dao.findAll();
    }

    public void create(String number, String origin, String dest, String status) throws SQLException {
        dao.create(number, origin, dest, status);
    }
}