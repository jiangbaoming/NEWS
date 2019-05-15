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
import java.security.interfaces.RSAKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/09
 * @Modified By：
 */
@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    public void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        //用户输入验证码
        String imgCode = request.getParameter("imgCode");
        //原始保存验证码
        String oimgCode = (String) request.getSession().getAttribute("code");
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
                    request.getSession().setAttribute("loginUser", user);
                    response.getWriter().write(JSON.toJSONString(NewsResult.success(user)));
                } else {//密码不正确
                    response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "密码不正确！")));
                }
            } else {//用户不存在
                response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "用户不存在！")));
            }
        }else { //验证码不正确
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "验证码不正确！")));
        }
    }

    public void getUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<User> users = userService.getUsers(pageNum, pageSize);
        int totalCount = userService.getTotalCount();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "ok");
        result.put("totalCount", totalCount);
        result.put("data", users);
        response.getWriter().write(JSON.toJSONString(result));

    }

    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String id = request.getParameter("id");
        boolean result = userService.delete(Integer.parseInt(id));
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(500, "服务器出错！")));
        }
    }

    public void userCodeIsExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String userCode = request.getParameter("userCode");
        boolean result = userService.userCodeIsExit(userCode);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "该登录名不可用！")));
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        Integer oid = Integer.parseInt(request.getParameter("oid"));
        Integer role = Integer.parseInt(request.getParameter("role"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhone(phone);
        user.setOid(oid);
        user.setRole(role);
        user.setCreator(loginUser.getUserName());
        user.setCreateDate(new Date());
        user.setModifier(loginUser.getUserName());
        user.setModifyDate(new Date());
        boolean result = userService.add(user);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }

    public void changeRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        Integer role = Integer.parseInt(request.getParameter("role"));
        Integer id = Integer.parseInt(request.getParameter("id"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        boolean result = userService.changeRole(id, role, loginUser);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String param = request.getParameter("param");
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<User> users = userService.getUsers(pageNum, pageSize, param);
        int totalCount = userService.getTotalCount(param);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "ok");
        result.put("totalCount", totalCount);
        result.put("data", users);
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String userName = request.getParameter("userName");
        String phone = request.getParameter("phone");
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer oid = Integer.parseInt(request.getParameter("oid"));
        /*Integer role = Integer.parseInt(request.getParameter("role"));*/
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setPhone(phone);
        user.setOid(oid);
        /*user.setRole(role);*/
        user.setModifier(loginUser.getUserName());
        user.setModifyDate(new Date());
        boolean result = userService.update(user);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }

    public void modify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String userName = request.getParameter("userName");
        String phone = request.getParameter("phone");
        Integer id = Integer.parseInt(request.getParameter("id"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setPhone(phone);
        user.setModifier(loginUser.getUserName());
        user.setModifyDate(new Date());
        boolean result = userService.modify(user);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }

    public void modifyPwd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        Integer uid = Integer.parseInt(request.getParameter("uid"));
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        User user=userService.getUserById(uid);
        if (oldPassword.equals(user.getPassword())){
            boolean result=   userService.modifyPwd(uid,newPassword);
            if (result){
                response.getWriter().write(JSON.toJSONString(NewsResult.success()));
            }else {
                response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错!")));
            }
        }else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "原密码错误")));
        }
    }

    public void getUserInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        Integer id=Integer.parseInt(request.getParameter("id"));
        User user=userService.getUserById(id);
        response.getWriter().write(JSON.toJSONString(NewsResult.success(user)));
    }
}
