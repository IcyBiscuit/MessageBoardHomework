package com.imooc.DAO;

import com.imooc.beans.Message;
import com.imooc.beans.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

class MessageDAOImplTest {
    MessageDAO messageDAO = new MessageDAOImpl();

    @Test
    void save() {
        User testUser = new User();

        testUser.setId(666);
        testUser.setName("unitTester");

        Message testMessage = new Message();
        testMessage.setUserId(testUser.getId());
        testMessage.setUsername(testUser.getName());
        testMessage.setContent("this is a unit test");
        testMessage.setTitle("Unit Test" + Math.random());
        testMessage.setCreateTime(new Date());

        Assertions.assertNotEquals(-1, messageDAO.save(testMessage));
    }

    @Test
    void getMessages() {
        List<Message> messages = messageDAO.getMessages(1, 5);
        Assertions.assertNotNull(messages);
        Assertions.assertEquals(5, messages.size());
    }

    @Test
    void countMessages() {
        int counts = messageDAO.countMessages();
        Assertions.assertNotEquals(-1, counts);
        System.out.println(counts);
    }

    @Test
    void getMessageById() {
        List<Message> messages = messageDAO.getMessages(2L,1, 5);
        Assertions.assertNotNull(messages);
        Assertions.assertEquals(4, messages.size());
    }

}