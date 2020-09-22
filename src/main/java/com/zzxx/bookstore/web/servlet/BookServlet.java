package com.zzxx.bookstore.web.servlet;

import com.zzxx.bookstore.domain.Book;
import com.zzxx.bookstore.service.BookService;
import com.zzxx.bookstore.service.impl.BookServiceImpl;
import com.zzxx.bookstore.util.PageBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@WebServlet("/book/*")
public class BookServlet extends BaseServlet {
    private BookService bs = new BookServiceImpl();

    public void queryPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String _tid = request.getParameter("tid");
        if (_tid==null||"".equals(_tid)){
            _tid = "0";
        }
        int tid = Integer.parseInt(_tid);

        String str = request.getParameter("search");
        if(str == null || "".equals(str)){
            str = "";
        }
        String search = URLDecoder.decode(str,"utf-8");

        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        if (currentPage==null||"".equals(currentPage)){
            currentPage = "1";
        }
        if (pageSize==null||"".equals(pageSize)){
            pageSize = "6";
        }

        PageBean<Book> pageBean = bs.findPage(tid,currentPage,pageSize,search);

        writeMapper(response,pageBean);
        /*String _tid = request.getParameter("tid");
        if (_tid==null||"".equals(_tid)){
            _tid = "0";
        }
        int tid = Integer.valueOf(_tid);

        String search = request.getParameter("search");

        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        if (currentPage==null||"".equals(currentPage)){
            currentPage = "1";
        }
        if (pageSize==null||"".equals(pageSize)){
            pageSize = "6";
        }

        PageBean<Book> pageBean = bs.findPage(tid,currentPage,pageSize,search);
        writeMapper(response,pageBean);*/
    }


    public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int bid = Integer.parseInt(request.getParameter("bid"));
        Book book = bs.findOne(bid);

        writeMapper(response,book);
    }
}
