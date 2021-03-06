package com.imooc.service;

import com.imooc.DAO.FakeUserDAOImpl;
import com.imooc.beans.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private static UserServiceImpl userService = new UserServiceImpl();

    static {
        userService.setUserDAO(new FakeUserDAOImpl());
    }

    @Test
    void login() {
        User login = userService.login("www", "rrr");
        assertNotNull(login);

    }

    @Test
    void getUserById() {
        User userById = userService.getUser(111L);
        assertNotNull(userById);
    }

    @Test
    void updateUser() {
        boolean b = userService.updateUser(new User());
        assertEquals(Boolean.TRUE,b);

    }
}