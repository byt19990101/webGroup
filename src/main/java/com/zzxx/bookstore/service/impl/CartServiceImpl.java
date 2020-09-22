package com.zzxx.bookstore.service.impl;

import com.zzxx.bookstore.dao.CartDao;
import com.zzxx.bookstore.dao.impl.CartDaoImpl;
import com.zzxx.bookstore.domain.Book;
import com.zzxx.bookstore.domain.Cart;
import com.zzxx.bookstore.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();
//    private Cart cart = new Cart();
    @Override
    public void add(int uid, int bid,int num) {
        cartDao.update(uid,bid,num);
    }

    @Override
    public List<Cart> findByUid(int uid) {
        List<Book> listBook = cartDao.findBookByUid(uid);
        List<Cart> listCart =  cartDao.findByUid(uid);
        for (int i = 0; i < listCart.size(); i++) {
            listCart.get(i).setBook(listBook.get(i));
        }
        return listCart;
    }

    @Override
    public void deleteByBid(int uid,String[] bids) {
        for (String _id : bids){
            if (_id==null||"".equals(_id)){
                return;
            }
            int bid = Integer.parseInt(_id);
            cartDao.deleteOne(uid,bid);
        }
    }

    @Override
    public void deleteAll(int uid) {
        cartDao.deleteAll(uid);
    }

    @Override
    public void account(int uid,String[] bids) {
        for (String _id : bids){
            if (_id==null||"".equals(_id)){
                return;
            }
            int bid = Integer.parseInt(_id);
            cartDao.account(uid,bid);
        }
    }
}
