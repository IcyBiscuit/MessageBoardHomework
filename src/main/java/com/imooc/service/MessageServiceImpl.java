package com.imooc.service;

import com.imooc.DAO.MessageDAO;
import com.imooc.DAO.MessageDAOImpl;
import com.imooc.beans.Message;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    private MessageDAO messageDAO = new MessageDAOImpl();

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public boolean addMeassage(Message message) {

        int save = messageDAO.save(message);

        if (save != -1)
            return true;
        else
            return false;
    }

    public List<Message> listMessage(int page, int size) {

        List<Message> messages = messageDAO.getMessages(page, size);
        return messages;
    }

    public int countMessage() {
        int count = messageDAO.countMessages();
        return count;
    }
    public int countMessage(Long id) {
        int count = messageDAO.countMessages(id);
        return count;
    }

    @Override
    public List<Message> listMessageById(Long userId, int page, int size) {
        List<Message> messageById = messageDAO.getMessageById(userId, page, size);
        return messageById;

    }
}
