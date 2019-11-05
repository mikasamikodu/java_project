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
            <div><a class="navbar-brand" style="font-size:32px;" href="${APP_PATH }/advertisement/index.htm">众筹平台 - 用户维护</a></div>
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
				  <li><a href="${APP_PATH}/advertisement/index.htm">数据列表</a></li>
				  <li class="active">编辑</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="addForm" enctype="multipart/form-data" method="post">
				  <div class="form-group">
					<label for="fname">广告名称</label>
					<input type="text" class="form-control" name="name" id="fname" value="${advertisement.name }" placeholder="请输入广告名称">
				  </div>
				  <div class="form-group">
					<label for="furl">广告链接</label>
					<input type="text" class="form-control" id="furl" name="url" value="${advertisement.url }"  placeholder="请输入广告链接">
				  </div>
				  <div class="form-group">
					<label for="fadvpicc">广告图片</label><br/>
					<input type="file" class="form-control" id="fadvpic" name="advpic"/>
				  </div>
				  <button type="button" id="addBtn" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 修改</button>
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
    <script src="${APP_PATH }/jquery/jquery-form.min.js"></script>
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
 	/* 		var form = $("#addForm");
 			form.attr("action", "${APP_PATH}/advertisement/upload.do");
 			form[0].submit();
 */			
 			var options = {
		 		url : "${APP_PATH}/advertisement/upload.do",
		 		beforeSubmit : function(){
					layerIndex = layer.msg("正在保存，请稍等...", {icon:1, shift:2});
					return true;
		 		},
		 		success : function(result){
		 			layer.close(layerIndex);
		 			if(result.success){
		 				layer.msg("保存成功", {time:1000, icon: 6, shift:2});
		 				window.location.href = "${APP_PATH}/advertisement/index.htm";
		 			}else{
		 				layer.msg(result.message, {time:1000, icon: 5, shift:6});
		 			}
		 		}
 			};
 			$("#addForm").ajaxSubmit(options);	
 			return;
 		});
 	</script>
  </body>
</html>
