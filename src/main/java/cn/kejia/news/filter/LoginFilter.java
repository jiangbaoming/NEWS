package cn.kejia.news.filter;

import cn.kejia.news.model.User;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/21
 * @Modified By：
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/news")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("登陆过滤");
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (null != loginUser) {
            System.out.println("已登录");
            chain.doFilter(request, response);
        } else {
            System.out.println("未登录");
            response.sendRedirect("/login.html");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
