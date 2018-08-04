package com.imooc.DAO;

import com.imooc.beans.Message;

import java.util.ArrayList;
import java.util.List;

public class FakeMessageDAOImpl implements MessageDAO {

    @Override
    public int save(Message message) {
        return 666;
    }

    @Override
    public List<Message> getMessages(int page, int pageSize) {

        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message());
        return messageList;
    }

    @Override
    public List<Message> getMessageById(Long userId, int page, int pageSize) {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message());
        return messageList;
    }

    @Override
    public int countMessages() {

        return 666;
    }

    @Override
    public int countMessages(Long id) {
        return 666;
    }
}
