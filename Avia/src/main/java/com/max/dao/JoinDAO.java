package com.max.dao;


import com.max.model.Join;
import com.max.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoinDAO {

    private final Connection connection = DB.getConnection();

    public JoinDAO() throws Exception {}


    public List<Join> findAll() throws SQLException {
        List<Join> list = new ArrayList<>();
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM flight_crews");
        while (rs.next()) {
            Join j = new Join();
            j.setFlightId(rs.getInt("flight_id"));
            j.setCrewId(rs.getInt("crew_id"));
            list.add(j);
        }
        return list;
    }

    public void removeFromFlight(int flightId, int memberId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM flight_crews WHERE flight_id = ? AND crew_id = ?");
        stmt.setInt(1, flightId);
        stmt.setInt(2, memberId);
        stmt.executeUpdate();
    }

    public void assignToFlight(int flightId, int crewId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO flight_crews (flight_id, crew_id) VALUES (?, ?)");
        stmt.setInt(1, flightId);
        stmt.setInt(2, crewId);
        stmt.executeUpdate();
    }

}
