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
    public int getTotalCount() {
        int totalCount = userDao.getTotalCount();
        return totalCount;
    }

    @Override
    public boolean delete(int id) {
        int rows = userDao.delete(id);
        return rows > 0 ? true : false;
    }
}
