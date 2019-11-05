<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" href="${APP_PATH }/css/doc.min.css">
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
            <div><a class="navbar-brand" style="font-size:32px;" href="${APP_PATH }/user/index.htm">众筹平台 - 用户维护</a></div>
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
				<ol class="breadcrumb">
				  <li><a href="${APP_PATH }/main.htm">首页</a></li>
				  <li><a href="${APP_PATH}/role/index.htm">数据列表</a></li>
				  <li class="active">新增</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="addForm">
				  <div class="form-group">
					<label for="name">角色名称</label>
					<input type="text" class="form-control" id="name" placeholder="请输入角色名称">
				  </div>
				  <button type="button" id="addBtn" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
				  <button type="button" id="resetBtn" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<%@ include file="/WEB-INF/jsp/common/help.jsp" %>
	  </div>
	</div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
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
        });
        
        $("#resetBtn").click(function(){
        	$("#addForm")[0].reset();
        });
        
        $("#addBtn").click(function(){
        	var name = $("#name");
        	var layerIndex = -1;
  	
        	if($.trim(name.val()) == ""){
       		 //icon5效果是笑脸，shift:6效果是抖动，time:1000效果持续时间是1秒
        		 layer.msg("角色名称不能为空", {time:1000, icon:5, shift:1}, function(){
        			 name.val("");
        			 name.focus();
        		 });
        		 return false;
       	 	}
        	
        	$.ajax({
        		type : "post",
        		url : "${APP_PATH}/role/doAdd.do",
        		data : {
        			"name" : name.val()
        		},
        		beforeSend : function(){
        			layerIndex = layer.msg("正在新增角色信息，请稍候...",{icon : 6});
        			return true;
        		},
        		success : function(result){
        			layer.close(layerIndex);
        			layer.msg("新增角色信息成功", {time:500, icon: 6});
        			if(result.success){
         				window.location.href = "${APP_PATH}/role/index.htm";
        			}else{
        				layer.msg(result.message, {time:1000, icon: 6});
        			}
        		},
        		error : function(){
        			layer.msg("新增角色信息出现问题", {time:1000, icon: 6});
        		}
        	});
        });
    </script>
  </body>
</html>
