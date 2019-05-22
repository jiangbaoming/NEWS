package cn.kejia.news.backendController;


import cn.kejia.news.model.NewsType;
import cn.kejia.news.model.User;
import cn.kejia.news.service.NewsTypeService;
import cn.kejia.news.service.impl.NewsTypeServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/21
 * @Modified By：
 */
@WebServlet(name = "BackendIndexServlet", urlPatterns = "/backend/index")
public class BackendIndexServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        List<Map> navs = new ArrayList();
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser.getRole()>0){
            Map<String, Object> nav = new HashMap<>();
            nav.put("title", "机构管理");
            nav.put("icon", "&#xe60a;");
            nav.put("href", "page/organization/organizationList.html");
            nav.put("spread", false);
            navs.add(nav);
            Map<String, Object> nav1 = new HashMap<>();
            nav1.put("title", "用户管理");
            nav1.put("icon", "&#xe770;");
            nav1.put("href", "page/admin/adminList.html");
            nav1.put("spread", false);
            navs.add(nav1);
            Map<String, Object> nav2 = new HashMap<>();
            nav2.put("title", "分类管理");
            nav2.put("icon", "&#xe60a;");
            nav2.put("href", "page/newsType/newsTypeList.html");
            nav2.put("spread", false);
            navs.add(nav2);
            Map<String, Object> nav3 = new HashMap<>();
            nav3.put("title", "文章管理");
            nav3.put("icon", "&#xe60a;");
            nav3.put("href", "");
            nav3.put("spread", false);
            nav3.put("children", getTypeList(0));
            navs.add(nav3);
        }else {
            Map<String, Object> nav3 = new HashMap<>();
            nav3.put("title", "文章管理");
            nav3.put("icon", "&#xe60a;");
            nav3.put("href", "");
            nav3.put("spread", false);
            nav3.put("children", getTypeList(0));
            navs.add(nav3);
        }
        response.getWriter().write(JSON.toJSONString(navs));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private List<Map> getTypeList(Integer pid) {
        NewsTypeService newsTypeService = new NewsTypeServiceImpl();
        List<Map> list = new ArrayList<>();
        List<NewsType> newsTypelist = newsTypeService.getByPid(pid);
        for (NewsType newsType : newsTypelist) {
            Map<String, Object> nav3 = new HashMap<>();
            List<NewsType> newsTypes=   newsTypeService.getByPid(newsType.getTid());
            nav3.put("title", newsType.gettName());
            nav3.put("icon", "&#xe60a;");
            nav3.put("spread", false);
            nav3.put("tid", newsType.getTid());
            if (newsTypes.size()>0){
                nav3.put("href", "");
                nav3.put("children",getTypeList(newsType.getTid()));
            }else {
                nav3.put("href", "page/news/informationList.html");
            }
            list.add(nav3);
        }
        return list;
    }
}
