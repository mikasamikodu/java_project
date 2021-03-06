<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 流程管理</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
           <%@ include file="/WEB-INF/jsp/common/top.jsp" %>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<%@ include file="/WEB-INF/jsp/common/left.jsp" %>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
				<form id="addForm">
				  <div class="form-group">
					<label>用户真实姓名:${member.username }</label>
				  </div>
				  <div class="form-group">
					<label>用户银行卡号：${member.cardnum }</label>
				  </div>
				  <div class="form-group">
					<label>用户电话:${member.telephone}</label>
				  </div>
				  <c:forEach items="${certImgs }" var="certimg">
				  	<div class="form-group">
						<label>${certimg.name }</label>
						<br/>
						<img src="${APP_PATH }/pictures/cert/${certimg.iconpath}">
				  	</div>
				  </c:forEach>
				  <button type="button" id="passBtn" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 审核通过</button>
				  <button type="button" id="refuseBtn" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 审核拒绝</button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
    <script type="text/javascript">
        $(function () {
		   $(".list-group-item").click(function(){
		    if ( $(this).find("ul") ) {
				$(this).toggleClass("tree-closed");
				if ( $(this).hasClass("tree-closed") ) {
					$("ul", this).hide("fast");
				} else {
					$("ul", this).show("fast");
				}
			 }
		   });
		   $("#passBtn").click(function(){
			   $.ajax({
				   type: "post",
				   url: "${APP_PATH}/authcert/pass.do",
				   data: {
					   "taskid": "${param.taskid}",
					   "memberid": "${param.memberid}"
				   },
				   success: function(result){
					   if(result.success){
						   window.location.href="${APP_PATH}/authcert/index.htm"
					   }
				   }
			   });
		   });
		   
		   $("#refuseBtn").click(function(){
			   $.ajax({
				   type: "post",
				   url: "${APP_PATH}/authcert/refuse.do",
				   data: {
					   "taskid": "${param.taskid}",
					   "memberid": "${param.memberid}"
				   },
				   success: function(result){
					   if(result.success){
						   window.location.href="${APP_PATH}/authcert/index.htm"
					   }
				   }
			   });
		   });
        });
    </script>
  </body>
</html>