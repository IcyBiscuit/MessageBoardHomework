package com.imooc.servlet;

import com.imooc.beans.Message;
import com.imooc.beans.User;
import com.imooc.service.MessageService;
import com.imooc.service.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddMessageServlet extends HttpServlet {
    private MessageService messageService = new MessageServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            String pathName = request.getServletPath();
//            System.out.println(pathName);
            if (pathName.equals(("/addmessage/submit"))) {

                String title = request.getParameter("title");
                String content = request.getParameter("content");

                Message message = new Message();
                message.setUsername(user.getName());
                message.setUserId(user.getId());

                message.setTitle(title);
                message.setContent(content);

                boolean b = messageService.addMessage(message);
                if (b) {
                    response.sendRedirect(request.getContextPath() + "/message/listall");
                } else {
                    request.getRequestDispatcher("/WEB-INF/views/biz/add_message.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/WEB-INF/views/biz/add_message.jsp").forward(request, response);
            }
        }
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
