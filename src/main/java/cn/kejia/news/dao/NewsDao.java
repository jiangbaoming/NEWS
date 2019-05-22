package cn.kejia.news.dao;

import cn.kejia.news.model.News;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/12
 * @Modified By：
 */
public interface NewsDao {
    /**
     * 添加新闻
     * @param news
     * @return
     */
    boolean add(News news);

    /**
     * 删除
     * @param nid
     * @return
     */
    boolean delete(int nid);

    /**
     * 修改
     * @param news
     * @return
     */
    boolean update(News news);

    /**
     * 分页显示新闻列表
     * @param pageNum  页码
     * @param pageSize  页容量
     * @param isAdmin  是否管理员
     * @param uid   当前登录用户ID
     * @param tid 文章类型
     * @return  文章列表
     */
    List<News> getNewsList(Integer pageNum, Integer pageSize,boolean isAdmin,Integer uid,String title,Integer tid);

    int getTotalCount(Integer uid ,String title,Integer tid);

    News getNewsByNid(Integer nid);

    boolean updateTimes(Integer nid, Integer num);

    List<News> getListByTid(Integer tid);

    boolean sort(Integer nid, Integer sort);
}
