<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/19
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="account/findAll" method="post">查询所有用户</a>
    <form action="account/saveAccount">
        姓名：<input type="text" name="name" /><br/>
        金额：<input type="text" name="money"/><br/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
