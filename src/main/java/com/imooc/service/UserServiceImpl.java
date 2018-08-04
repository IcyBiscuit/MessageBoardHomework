package com.imooc.service;

import com.imooc.DAO.UserDAO;
import com.imooc.DAO.UserDAOImpl;
import com.imooc.beans.User;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String username, String password) {
        User user = null;

        user = userDAO.login(username, password);
        return user;
    }

    public User getUserById(Long id) {
        User user = null;
        user = userDAO.getUserById(id);
        return user;
    }

    public boolean updateUser(User user) {

        int updatedUser = userDAO.updateUser(user);
        if (updatedUser != -1)
            return true;
        else
            return false;

    }
}

