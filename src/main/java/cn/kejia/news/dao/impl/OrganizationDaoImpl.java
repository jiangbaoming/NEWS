package cn.kejia.news.dao.impl;

import cn.kejia.news.dao.BaseDao;
import cn.kejia.news.dao.OrganizationDao;
import cn.kejia.news.model.Organization;

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
public class OrganizationDaoImpl extends BaseDao implements OrganizationDao {
    @Override
    public Object tableToObject(ResultSet rs) throws SQLException {
        Organization oz = new Organization();
        oz.setOid(rs.getInt("oid"));
        oz.setOname(rs.getString("oname"));
        oz.setParentId(rs.getInt("parentId"));
        oz.setCreator(rs.getString("creator"));
        oz.setCreateDate(rs.getDate("createDate"));
        oz.setModifier(rs.getString("modifier"));
        oz.setModifyDate(rs.getDate("modifyDate"));
        return oz;
    }


    @Override
    public boolean delete(int oid) {
        String sql = "delete from organization where oid = ?";
        Object[] params = {oid};
        int rows = executeUpdata(sql, params);
        return rows > 0;
    }

    @Override
    public boolean update(Organization oz) {
        String sql = "update organization set oname = ?,  parentId =?, modifier = ?,  modifyDate = ? WHERE oid = ?";
        Object[] params = {oz.getOname(), oz.getParentId(), oz.getModifier(), oz.getModifyDate(), oz.getOid()};
        int rows = executeUpdata(sql, params);
        return rows > 0;
    }

    @Override
    public boolean add(Organization oz) {
        String sql = "insert into organization( oname,parentId,creator,createDate,modifier,modifyDate) VALUE (?,?,?,?,?,?)";
        Object[] params = {oz.getOname(), oz.getParentId(), oz.getCreator(), oz.getCreateDate(), oz.getModifier(), oz.getModifyDate()};
        int rows = executeUpdata(sql, params);
        return rows > 0;
    }

    @Override
    public List<Organization> getListByPid(int pid) {
        String sql = "select * from organization where parentId= ?";
        Object[] params = {pid};
        List<Organization> organizationList = new ArrayList<>();
        rs = executeQuriy(sql, params);
        Organization oz = null;
        try {
            while (rs.next()) {
                oz = (Organization) tableToObject(rs);
                organizationList.add(oz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return organizationList;
    }

    @Override
    public Organization getOrganizationById(Integer oid) {
        String sql = "select * from organization where oid = ?";
        Object[] params = {oid};
        rs = executeQuriy(sql, params);
        Organization oz = null;
        try {
            while (rs.next()) {
                oz = (Organization) tableToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return oz;
    }

    @Override
    public List<Organization> getList(Integer pageNum, Integer pageSize) {
        String sql = "select * from organization limit ?,?";
        Object[] params = {pageNum, pageSize};
        List<Organization> organizationList = new ArrayList<>();
        rs = executeQuriy(sql, params);
        Organization oz = null;
        try {
            while (rs.next()) {
                oz = (Organization) tableToObject(rs);
                organizationList.add(oz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return organizationList;
    }

    @Override
    public int getTotalCount() {
        String sql = "select count(1) from organization";
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
    public List<Organization> getList(Integer pageNum, Integer pageSize, Integer pid) {
        String sql = "select * from organization where parentId=? limit ?,?";
        Object[] params = {pid,pageNum, pageSize};
        List<Organization> organizationList = new ArrayList<>();
        rs = executeQuriy(sql, params);
        Organization oz = null;
        try {
            while (rs.next()) {
                oz = (Organization) tableToObject(rs);
                organizationList.add(oz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, rs, pstmt);
        }
        return organizationList;
    }

    @Override
    public int getTotalCount(Integer pid) {
        String sql = "select count(1) from organization where parentId = ?";
        Object[] params = {pid};
        rs = executeQuriy(sql, params);
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
    public boolean reName(String oname, Integer oid) {
        String sql = "update organization set oname = ? WHERE oid = ?";
        Object[] params = {oname,oid};
        int rows = executeUpdata(sql, params);
        return rows > 0;
    }
}
