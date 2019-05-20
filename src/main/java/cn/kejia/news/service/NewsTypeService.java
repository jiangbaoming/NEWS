package cn.kejia.news.service;

import cn.kejia.news.model.NewsType;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/14
 * @Modified By：
 */
public interface NewsTypeService {
    
    List<NewsType> getList(Integer pageNum, Integer pageSize);

    int getTotalCount();

    boolean add(String tName);

    boolean update(NewsType newsType);

    boolean delete(Integer tid);

    List<NewsType> getAll();

    String getTname(Integer tid);
}
