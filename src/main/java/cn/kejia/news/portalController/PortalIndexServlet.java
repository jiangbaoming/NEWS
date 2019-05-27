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
import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/19
 * @Modified By：
 */
@WebServlet(name = "PortalIndexServlet", urlPatterns = "/index")
public class PortalIndexServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("首页");
        NewsTypeService newsTypeService = new NewsTypeServiceImpl();
        NewsService newsService = new NewsServiceImpl();
        //导航分类
        List<NewsType> navigations = newsTypeService.getByPid(0);
        request.setAttribute("navigations", navigations);
        //轮播图
        List<News> bannerList = newsService.getList(1, 4, null, null, null);
        request.setAttribute("bannerList", bannerList);
        //栏目列表
        List<NewsType> programaList = newsTypeService.getAll();
        Iterator<NewsType> it = programaList.iterator();
        while (it.hasNext()){
            NewsType newsType = it.next();
            List<News> listByTid = newsService.getListByTid(newsType.getTid());
            if (listByTid.size() > 0) {
                newsType.setNewsList(listByTid);
            } else {
                it.remove();
            }
        }
        request.setAttribute("programaList", programaList);
        request.getRequestDispatcher("/portal/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
