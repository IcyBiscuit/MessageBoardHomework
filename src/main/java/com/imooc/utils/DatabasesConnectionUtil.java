package com.imooc.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabasesConnectionUtil {

    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }

    public static void release(Connection connection, Statement statement) {

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }

}
