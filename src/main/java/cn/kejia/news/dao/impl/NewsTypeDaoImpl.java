package cn.kejia.news.dao.impl;

import cn.kejia.news.dao.BaseDao;
import cn.kejia.news.dao.NewsTypeDao;
import cn.kejia.news.model.NewsType;
import cn.kejia.news.model.User;

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
public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {

    @Override
    public Object tableToObject(ResultSet rs) throws SQLException {
        NewsType nt = new NewsType();
        nt.setTid(rs.getInt("tid"));
        nt.settName(rs.getString("tName"));
        nt.settSorting(rs.getInt("tSorting"));
        return nt;
    }

    @Override
    public boolean add(NewsType newsType) {
        String sql = "insert into newstype (tName) VALUES (?)";
        Object[] params = {newsType.gettName()};
        int rows = executeUpdata(sql, params);
        return rows > 0;
    }

    @Override
    public boolean update(NewsType newsType) {
        String sql = "update newstype set tName = ?  where tid = ?";
        Object[] params = {newsType.gettName(), newsType.getTid()};
        int rows = executeUpdata(sql, params);
        return rows > 0;
    }

    @Override
    public boolean delete(int tid) {
        String sql = "delete from newstype where tid = ?";
        Object[] params = {tid};
        int rows = executeUpdata(sql, params);
        return rows > 0;
    }

    @Override
    public List<NewsType> getAllNewsType(Integer pageNum, Integer pageSize) {
        String sql = "select * from newstype order by tSorting desc limit ? ,?";
        Object[] params = {pageNum, pageSize};
        rs = executeQuriy(sql, params);
        NewsType newsType = null;
        List<NewsType> newsTypeList = new ArrayList<>();
        try {
            while (rs.next()) {
                newsType = (NewsType) tableToObject(rs);
                newsTypeList.add(newsType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return newsTypeList;
    }

    @Override
    public int getTotalCount() {
        String sql = "select count(1) from newstype";
        rs = executeQuriy(sql, null);
        int totalCount = 0;
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

    @Override
    public List<NewsType> getAllNewsType() {
        String sql = "select * from newstype order by tSorting desc";
        rs = executeQuriy(sql, null);
        NewsType newsType = null;
        List<NewsType> newsTypeList = new ArrayList<>();
        try {
            while (rs.next()) {
                newsType = (NewsType) tableToObject(rs);
                newsTypeList.add(newsType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return newsTypeList;
    }

    @Override
    public String getTname(Integer tid) {
        String sql="select tName from newstype where tid = ? ";
        Object[] params={tid};
        rs=executeQuriy(sql, params);
        String tName=null;
        try {
            while (rs.next()) {
                tName = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return tName;
    }

    @Override
    public boolean sort(Integer tid, Integer sort) {
        String sql = "update newstype set tSorting = ?  where tid = ?";
        Object[] params = {sort, tid};
        int rows = executeUpdata(sql, params);
        return rows>0;
    }

    @Override
    public List<NewsType> getByPid(Integer tid) {
        String sql = "select * from newstype where pid = ?";
        Object[] params={tid};
        rs = executeQuriy(sql, params);
        NewsType newsType = null;
        List<NewsType> newsTypeList = new ArrayList<>();
        try {
            while (rs.next()) {
                newsType = (NewsType) tableToObject(rs);
                newsTypeList.add(newsType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return newsTypeList;
    }
}
