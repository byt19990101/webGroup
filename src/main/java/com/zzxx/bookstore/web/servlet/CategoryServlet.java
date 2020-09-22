package com.zzxx.bookstore.web.servlet;

import com.zzxx.bookstore.domain.Type;
import com.zzxx.bookstore.service.CategoryService;
import com.zzxx.bookstore.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> list = categoryService.findTname();

        writeMapper(response, list);
    }
}
