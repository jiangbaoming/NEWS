import cn.kejia.news.dao.UserDao;
import cn.kejia.news.dao.impl.UserDaoImpl;
import cn.kejia.news.model.News;
import cn.kejia.news.model.NewsType;
import cn.kejia.news.model.User;
import cn.kejia.news.service.NewsService;
import cn.kejia.news.service.NewsTypeService;
import cn.kejia.news.service.impl.NewsServiceImpl;
import cn.kejia.news.service.impl.NewsTypeServiceImpl;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/12
 * @Modified By：
 */
public class test {
    UserDao userDao=new UserDaoImpl();
    @Test
    public void test1(){
        NewsTypeService newsTypeService=new NewsTypeServiceImpl();
        NewsService newsService=new NewsServiceImpl();
        //导航分类
        List<NewsType> newsTypes = newsTypeService.getAll();
        //栏目列表
        for (NewsType newsType : newsTypes) {
            List<News> listByTid = newsService.getListByTid(newsType.getTid());
            newsType.setNewsList(listByTid);
            if (newsType.getTid()==11){
                System.out.println(null!=(newsType.getNewsList().get(0).getBanner().trim()) && !"".equals(newsType.getNewsList().get(0).getBanner().trim()));
            }
        }

    }
}
