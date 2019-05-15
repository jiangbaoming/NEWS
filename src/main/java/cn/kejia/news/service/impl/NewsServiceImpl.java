package cn.kejia.news.service.impl;

import cn.kejia.news.dao.NewsDao;
import cn.kejia.news.dao.impl.NewsDaoImpl;
import cn.kejia.news.model.News;
import cn.kejia.news.service.NewsService;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/14
 * @Modified By：
 */
public class NewsServiceImpl implements NewsService {

    @Override
    public boolean add(News news) {
        NewsDao newsDao=new NewsDaoImpl();
        boolean result = newsDao.add(news);
        return result;
    }

    @Override
    public List<News> getList(Integer pageNum, Integer pageSize, Integer uid) {
        NewsDao newsDao=new NewsDaoImpl();
        List<News> newsList=null;
        if (null!= uid){
            newsList=newsDao.getNewsList((pageNum-1)*pageSize, pageSize, false, uid);
        }else{
            newsList=newsDao.getNewsList((pageNum-1)*pageSize, pageSize, true, null);
        }
        return newsList;
    }

    @Override
    public int getTotalCount(Integer id) {
        NewsDao newsDao=new NewsDaoImpl();
        return 0;
    }
}
