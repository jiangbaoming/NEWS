package cn.kejia.news.backendController;

import cn.kejia.news.model.NewsType;
import cn.kejia.news.service.NewsTypeService;
import cn.kejia.news.service.impl.NewsTypeServiceImpl;
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
 * @Date:2019/05/14
 * @Modified By：
 */
@WebServlet(name = "NewsTypeServlet", urlPatterns = "/newsType")
public class NewsTypeServlet extends BaseServlet {

    public void getAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsTypeService newsTypeService = new NewsTypeServiceImpl();
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<NewsType> list = newsTypeService.getList(pageNum, pageSize);
        int totalCount = newsTypeService.getTotalCount();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "ok");
        result.put("totalCount", totalCount);
        result.put("data", list);
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsTypeService newsTypeService = new NewsTypeServiceImpl();
        String tName = request.getParameter("tName");
        boolean result = newsTypeService.add(tName);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsTypeService newsTypeService = new NewsTypeServiceImpl();
        String tName = request.getParameter("tName");
        Integer tid=Integer.parseInt(request.getParameter("tid"));
        NewsType newsType=new NewsType();
        newsType.setTid(tid);
        newsType.settName(tName);
        boolean result = newsTypeService.update(newsType);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsTypeService newsTypeService = new NewsTypeServiceImpl();
        Integer tid=Integer.parseInt(request.getParameter("tid"));
        boolean result = newsTypeService.delete(tid);
        if (result) {
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        } else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }

    public void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsTypeService newsTypeService = new NewsTypeServiceImpl();
        List<NewsType> list=newsTypeService.getAll();
        response.getWriter().write(JSON.toJSONString(NewsResult.success(list)));
    }
}
