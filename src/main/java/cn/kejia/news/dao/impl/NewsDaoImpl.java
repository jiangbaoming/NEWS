package cn.kejia.news.dao.impl;

import cn.kejia.news.dao.BaseDao;
import cn.kejia.news.dao.NewsDao;
import cn.kejia.news.model.News;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/12
 * @Modified By：
 */
public class NewsDaoImpl extends BaseDao implements NewsDao {


    @Override
    public Object tableToObject(ResultSet rs) throws SQLException {
        News news = new News();
        news.setNid(rs.getInt("nid"));
        news.setTid(rs.getInt("tid"));
        news.setUid(rs.getInt("uid"));
        news.setTitle(rs.getString("title"));
        news.setBanner(rs.getString("banner"));
        news.setContent(rs.getString("content"));
        news.setReleaseDate(rs.getDate("releaseDate"));
        news.setTimes(rs.getInt("times"));
        news.setIntroduction(rs.getString("introduction"));
        return news;
    }

    @Override
    public boolean add(News news) {
        String sql = "insert into news ( uid,title, banner,times,content,releaseDate,tid,introduction)values(?,?,?,?,?,?,?,?)";
        Object[] params = {news.getUid(), news.getTitle(), news.getBanner(), news.getTimes(), news.getContent(), news.getReleaseDate(), news.getTid(), news.getIntroduction()};
        int rows = executeUpdata(sql, params);
        return rows > 0;
    }

    @Override
    public boolean delete(int nid) {
        String sql = "delete from news where nid = ?";
        Object[] params = {nid};
        int rows = executeUpdata(sql, params);
        return rows > 0;
    }

    @Override
    public boolean update(News news) {
        String sql = "update news set  title = ?, banner = ?,content = ?,releaseDate = ?, tid= ? ,introduction=? WHERE nid = ? ";
        Object[] params = {news.getTitle(), news.getBanner(), news.getContent(), news.getReleaseDate(), news.getTid(), news.getIntroduction(), news.getNid()};
        int rows = executeUpdata(sql, params);
        return rows > 0;
    }

    @Override
    public List<News> getNewsList(Integer pageNum, Integer pageSize, boolean isAdmin, Integer uid, String title) {
        String sql = "";
        Object[] params = null;
        News news = null;
        List<News> newsList = new ArrayList<>();
        if (isAdmin) {
            if (null != title){
                sql = "select * from news where title like \"%\"?\"%\" limit ? ,?";
                params = new Object[]{title,pageNum, pageSize};
            }else {
                sql = "select * from news limit ? ,?";
                params = new Object[]{pageNum, pageSize};
            }

        } else {
            if (null != title){
                sql = "select * from news where uid= ? and title like \"%\"?\"%\" limit ? , ?";
                params = new Object[]{uid, title,pageNum, pageSize};
            }else {
                sql = "select * from news where uid= ? limit ? , ?";
                params = new Object[]{uid, pageNum, pageSize};
            }

        }
        rs = executeQuriy(sql, params);
        try {
            while (rs.next()) {
                news = (News) tableToObject(rs);
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return newsList;
    }

    @Override
    public int getTotalCount(Integer uid,String title) {
        String sql = "";
        Object[] params = null;
        int totalCount = 0;
        if (null != uid) {
            if (null != title){
                sql = "select count(1) from news where uid = ? and title like \"%\"?\"%\" ";
                params = new Object[]{uid,title};
            }else {
                sql = "select count(1) from news where uid = ?";
                params = new Object[]{uid};
            }

        } else {
            if (null != title){
                sql = "select count(1) from news where title like \"%\"?\"%\" ";
                params = new Object[]{title};
            }else {
                sql = "select count(1) from news ";
            }

        }
        rs = executeQuriy(sql, params);
        try {
            while (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return totalCount;
    }
}
