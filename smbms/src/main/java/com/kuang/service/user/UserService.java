package com.kuang.service.user;

import com.kuang.pojo.Role;
import com.kuang.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public User login(String userCode, String password) throws SQLException, ClassNotFoundException;

    public boolean updatePwd(long id, String password);

    public int getUserCount(String username, int userRole);

    //获取用户列表
    public List<User> getUserList(String username, int userRole, int currentPageNo, int pageSize
    );


}
