package cn.kejia.news.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/13
 * @Modified By：
 */
@WebServlet(name = "OrganizationServlet",urlPatterns = "/organization")
public class OrganizationServlet extends BaseServlet {

    public void getAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write("[\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"zzz\",\n" +
                "      \"open\": true,\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"id\": 2,\n" +
                "          \"name\": \"1\",\n" +
                "          \"open\": false,\n" +
                "          \"checked\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 3,\n" +
                "          \"name\": \"2\",\n" +
                "          \"open\": false,\n" +
                "          \"checked\": true\n" +
                "\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 17,\n" +
                "          \"name\": \"3z\",\n" +
                "          \"open\": false,\n" +
                "          \"checked\": true\n" +
                "        }\n" +
                "      ],\n" +
                "      \"checked\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"name\": \"评论\",\n" +
                "      \"open\": false,\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"id\": 5,\n" +
                "          \"name\": \"留言列表\",\n" +
                "          \"open\": false,\n" +
                "          \"checked\": false\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 6,\n" +
                "          \"name\": \"发表留言\",\n" +
                "          \"open\": false,\n" +
                "          \"checked\": false\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 333,\n" +
                "          \"name\": \"233333\",\n" +
                "          \"open\": false,\n" +
                "          \"checked\": false\n" +
                "        }\n" +
                "      ],\n" +
                "      \"checked\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"name\": \"权限管理\",\n" +
                "      \"open\": false,\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"id\": 8,\n" +
                "          \"name\": \"用户列表\",\n" +
                "          \"open\": false,\n" +
                "          \"children\": [\n" +
                "            {\n" +
                "              \"id\": 40,\n" +
                "              \"name\": \"添加用户\",\n" +
                "              \"open\": false,\n" +
                "              \n" +
                "              \"url\": null,\n" +
                "              \"title\": \"40\",\n" +
                "              \"checked\": false,\n" +
                "              \"level\": 2,\n" +
                "              \"check_Child_State\": 0,\n" +
                "              \"check_Focus\": false,\n" +
                "              \"checkedOld\": false,\n" +
                "              \"dropInner\": false,\n" +
                "              \"drag\": false,\n" +
                "              \"parent\": false\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 41,\n" +
                "              \"name\": \"编辑用户\",\n" +
                "              \"open\": false,\n" +
                "              \"checked\": false\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 42,\n" +
                "              \"name\": \"删除用户\",\n" +
                "              \"open\": false,\n" +
                "              \"checked\": false\n" +
                "            }\n" +
                "          ],\n" +
                "          \"checked\": false\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 11,\n" +
                "          \"name\": \"角色列表\",\n" +
                "          \"open\": false,\n" +
                "          \"checked\": false\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 13,\n" +
                "          \"name\": \"所有权限\",\n" +
                "          \"open\": false,\n" +
                "          \"children\": [\n" +
                "            {\n" +
                "              \"id\": 34,\n" +
                "              \"name\": \"添加权限\",\n" +
                "              \"open\": false,\n" +
                "              \"checked\": false\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 37,\n" +
                "              \"name\": \"编辑权限\",\n" +
                "              \"open\": false,\n" +
                "              \"checked\": false\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 38,\n" +
                "              \"name\": \"删除权限\",\n" +
                "              \"open\": false,\n" +
                "              \"checked\": false\n" +
                "            }\n" +
                "          ],\n" +
                "          \"checked\": false\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 15,\n" +
                "          \"name\": \"操作日志\",\n" +
                "          \"open\": false,\n" +
                "          \"checked\": false\n" +
                "        }\n" +
                "      ],\n" +
                "      \"checked\": false\n" +
                "    }\n" +
                "  ]");
    }
}
