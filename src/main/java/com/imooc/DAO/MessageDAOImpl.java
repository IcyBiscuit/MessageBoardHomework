package com.imooc.DAO;

import com.imooc.beans.Message;
import com.imooc.utils.DatabasesConnectionUtil;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    public int save(Message message) {
        final String sql = "insert into message(user_id, username, title, content, create_time) values (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);

            statement = connection.prepareStatement(sql);
            statement.setLong(1, message.getUserId());
            statement.setString(2, message.getUsername());
            statement.setString(3, message.getTitle());
            statement.setString(4, message.getContent());
            statement.setTimestamp(5, new Timestamp(message.getCreateTime().getTime()));

            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasesConnectionUtil.release(connection, statement);
        }
        return -1;
    }

    public List<Message> getMessages(int page, int pageSize) {
        final String sql = "select * from message " +
                "where is_deleted = 0 " +
                "order by create_time desc limit ?, ?";//limit m, n：从第m条开始，取出总共n条记录

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Message> messages = null;

        try {

            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (page - 1) * pageSize);
            statement.setInt(2, pageSize);
            resultSet = statement.executeQuery();


            messages = new ArrayList<>();
            while (resultSet.next()) {
                messages.add(new Message(resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("create_time")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasesConnectionUtil.release(connection, statement, resultSet);
        }
        return messages;
    }

    public int countMessages() {
        final String sqlString = "select count(*) counts from message where is_deleted=0";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sqlString);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt("counts");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasesConnectionUtil.release(connection, statement, resultSet);
        }
        return -1;
    }

    public int countMessages(Long id) {
        final String sqlString = "select count(*) counts from message where is_deleted=0 and user_id=?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setLong(1,id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt("counts");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasesConnectionUtil.release(connection, statement, resultSet);
        }
        return -1;
    }

    @Override
    public List<Message> getMessageById(Long userId, int page, int pageSize) {
        final String sql ="select * from message " +
                "where is_deleted = 0 and user_id= ? " +
                "order by create_time desc limit ?, ?";

        Connection connection =null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Message> messages = null;
        try {

            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1,userId);
            statement.setInt(2, (page - 1) * pageSize);
            statement.setInt(3, pageSize);
            resultSet = statement.executeQuery();

            messages = new ArrayList<>();
            while (resultSet.next()) {
                messages.add(new Message(resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("create_time")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasesConnectionUtil.release(connection, statement, resultSet);
        }
        return messages;
    }
}
