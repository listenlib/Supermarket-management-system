package com.kuang.dao.role;

import com.kuang.dao.BaseDao;
import com.kuang.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    @Override
    public List<Role> getRoleList(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "select * from smbms_role";
        List<Role> roleList = new ArrayList<>();
        Object[] params = {};
        if (connection != null) {
            rs = BaseDao.execute(connection, sql, params, rs, preparedStatement);
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleName(rs.getString("roleName"));
                role.setRoleCode(rs.getString("roleCode"));
                roleList.add(role);
            }
        }
        BaseDao.closeResource(connection, preparedStatement, rs);
        return roleList;
    }
}
