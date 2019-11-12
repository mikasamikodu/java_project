<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
	<style>
#footer {
    padding: 15px 0;
    background: #fff;
    border-top: 1px solid #ddd;
    text-align: center;
}
	</style>
  </head>
  <body>
 <div class="navbar-wrapper">
      <div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			  <div class="container">
				<div class="navbar-header">
				  <a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a>
				</div>
            <div id="navbar" class="navbar-collapse collapse" style="float:right;">
              <ul class="nav navbar-nav">
                <%@ include file="/WEB-INF/jsp/common/top.jsp" %>
              </ul>
            </div>
			  </div>
			</nav>

      </div>
    </div>

    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>实名认证 - 申请</h1>
      </div>

		<ul class="nav nav-tabs" role="tablist">
		  <li role="presentation" ><a href="${APP_PATH }/member/basicinfo.htm"><span class="badge">1</span> 基本信息</a></li>
		  <li role="presentation" class="active"><a href="${APP_PATH }/member/uploadCert.htm"><span class="badge">2</span> 资质文件上传</a></li>
		  <li role="presentation"><a href="${APP_PATH }/member/apply2.htm"><span class="badge">3</span> 邮箱确认</a></li>
		  <li role="presentation"><a href="${APP_PATH }/member/apply3.htm"><span class="badge">4</span> 申请确认</a></li>
		</ul>
        
		<form id='uploadForm' role="form" style="margin-top:20px;" method="post" enctype="multipart/form-data">
			<c:forEach items="${certs }" var="cert" varStatus="status">
			  <div class="form-group">
				<label for="exampleInputEmail1">${cert.name }</label>
				<input type="hidden" name="certs[${status.index }].certid" value="${cert.id }"/>
				<input type="file" name="certs[${status.index }].fileImg" class="form-control" >
	            <br>
	            <img src="${APP_PATH }/img/pic.jpg" style="display:none;">
			  </div>
			</c:forEach>
          <button type="button" onclick="window.location.href='${APP_PATH}/member/basicinfo.htm'" class="btn btn-default">上一步</button>
		  <button type="button" id="next" class="btn btn-success">下一步</button>
		</form>
		<hr>
    </div> <!-- /container -->
        <div class="container" style="margin-top:20px;">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div id="footer">
                        <div class="footerNav">
                             <a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a rel="nofollow" href="http://www.atguigu.com">服务条款</a> | <a rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a rel="nofollow" href="http://www.atguigu.com">联系我们</a>
                        </div>
                        <div class="copyRight">
                            Copyright ?2017-2017 atguigu.com 版权所有
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script src="${APP_PATH }/jquery/jquery-form.min.js"></script>
	<script>
		  $('#myTab a').click(function (e) {
	        e.preventDefault()
	        $(this).tab('show')
	      });
		  $("#next").click(function(){
			  var options = {
			 		url : "${APP_PATH}/member/upload.do",
			 		beforeSubmit : function(){
						layerIndex = layer.msg("正在上传，请稍等...", {icon:1, shift:2});
						return true;
			 		},
			 		success : function(result){
			 			layer.close(layerIndex);
			 			if(result.success){
			 				layer.msg("上传成功", {time:1000, icon: 6, shift:2});
			 				window.location.href = "${APP_PATH}/member/checkemail.htm";
			 			}else{
			 				layer.msg(result.message, {time:1000, icon: 5, shift:6});
			 			}
			 		}
	 			};
	 			$("#uploadForm").ajaxSubmit(options);	
	 			return;
		  });
		  
		  $(":file").change(function(event){
			  var files = event.target.files;
			  var file;
			  if(files&&files.length>0){
				  file = files[0];
				  var URL = window.URL||window.webkitURL;//当前页面地址栏的路径
				  var imgURL = URL.createObjectURL(file);//本地图片路径
				  var imgObj = $(this).next().next();//获取同辈紧邻的元素
				  imgObj.attr("src", imgURL);
				  imgObj.show();
			  }
		  });
	</script>
  </body>
</html>