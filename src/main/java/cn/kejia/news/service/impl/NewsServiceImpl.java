package cn.kejia.news.service.impl;

import cn.kejia.news.dao.NewsDao;
import cn.kejia.news.dao.NewsTypeDao;
import cn.kejia.news.dao.UserDao;
import cn.kejia.news.dao.impl.NewsDaoImpl;
import cn.kejia.news.dao.impl.NewsTypeDaoImpl;
import cn.kejia.news.dao.impl.UserDaoImpl;
import cn.kejia.news.model.News;
import cn.kejia.news.model.NewsType;
import cn.kejia.news.model.User;
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
        news.setSorting(5);
        NewsDao newsDao = new NewsDaoImpl();
        boolean result = newsDao.add(news);
        return result;
    }

    @Override
    public List<News> getList(Integer pageNum, Integer pageSize, Integer uid, String title, Integer tid) {
        NewsDao newsDao = new NewsDaoImpl();
        List<News> newsList = null;
        if (null != uid) {
            if (null != title) {
                if (null != tid) {
                    newsList = newsDao.getNewsList((pageNum - 1) * pageSize, pageSize, false, uid, title, tid);
                } else {
                    newsList = newsDao.getNewsList((pageNum - 1) * pageSize, pageSize, false, uid, title, null);
                }
            } else {
                if (null != tid) {
                    newsList = newsDao.getNewsList((pageNum - 1) * pageSize, pageSize, false, uid, null, tid);
                } else {
                    newsList = newsDao.getNewsList((pageNum - 1) * pageSize, pageSize, false, uid, null, null);
                }
            }
        } else {
            if (null != title) {
                if (null != tid) {
                    newsList = newsDao.getNewsList((pageNum - 1) * pageSize, pageSize, true, null, title,  tid);
                } else {
                    newsList = newsDao.getNewsList((pageNum - 1) * pageSize, pageSize, true, null, title, null);
                }
            } else {
                if (null != tid) {
                    newsList = newsDao.getNewsList((pageNum - 1) * pageSize, pageSize, true, null, null, tid);
                } else {
                    newsList = newsDao.getNewsList((pageNum - 1) * pageSize, pageSize, true, null, null, null);
                }
            }
        }
        UserDao userDao = new UserDaoImpl();
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        for (News news : newsList) {
            User user = userDao.getUserById(news.getUid());
            String tname = newsTypeDao.getTname(news.getTid());
            news.setuName(user.getUserName());
            news.settName(tname);
        }
        return newsList;
    }

    @Override
    public int getTotalCount(Integer uid, String title, Integer tid) {
        NewsDao newsDao = new NewsDaoImpl();
        int totalCount = 0;
        if (null != uid) {
            if (null != title) {
                if (null != tid){
                    totalCount = newsDao.getTotalCount(uid, title,tid);
                }else {
                    totalCount = newsDao.getTotalCount(uid, title,null);
                }
            } else {
                if (null != tid){
                    totalCount = newsDao.getTotalCount(uid, null,tid);
                }else {
                    totalCount = newsDao.getTotalCount(uid, null,null);
                }
            }
        } else {
            if (null != title) {
                if (null != tid){
                    totalCount = newsDao.getTotalCount(null, title,tid);
                }else {
                    totalCount = newsDao.getTotalCount(null, title,null);
                }
            } else {
                if (null != tid){
                    totalCount = newsDao.getTotalCount(null, null,tid);
                }else {
                    totalCount = newsDao.getTotalCount(null, null,null);
                }

            }
        }
        return totalCount;
    }

    @Override
    public boolean update(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        boolean result = newsDao.update(news);
        return result;
    }

    @Override
    public boolean delete(Integer nid) {
        NewsDao newsDao = new NewsDaoImpl();
        boolean result = newsDao.delete(nid);
        return result;
    }

    @Override
    public News getNewsByNid(Integer nid) {
        UserDao userDao = new UserDaoImpl();
        NewsDao newsDao = new NewsDaoImpl();
        News news = newsDao.getNewsByNid(nid);
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        news.settName(newsTypeDao.getTname(news.getTid()));
        news.setuName(userDao.getUserById(news.getUid()).getUserName());
        return news;
    }

    @Override
    public boolean updateTimes(Integer nid, Integer num) {
        NewsDao newsDao = new NewsDaoImpl();
        return newsDao.updateTimes(nid, num);
    }

    @Override
    public List<News> getListByTid(Integer tid) {
        NewsDao newsDao = new NewsDaoImpl();
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        List<News> list = newsDao.getListByTid(tid);
        for (News news : list) {
            String tname = newsTypeDao.getTname(news.getTid());
            news.settName(tname);
        }
        return list;
    }

    @Override
    public boolean sort(Integer nid, Integer sort) {
        NewsDao newsDao = new NewsDaoImpl();
        boolean result = newsDao.sort(nid, sort);
        return result;
    }
}
