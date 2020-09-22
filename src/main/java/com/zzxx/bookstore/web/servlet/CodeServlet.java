package com.zzxx.bookstore.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzxx.bookstore.domain.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String check_code = request.getParameter("check_code");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");

        ResultInfo info = new ResultInfo();
        // 3.判断验证码
        if (checkcode_server != null && checkcode_server.equalsIgnoreCase(check_code)) {
            info.setFlag(true);
        } else {
            info.setFlag(false);
        }

        response.setContentType("application/json;charset = utf-8");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
