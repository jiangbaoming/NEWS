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
}
