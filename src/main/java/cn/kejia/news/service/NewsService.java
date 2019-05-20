package cn.kejia.news.service;

import cn.kejia.news.model.News;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/14
 * @Modified By：
 */
public interface NewsService {

    boolean add(News news);

    List<News> getList(Integer pageNum, Integer pageSize, Integer uid,String title);

    int getTotalCount(Integer uid,String title);

    boolean update(News news);

    boolean delete(Integer nid);

    News getNewsByNid(Integer nid);

    boolean updateTimes(Integer nid,Integer num);

    List<News> getListByTid(Integer tid);
}
