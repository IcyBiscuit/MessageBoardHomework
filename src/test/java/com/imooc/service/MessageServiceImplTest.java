package com.imooc.service;

import com.imooc.DAO.FakeMessageDAOImpl;
import com.imooc.beans.Message;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceImplTest {
    private static MessageServiceImpl messageService=new MessageServiceImpl();
    static {
        messageService.setMessageDAO(new FakeMessageDAOImpl());
    }

    @Test
    void addMeassage() {
        boolean b = messageService.addMeassage(new Message());
        assertEquals(Boolean.TRUE,b);
    }

    @Test
    void listMessage() {
        List<Message> messages = messageService.listMessage(3, 5);
        assertEquals(1,messages.size());
        assertNotNull(messages);

    }

    @Test
    void countMessage() {
        assertEquals(666,messageService.countMessage());
        assertEquals(666,messageService.countMessage(666L));
    }
}