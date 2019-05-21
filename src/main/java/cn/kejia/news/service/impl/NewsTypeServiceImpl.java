package cn.kejia.news.service.impl;

import cn.kejia.news.dao.NewsTypeDao;
import cn.kejia.news.dao.impl.NewsTypeDaoImpl;
import cn.kejia.news.model.NewsType;
import cn.kejia.news.service.NewsTypeService;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/14
 * @Modified By：
 */
public class NewsTypeServiceImpl implements NewsTypeService {

    @Override
    public List<NewsType> getList(Integer pageNum, Integer pageSize) {
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        List<NewsType> list = newsTypeDao.getAllNewsType((pageNum - 1) * pageSize, pageSize);
        return list;
    }

    @Override
    public int getTotalCount() {
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        int totalCount = newsTypeDao.getTotalCount();
        return totalCount;
    }

    @Override
    public boolean add(String tName) {
        NewsType newsType = new NewsType();
        newsType.settName(tName);
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        boolean result = newsTypeDao.add(newsType);
        return result;
    }

    @Override
    public boolean update(NewsType newsType) {
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        boolean result = newsTypeDao.update(newsType);
        return result;
    }

    @Override
    public boolean delete(Integer tid) {
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        boolean result = newsTypeDao.delete(tid);
        return result;
    }

    @Override
    public List<NewsType> getAll() {
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        List<NewsType> list = newsTypeDao.getAllNewsType();
        return list;
    }

    @Override
    public String getTname(Integer tid) {
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        String tname = newsTypeDao.getTname(tid);
        return tname;
    }

    @Override
    public boolean sort(Integer tid, Integer sort) {
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        boolean result=newsTypeDao.sort(tid,sort);
        return result;
    }

    @Override
    public List<NewsType> getByPid(Integer tid) {
        NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
        return newsTypeDao.getByPid(tid);
    }
}
