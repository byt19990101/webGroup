package com.zzxx.bookstore.web.servlet;

import com.zzxx.bookstore.domain.Cart;
import com.zzxx.bookstore.domain.ResultInfo;
import com.zzxx.bookstore.service.CartService;
import com.zzxx.bookstore.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet {
    private CartService cartService = new CartServiceImpl();
    public void  add(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int number = Integer.valueOf(request.getParameter("number"));
        int bid = Integer.valueOf(request.getParameter("bid"));
        int uid = Integer.valueOf(request.getParameter("uid"));
        cartService.add(uid,bid,number);

        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        writeMapper(response,info);
    }

    public void  findCartByUid(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<Cart> list = new ArrayList<>();
        int uid = Integer.valueOf(request.getParameter("uid"));
        list = cartService.findByUid(uid);

        writeMapper(response,list);
    }

    public void  deleteSelect(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int uid = Integer.valueOf(request.getParameter("uid"));
        String[] bids = request.getParameterValues("bids");

        cartService.deleteByBid(uid,bids);

        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        writeMapper(response,info);
    }

    public void  account(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int uid = Integer.valueOf(request.getParameter("uid"));
        String[] bids = request.getParameterValues("bids");

        cartService.account(uid,bids);

        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        writeMapper(response,info);
    }
}
