<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='${pageContext.request.contextPath }/js/myJS.js'></script>
<script type='text/javascript'>
	function checkName() {
		//获取用户名
		var name = document.getElementsByTagName("input")[0];
		//创建XMLHTTPRequest对象
		var xhr = getXMLHttpRequest();
		//处理结果
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4) {
				if(xhr.status == 200) {
					var span = document.getElementById("span");
					if(xhr.responseText=="true") {
						span.innerHTML = "<font color='red'>用户名已存在</font>";
					}else {
						span.innerHTML = "<font color='green'>用户名可以使用</font>";
					}
				}
			}
		};
		//创建连接
		xhr.open("get","${pageContext.request.contextPath }/servlet/CheckNameServlet?name="+name.value);
		//发送请求
		xhr.send(null);
	}
</script>
</head>
<body>
	用户名:<input type='text' name='name' onblur="checkName()"/><span id='span'></span><br/>
	密码:<input type='password' name='password'/><br/>
</body>
</html>