<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/upload" method="post" enctype="multipart/form-data">
		<label for="name">name :</label>
		<input id='name' type="text" name="name"></input><br/>
		<label for="photo">photo:</label>
		<input type="file" name="image"></input><br/>
		<input type="submit" value="submit"></input>
	</form>
</body>
</html>