package cn.kejia.news.dao;

import cn.kejia.news.model.Organization;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/12
 * @Modified By：
 */
public interface OrganizationDao {


    /**
     *
     * 删除部门
     * @param did
     * @return
     */
    boolean delete(int did);

    /**
     * 修改部门信息
     * @param oz
     * @return
     */
    boolean update(Organization oz);

    /**
     * 添加部门信息
     * @param oz
     * @return
     */
    boolean add(Organization oz);

    /**
     * 获取子部门列表
     * @param pid
     * @return
     */
    List<Organization> getListByPid(int pid);

    /**
     * 根据oid获取Organization
     * @param oid
     * @return
     */
    Organization getOrganizationById(Integer oid);

    List<Organization> getList(Integer pageNum, Integer pageSize);

    int getTotalCount();

    List<Organization> getList(Integer pageNum, Integer pageSize, Integer pid);

    int getTotalCount(Integer pid);

    boolean reName(String oname, Integer oid);
}
