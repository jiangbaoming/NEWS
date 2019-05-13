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
}
