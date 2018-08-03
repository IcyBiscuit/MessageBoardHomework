package com.imooc.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class DatabasesConnectionUtilTest {
    private Connection connection=null;

    @Test
    void getConnection() {
        try {
            connection = DatabasesConnectionUtil.getConnection();
            Assertions.assertNotNull(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}