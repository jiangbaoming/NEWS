package cn.kejia.news.service.impl;

import cn.kejia.news.dao.OrganizationDao;
import cn.kejia.news.dao.UserDao;
import cn.kejia.news.dao.impl.OrganizationDaoImpl;
import cn.kejia.news.dao.impl.UserDaoImpl;
import cn.kejia.news.model.Organization;
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



    public User getUserByUserCode(String userCode) {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByUserCode(userCode);
        return user;
    }

    @Override
    public List<User> getUsers(Integer pageNum, Integer pageSize) {
        UserDao userDao = new UserDaoImpl();
        OrganizationDao organizationDao = new OrganizationDaoImpl();
        List<User> userList = userDao.getUsers((pageNum - 1) * pageSize, pageSize);
        for (User user : userList) {
            Organization organization = organizationDao.getOrganizationById(user.getOid());
            user.setOrganizationName(organization.getOname());
        }
        return userList;
    }

    @Override
    public List<User> getUsers(Integer pageNum, Integer pageSize, String param) {
        UserDao userDao = new UserDaoImpl();
        List<User> userList = userDao.getUsers((pageNum - 1) * pageSize, pageSize, param);
        return userList;
    }


    @Override
    public int getTotalCount() {
        UserDao userDao = new UserDaoImpl();
        int totalCount = userDao.getTotalCount();
        return totalCount;
    }

    @Override
    public int getTotalCount(String param) {
        UserDao userDao = new UserDaoImpl();
        int totalCount = userDao.getTotalCount(param);
        return totalCount;
    }

    @Override
    public boolean delete(int id) {
        UserDao userDao = new UserDaoImpl();
        int rows = userDao.delete(id);
        return rows > 0;
    }

    @Override
    public boolean userCodeIsExit(String userCode) {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByUserCode(userCode);
        return user == null;
    }

    @Override
    public boolean add(User user) {
        UserDao userDao = new UserDaoImpl();
        int rows = userDao.add(user);
        return rows > 0;
    }

    @Override
    public boolean changeRole(Integer id, Integer role, User loginUser) {
        UserDao userDao = new UserDaoImpl();
        int rows = userDao.changeRole(id, role, loginUser);
        return rows > 0;
    }

    @Override
    public boolean update(User user) {
        UserDao userDao = new UserDaoImpl();
        int rows = userDao.update(user);
        return rows > 0;
    }

    @Override
    public User getUserById(Integer uid) {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserById(uid);
        return user;
    }

    @Override
    public boolean modifyPwd(Integer uid, String newPassword) {
        UserDao userDao = new UserDaoImpl();
        int rows = userDao.updatePassword(uid, newPassword);
        return rows > 0;
    }

    @Override
    public boolean modify(User user) {
        UserDao userDao = new UserDaoImpl();
        int rows = userDao.modify(user);
        return rows > 0;
    }
}
