package com.imooc.DAO;

import com.imooc.beans.Message;
import com.imooc.utils.DatabasesConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {
    /**
     * 保存消息
     * @param message
     * @return
     */
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

    /**
     * 更新留言信息
     * @param message
     * @return 失败返回-1 成功返回sql查询的返回值
     */
    @Override
    public int updateMessage(Message message) {
        final String sql = "update message set title = ? ,content=? where id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, message.getTitle());
            statement.setString(2, message.getContent());
            statement.setLong(3, message.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            DatabasesConnectionUtil.release(connection, statement);
        }

    }
    /**
     * 删除留言信息
     * @param id
     * @return 失败返回-1 成功返回sql查询的返回值
     */
    @Override
    public int deleteMessage(Long id) {
        final String sql = "update message set is_deleted = 1 where id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            return statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            DatabasesConnectionUtil.release(connection, statement);
        }
    }

    /**
     * 根据留言的id获取响应的留言信息
     * @param id
     * @return 查询成功返回响应的Message实例 失败返回null
     */
    @Override
    public Message getMessage(Long id) {
        final String sql = "select  * from message where is_deleted=0 and id =?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Message message = null;
        try {

            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                message = new Message(resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("create_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabasesConnectionUtil.release(connection, statement, resultSet);
        }
        return message;


    }

    /**
     * 获得消息列表
     * @param page 页数
     * @param pageSize 分页大小 响应值可以在web.xml中设置
     * @return 返回一个Message实例组成的List
     */
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

    /**
     * 根据用户id获得对应的消息列表
     * @param page 页数
     * @param pageSize 分页大小 响应值可以在web.xml中设置
     * @param userId 用户的userId
     * @return 返回一个Message实例组成的List
     */
    @Override
    public List<Message> getMessages(Long userId, int page, int pageSize) {
        final String sql = "select * from message " +
                "where is_deleted = 0 and user_id= ? " +
                "order by create_time desc limit ?, ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Message> messages = null;
        try {

            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
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

    /**
     * 计算所有未删除消息的总数
     * @return 返回响应的数目
     */
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

    /**
     * 计算某一用户的留言数目
     * @param id
     * @return
     */
    public int countMessages(Long id) {
        final String sqlString = "select count(*) counts from message where is_deleted=0 and user_id=?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabasesConnectionUtil.getConnection();
            statement = connection.prepareStatement(sqlString);
            statement.setLong(1, id);
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

}
