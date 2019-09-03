<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/servlet/AddBookServlet" method="post">
		<table width='100%' cellspacing="1" cellpadding="4" align='center' border='1px solid black'>
			<tr>
				<td align='center' colSpan='4'  height='26px'>添加商品</td>
			</tr>
			<tr>
				<td>商品名称</td>
				<td><input type='text' name='name'/></td>			
				<td>商品价格</td>
				<td><input type='text' name='price'/></td>			
			</tr>
			<tr>
				<td>商品数量</td>
				<td><input type='text' name='pnum'/></td>			
				<td>商品类别</td>
				<td>
					<select name='category'>
						<option value='未选择' selected>--选择商品类添加--</option>
						<option value='文学'>文学</option>
						<option value='生活'>生活</option>
						<option value='计算机'>计算机</option>
						<option value='外语'>外语</option>
						<option value='经营'>经营</option>
						<option value='励志'>励志</option>
						<option value='社科'>社科</option>
						<option value='学术'>学术</option>
						<option value='少儿'>少儿</option>
						<option value='艺术'>艺术</option>
						<option value='原版'>原版</option>
						<option value='科技'>科技</option>
						<option value='考试'>考试</option>
						<option value='生活百科'>生活百科</option>
					</select>
				</td>			
			</tr>
			<tr>
				<td>商品图片</td>
				<td colspan='3'><input type='file' name='image' value='选择文件'/><span>未选择任何文件</span></td>
			</tr>
			<tr>
				<td>商品描述</td>
				<td colspan='3'>
					<textarea cols='155' rows='4' name='description'></textarea>
				</td>
			</tr>
			<tr>
				<td><input type='submit' value='确定'/></td>
				<td><input type='button' value='返回'/></td>
				<td><input type='reset' value='重置'/></td>
			</tr>
		</table>
	</form>

</body>
</html>