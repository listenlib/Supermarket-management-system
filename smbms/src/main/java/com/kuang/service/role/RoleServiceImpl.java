package com.kuang.service.role;

import com.kuang.dao.BaseDao;
import com.kuang.dao.role.RoleDao;
import com.kuang.dao.role.RoleDaoImpl;
import com.kuang.dao.user.UserDao;
import com.kuang.dao.user.UserDaoImpl;
import com.kuang.pojo.Role;
import com.kuang.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getRoleList() throws SQLException {
        Connection connection = null;
        List<Role> roleList =null;
        try {
            connection = BaseDao.getConnection();

            roleList = roleDao.getRoleList(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return roleList;
    }
@Test
    public  void test() throws SQLException {
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();

    }
}
