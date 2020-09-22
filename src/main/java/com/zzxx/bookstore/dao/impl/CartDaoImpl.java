package com.zzxx.bookstore.dao.impl;

import com.zzxx.bookstore.dao.CartDao;
import com.zzxx.bookstore.domain.Book;
import com.zzxx.bookstore.domain.Cart;
import com.zzxx.bookstore.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private JdbcTemplate jdbcTemplate =new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public void update(int uid, int bid, int num) {
        Integer ci = 0;
        String sql1 = "select num from tab_cart where uid=? and bid=? ";
        try {
             ci = jdbcTemplate.queryForObject(sql1, Integer.class, uid, bid);
        } catch (Exception e) {
                String sql = "insert into tab_cart (uid, date, bid, num, is_pay) VALUES (?,?,?,?,'N'); ";
                jdbcTemplate.update(sql, uid, new Date(), bid, num);
        }
        String sql = "update tab_cart set num = ? where uid =? and bid = ? ";
        jdbcTemplate.update(sql, ci + num,uid,  bid );
    }

    @Override
    public List<Cart> findByUid(int uid) {
        String sql = "select tc.bid,sum(tc.num) num,tc.date,tb.price from tab_cart as tc ,tab_books as tb ,tab_user as tu where tc.uid = tu.uid and tc.bid = tb.bid and tc.uid = ? and tc.is_pay = 'N' group by bid";
        return  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cart.class),uid);
    }

    @Override
    public List<Book> findBookByUid(int uid) {
        String sql  = "select * from  tab_books where bid in  (SELECT  bid FROM tab_cart where uid = ? and is_pay = \"N\" )";
        try {
            List<Book> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), uid);
            return query;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void deleteOne(int uid, int bid) {
        String sql = "delete  from tab_cart where uid = ? and bid = ?";
        jdbcTemplate.update(sql,uid,bid);
    }

    @Override
    public void deleteAll(int uid) {
        String sql = "delete  from tab_cart where uid = ? and is_pay = \"N\" ";
        jdbcTemplate.update(sql,uid);
    }

    @Override
    public void account(int uid, int bid) {
        Integer a = 0;
        Integer b = 0;
        String sql1 = "update book_store.tab_cart set is_pay = \"Y\"  where uid = ? and bid = ? ";
        jdbcTemplate.update(sql1,uid,bid);

        String sql2 = "select sales from tab_books where bid = ? ";
        String sql3 = "select num from tab_cart where uid = ? and bid = ?";
        try {
            a = jdbcTemplate.queryForObject(sql2,Integer.class,bid);
            b = jdbcTemplate.queryForObject(sql3,Integer.class,uid,bid);
        }catch (Exception e){
            return;
        }
        String sql4 = "update tab_books set sales = ? where  bid = ? ";
        jdbcTemplate.update(sql4, a+b, bid );

    }
}
