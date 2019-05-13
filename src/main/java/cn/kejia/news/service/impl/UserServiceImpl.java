package cn.kejia.news.service.impl;

import cn.kejia.news.dao.UserDao;
import cn.kejia.news.dao.impl.UserDaoImpl;
import cn.kejia.news.model.User;
import cn.kejia.news.service.UserService;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/09
 * @Modified By：
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();


    public User getUserByUserCode(String userCode) {
        User user = userDao.getUserByUserCode(userCode);
        return user;
    }

    @Override
    public List<User> getUsers(Integer pageNum, Integer pageSize) {
        List<User> userList = userDao.getUsers((pageNum - 1) * pageSize, pageSize);
        return userList;
    }

    @Override
    public List<User> getUsers(Integer pageNum, Integer pageSize, String param) {
        List<User> userList = userDao.getUsers((pageNum - 1) * pageSize, pageSize,param);
        return userList;
    }


    @Override
    public int getTotalCount() {
        int totalCount = userDao.getTotalCount();
        return totalCount;
    }

    @Override
    public int getTotalCount(String param) {
        int totalCount = userDao.getTotalCount(param);
        return totalCount;
    }

    @Override
    public boolean delete(int id) {
        int rows = userDao.delete(id);
        return rows > 0;
    }

    @Override
    public boolean userCodeIsExit(String userCode) {
        User user = userDao.getUserByUserCode(userCode);
        return user == null;
    }

    @Override
    public boolean add(User user) {
        int rows = userDao.add(user);
        return rows > 0;
    }

    @Override
    public boolean changeRole(Integer id, Integer role) {
        int rows = userDao.changeRole(id, role);
        return rows > 0;
    }

    @Override
    public boolean update(User user) {
        int rows = userDao.update(user);
        return rows > 0;
    }
}
