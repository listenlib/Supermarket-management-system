package com.kuang.serlvet.user;

import com.kuang.pojo.User;
import com.kuang.service.user.UserService;
import com.kuang.service.user.UserServiceImpl;
import com.kuang.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("userCode");
        String password = req.getParameter("userPassword");
        UserService userService = new UserServiceImpl();
        try {
            User user = userService.login(username, password);

            if (user != null) {
                req.getSession().setAttribute(Constants.USER_SESSION, user);
                resp.sendRedirect("jsp/frame.jsp");

            } else {
                req.setAttribute("error", "用户名或者密码不正确");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
