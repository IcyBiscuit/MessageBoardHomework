package com.imooc.service;

import com.imooc.beans.Message;

import java.util.List;

public interface MessageService {
    boolean addMeassage(Message message);

    List<Message> listMessage(int page, int size);

    int countMessage();
}
