package com.zzxx.bookstore.dao;

import com.zzxx.bookstore.domain.User;

public interface UserDao {
    User findByUsername(String username);

    void insertUser(User user);

    int updateUserStatus(String code);

    User findByUsernameAndPwd(String username, String password);

    boolean update(User user);

}
