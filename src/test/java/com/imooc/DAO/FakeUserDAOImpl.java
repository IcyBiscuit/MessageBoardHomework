package com.imooc.DAO;

import com.imooc.beans.User;

public class FakeUserDAOImpl implements UserDAO {
    @Override
    public User login(String username, String password) {
        return new User();
    }

    @Override
    public User getUserById(Long id) {

        return new User();
    }

    @Override
    public int updateUser(User user) {
        return 666;
    }

    @Override
    public int addUser(User user) {
        return 666;
    }
}
