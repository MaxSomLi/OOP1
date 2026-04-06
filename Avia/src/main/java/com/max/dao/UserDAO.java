package com.max.dao;


import com.max.model.User;
import com.max.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection connection = DB.getConnection();

    public UserDAO() throws Exception {}

    public void registerUser(String name, String password) throws SQLException {
        PreparedStatement st = connection.prepareStatement("INSERT INTO user_n (name, password) VALUES (?, ?)");
        st.setString(1, name);
        st.setString(2, password);
        st.executeUpdate();
    }

    public void removeUser(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement("DELETE FROM user_n WHERE id = ?");
        st.setInt(1, id);
        st.executeUpdate();
    }

    public List<User> allUsers() throws SQLException {
        PreparedStatement st = connection.prepareStatement("SELECT * FROM user_n");
        ResultSet rs = st.executeQuery();
        List<User> users = new ArrayList<User>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            users.add(user);
        }
        return users;
    }

}
