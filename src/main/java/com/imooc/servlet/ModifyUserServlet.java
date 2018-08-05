package com.imooc.servlet;

import com.imooc.beans.User;
import com.imooc.service.UserService;
import com.imooc.service.UserServiceImpl;
import com.imooc.utils.EncryptUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifyUserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathName = request.getServletPath();

        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            if (pathName.equals("/my/info/modify/submit")) {

//                String name = request.getParameter("name");
                String realName = request.getParameter("realName");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");

                String password = request.getParameter("password");
                if (password != null && !password.equals("")) {
                    String encryptedPassword = EncryptUtil.getEncryptedString(password);
                    user.setPassword(encryptedPassword);
                }

                user.setRealName(realName);
                user.setPhone(phone);
                user.setAddress(address);

                String birthday = request.getParameter("birthday");
                if (birthday != null && !birthday.equals("")) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = format.parse(birthday);
                        user.setBirthday(new java.sql.Date(date.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("生日日期转换失败");
                        request.getRequestDispatcher("/WEB-INF/views/biz/edit_user.jsp").forward(request, response);
                    }
                }

                boolean b = userService.updateUser(user);

                response.sendRedirect(request.getContextPath() + "/my/info");

            } else {
                request.getRequestDispatcher("/WEB-INF/views/biz/edit_user.jsp").forward(request, response);
            }
        }
        return;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}