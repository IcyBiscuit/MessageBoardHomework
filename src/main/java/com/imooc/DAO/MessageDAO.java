package com.imooc.DAO;

import com.imooc.beans.Message;

import java.util.List;

public interface MessageDAO {

    boolean save(Message message);

    List<Message> getMessages(int page, int pageSize);

    int countMessages();
}
