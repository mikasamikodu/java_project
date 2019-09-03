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
	function add() {
		window.location.href = "../admin/products/add.jsp";
	}

	function deleteBook(id,name) {
		if(confirm("是否确定删除"+name+"?")) {
			location.href = "${pageContext.request.contextPath}/servlet/DeleteBookServlet?id="+id;
		}
	}
	
	function checkAll() {
		var check = document.getElementById("check").checked;
		var checked = document.getElementsByName("checked");
		for(var i=0;i<checked.length;i++) {
			checked[i].checked = check;
		}
	}
	
	function batch() {
		/* var checked = document.getElementsByName("checked");
		var str = "";
		for(var i=0;i<checked.length;i++) {
			if(checked[i].checked) {
				str=str+"checked="+checked[i].value+"&";
			}
		}
		str.substring(0,str.length-1);
		if(str!=null) {
			location.href = "${pageContext.request.contextPath}/servlet/DeleteAllBookServlet?"+str;
		} */
		var form = document.forms[1];
		//form.action = "${pageContext.request.contextPath}/servlet/DeleteAllBookServlet";
		form.submit();
	}
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/servlet/FindBooksServlet" method='post'>
		<table border="1px solid black">
			<tr>
				<td colspan='4' align='center'>查询条件</td>
			</tr>
			<tr>
				<td align='right' width='30%'>商品编号:</td>
				<td>
					<input type='text' width='10%'  name='id'/>
				</td><td align='right' width='29.4%'>商品类别:</td>
				<td>
					<select name='category'>
						<option value='' selected>--选择商品类别查询--</option>
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
				<td align='right' width='29%'>商品名称:</td>
				<td>
					<input type='text' width='10%' name='name'/>
				</td>
				<td align='right' width='29%'>商品价格区间:</td>
				<td>
					<input type='text' width='5%' name='minprice'/>-<input type='text' width='5%' name='maxprice'/>
				</td>
			</tr>
			<tr align='right'>
				<td colspan='3'></td>
				<td><input type='submit' value='查询'/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type='reset' value='重置'/>
				</td>
			</tr>
		</table>
	</form>
	<form action='${pageContext.request.contextPath}/servlet/DeleteAllBookServlet' method='post'>
		<table border="1px solid black">
			<tr>
				<td colspan='8' align='center'>商品列表</td>
			</tr>
			<tr>
				<td align='center' width='10%'>
					<input type='checkbox' id='check' onclick="checkAll()"/>全选/全不选
				</td>
				<td align='center' width='24%'>商品编号</td>
				<td align='center' width='18%'>商品名称</td>
				<td align='center' width='9%'>商品价格</td>
				<td align='center' width='9%'>商品数量</td>
				<td align='center' width='8%'>商品类别</td>
				<td align='center' width='8%'>编辑</td>
				<td align='center' width='8%'>删除</td>
			</tr>
			<c:forEach items='${books }' var='book'>
				<tr>
					<td height='22px' align='center' width='10%'>
						<input type='checkbox' name='checked' value='${book.id }'/>
					</td>
					<td height='22px' align='center' width='18%'>${book.id}</td>
					<td height='22px' align='center' width='18%'>${book.name}</td>
					<td height='22px' align='center' width='9%'>${book.price}</td>
					<td height='22px' align='center' width='9%'>${book.pnum}</td>
					<td height='22px' align='center' width='8%'>${book.category}</td>
					<td height='22px' align='center' width='8%'>
						<a href='${pageContext.request.contextPath }/servlet/FindBookByIDServlet?id=${book.id}'>编辑</a>
					</td>
					<td height='22px' align='center' width='8%'>
						<a href="javascript:deleteBook('${book.id }','${book.name }')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<input type='button' onclick="add()" value='添加'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type='button' onclick="batch()" value='批量删除'>
</body>
</html>