package com.imooc.DAO;

import com.imooc.beans.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

class UserDAOImplTest {
    private UserDAO userDAO = new UserDAOImpl();

    @Test
    void login() {
        User testUser = new User();
        testUser.setId(10L);
        testUser.setName("UnitTester");
        testUser.setPassword("66666666");

        User resultUser = userDAO.login(testUser.getName(), testUser.getPassword());
        System.out.println(testUser.toString());
        System.out.println(resultUser.toString());

//        Assertions.assertArrayEquals(expected,actual);
        Assertions.assertEquals(testUser,resultUser);
        Assertions.assertNull(userDAO.login("UnitTester", "6666666"));
        Assertions.assertNull(userDAO.login("UnitTeste", "66666666"));
        Assertions.assertNull(userDAO.login("Tester", "123456"));


    }

    @Test
    void getUserById() {
        User user = userDAO.getUserById(10L);
        Assertions.assertNotNull(user);
        String[] target = {"UnitTester", "66666666"};
        String[] result = {user.getName(), user.getPassword()};

        Assertions.assertArrayEquals(target, result);
    }

    @Test
    void addUser() {
        User testUser = new User();
        testUser.setName("UnitTester");
        testUser.setPassword("66666666");

        userDAO = new UserDAOImpl();

        int result = userDAO.addUser(testUser);

        Assertions.assertNotEquals(-1, result);
    }

    @Test
    void updateUser() {
        User testUser = new User();
        testUser.setId(10L);
        testUser.setName("UnitTester");
        testUser.setPassword("66666666");
        testUser.setAddress("test address");
        testUser.setBirthday(new Date(new java.util.Date().getTime()));
        testUser.setPhone("12345678900");
        testUser.setRealName("test real name");

        int result = userDAO.updateUser(testUser);
        Assertions.assertNotEquals(-1,result);
        Assertions.assertEquals(testUser,userDAO.getUserById(testUser.getId()));

    }
}