package com.zzxx.bookstore.web.servlet;

import com.zzxx.bookstore.domain.ResultInfo;
import com.zzxx.bookstore.domain.User;
import com.zzxx.bookstore.service.UserService;
import com.zzxx.bookstore.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.security.auth.login.LoginException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService us = new UserServiceImpl();

    //检查用户名是否存在
    public void checkUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = request.getParameter("username");
        boolean isExists = us.checkUser(username);

        ResultInfo info = new ResultInfo();
        info.setFlag(!isExists);

        writeMapper(response,info);
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = us.insertUser(user);

        ResultInfo info = new ResultInfo();
        info.setFlag(flag);

        if (!flag){
            info.setErrorMsg("注册失败!");
        }
        writeMapper(response,info);
    }

    //激活
    public void  active(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String code = request.getParameter("code");
        boolean flag = us.active(code);

       response.setContentType("text/html;charset=utf-8");

        if (flag){
            response.sendRedirect(request.getContextPath()+"/login.html");
        }else {
            response.getWriter().write("激活失败,请联系管理员");
        }
    }

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String check = request.getParameter("check");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ResultInfo info = new ResultInfo();
        // 3.判断验证码
        if (check == null || !check.equalsIgnoreCase(checkcode_server)) {
            info.setErrorMsg("验证码错误");
            info.setFlag(false);
        } else {
            try {
                User user = us.findByUsernameAndPwd(username,password);
                request.getSession().setAttribute("loginUser",user);
                info.setFlag(true);
            } catch (LoginException e) {
                info.setFlag(false);
                info.setErrorMsg(e.getMessage());
            }
        }
        writeMapper(response,info);

    }

    //修改个人信息
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = us.update(user);

        ResultInfo info = new ResultInfo();
        info.setFlag(flag);
        System.out.println(user.getName());

        if (!flag){
            info.setErrorMsg("修改失败!");
        }else {
            info.setErrorMsg("修改成功!");
        }
        writeMapper(response,info);
    }

    //获取登录信息
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws Exception{
        User user = (User) request.getSession().getAttribute("loginUser");

       writeMapper(response,user);
    }

    //登出
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.getSession().removeAttribute("loginUser");
        response.sendRedirect(request.getContextPath()+"/login.html");
    }



}
