package com.zzxx.bookstore.dao;

import com.zzxx.bookstore.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public interface BaseDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

}
