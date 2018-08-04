package com.imooc.servlet;

import com.imooc.beans.Message;
import com.imooc.beans.User;
import com.imooc.service.MessageService;
import com.imooc.service.MessageServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserMessageServlet extends HttpServlet {
    int pageSize;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pageSize = Integer.parseInt(config.getInitParameter("pageSize"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");


        if (user != null) {
            String pageStr = request.getParameter("page");//当前页码

            int page = 1;//页码默认值为1

            if (null != pageStr && (!"".equals(pageStr))) {
                try {
                    page = Integer.parseInt(pageStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            MessageService messageService = new MessageServiceImpl();
            List<Message> messages = messageService.listMessageById(user.getId(), page, pageSize);

            int count = messageService.countMessage(user.getId());
            int last = count % pageSize == 0 ? (count / pageSize) : ((count / pageSize) + 1);

            request.setAttribute("messages", messages);
            request.setAttribute("page", page);
            request.setAttribute("last",last);
            request.getRequestDispatcher("/WEB-INF/views/biz/usermessage_list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
