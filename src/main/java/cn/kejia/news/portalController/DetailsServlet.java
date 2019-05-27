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
@WebServlet(name = "DetailsServlet", urlPatterns = "/details")
public class DetailsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("details");
        NewsTypeService newsTypeService = new NewsTypeServiceImpl();
        NewsService newsService = new NewsServiceImpl();
        //导航分类
        List<NewsType> navigations = newsTypeService.getByPid(0);
        request.setAttribute("navigations", navigations);
        //热门资讯
        List<News> bannerList = newsService.getList(1, 10, null, null,null);
        request.setAttribute("bannerList", bannerList);

        Integer nid = Integer.parseInt(request.getParameter("nid"));
        News news = newsService.getNewsByNid(nid);
        //浏览次数
        Integer num=news.getTimes()+1;
        newsService.updateTimes(news.getNid(), num);
        news.setTimes(num);
        //文章类型
        request.setAttribute("tid", news.getTid());
        request.setAttribute("bannerList", bannerList);
        request.setAttribute("news", news);
        request.getRequestDispatcher("/portal/details.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
