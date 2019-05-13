package cn.kejia.news.service.impl;

import cn.kejia.news.dao.OrganizationDao;
import cn.kejia.news.dao.impl.OrganizationDaoImpl;
import cn.kejia.news.model.Organization;
import cn.kejia.news.service.OrganizationService;

import java.util.List;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/13
 * @Modified By：
 */
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationDao organizationDao = new OrganizationDaoImpl();


    @Override
    public List<Organization> getBySuperId(Integer pid) {
        List<Organization> list = organizationDao.getListByPid(pid);
        return list;
    }
}
