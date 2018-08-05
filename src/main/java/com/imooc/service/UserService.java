package com.imooc.service;

import com.imooc.beans.User;

public interface UserService {
    User login(String username, String password);

    User getUser(Long id);

    User getUser(String name);

    boolean updateUser(User user);

    boolean addUser(User user);

}
