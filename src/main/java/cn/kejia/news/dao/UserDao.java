package cn.kejia.news.dao;

import cn.kejia.news.model.User;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/12
 * @Modified By：
 */
public interface UserDao {

    User getUserByUserCode(String userCode);

    List<User> getUsers(Integer pageNum,Integer pageSize);

    List<User> getUsers(Integer pageNum,Integer pageSize,String param);

    int getTotalCount();

    int getTotalCount(String param);

    int updatePassword(Integer id,String newPassword);

    int update(User user);

    int delete(Integer uid);

    int add(User user);

    int changeRole(Integer id, Integer role,User loginUser);

    User getUserById(Integer uid);

    int modify(User user);
}
