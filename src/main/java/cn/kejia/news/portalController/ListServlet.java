package cn.kejia.news.portalController;

import cn.kejia.news.model.News;
import cn.kejia.news.model.NewsType;
import cn.kejia.news.service.NewsService;
import cn.kejia.news.service.NewsTypeService;
import cn.kejia.news.service.impl.NewsServiceImpl;
import cn.kejia.news.service.impl.NewsTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/19
 * @Modified By：
 */
@WebServlet(name = "ListServlet", urlPatterns = "/list")
public class ListServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("list");
        Integer tid = Integer.parseInt(request.getParameter("tid"));
        NewsTypeService newsTypeService = new NewsTypeServiceImpl();
        NewsService newsService = new NewsServiceImpl();
        //导航分类
        List<NewsType> newsTypes = newsTypeService.getAll();
        //分类列表
        List<News> list = newsService.getListByTid(tid);
        //资讯推荐
        List<News> bannerList = newsService.getList(1, 10, null, null,null);
        String tName = newsTypeService.getTname(tid);
        request.setAttribute("bannerList", bannerList);
        request.setAttribute("newsTypes", newsTypes);
        request.setAttribute("tid", tid);
        request.setAttribute("tName", tName);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/portal/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
