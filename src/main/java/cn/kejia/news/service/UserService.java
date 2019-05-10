package cn.kejia.news.service;

import cn.kejia.news.model.User;

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
}
