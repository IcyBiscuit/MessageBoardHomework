package com.imooc.DAO;

import com.imooc.beans.User;

public interface UserDAO {

    User login(String username, String password);

    User getUser(Long id);

    User getUser(String name);
    int updateUser(User user);

    int addUser(User user);



}
