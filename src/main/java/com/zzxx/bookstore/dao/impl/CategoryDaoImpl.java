package com.zzxx.bookstore.dao.impl;

import com.zzxx.bookstore.dao.CategoryDao;
import com.zzxx.bookstore.domain.Type;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public List<Type> findTname() {
        String sql = "select * from tab_type";
        List<Type> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Type.class));
        return list;
    }
}
