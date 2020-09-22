package com.zzxx.bookstore.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response){
        //用路径判断调用哪个方法
        String uri = request.getRequestURI();
        String method = uri.substring(uri.lastIndexOf("/")+1);
        //1.反射  获得method所在字节码对象
        //this是当前对象,谁调用这个方法,对象就是谁

        try {
            Method method1 = this.getClass().getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            try {
                method1.invoke(this,request,response);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    protected void writeMapper(HttpServletResponse response,Object obj) throws IOException {
        response.setContentType("application/json;charset = utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),obj);
    }
}
