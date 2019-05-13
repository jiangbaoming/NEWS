package cn.kejia.news.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/02
 * @Modified By：
 */
public class BaseServlet extends javax.servlet.http.HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 获取请求标识
        String methodName = request.getParameter("method");

        // 获取指定类的字节码对象
        Class<? extends BaseServlet> clazz = this.getClass();//这里的this指的是继承BaseServlet对象
        // 通过类的字节码对象获取方法的字节码对象
        System.out.println("调用方法:---->"+clazz.getName()+"____>"+methodName);
        Method method = null;
        try {
            method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
           /* Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println("方法参数:_______>"+parameter.getName());
            }*/
        } catch (NoSuchMethodException e) {
            System.out.println("方法不存在！");
            e.printStackTrace();
        }
        // 调用 method 方法
        try {
            method.invoke(this, request, response);
        } catch (IllegalAccessException e) {
            System.out.println("没有对该类的访问权限");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("此处接收被调用方法内部未被捕获的异常");
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
