<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/24
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>使用Commons-FileUpload组件实现文件上传</title>
</head>
<body>
使用Commons-FileUpload组件实现文件上传
<div>
    <form action="FileUploadServlet" method="post" enctype="multipart/form-data">
        我叫：<input type="text" name="userName">
        <!--<input type="file" name="myFile" multiple>，加上multiple属性，可以一次上传多个文件-->
        选择文件：<input type="file" name="myFile" >
        <input type="submit" value="上传">
    </form>
</div>
</body>
</html>