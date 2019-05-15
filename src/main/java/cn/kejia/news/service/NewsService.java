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

    List<News> getList(Integer pageNum, Integer pageSize, Integer uid);

    int getTotalCount(Integer uid);
}