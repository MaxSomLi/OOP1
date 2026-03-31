package com.max.dao;

import com.max.model.CrewMember;
import com.max.util.DB;

import java.sql.*;
import java.util.*;

public class CrewDAO {

    private final Connection connection = DB.getConnection();

    public CrewDAO() throws Exception {}

    public void create(String name, String role) throws SQLException {
        String sql = "INSERT INTO crew_members (name, role) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, role);
        stmt.executeUpdate();
    }

    public List<CrewMember> findAll() throws SQLException {
        List<CrewMember> crewList = new ArrayList<>();
        String sql = "SELECT id, name, role FROM crew_members";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            CrewMember member = new CrewMember();
            member.setId(rs.getInt("id"));
            member.setName(rs.getString("name"));
            member.setRole(rs.getString("role"));
            crewList.add(member);
        }
        return crewList;
    }

    public void delete(int id) {
        String sql = "DELETE FROM crew_members WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}