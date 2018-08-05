package com.imooc.service;

import com.imooc.beans.Message;

import java.util.List;

public interface MessageService {
    boolean addMessage(Message message);

    List<Message> listMessages(int page, int size);

    int countMessage();

    int countMessage(Long id);

    List<Message> listMessages(Long userId, int page, int size);

    Message getMessage(Long id);

    boolean updateMessage(Message message);

    boolean deleteMessage(Long id);
}
