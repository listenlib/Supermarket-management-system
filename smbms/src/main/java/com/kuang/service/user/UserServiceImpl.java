package com.kuang.service.user;

import com.kuang.dao.BaseDao;
import com.kuang.dao.user.UserDao;
import com.kuang.dao.user.UserDaoImpl;
import com.kuang.pojo.User;
import org.junit.Test;
import sun.nio.cs.US_ASCII;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String password) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        User user = null;
        connection = BaseDao.getConnection();
        user = userDao.getLoginUser(connection, userCode, password);
        BaseDao.closeResource(connection, null, null);

        return user;
    }

    @Override
    public boolean updatePwd(long id, String password) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection, id, password) > 0) {
                flag = true;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public int getUserCount(String username, int userRole) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection, username, userRole);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }


        return count;
    }

    @Override
    public List<User> getUserList(String username, int userRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = new ArrayList<>();

        try {
            connection = BaseDao.getConnection();

            userList = userDao.getUserList(connection, username, userRole, currentPageNo, pageSize);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }


        return userList;
    }

    @Test
    public void test() throws SQLException, ClassNotFoundException {
        UserService userService = new UserServiceImpl();
      List<User> userList= userService.getUserList(null,0,1, 5);

       for (User user:userList){
           System.out.println(user.getUserName());
       }
       
    }
}
