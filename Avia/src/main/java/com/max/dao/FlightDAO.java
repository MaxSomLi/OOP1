package com.max.dao;


import com.max.model.Flight;
import com.max.util.DB;

import java.sql.*;
import java.util.*;

public class FlightDAO {

    private final Connection connection = DB.getConnection();

    public FlightDAO() throws Exception {}


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

    public void create(String number, String origin, String dest, String status) throws SQLException {
        PreparedStatement st = connection.prepareStatement("INSERT INTO flights(flight_number, origin, destination, status) VALUES (?, ?, ?, ?)");
        st.setString(1, number);
        st.setString(2, origin);
        st.setString(3, dest);
        st.setString(4, status);
        st.executeUpdate();
    }

    public void delete(int id) {
        String sql = "DELETE FROM flights WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}