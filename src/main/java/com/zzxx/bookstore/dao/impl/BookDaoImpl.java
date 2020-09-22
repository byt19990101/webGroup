package com.zzxx.bookstore.dao.impl;

import com.zzxx.bookstore.dao.BookDao;
import com.zzxx.bookstore.domain.Book;
import com.zzxx.bookstore.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Book findByBid(int bid) {
        String sql = "select * from tab_books where bid = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), bid);
}

    @Override
    public Integer findCount(int tid) {
        String sql = "select count(*) from tab_books where tid = ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, tid);
    }

    @Override
    public List<Book> findByPage(int tid, Integer currentPage, Integer pageSize, String search) {
        int start = (currentPage-1)*pageSize;
        if(tid != 0){
            String sql = "select * from tab_books where tid = ?  limit ? , ?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), tid, start, pageSize);
        }else{
            StringBuilder _sql = new StringBuilder("select * from tab_books where 1=2 ");
            List<Object> params = new ArrayList<>();
            _sql.append(" or bookname like ? or author like ? or publisher like ? ");
            for (int i = 0; i < 3; i++) {
                params.add("%" + search + "%");
            }

            //继续拼接分页limit条件
            _sql.append("limit ?,?");
            //继续添加分页的参数
            params.add(start);
            params.add(pageSize);

            return jdbcTemplate.query(_sql.toString(),new BeanPropertyRowMapper<>(Book.class),params.toArray());
        }
        /*int start = (currentPage-1)*pageSize;
        String sql = "select * from tab_books where tid = ? order by sales desc limit ? , ? ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), tid, start, pageSize);*/
    }

    @Override
    public int findTotalCount(String search) {
        StringBuilder _sql = new StringBuilder("select count(*) from tab_books where 1=2 ");
            List<Object> params = new ArrayList<>();
            _sql.append(" or bookname like ? or author like ? or publisher like ? ");
            for (int i = 0; i < 3 ; i++) {
                params.add("%" + search + "%");
        }
        Integer count = jdbcTemplate.queryForObject(_sql.toString(),Integer.class,params.toArray());
        return count;
    }

}
