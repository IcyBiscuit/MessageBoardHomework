package com.imooc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class BasePathFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        String basePath = String.format("%s://%s:%s%s",request.getScheme(),request.getServerName(),request.getServerPort(),request.getContextPath());
        req.setAttribute("basepath",basePath);
//        System.out.println(basePath);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
