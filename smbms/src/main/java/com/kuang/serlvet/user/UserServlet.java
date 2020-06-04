package com.kuang.serlvet.user;

import com.alibaba.fastjson.JSONArray;
import com.kuang.pojo.Role;
import com.kuang.pojo.User;
import com.kuang.service.role.RoleService;
import com.kuang.service.role.RoleServiceImpl;
import com.kuang.service.user.UserService;
import com.kuang.service.user.UserServiceImpl;
import com.kuang.utils.Constants;
import com.kuang.utils.PageSupport;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String method = req.getParameter("method");
        if (method.equals("savewd") && method != null) {
            this.updatePwd(req, resp);
        } else if (method.equals("pwdmodify") && method != null) {
            this.pwdModify(req, resp);
        } else if (method.equals("query") && method != null) {

            try {
                this.query(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 修改密码
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag = false;
        if (o != null && !StringUtils.isNullOrEmpty(newpassword)) {

            UserServiceImpl userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) o).getId(), newpassword);
            if (flag) {
                req.setAttribute("message", "密码修改成功，请退出");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            } else {
                req.setAttribute("message", "密码修改失败");
            }

        } else {
            req.setAttribute("message", "新密码有问题");
        }

        req.getRequestDispatcher("/jsp/pwdmodify.jsp").forward(req, resp);
    }


    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");

        Map<String, String> resultMap = new HashMap<String, String>();

        if (o == null) {
            resultMap.put("result", "sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldpassword)) {
            resultMap.put("result", "error");
        } else {
            String userPassword = ((User) o).getUserPassword();
            if (userPassword.equals(oldpassword)) {
                resultMap.put("result", "true");
            } else {
                resultMap.put("result", "false");
            }
        }

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String userName = req.getParameter("queryUserName");
        String temp = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole = 0;

        if (userName == null) {
            userName = "";
        }

        int pageSize = 5;
        int currentPageNo = 1;
        UserService userService = new UserServiceImpl();


        if (temp != null && !temp.equals("")) {
            queryUserRole = Integer.parseInt(temp);
        }

        if (pageIndex != null) {
            currentPageNo = Integer.parseInt(pageIndex);
        }

        int totalCount = userService.getUserCount(userName, queryUserRole);
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setTotalPageCount(totalCount, pageSize);
        pageSupport.setPageSize(pageSize);

        int totalPageCount = pageSupport.getTotalPageCount();

        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }


        List<User> userList = userService.getUserList(userName, queryUserRole, currentPageNo, pageSize);

        req.setAttribute("userList", userList);

        RoleService roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        req.setAttribute("roleList", roleList);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo", currentPageNo);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("queryUserName", userName);
        req.setAttribute("queryUserRole", queryUserRole);
        req.getRequestDispatcher("userlist.jsp").forward(req, resp);

    }
}
