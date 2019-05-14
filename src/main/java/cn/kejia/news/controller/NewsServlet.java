package cn.kejia.news.controller;

import cn.kejia.news.utils.NewsResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/10
 * @Modified By：
 */
@WebServlet(name = "NewsServlet",urlPatterns = "/news")
public class NewsServlet extends BaseServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String content = request.getParameter("content");
        System.out.println(content);
        response.getWriter().write(JSON.toJSONString(NewsResult.build(200, "失败！！！！")));
    }
}
