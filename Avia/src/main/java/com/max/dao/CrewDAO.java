package com.max.dao;

import com.max.model.CrewMember;
import com.max.util.DB;

import java.sql.*;
import java.util.*;

public class CrewDAO {

    private final Connection connection = DB.getConnection();

    public CrewDAO() throws Exception {}

    public void updatePassword(String password, int id) throws Exception {
        PreparedStatement stmt = connection.prepareStatement("UPDATE crew_members SET PASSWORD = ? WHERE ID = ?");
        stmt.setString(1, password);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }

    public void create(String name, boolean isAdmin, String password) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO crew_members (name, is_admin, password) VALUES (?, ?, ?)");
        stmt.setString(1, name);
        stmt.setBoolean(2, isAdmin);
        stmt.setString(3, password);
        stmt.executeUpdate();
    }

    public List<CrewMember> findAll() throws SQLException {
        List<CrewMember> crewList = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM crew_members");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            CrewMember member = new CrewMember();
            member.setId(rs.getInt("id"));
            member.setName(rs.getString("name"));
            member.setAdmin(rs.getBoolean("is_admin"));
            member.setPassword(rs.getString("password"));
            crewList.add(member);
        }
        return crewList;
    }

    public void delete(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM crew_members WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void verify(int id, boolean isAdmin) throws SQLException {
        PreparedStatement psf = connection.prepareStatement("SELECT * FROM user_n WHERE id = ?");
        psf.setInt(1, id);
        String name = "", password = "";
        ResultSet rs = psf.executeQuery();
        while (rs.next()) {
            name = rs.getString("name");
            password = rs.getString("password");
        }
        PreparedStatement ps = connection.prepareStatement("INSERT INTO crew_members (name, is_admin, password) VALUES (?, ?, ?)");
        ps.setString(1, name);
        ps.setBoolean(2, isAdmin);
        ps.setString(3, password);
        ps.executeUpdate();
    }

}