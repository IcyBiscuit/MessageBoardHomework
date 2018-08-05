package com.imooc.service;

import com.imooc.DAO.MessageDAO;
import com.imooc.DAO.MessageDAOImpl;
import com.imooc.beans.Message;

import java.util.Date;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    private MessageDAO messageDAO = new MessageDAOImpl();

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public boolean addMessage(Message message) {

        message.setCreateTime(new Date());
        int save = messageDAO.save(message);

        if (save != -1)
            return true;
        else
            return false;
    }

    public List<Message> listMessages(int page, int size) {

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
    public boolean updateMessage(Message message) {
        int i = messageDAO.updateMessage(message);
        if (i != -1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMessage(Long id) {
        int i = messageDAO.deleteMessage(id);
        if (i != -1) {
            return true;
        }
        return false;
    }

    public List<Message> listMessages(Long userId, int page, int size) {
        List<Message> messageById = messageDAO.getMessages(userId, page, size);
        return messageById;

    }

    @Override
    public Message getMessage(Long id) {
        Message message = messageDAO.getMessage(id);
        return message;
    }
}
