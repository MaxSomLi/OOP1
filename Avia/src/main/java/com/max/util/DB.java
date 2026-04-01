package com.max.util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {

    private static final String URL = "jdbc:postgresql://localhost:6666/avia?sslmode=disable";

    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        try (InputStream input = DB.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                return null;
            }
            props.load(input);
        } catch (IOException ignored) {}
        String USER = props.getProperty("admin.login");
        String PASSWORD = props.getProperty("admin.password");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}