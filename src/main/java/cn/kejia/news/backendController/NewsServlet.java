package cn.kejia.news.backendController;

import cn.kejia.news.model.News;
import cn.kejia.news.model.User;
import cn.kejia.news.service.NewsService;
import cn.kejia.news.service.NewsTypeService;
import cn.kejia.news.service.impl.NewsServiceImpl;
import cn.kejia.news.service.impl.NewsTypeServiceImpl;
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
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        String tidStr=request.getParameter("tid");
        Integer tid=null;
        if (null != tidStr && ! "".equals(tidStr)){
            tid  =Integer.parseInt(tidStr);
        }
        Integer userRole = loginUser.getRole();
        List<News> newsList = null;
        int totalCount = 0;
        switch (userRole) {
            case 1:
                newsList = newsService.getList(pageNum, pageSize, null, null,tid);
                totalCount = newsService.getTotalCount(null, null,tid);
                break;
            case 0:
                newsList = newsService.getList(pageNum, pageSize, loginUser.getId(), null,tid);
                totalCount = newsService.getTotalCount(loginUser.getId(), null,tid);
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

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        NewsService newsService = new NewsServiceImpl();
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        String introduction = request.getParameter("introduction");
        String banner = request.getParameter("banner");
        Integer tid = Integer.parseInt(request.getParameter("tid"));
        Integer nid = Integer.parseInt(request.getParameter("nid"));
        News news = new News();
        news.setNid(nid);
        news.setTitle(title);
        news.setIntroduction(introduction);
        news.setTid(tid);
        news.setBanner(banner);
        news.setContent(content);
        news.setReleaseDate(new Date());
        boolean result = newsService.update(news);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsService newsService = new NewsServiceImpl();
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String title = request.getParameter("title");
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer userRole = loginUser.getRole();
        List<News> newsList = null;
        int totalCount = 0;
        switch (userRole) {
            case 1:
                newsList = newsService.getList(pageNum, pageSize, null, title,null);
                totalCount = newsService.getTotalCount(null, title,null);
                break;
            case 0:
                newsList = newsService.getList(pageNum, pageSize, loginUser.getId(), title,null);
                totalCount = newsService.getTotalCount(loginUser.getId(), title,null);
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

    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsService newsService = new NewsServiceImpl();
        Integer nid = Integer.parseInt(request.getParameter("nid"));
        boolean result = newsService.delete(nid);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }
    public void sort(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsService newsService = new NewsServiceImpl();
        Integer nid = Integer.parseInt(request.getParameter("nid"));
        Integer sort=Integer.parseInt(request.getParameter("sort"));
        boolean result = newsService.sort(nid,sort);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }

}
