package com.zzxx.bookstore.service;

import com.zzxx.bookstore.domain.Book;
import com.zzxx.bookstore.util.PageBean;

public interface BookService {
    Book findOne(int bid);

    PageBean<Book> findPage(int tid, String currentPage, String pageSize, String search);


}
