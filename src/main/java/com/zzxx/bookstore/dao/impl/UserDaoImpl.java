package com.zzxx.bookstore.dao.impl;

import com.zzxx.bookstore.dao.UserDao;
import com.zzxx.bookstore.domain.User;
import com.zzxx.bookstore.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username) {
        String sql = "select * from tab_user where username = ?";
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public void insertUser(User user) {
        String sql = "insert into tab_user values(null,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(),
                user.getTelephone(), user.getAddress(),user.getSex(), user.getStatus(), user.getCode());
    }

    @Override
    public int updateUserStatus(String code) {
        String sql = "update tab_user set status = 'Y' where code = ?";
        int i = jdbcTemplate.update(sql, code);
        return i;
    }

    @Override
    public User findByUsernameAndPwd(String username, String password) {
        String sql = "select * from tab_user where username = ? and password = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
            return user;
        }catch (Exception e){
            return null;
        }
        /*List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        return list.size() == 0 ? null : list.get(0);*/
    }

    @Override
    public boolean update(User user) {
        String sql = "update tab_user set password = ?, email = ?, telephone = ?,address = ?,sex = ? where username = ?";
        int update = jdbcTemplate.update(sql,  user.getPassword(), user.getEmail(), user.getTelephone(), user.getAddress(), user.getSex(), user.getUsername());
        if (update==0){
            return false;
        }
        return true;
    }
}
