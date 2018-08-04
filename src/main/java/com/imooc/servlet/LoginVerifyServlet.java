package com.imooc.servlet;


import com.google.code.kaptcha.Constants;
import com.imooc.beans.User;
import com.imooc.service.UserService;
import com.imooc.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginVerifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kcode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String idcode = request.getParameter("kaptcha");

//        System.out.println(kcode);
//        System.out.println(idcode);

        if (idcode != null && !idcode.trim().equals("") && idcode.equalsIgnoreCase(kcode)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserService userService = new UserServiceImpl();
            User user = userService.login(username, password);

            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath()+"/message/listall");
            } else {
                request.setAttribute("verifyerrmsg", "用户名或密码有误");
                request.getRequestDispatcher("/WEB-INF/views/biz/login.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("kaptchaerrmsg", "验证码输入有误");
            request.getRequestDispatcher("/WEB-INF/views/biz/login.jsp").forward(request, response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
