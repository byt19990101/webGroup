package com.zzxx.bookstore.web.filter;

import com.zzxx.bookstore.domain.User;
import com.zzxx.bookstore.service.UserService;
import com.zzxx.bookstore.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AutoLoginFilter")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;

        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                } else {
                    if (cookie.getName().equals("password")) {
                        password = cookie.getValue();
                    }
                }
            }
            if (username != null && password != null) {
                //该去登陆了

                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                UserService userService = new UserServiceImpl();
                try {
                    User loginUser = userService.findByUsernameAndPwd(user.getUsername(),user.getPassword());
                    HttpSession session = request.getSession();
                    session.setAttribute("loginUser", loginUser);
                } catch (Exception e) {

                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
