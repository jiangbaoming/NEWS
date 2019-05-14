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

 //   private OrganizationDao organizationDao = new OrganizationDaoImpl();


    @Override
    public List<Organization> getBySuperId(Integer pid) {
        OrganizationDao organizationDao = new OrganizationDaoImpl();
        List<Organization> list = organizationDao.getListByPid(pid);
        return list;
    }

    @Override
    public List<Organization> getList(Integer pageNum, Integer pageSize) {
        OrganizationDao organizationDao = new OrganizationDaoImpl();
        List<Organization> organizations = organizationDao.getList((pageNum - 1) * pageSize, pageSize);
        for (Organization organization : organizations) {
            if (organization.getParentId() == 0) {
                organization.setPname("顶级部门");
            } else {
                Organization oz = organizationDao.getOrganizationById(organization.getParentId());
                organization.setPname(oz.getOname());
            }
        }
        return organizations;
    }

    @Override
    public int getTotalCount() {
        OrganizationDao organizationDao = new OrganizationDaoImpl();
        int totalCount = organizationDao.getTotalCount();
        return totalCount;
    }

    @Override
    public List<Organization> getList(Integer pageNum, Integer pageSize, Integer pid) {
        OrganizationDao organizationDao = new OrganizationDaoImpl();
        List<Organization> organizations = organizationDao.getList((pageNum - 1) * pageSize, pageSize,pid);
        for (Organization organization : organizations) {
            if (organization.getParentId() == 0) {
                organization.setPname("顶级部门");
            } else {
                Organization oz = organizationDao.getOrganizationById(organization.getParentId());
                organization.setPname(oz.getOname());
            }
        }
        return organizations;
    }

    @Override
    public int getTotalCount(Integer pid) {
        OrganizationDao organizationDao = new OrganizationDaoImpl();
        int totalCount = organizationDao.getTotalCount(pid);
        return totalCount;
    }

    @Override
    public boolean add(Organization oz) {
        OrganizationDao organizationDao = new OrganizationDaoImpl();
        boolean result=organizationDao.add(oz);
        return result;
    }

    @Override
    public boolean reName(String oname, Integer oid) {
        OrganizationDao organizationDao = new OrganizationDaoImpl();
       boolean result= organizationDao.reName(oname,oid);
        return result;
    }

    @Override
    public boolean delete(Integer oid) {
        OrganizationDao organizationDao = new OrganizationDaoImpl();
        boolean result = organizationDao.delete(oid);
        return result;
    }
}
