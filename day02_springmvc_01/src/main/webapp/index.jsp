<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/8
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.js"></script>
    <script>
        $(function(){
            $("#btn").click(function(){
                $.ajax({
                    url: "user/testAjax",
                    contentType: "application/json;charset=UTF-8",
                    data: '{"name":"tom","password":"1234","age":23}',
                    dataType: "json",
                    type: "post",
                    success: function(data){
                        alert(data.toString());
                        alert(data.name);
                        alert(data.age);
                        alert(data.password);
                    }
                });
            });
           }
        )
    </script>
</head>
<body>
    <a href="user/testString">testString</a><br/>
    <a href="user/testVoid">testVoid</a><br/>
    <a href="user/testForward">testForward</a><br/>
    <a href="user/testModelAndView">testModelAndView</a><br/>
    <button id="btn">发送</button>
</body>
</html>
