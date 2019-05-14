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

    private UserDao userDao = new UserDaoImpl();

    private OrganizationDao organizationDao = new OrganizationDaoImpl();


    public User getUserByUserCode(String userCode) {
        User user = userDao.getUserByUserCode(userCode);
        return user;
    }

    @Override
    public List<User> getUsers(Integer pageNum, Integer pageSize) {
        List<User> userList = userDao.getUsers((pageNum - 1) * pageSize, pageSize);
        for (User user : userList) {
            Organization organization = organizationDao.getOrganizationById(user.getOid());
            user.setOrganizationName(organization.getOname());
        }
        return userList;
    }

    @Override
    public List<User> getUsers(Integer pageNum, Integer pageSize, String param) {
        List<User> userList = userDao.getUsers((pageNum - 1) * pageSize, pageSize, param);
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
    public boolean changeRole(Integer id, Integer role, User loginUser) {
        int rows = userDao.changeRole(id, role, loginUser);
        return rows > 0;
    }

    @Override
    public boolean update(User user) {
        int rows = userDao.update(user);
        return rows > 0;
    }

    @Override
    public User getUserById(Integer uid) {
        User user = userDao.getUserById(uid);
        return user;
    }

    @Override
    public boolean modifyPwd(Integer uid, String newPassword) {
        int rows = userDao.updatePassword(uid, newPassword);
        return rows > 0;
    }

    @Override
    public boolean modify(User user) {
        int rows = userDao.modify(user);
        return rows > 0;
    }
}
