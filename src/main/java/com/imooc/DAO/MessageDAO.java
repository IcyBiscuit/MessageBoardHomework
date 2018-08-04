package com.imooc.DAO;

import com.imooc.beans.Message;

import java.util.List;

public interface MessageDAO {

    int save(Message message);

    List<Message> getMessages(int page, int pageSize);

    List<Message> getMessageById(Long userId, int page, int pageSize);

    int countMessages();

    int countMessages(Long id);
}
