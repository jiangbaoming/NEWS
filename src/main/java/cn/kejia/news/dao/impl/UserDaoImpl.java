package cn.kejia.news.dao.impl;


import cn.kejia.news.dao.BaseDao;
import cn.kejia.news.dao.UserDao;
import cn.kejia.news.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/12
 * @Modified By：
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public Object tableToObject(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserCode(rs.getString("userCode"));
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setRole(rs.getInt("role"));
        user.setOid(rs.getInt("oid"));
        user.setCreator(rs.getString("creator"));
        user.setCreateDate(rs.getDate("createDate"));
        user.setModifier(rs.getString("modifier"));
        user.setModifyDate(rs.getDate("modifyDate"));
        return user;
    }

    @Override
    public User getUserByUserCode(String userCode) {
        User test = null;
        String sql = "select * from user where userCode = ? ";
        Object[] params = {userCode};
        rs = executeQuriy(sql, params);
        try {
            while (rs.next()) {
                test = (User) tableToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return test;
    }

    @Override
    public List<User> getUsers(Integer pageNum, Integer pageSize) {
        List<User> users = new ArrayList<>();
        String sql = "select * from user limit ?,?";
        Object[] params = {pageNum, pageSize};
        rs = executeQuriy(sql, params);
        try {
            while (rs.next()) {
                User user = (User) tableToObject(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return users;
    }

    @Override
    public List<User> getUsers(Integer pageNum, Integer pageSize, String param) {
        List<User> users = new ArrayList<>();
        String sql = "select * from user where userCode like \"%\"?\"%\" or userName like \"%\"?\"%\" or phone like \"%\"?\"%\" limit ?,?";
        Object[] params = {param, param, param, pageNum, pageSize};
        rs = executeQuriy(sql, params);
        try {
            while (rs.next()) {
                User user = (User) tableToObject(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return users;
    }

    @Override
    public int getTotalCount() {
        String sql = "select count(1) from user";
        rs = executeQuriy(sql, null);
        int total = 0;
        try {
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return total;
    }

    @Override
    public int getTotalCount(String param) {
        String sql = "select count(1) from user where userCode like \"%\"?\"%\" or userName like \"%\"?\"%\" or phone like \"%\"?\"%\"";
        Object[] params = {param, param, param};
        rs = executeQuriy(sql, params);
        int total = 0;
        try {
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return total;
    }

    @Override
    public int updatePassword(Integer id, String newPassword) {
        String sql = "update user set password = ? where id = ?";
        Object[] params = {newPassword, id};
        int rows = executeUpdata(sql, params);
        return rows;
    }

    @Override
    public int update(User user) {
        String sql = "update user set  userName = ?, phone = ?, oid = ?, modifier = ?, modifyDate = ? where id = ?";
        Object[] params = {user.getUserName(), user.getPhone(), user.getOid(), user.getModifier(), user.getModifyDate(), user.getId()};
        int rows = executeUpdata(sql, params);
        return rows;
    }

    @Override
    public int delete(Integer uid) {
        String sql = "delete from user where id= ?";
        Object[] params = {uid};
        int rows = executeUpdata(sql, params);
        return rows;
    }

    @Override
    public int add(User user) {
        String sql = "insert into user(userCode, userName,password,phone,oid,creator,createDate,modifier,modifyDate,role)values(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {user.getUserCode(), user.getUserName(), user.getPassword(), user.getPhone(), user.getOid(), user.getCreator(), user.getCreateDate(), user.getModifier(), user.getModifyDate(), user.getRole()};
        int rows = executeUpdata(sql, params);
        return rows;
    }

    @Override
    public int changeRole(Integer id, Integer role, User loginUser) {
        String sql = "update user set  role = ?,modifier = ?, modifyDate = ? where id = ?";
        Object[] params = {role, loginUser.getUserName(), new Date(), id};
        int rows = executeUpdata(sql, params);
        return rows;
    }

    @Override
    public User getUserById(Integer uid) {
        String sql = "select * from user where id = ?";
        Object[] params = {uid};
        rs = executeQuriy(sql, params);
        User user = null;
        try {
            while (rs.next()) {
                user = (User) tableToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return user;
    }

    @Override
    public int modify(User user) {
        String sql = "update user set  userName = ?, phone = ?, modifier = ?, modifyDate = ? where id = ?";
        Object[] params = {user.getUserName(), user.getPhone(), user.getModifier(), user.getModifyDate(), user.getId()};
        int rows = executeUpdata(sql, params);
        return rows;
    }
}
