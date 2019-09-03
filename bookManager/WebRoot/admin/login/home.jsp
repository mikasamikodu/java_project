<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<!--设置不缓存页面-->
<meta http-equiv="Pragma" content="no-cache">
<!--设置不修改消息存储-->
<meta http-equiv="Cache-control" content="no-cache">
<!--同上-->
<meta http-equiv="Cache" content="no-cache">
<title>Insert title here</title>
</head>
<body>
	<div>这是首页</div>
	<a href='${pageContext.request.contextPath }/servlet/BookListServlet'>查询</a><br/>
	<a href="${pageContext.request.contextPath }/foot.jsp">注册页面-姓名版</a><br/>
	<a href="${pageContext.request.contextPath }/register.jsp">注册页面-邮箱版</a><br/>
</body>
</html>