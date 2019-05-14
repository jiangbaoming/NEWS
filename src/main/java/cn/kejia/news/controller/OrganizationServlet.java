package cn.kejia.news.controller;

import cn.kejia.news.model.Organization;
import cn.kejia.news.model.User;
import cn.kejia.news.service.OrganizationService;
import cn.kejia.news.service.impl.OrganizationServiceImpl;
import cn.kejia.news.utils.NewsResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/13
 * @Modified By：
 */
@WebServlet(name = "OrganizationServlet", urlPatterns = "/organization")
public class OrganizationServlet extends BaseServlet {

    // private OrganizationService organizationService = new OrganizationServiceImpl();

    public void getAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Map<String, Object>> mapList = getByPid(0);
        response.getWriter().write(JSON.toJSONString(mapList));
    }


    private List<Map<String, Object>> getByPid(int pid) {
        OrganizationService organizationService = new OrganizationServiceImpl();
        List<Map<String, Object>> mapList = new ArrayList<>();
        //获取根节点
        List<Organization> rootList = organizationService.getBySuperId(pid);
        for (Organization organization : rootList) {
            //判断是否为父节点
            List<Organization> departmentListBySuperId = organizationService.getBySuperId(organization.getOid());
            if (departmentListBySuperId.size() > 0) {
                Map<String, Object> departmentListVo = new HashMap<>();
                departmentListVo.put("id", organization.getOid());
                departmentListVo.put("name", organization.getOname());
                //递归调用
                departmentListVo.put("children", getByPid(organization.getOid()));
                mapList.add(departmentListVo);
            } else {
                Map<String, Object> departmentListVo = new HashMap<>();
                departmentListVo.put("id", organization.getOid());
                departmentListVo.put("name", organization.getOname());
                mapList.add(departmentListVo);
            }
        }
        return mapList;
    }

    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrganizationService organizationService = new OrganizationServiceImpl();
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String pid = request.getParameter("pid");
        List<Organization> organizations = null;
        int totalCount = 0;
        if (null != pid && !"".equals(pid)) {
            organizations = organizationService.getList(pageNum, pageSize, Integer.parseInt(pid));
            totalCount = organizationService.getTotalCount(Integer.parseInt(pid));
        } else {
            organizations = organizationService.getList(pageNum, pageSize);
            totalCount = organizationService.getTotalCount();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "ok");
        result.put("totalCount", totalCount);
        result.put("data", organizations);
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void addParent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrganizationService organizationService = new OrganizationServiceImpl();
        String oname=request.getParameter("oname");
        Organization oz=new Organization();
        User loginUser= (User) request.getSession().getAttribute("loginUser");
        oz.setOname(oname);
        oz.setParentId(0);
        oz.setCreator(loginUser.getUserName());
        oz.setCreateDate(new Date());
        oz.setModifier(loginUser.getUserName());
        oz.setModifyDate(new Date());
        boolean result=organizationService.add(oz);
        if (result){
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        }else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }
    public void addChild(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrganizationService organizationService = new OrganizationServiceImpl();
        String oname=request.getParameter("oname");
        Integer pid=Integer.parseInt(request.getParameter("pid"));
        Organization oz=new Organization();
        User loginUser= (User) request.getSession().getAttribute("loginUser");
        oz.setOname(oname);
        oz.setParentId(pid);
        oz.setCreator(loginUser.getUserName());
        oz.setCreateDate(new Date());
        oz.setModifier(loginUser.getUserName());
        oz.setModifyDate(new Date());
        boolean result=organizationService.add(oz);
        if (result){
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        }else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }
    public void reName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrganizationService organizationService = new OrganizationServiceImpl();
        String oname=request.getParameter("oname");
        Integer oid=Integer.parseInt(request.getParameter("oid"));
        boolean result=organizationService.reName(oname,oid);
        if (result){
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        }else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrganizationService organizationService = new OrganizationServiceImpl();
        Integer oid=Integer.parseInt(request.getParameter("oid"));
        boolean result=organizationService.delete(oid);
        if (result){
            response.getWriter().write(JSON.toJSONString(NewsResult.success()));
        }else {
            response.getWriter().write(JSON.toJSONString(NewsResult.build(201, "服务器出错！")));
        }
    }
}
