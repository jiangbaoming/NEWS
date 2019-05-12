package cn.kejia.news.controller;

import cn.kejia.news.model.User;
import cn.kejia.news.service.UserService;
import cn.kejia.news.service.impl.UserServiceImpl;
import cn.kejia.news.utils.NewsResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/09
 * @Modified By：
 */
@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        user.setUserName("admin");
        user.setRole(1);
        response.getWriter().write(JSON.toJSONString(NewsResult.success(user)));
        /*String imgCode = request.getParameter("imgCode");
        System.out.println("用户输入验证码————————" + imgCode);
        String oimgCode = (String) request.getSession().getAttribute("code");
        System.out.println("原始保存验证码————————" + oimgCode);
        boolean flag = false;
        if (imgCode.equalsIgnoreCase(oimgCode)) {
            flag = true;
        }
        //验证码正确
        if (flag) {
            String userCode = request.getParameter("userCode");
            String password = request.getParameter("password");
            User user = userService.getUserByUserCode(userCode);
            if (null != user) {//用户存在
                //验证密码
                if (password.equals(user.getPassword())) {//密码正确
                    //清空密码
                    user.setPassword(null);
                    response.getWriter().write(JSON.toJSONString(NewsResult.success(user)));
                } else {//密码不正确
                    response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "密码不正确！")));
                }
            } else {//用户不存在
                response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "用户不存在！")));
            }
        }else { //验证码不正确
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "验证码不正确！")));
        }*/
    }
    public void getUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users =userService.getUsers();
        int totalCount=userService.getTotalCount();
        Map<String,Object> result=new HashMap<>();
        result.put("code", 200);
        result.put("msg", "ok");
        result.put("totalCount", totalCount);
        result.put("data", users);
        response.getWriter().write(JSON.toJSONString(result));

    }
}
