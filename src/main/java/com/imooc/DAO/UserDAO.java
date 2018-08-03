package com.imooc.DAO;

import com.imooc.beans.User;

public interface UserDAO {

    User login(String username, String password);

    User getUserById(Long id);

    boolean updateUser(User user);

}
