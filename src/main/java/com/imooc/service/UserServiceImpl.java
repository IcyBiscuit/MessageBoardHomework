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

    public User getUser(Long id) {
        User user = null;
        user = userDAO.getUser(id);
        return user;
    }

    @Override
    public User getUser(String name) {
        User user = userDAO.getUser(name);
        return user;

    }

    public boolean updateUser(User user) {

        int updatedUser = userDAO.updateUser(user);
        if (updatedUser != -1)
            return true;
        else
            return false;

    }

    @Override
    public boolean addUser(User user) {
        int i = userDAO.addUser(user);
        if (i != -1) {
            return true;
        } else {
            return false;
        }
    }
}

