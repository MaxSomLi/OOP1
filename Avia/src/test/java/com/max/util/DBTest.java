package com.max.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DBTest {

    @Test
    public void testGetConnection() throws Exception {
        Connection connection = DB.getConnection();
        Assertions.assertNotNull(connection, "Database connection should not be null");
        Assertions.assertFalse(connection.isClosed());
        connection.close();
    }
}