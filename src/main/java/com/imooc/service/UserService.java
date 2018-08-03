package com.imooc.service;

import com.imooc.beans.User;

public interface UserService {
    User login(String username, String password);

    User getUserById(Long id);

    boolean updateUser(User user);

}
