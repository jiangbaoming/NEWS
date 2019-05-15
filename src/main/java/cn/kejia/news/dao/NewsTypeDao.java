package cn.kejia.news.dao;

import cn.kejia.news.model.NewsType;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/12
 * @Modified By：
 */
public interface NewsTypeDao {

    /**
     * 添加新闻分类
     * @param newsType
     * @return
     */
    boolean add(NewsType newsType);

    /**
     * 修改
     * @param newsType
     * @return
     */
    boolean update(NewsType newsType);

    /**
     * 删除
     * @param tid
     * @return
     */
    boolean delete(int tid);

    /**
     * 获取所有新闻分类
     * @return
     */
    List<NewsType> getAllNewsType(Integer pageNum,Integer paeSize);

    int getTotalCount();

    List<NewsType> getAllNewsType();
}
