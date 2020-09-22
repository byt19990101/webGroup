package com.zzxx.bookstore.dao;

import com.zzxx.bookstore.domain.Book;

import java.util.List;

public interface BookDao extends BaseDao {

    Book findByBid(int bid);

    Integer findCount(int tid);

    List<Book> findByPage(int tid, Integer currentPage, Integer pageSize, String search);

    int findTotalCount(String search);


//    int findSalesByBid(int bid);
}
