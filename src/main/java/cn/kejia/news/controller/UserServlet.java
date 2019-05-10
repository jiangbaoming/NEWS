package cn.kejia.news.controller;

import cn.kejia.news.model.User;
import cn.kejia.news.service.UserService;
import cn.kejia.news.service.impl.UserServiceImpl;
import cn.kejia.news.utils.NewsResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/09
 * @Modified By：
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user=new User();
        user.setUserName("admin");
        user.setRole(1);
        response.getWriter().write(JSON.toJSONString(NewsResult.success(user)));
       /* String userCode = request.getParameter("userCode");
        String password = request.getParameter("password");
        User user = userService.getUserByUserCode(userCode);
        if (null != user) {//用户存在
            if (password.equals(user.getPassword())) {//验证密码
                //清空密码
                user.setPassword(null);
                response.getWriter().write(JSON.toJSONString(NewsResult.success(user)));
            } else {
                response.getWriter().write(JSON.toJSONString(NewsResult.build(500, "密码不正确！")));
            }
        } else {//用户不存在
            response.getWriter().write(JSON.toJSONString(NewsResult.build(404, "用户不存在！")));
        }*/
    }
}
