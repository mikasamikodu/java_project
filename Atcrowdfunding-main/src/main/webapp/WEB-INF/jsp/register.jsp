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

      <form id="registerForm" action='doRegister.do' method="post"  class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户注册</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" name="loginacct" class="form-control" id="loginacct" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" name="userpswd" class="form-control" id="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="text" name="email" class="form-control" id="email" placeholder="请输入邮箱地址" style="margin-top:10px;">
			<span class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select name="usertype" id="usertype" class="form-control" >
                <option value="1">企业</option>
                <option value="0">个人</option>
            </select>
		  </div>
        <div class="checkbox">
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="login.htm">我有账号</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="doRegister()" > 注册</a>
      </form>
    </div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
     <script src="${APP_PATH }/jquery/layer/layer.js"></script>
    <script type="text/javascript">
    	function doRegister(){
    		//同步请求
    		//$("#registerForm").submit();
    		//ajax异步请求
    		var loginacct = $("#loginacct");
    		var userpswd = $("#userpswd");
    		var email = $("#email");
    		var usertype = $("#usertype");
    		
    		//表单验空
    		if($.trim(loginacct.val()) == ""){
    			 layer.msg("账号不能为空", {time:1000, icon:5, shift:6}, function(){
        			 loginacct.val("");
            		 loginacct.focus();
        		 });
    			
    			/* alert("账户名不能为空"); */
    			return false;
    		}
    		if($.trim(userpswd.val()) == ""){
    			 layer.msg("密码不能为空", {time:1000, icon:5, shift:6}, function(){
    				 userpswd.val("");
    	    			userpswd.focus();
        		 });
    			return false;
    		}
    		if($.trim(email.val()) == ""){
    			 layer.msg("邮箱不能为空", {time:1000, icon:5, shift:6}, function(){
    				 email.val("");
    	    			email.focus();
        		 });
    			return false;
    		}
    		if($.trim(usertype.val()) == ""){
    			 layer.msg("请选择帐户类型", {time:1000, icon:5, shift:6}, function(){
    				 usertype.val("");
    				 usertype.focus();
        		 });
    			return false;
    		}
    		
    		$.ajax({
    			type : "POST",
    			url : "doRegister.do",
    			data : {
    				loginacct : loginacct.val(),
    				userpswd : userpswd.val(),
    				email : email.val(),
    				usertype : usertype.val()
    			},
    			success : function(result){
    				if(result.success){
    					console.log("success");
    					window.location.href="${APP_PATH}/login.htm";
    				}else{
    					console.log("fail");
    					alert(result.message);
    					return;
    				}
    			},
    			error : function(e){
    				alert("error"+e.message);
					return;
    			}
    		});
    	}
    </script>
  </body>
</html>