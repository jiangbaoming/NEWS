package cn.kejia.news.service;

import cn.kejia.news.model.User;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/09
 * @Modified By：
 */
public interface UserService {

    /**
     * 根据用户编码获取用户
     * @param userCode（唯一）
     * @return 用户
     */
    User getUserByUserCode(String userCode);

    /**
     * 获取所有用户
     * @return
     */
    List<User> getUsers(Integer pageNum,Integer pageSize);

    /**
     * 带条件的分页
     * @param pageNum
     * @param pageSize
     * @param param
     * @return
     */
    List<User> getUsers(Integer pageNum,Integer pageSize,String param);

    /**
     * 获取用户总数
     * @return
     */
    int getTotalCount();

    /**
     * 带条件的总数
     * @param param
     * @return
     */
    int getTotalCount(String param);

    /**
     * 根据ID删除
     * @param parseInt
     * @return
     */
    boolean delete(int parseInt);

    /**
     * 该登录名是否已存在
     * @param userCode
     * @return
     */
    boolean userCodeIsExit(String userCode);

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean add(User user);

    /**
     * 更改用户角色
     * @param id
     * @param role
     * @return
     */
    boolean changeRole(Integer id, Integer role);

    /**
     * 修改
     * @param user
     * @return
     */
    boolean update(User user);
}
