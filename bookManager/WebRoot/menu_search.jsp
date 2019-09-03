<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/myJS.js"></script>
<script type="text/javascript">
	function search(){
		
		var div2 = document.getElementById("search2");
		if(""==div2.value) {
			div2.style.display = "none";
			return;
		}
		var div = document.getElementById("search").value;
		var xhr = getXMLHttpRequest();
		xhr.onreadystatechange = function() {
			if(xhr.readyState==4) {
				if(xhr.status==200) {
					var names = xhr.responseText;
					names = names.substring(2, names.length-2);
					var name = names.split("\",\"");
					var childDiv = "";
					//div2.style.border = "1px solid black";
					for(var i=0;i<name.length;i++) {
						childDiv += "<div onmouseover='changebackground_over(this)' onmouseout='changebackground_out(this)' onclick='change(this)'>"+name[i]+"</div>";
					}
					div2.innerHTML = childDiv;
					div2.style.display = "block";
				}
			}
		};
		xhr.open("get", "${pageContext.request.contextPath }/servlet/SearchBooksAJAXServlet?name="+div+"&time="+new Date().getTime());
		xhr.send(null);
	}
	
	function changebackground_over(div) {
		div.style.backgroundColor= "gray";
	}
	
	function changebackground_out(div) {
		div.style.backgroundColor= "";
	}
	
	function change(div) {
		var divS = document.getElementById("search");
		divS.value = div.innerHTML;
		//var div2 = document.getElementById("search2");
		//div2.innerHTML = "";
		div.parentNode.style.display = "none";
	}
	
	/* function Person() {
		this.name="tom";
		this.age=10;
		this.show=function(){
			alert(this.name);
		};
	}
	
	var person = new Person();
	document.write(person.name);
	document.write(person.age);
	person.show(); */
	
	var person2 = {name:"tom", age:12};
	document.write(person2.name);
	var person3 = [{name:"tom", age:12}, {name:"jack", age:15}];
	document.write(person3[1].name);
</script>
</head>
<body>
	<div style="background-color: rgb(100,100,100);height: 20px;">
		搜索框:<input type="text" id="search" style="width:100px;height:15px;background-color: white;" onkeyup="search()"/>搜索
	</div>
	<div id="search2" style="width: 102px;background-color: white;position: absolute;left: 61px;top: 31px;"></div>
</body>
</html>