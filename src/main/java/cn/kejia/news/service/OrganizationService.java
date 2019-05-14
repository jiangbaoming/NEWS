package cn.kejia.news.service;

import cn.kejia.news.model.Organization;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/13
 * @Modified By：
 */
public interface OrganizationService {


    List<Organization> getBySuperId(Integer pid);

    List<Organization> getList(Integer pageNum, Integer pageSize);

    int getTotalCount();

    List<Organization> getList(Integer pageNum, Integer pageSize, Integer pid);

    int getTotalCount(Integer pid);
}
