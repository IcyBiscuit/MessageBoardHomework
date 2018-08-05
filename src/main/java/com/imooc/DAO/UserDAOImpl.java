package com.imooc.DAO;

import com.imooc.beans.User;
import com.imooc.utils.DatabasesConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class UserDAOImpl implements UserDAO {
    /**
     *
     * @param username
     * @param password
     * @return 登陆成功返回对应User 失败返回null
     */
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

    /**
     * 根据用户名寻找对应用户
     * @param name 用户名
     * @return 找到返回相应的User 没有找到返回null
     */
    @Override
    public User getUser(String name) {
        final String sql = "select * from user where username = ?";
        User user = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            resultSet = statement.executeQuery();

            user = buildUserFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasesConnectionUtil.release(connection, statement, resultSet);
        }
        return user;

    }

    /**
     * 根据用户id寻找对应用户
     * @param id 用户 id
     * @return 找到返回相应的User 没有找到返回null
     */
    @Override
    public User getUser(Long id) {
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

    /**
     * 添加用户
     * @param user User bean实例
     * @return 添加失败返回 -1 添加成功返回查询语句的返回值
     */
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

    /**
     * 更新用户信息
     * @param user 一个User 实例
     * @return 更新失败返回-1 查询成功返回查询语句的返回值
     */
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
            statement.setDate(4, user.getBirthday());
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

    /**
     * 一个构建User实例的方法
     * @param resultSet
     * @return 返回根据resultSet构建的一个User实例
     * @throws SQLException
     */
    private User buildUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {

            user = new User();

            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRealName(resultSet.getString("real_name"));

            Date birthday = resultSet.getDate("birthday");

            if (birthday != null) {
                user.setBirthday(new Date(birthday.getTime()));
            } else {
                user.setBirthday(null);
            }

            user.setPhone(resultSet.getString("phone"));
            user.setAddress(resultSet.getString("address"));
        }
        return user;
    }
}
