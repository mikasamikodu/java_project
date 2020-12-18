<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath %>>"/>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="jquery/jquery-2.1.1.min.js"></script>
		<script type="text/javascript">
			function login(){
				$.ajax({
					type: "post",
					url: "user/login",
					data: {username: "tom"},
					
					success: function(data){
						var msg = data.result;
						$("#text").append(msg);
					},
					error: function(){
						$("#text").append("页面出现错误");
					}
				});
			}
		</script>
	</head>
	<body>
		<form action="">
			<input type="button" value="登录" onclick="login()"/><br/>
			<div id="text" style="width:100%;height:120px;color:red;"></div>
		</form>
	</body>
</html>