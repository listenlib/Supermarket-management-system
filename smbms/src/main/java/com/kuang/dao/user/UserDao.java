package com.kuang.dao.user;

import com.kuang.pojo.Role;
import com.kuang.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //得到登录的用户
    public User getLoginUser(Connection connection, String userCode, String password) throws SQLException;

    //修改密码
    public int updatePwd(Connection connection, long id, String password) throws SQLException;

    //获取用户总数
    public int getUserCount(Connection connection, String username, int userRole) throws SQLException;


    //获取用户列表
    public List<User> getUserList(Connection connection, String username, int userRole, int currentPageNo, int pageSize
    ) throws SQLException;


}
