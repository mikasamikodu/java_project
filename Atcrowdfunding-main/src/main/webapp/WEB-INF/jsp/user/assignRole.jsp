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
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户维护</a></div>
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
				  <li><a href="${APP_PATH}/main.htm">首页</a></li>
				  <li><a href="${APP_PATH}/user/index.htm">数据列表</a></li>
				  <li class="active">分配角色</li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
				<form role="form" class="form-inline">
				  <div class="form-group">
					<label for="exampleInputPassword1">未分配角色列表</label><br>
					<select id="leftSelect" class="form-control" multiple size="10" style="width:210px;overflow-y:auto;">
                       <c:forEach items="${left }" var="role">
	                        <option value="${role.id }">${role.name }</option>
                       </c:forEach>
                    </select>
				  </div>
				  <div class="form-group" style="width:60px;overflow-y:auto;">
                        <ul  style="padding-left: 0px;padding-top: 10px;">
                        	<li id="leftBtn" class="btn btn-default glyphicon glyphicon-chevron-right"  style="margin-bottom:10px"/>
                            <li id="rightBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-bottom:10px"/>
                            <li id="leftBtn2" class="btn btn-default" style="margin-bottom:10px">
                            	<span class="glyphicon glyphicon-chevron-right"></span>
                            	<span class="glyphicon glyphicon-chevron-right"></span>
                            </li>
                            <li id="rightBtn2" class="btn btn-default">
                            	<span class="glyphicon glyphicon-chevron-left"></span>
                            	<span class="glyphicon glyphicon-chevron-left"></span>
                            </li>
                        </ul>
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">已分配角色列表</label><br>
					<select id="rightSelect" class="form-control" multiple size="10" style="width:210px;overflow-y:auto;">
                        <c:forEach items="${right }" var="role">
	                        <option value="${role.id }">${role.name }</option>
                       </c:forEach>
                    </select>
				  </div>
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
			     
            
            
            $("#leftSelect option").on("dblclick", function(){
            	var selected = $("#leftSelect option:selected");
            	leftSelect(selected)
            });
            
            $("#leftBtn").click(function(){
            	var selected = $("#leftSelect option:selected");
            	leftSelect(selected)
            });
            
            $("#leftBtn2").click(function(){
            	var selected = $("#leftSelect option");
            	leftSelect(selected)
            });
            function leftSelect(selected){
            	if(!selected[0]){
            		layer.msg("左侧至少有一项可供分配！", {time: 599, icon: 5, shift:9});
            		return false;
            	}
            	var str = {
            		"userid" : "${param.id}"
            	};
            	$.each(selected, function(i,n){
	            	str["ids["+i+"]"] = n.value;
            	});
            	selected.appendTo($("#rightSelect"));
            	$.ajax({
            		type : "post",
            		url : "${APP_PATH}/user/addRole.do",
            		data : str,
            		beforeSend : function(){
            			layerIndex = layer.msg("正在分配..", {icon:4, shift:3});
            			return true;
            		},
            		success : function(result){
            			layer.close(layerIndex);
            			if(result.success){
	            			layer.msg("分配成功", {time:500, icon: 6, shift: 4});
             				//window.location.href = "${APP_PATH}/user/index.htm";
            			}else{
            				layer.msg(result.message, {time:1000, icon: 6});
            			}
            		},
            		error : function(){
            			layer.msg("分配失败", {time:1000, icon: 6});
            		}
            	});
            };
            
            $("#rightSelect option").on("dblclick", function(){
            	var selected = $("#rightSelect option:selected");
            	rightSelect(selected)
            });
            
            $("#rightBtn").click(function(){
            	var selected = $("#rightSelect option:selected");
            	rightSelect(selected)
            });
            
            $("#rightBtn2").click(function(){
            	var selected = $("#rightSelect option");
            	rightSelect(selected)
            });
            
            function rightSelect(selected){
            	if(!selected[0]){
            		layer.msg("右边至少一项可供分配！", {time: 599, icon: 5, shift:9});
            		return false;
            	}
            	var str = {
               		"userid" : "${param.id}"
               	};
               	$.each(selected, function(i,n){
   	            	str["ids["+i+"]"] = n.value;
               	});
            	selected.appendTo($("#leftSelect"));
            	$.ajax({
            		type : "post",
            		url : "${APP_PATH}/user/removeRole.do",
            		data : str,
            		beforeSend : function(){
            			layerIndex = layer.msg("正在解除分配..", {icon:4, shift:3});
            			return true;
            		},
            		success : function(result){
            			layer.close(layerIndex);
            			if(result.success){
	            			layer.msg("解除分配成功", {time:500, icon: 6, shift: 4});
             				//window.location.href = "${APP_PATH}/user/index.htm";
            			}else{
            				layer.msg(result.message, {time:1000, icon: 6});
            			}
            		},
            		error : function(){
            			layer.msg("解除分配失败", {time:1000, icon: 6});
            		}
            	});
            };
     </script>
  </body>
</html>