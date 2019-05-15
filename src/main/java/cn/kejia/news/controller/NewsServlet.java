package cn.kejia.news.controller;

import cn.kejia.news.model.News;
import cn.kejia.news.model.User;
import cn.kejia.news.service.NewsService;
import cn.kejia.news.service.impl.NewsServiceImpl;
import cn.kejia.news.utils.NewsResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/10
 * @Modified By：
 */
@WebServlet(name = "NewsServlet", urlPatterns = "/news")
public class NewsServlet extends BaseServlet {

    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        NewsService newsService = new NewsServiceImpl();
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        String introduction = request.getParameter("introduction");
        String banner = request.getParameter("banner");
        Integer tid = Integer.parseInt(request.getParameter("tid"));
        News news = new News();
        news.setUid(loginUser.getId());
        news.setTitle(title);
        news.setIntroduction(introduction);
        news.setTid(tid);
        news.setBanner(banner);
        news.setContent(content);
        news.setReleaseDate(new Date());
        news.setTimes(0);
        boolean result = newsService.add(news);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }

    public void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsService newsService = new NewsServiceImpl();
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        User loginUser= (User) request.getSession().getAttribute("loginUser");
        Integer userRole = loginUser.getRole();
        List<News> newsList = null;
        int totalCount = 0;
        switch (userRole) {
            case 1:
                newsList = newsService.getList(pageNum, pageSize,null);
                totalCount = newsService.getTotalCount(null);
                break;
            case 0:
                newsList = newsService.getList(pageNum, pageSize,loginUser.getId());
                totalCount = newsService.getTotalCount(loginUser.getId());
                break;
            default:
                break;
        }
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "ok");
        result.put("totalCount", totalCount);
        result.put("data", newsList);
        response.getWriter().write(JSON.toJSONString(result));

    }
}
