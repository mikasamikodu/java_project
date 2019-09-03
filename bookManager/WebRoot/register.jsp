<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='${pageContext.request.contextPath }/js/myJS.js'></script>
<script type='text/javascript'>
	function ckEmail() {
		var email = document.getElementsByName("email")[0].value;
		var xml = getXMLHttpRequest();
		xml.onreadystatechange = function() {
			if(xml.readyState==4) {
				if(xml.status==200) {
					var span = document.getElementById("span");
					if(xml.responseText=="true") {
						span.innerHTML = "邮箱已占用！！";
						span.style.color = "red";
					}else {
						span.innerHTML = "邮箱可以使用。";
						span.style.color = "green";
					}
				}
			}
		};
		xml.open( "get", "${pageContext.request.contextPath}/servlet/CheckEmailServlet?email="+email);
		xml.send(null);
	}
</script>
</head>
<body>
	<input type='text' name='email' onblur="ckEmail()"/><span id='span'>请输入邮箱</span>
</body>
</html>