package com.kuang.filter;


import com.kuang.pojo.User;
import com.kuang.utils.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        if (user == null) {
            response.sendRedirect(((HttpServletRequest) req).getContextPath() + "/error.jsp");
        }else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
