package com.zzxx.bookstore.service;

import com.zzxx.bookstore.domain.User;

import javax.security.auth.login.LoginException;

public interface UserService {
    boolean checkUser(String username);

    boolean insertUser(User user);

    boolean active(String code);

    User findByUsernameAndPwd(String username, String password) throws LoginException;

    boolean update(User user);
}
