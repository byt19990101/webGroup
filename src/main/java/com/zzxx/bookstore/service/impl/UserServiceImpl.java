package com.zzxx.bookstore.service.impl;

import com.zzxx.bookstore.dao.UserDao;
import com.zzxx.bookstore.dao.impl.UserDaoImpl;
import com.zzxx.bookstore.domain.User;
import com.zzxx.bookstore.service.UserService;
import com.zzxx.bookstore.util.MailUtils;
import com.zzxx.bookstore.util.Md5Util;
import com.zzxx.bookstore.util.UuidUtil;

import javax.security.auth.login.LoginException;

public class UserServiceImpl implements UserService {
    private UserDao ud = new UserDaoImpl();
    @Override
    public boolean checkUser(String username) {
        User user = ud.findByUsername(username);
        if (user!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean insertUser(User user) {
        try {
            String password = user.getPassword();
            String passwordMD5 = Md5Util.encodeByMd5(password);
            user.setPassword(passwordMD5);

            user.setStatus("N");
            user.setCode(UuidUtil.getUuid());
            ud.insertUser(user);

            String text = "<a href='http://192.168.56.101/bookstore/user/active?code="+user.getCode()+"'>账号激活</a>";
            MailUtils.sendMail(user.getEmail(),text,"空壳网上书店账号激活");

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean active(String code) {
        int count = ud.updateUserStatus(code);
        return count != 0;
    }

    @Override
    public User findByUsernameAndPwd(String username, String password) throws LoginException {
        String passwordMD5  = "";
        try {
            passwordMD5 = Md5Util.encodeByMd5(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = ud.findByUsernameAndPwd(username,passwordMD5);
        if (user == null){
            throw new LoginException("账号/密码错误");
        }else if (user.getStatus().equals("N")){
            throw new LoginException("账号未激活");
        }else {
            return user;
        }
    }

    @Override
    public boolean update(User user) {
        /*String password = user.getPassword();
        String address = user.getAddress();
        String telephone = user.getTelephone();
        String username = user.getUsername();*/
        String password = user.getPassword();
        String passwordMD5 = null;
        try{
            passwordMD5 = Md5Util.encodeByMd5(password);
        }catch (Exception e){
            e.printStackTrace();
        }
        user.setPassword(passwordMD5);
        return ud.update(user);
    }
}
