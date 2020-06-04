package com.kuang.dao.user;

import com.kuang.dao.BaseDao;
import com.kuang.pojo.Role;
import com.kuang.pojo.User;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User getLoginUser(Connection connection, String userCode, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        User user = null;


        if (connection != null) {
            String sql = "select * from smbms_user where userCode=? and userPassword=?";
            Object[] params = {userCode, password};
            resultSet = BaseDao.execute(connection, sql, params, resultSet, preparedStatement);

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setCreationDate(resultSet.getDate("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyDate(resultSet.getDate("modifyDate"));
                user.setIdPicPath(resultSet.getString("idPicPath"));
                user.setWorkPicPath(resultSet.getString("workPicPath"));

            }
        }

        BaseDao.closeResource(null, preparedStatement, resultSet);


        return user;
    }


    @Override
    public int updatePwd(Connection connection, long id, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection != null) {
            String sql = "update  smbms_user set  userPassword=? where id=?";
            Object[] params = {password, id};
            execute = BaseDao.execute(connection, sql, params, preparedStatement);
        }
        return execute;
    }

    @Override
    public int getUserCount(Connection connection, String username, int userRole) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int count = 0;

        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole=r.id");

            ArrayList<Object> list = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(username)) {
                sql.append(" and u.userName like ?");
                list.add("%" + username + "%");
            }
            if (userRole > 0) {
                sql.append(" and r.id=?");
                list.add(userRole);
            }
            Object[] params = list.toArray();

            rs = BaseDao.execute(connection, sql.toString(), params, rs, preparedStatement);
            if (rs.next()) {
                count = rs.getInt("count");
            }

            BaseDao.closeResource(null, preparedStatement, rs);


        }

        return count;
    }

    @Override
    public List<User> getUserList(Connection connection, String username, int userRole, int currentPageNo, int pageSize) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<User> userList = new ArrayList<User>();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select *  from smbms_user u,smbms_role r where u.userRole=r.id");

            List<Object> list = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(username)) {
                sql.append(" and u.userName like ?");
                list.add("%" + username + "%");
            }
            if (userRole > 0) {
                sql.append(" and r.id=?");
                list.add(userRole);
            }

            sql.append(" order by  r.creationDate desc  limit ?,?");

            list.add((currentPageNo - 1) * pageSize);
            list.add(pageSize);
            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, preparedStatement);


            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getDate("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getDate("modifyDate"));
                user.setIdPicPath(rs.getString("idPicPath"));
                user.setWorkPicPath(rs.getString("workPicPath"));
                user.setUserRoleName(rs.getString("roleName"));
                System.out.println(rs.getString("roleName"));
                userList.add(user);

            }
            BaseDao.closeResource(null, preparedStatement, rs);

        }

        return userList;
    }


}
