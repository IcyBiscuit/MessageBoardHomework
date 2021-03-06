package com.imooc.DAO;

import com.imooc.beans.Message;

import java.util.List;

public interface MessageDAO {

    int save(Message message);

    int updateMessage(Message message);

    int deleteMessage(Long id);

    Message getMessage(Long id);

    List<Message> getMessages(int page, int pageSize);

    List<Message> getMessages(Long userId, int page, int pageSize);

    int countMessages();

    int countMessages(Long id);
}
