<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/8
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>传统文件上传</h3>
    <form action="file/upload" method="post" enctype="multipart/form-data">
        文件上传:<input type="file" name="upload"/><br/>
        <input type="submit" value="上传"/>
    </form>
    <h3>springmvc文件上传</h3>
    <form action="file/upload2" method="post" enctype="multipart/form-data">
        文件上传:<input type="file" name="upload"/><br/>
        <input type="submit" value="上传"/>
    </form>
    <h3>跨服务器文件上传</h3>
    <form action="file/upload3" method="post" enctype="multipart/form-data">
        文件上传:<input type="file" name="upload"/><br/>
        <input type="submit" value="上传"/>
    </form>
</body>
</html>
