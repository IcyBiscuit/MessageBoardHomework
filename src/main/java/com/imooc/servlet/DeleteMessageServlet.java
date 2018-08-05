package com.imooc.servlet;

import com.imooc.beans.Message;
import com.imooc.service.MessageService;
import com.imooc.service.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteMessageServlet extends HttpServlet {
    private MessageService messageService = new MessageServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long messageId = Long.parseLong(request.getParameter("messageId"));

        boolean b = messageService.deleteMessage(messageId);

        response.sendRedirect(request.getContextPath() + "/message/listall");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
