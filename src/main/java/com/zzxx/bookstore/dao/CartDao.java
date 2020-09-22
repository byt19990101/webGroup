package com.zzxx.bookstore.dao;

import com.zzxx.bookstore.domain.Book;
import com.zzxx.bookstore.domain.Cart;

import java.util.List;

public interface CartDao {
    void update(int bid, int uid, int num);

    List<Cart> findByUid(int uid);

    List<Book> findBookByUid(int uid);

    void deleteOne(int uid, int bid);

    void deleteAll(int uid);


    void account(int uid, int bid);
}
