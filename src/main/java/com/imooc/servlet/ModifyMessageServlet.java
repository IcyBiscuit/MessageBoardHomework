package com.imooc.servlet;

import com.imooc.beans.Message;
import com.imooc.service.MessageService;
import com.imooc.service.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModifyMessageServlet extends HttpServlet {
    private MessageService messageService = new MessageServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathName = request.getServletPath();
        Long messageId = Long.parseLong(request.getParameter("messageId"));
        Message message = messageService.getMessage(messageId);


        if (pathName.equals("/message/admin/modify/submit")) {

            String title = request.getParameter("title");
            String content = request.getParameter("content");

            message.setTitle(title);
            message.setContent(content);

            boolean b = messageService.updateMessage(message);

            response.sendRedirect(request.getContextPath() + "/message/listall");

        } else {

            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/views/biz/modify_message.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
