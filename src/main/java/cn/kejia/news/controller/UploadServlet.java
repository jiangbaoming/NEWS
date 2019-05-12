package cn.kejia.news.controller;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/08
 * @Modified By：
 */

import cn.kejia.news.model.ImageFiled;
import cn.kejia.news.utils.IDUtils;
import cn.kejia.news.utils.ImgResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//使用@WebServlet配置UploadServlet的访问路径
@WebServlet(name="UploadServlet",urlPatterns="/uploadServlet")
//使用注解@MultipartConfig将一个Servlet标识为支持文件上传
@MultipartConfig//标识Servlet支持文件上传
public class UploadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //存储路径
        String savePath =request.getServletContext().getRealPath("upload");// "D:\\IdeaProjects\\testFileUPload\\src\\main\\webapp\\uploadFile";//  request.getServletContext().getRealPath("upload");
        System.out.println("存储路径>>>>>>>>>>>>>>>>>"+savePath);
        File file=new File(savePath);
        if (!file.exists()){
            file.mkdirs();
        }
        //获取上传的文件集合
        Collection<Part> parts = request.getParts();
        //上传单个文件
        if (parts.size()==1) {
            //Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作。
            //Part part = parts[0];//从上传的文件集合中获取Part对象
            Part part = request.getPart("file");//通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
            //Servlet3没有提供直接获取文件名的方法,需要从请求头中解析出来
            //获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
            String header = part.getHeader("content-disposition");
            //获取文件名
            String fileName = getFileName(header);
            //获取后缀
            int i = fileName.lastIndexOf(".");
            String fileSuffix=fileName.substring(i);
            //文件重命名
            String newFileName= IDUtils.genImageName() +fileSuffix;
            System.out.println("文件重命名---------------------"+newFileName);
            //把文件写到指定路径
            part.write(savePath+File.separator+newFileName);
            String fileUrl="http://192.168.1.141:8080/upload/"+newFileName;
            ImageFiled imageFiled=new ImageFiled(fileUrl,fileName,newFileName);
            response.getWriter().write(JSON.toJSONString(ImgResult.ok(imageFiled)));
            System.out.println("文件>>>>>>>>>>>>>"+savePath+File.separator+newFileName);
        }else {
            //一次性上传多个文件
            List<String> filesUrlList=new ArrayList<>();
            for (Part part : parts) {//循环处理上传的文件
                //获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
                String header = part.getHeader("content-disposition");
                //获取文件名
                String fileName = getFileName(header);
                //把文件写到指定路径
                part.write(savePath+File.separator+fileName);
                String fileUrl="http://192.168.1.141:8080/upload/"+fileName;
                filesUrlList.add(fileUrl);
            }
            response.getWriter().write(JSONArray.toJSONString(filesUrlList));
        }
    }

    /**
     * 根据请求头解析出文件名
     * 请求头的格式：火狐和google浏览器下：form-data; name="file"; filename="snmp4j--api.zip"
     *                 IE浏览器下：form-data; name="file"; filename="E:\snmp4j--api.zip"
     * @param header 请求头
     * @return 文件名
     */
    public String getFileName(String header) {
        /**
         * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
         * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
         * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
         */
        String[] tempArr1 = header.split(";");
        /**
         *火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
         *IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
         */
        String[] tempArr2 = tempArr1[2].split("=");
        //获取文件名，兼容各种浏览器的写法
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");
        return fileName;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
