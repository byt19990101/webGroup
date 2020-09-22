package com.zzxx.bookstore.service;

import com.zzxx.bookstore.domain.Cart;

import java.util.List;

public interface CartService {
    void add(int uid, int bid,int num);

    List<Cart> findByUid(int uid);

    void deleteByBid(int uid,String[] bids);

    void deleteAll(int uid);

    void account(int uid,String[] bids);
}
