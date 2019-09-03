<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type='text/javascript'>
	function setCategory(t) {
		var category = document.getElementById("category");
		var array = category.options;
		for(var i=0;i<array.length;i++) {
			if(array[i].value==t) {
				array[i].selected=true;
				return;
			}
		}
	}
</script>
</head>
<body onload="setCategory('${book.category}')">
	<form action="${pageContext.request.contextPath }/servlet/UpdateBookServlet" method="post">
		<input type='hidden' name='id' value='${book.id }'/>
		<table width='100%' cellspacing="1" cellpadding="4" align='center' border='1px solid black'>
			<tr>
				<td align='center' colSpan='4'  height='26px'>修改商品信息</td>
			</tr>
			
			<tr>
				<td>商品名称</td>
				<td><input type='text' name='name' value='${book.name}'/></td>			
				<td>商品价格</td>
				<td><input type='text' name='price'  value='${book.price}'/></td>			
			</tr>
			<tr>
				<td>商品数量</td>
				<td><input type='text' name='pnum' value='${book.pnum}'/></td>			
				<td>商品类别</td>
				<td>
					<select id='category' name='category'>
						<option value='未选择'>--选择商品类添加--</option>
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
					<textarea cols='155' rows='4' name='description'>${book.description}</textarea>
				</td>
			</tr>
			<tr>
				<td><input type='submit' value='确定'/></td>
				<td><input type='button' value='返回'/></td>
			</tr>
		</table>
	</form>

</body>
</html>