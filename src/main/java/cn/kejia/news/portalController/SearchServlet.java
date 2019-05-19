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
@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("search");
        NewsTypeService newsTypeService=new NewsTypeServiceImpl();
        NewsService newsService=new NewsServiceImpl();
        List<NewsType> newsTypes = newsTypeService.getAll();
        List<News> bannerList = newsService.getList(1, 5, null, null);
        request.setAttribute("bannerList", bannerList);
        request.setAttribute("newsTypes", newsTypes);
        request.getRequestDispatcher("/portal/search.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
