package com.max.dao;


import com.max.model.Flight;
import com.max.util.DB;

import java.sql.*;
import java.util.*;

public class FlightDAO {

    private final Connection connection = DB.getConnection();

    public FlightDAO() throws Exception {}


    public List<Flight> findCrewFlights(String name) throws SQLException {
        List<Flight> list = new ArrayList<>();
        String sql = "SELECT * FROM flights WHERE id IN (SELECT flight_id FROM flight_crews WHERE crew_id IN (SELECT id FROM crew_members WHERE name = ?))";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Flight f = new Flight();
                    f.setId(rs.getInt("id"));
                    f.setFlightNumber(rs.getString("flight_number"));
                    f.setOrigin(rs.getString("origin"));
                    f.setDestination(rs.getString("destination"));
                    list.add(f);
                }
            }
        }
        return list;
    }

    public List<Flight> findAll() throws SQLException {
        List<Flight> list = new ArrayList<>();
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM flights");
        while (rs.next()) {
            Flight f = new Flight();
            f.setId(rs.getInt("id"));
            f.setFlightNumber(rs.getString("flight_number"));
            f.setOrigin(rs.getString("origin"));
            f.setDestination(rs.getString("destination"));
            list.add(f);
        }
        return list;
    }

    public void create(String number, String origin, String dest) throws SQLException {
        PreparedStatement st = connection.prepareStatement("INSERT INTO flights(flight_number, origin, destination) VALUES (?, ?, ?)");
        st.setString(1, number);
        st.setString(2, origin);
        st.setString(3, dest);
        st.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM flights WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }

}