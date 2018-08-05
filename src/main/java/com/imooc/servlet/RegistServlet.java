package com.imooc.servlet;

import com.google.code.kaptcha.Constants;
import com.imooc.beans.User;
import com.imooc.service.UserService;
import com.imooc.service.UserServiceImpl;
import com.imooc.utils.EncryptUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathName = request.getServletPath();
        if (pathName.equals("/regist/submit")) {
            String kcode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            String idcode = request.getParameter("kaptcha");

            if (idcode != null && !idcode.trim().equals("") && idcode.equalsIgnoreCase(kcode)) {

                String name = request.getParameter("username");
                String password = request.getParameter("password");
                String passwordRepeat = request.getParameter("passwordRepeat");
                User user = userService.getUser(name);

                if (user != null) {
                    request.setAttribute("usernameerrmsg", "用户名已存在");
                    request.getRequestDispatcher("/WEB-INF/views/biz/regist.jsp").forward(request, response);
                } else if (password.equals(passwordRepeat)) {
                    user = new User();
                    user.setName(name);

                    String encryptedPassword = EncryptUtil.getEncryptedString(password);
                    user.setPassword(encryptedPassword);

                    boolean b = userService.addUser(user);
                    if (b) {
                        request.getSession().setAttribute("user", userService.getUser(name));
                        response.sendRedirect(request.getContextPath() + "/message/listall");
                        return;
                    } else {
                        request.setAttribute("verifyerrmsg", "注册失败");
                        request.getRequestDispatcher("/WEB-INF/views/biz/regist.jsp").forward(request, response);
                        return;

                    }
                } else {
                    request.setAttribute("verifyerrmsg", "两次输入的密码不一致");
                    request.getRequestDispatcher("/WEB-INF/views/biz/regist.jsp").forward(request, response);
                    return;
                }

            } else {
                request.setAttribute("kaptchaerrmsg", "验证码输入有误");
                request.getRequestDispatcher("/WEB-INF/views/biz/regist.jsp").forward(request, response);
                return;
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/views/biz/regist.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
