package com.imooc.DAO;

import com.imooc.beans.User;
import com.imooc.utils.DatabasesConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class UserDAOImpl implements UserDAO {
    @Override
    public User login(String username, String password) {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        final String sql = "select * from user where username = ? and password = ?";

        try {
            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();
            user = buildUserFromResultSet(resultSet);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasesConnectionUtil.release(connection, statement, resultSet);
        }
        return user;


    }

    @Override
    public User getUserById(Long id) {
        final String sql = "select * from user where id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            user = buildUserFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasesConnectionUtil.release(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public int addUser(User user) {
        final String sql = "insert into user(username, password) values(?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasesConnectionUtil.release(connection, statement);
        }

        return -1;
    }

    @Override
    public int updateUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        final String sql = "UPDATE user SET username = ?, password = ?, real_name = ?, birthday = ?, phone = ?, address = ? WHERE id = ?";

        try {

            connection = DatabasesConnectionUtil.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRealName());
            statement.setDate(4,user.getBirthday());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getAddress());
            statement.setLong(7, user.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("查询用户信息失败。");
            e.printStackTrace();
            return -1;
        } finally {
            DatabasesConnectionUtil.release(connection, statement);
        }
    }


    private User buildUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {

            user = new User();

            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRealName(resultSet.getString("real_name"));
            user.setBirthday(new Date(resultSet.getDate("birthday").getTime()));
            user.setPhone(resultSet.getString("phone"));
            user.setAddress(resultSet.getString("address"));
        }
        return user;
    }
}
