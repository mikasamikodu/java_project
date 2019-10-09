<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/login.css">
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.htm" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container">

	  ${exception.message }
      <form id="loginForm" action="${APP_PATH }/doLogin.do" method="post" class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" value="admin" name="loginacct" id="loginacct" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" value="123" id="userpswd" name="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select name="type" id="type" class="form-control" >
                <option value="member">会员</option>
                <option value="user">管理</option>
            </select>
		  </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
          <br>
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="register.htm">我要注册</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
      </form>
    </div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
    <script src="${APP_PATH }/jquery/layer/layer.js"></script>
    <script>
     function dologin() {
    	 //异步请求
    	 var loginacct = $("#loginacct");
    	 var userpswd = $("#userpswd");
    	 var type = $("#type");
    	 
    	 if($.trim(loginacct.val()) == ""){
    		 //icon5效果是笑脸，shift:6效果是抖动，time:1000效果持续时间是1秒
    		 layer.msg("账号不能为空", {time:1000, icon:5, shift:6}, function(){
    			 loginacct.val("");
        		 loginacct.focus();
    		 });
    		/*  alert("账号不能为空"); */
    		 return false;
    	 }
    	 if($.trim(userpswd.val()) == ""){
    		 layer.msg("密码不能为空", {time:1000, icon:5, shift:6}, function(){
    			 userpswd.val("");
        		 userpswd.focus();
    		 });
    		/*  alert("密码不能为空"); */
    		 return false;
    	 }
    	 var layerIndex = -1; 
    	 $.ajax({
    		 type : "POST",
    		 url : "${APP_PATH}/doLogin.do",
    		 data : {
    			 "loginacct" : loginacct.val(),
    			 "userpswd" : userpswd.val(),
    			 "type" : type.val()
    		 },
    		 beforeSend : function(){
    			 //数据校验
    			 layerIndex = layer.msg("处理中...", {icon:6});
    			 return true;
    		 },
    		 success: function(result){
    			 layer.close(layerIndex);
    			 if(result.success){
	    			 window.location.href="${APP_PATH}/main.htm";
    			 }else{
    				layer.msg(result.message, {time:1000, icon:5, shift:6});
    				return false;
    			 }
    		 },
    		 error: function(){
    			 layer.msg("登录失败！！", {time:1000, icon:5, shift:6});
    			 return false;
    		 }
    	 });
	   // $("#loginForm").submit();//同步请求
       /*  var type = $(":selected").val();
        if ( type == "user" ) {
            window.location.href = "main.html";
        } else {
            window.location.href = "index.html";
        } */
    }
    </script>
  </body>
</html>