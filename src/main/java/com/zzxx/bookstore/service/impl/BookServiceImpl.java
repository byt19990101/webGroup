package com.zzxx.bookstore.service.impl;

import com.zzxx.bookstore.dao.BookDao;
import com.zzxx.bookstore.dao.impl.BookDaoImpl;
import com.zzxx.bookstore.domain.Book;
import com.zzxx.bookstore.service.BookService;
import com.zzxx.bookstore.util.PageBean;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bd = new BookDaoImpl();
    @Override
    public Book findOne(int bid) {
        Book book = bd.findByBid(bid);
        /*int sales = bd.findSalesByBid(bid);

        book.setSales(sales);*/

        return book;
    }

    @Override
    public PageBean<Book> findPage(int tid, String _currentPage, String _pageSize, String search) {
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        int totalCount ;
        if(tid != 0){
            totalCount = bd.findCount(tid);
        }else{
            totalCount = bd.findTotalCount(search);
        }

        List<Book> list = bd.findByPage(tid,currentPage,pageSize,search);

        int totalPage = (totalCount + pageSize - 1) / pageSize;

        PageBean<Book> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setList(list);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }
        /*Integer currentPage = Integer.valueOf(_currentPage);
        Integer pageSize = Integer.valueOf(_pageSize);
        PageBean<Book> pageBean = new PageBean<>();

        int totalCount = bd.findCount(tid);
        List<Book> list = bd.findByPage(tid,currentPage,pageSize,tname);

        int totalPage = (totalCount + pageSize - 1) / pageSize;

        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setList(list);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }*/


}
