<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<link rel="stylesheet" href="${APP_PATH }/css/pagination.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 角色维护</a></div>
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
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input id="content" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" id="searchBtn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" id="delBatch" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/role/add.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="allCheckbox"></th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination">
						</ul>
					 </td>
				 </tr>
			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/script/common.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script src="${APP_PATH }/jquery/jquery.pagination.js"></script>
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
			    showMenu();
			    queryPage(0);
            });
            
            function pageChange(pageNo){
            	queryPage(pageNo);
            }
            
           	var str = {
           		"pageSize" : 3
           	}
           	
            function queryPage(pageNo, jq){
            	str.pageNo = pageNo;
            	$.ajax({
            		type : "post",
            		url : "${APP_PATH}/role/doIndex.do",
            		data : str,
            		beforeSend : function(){
            			layerIndex = layer.msg("正在加载数据", {icon: 7, shift: 1});
            			return true;
            		},
            		success : function(result){
            			layer.close(layerIndex);
            			var page = result.page;
            			var datas = page.datas;
            			if(result.success){
            				layer.msg("数据加载成功", {time:1000, icon: 7, shift: 3});
            				var content = "";
            				$.each(datas, function(i,role){
            					content += '<tr>';
            		            content += '<td>'+(i+1)+'</td>';
            					content += '  <td><input type="checkbox" id='+role.id+'></td>';
            		            content += '<td>'+role.name+'</td>';
            		            content += '<td>';
            					content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/role/assignPermission.htm?id='+role.id+'\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
            					content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/role/update.htm?id='+role.id+'\'" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
            					content += '	  <button type="button" onclick="deleteRole('+role.id+')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
            					content += '  </td>';
            		          	content += '</tr>';
            				});
            				$("tbody").html(content);
            				/* var contentBar = "";
            				if(page.pageNo<=1){
            					contentBar += '<li class="disabled"><a href="javascript:void(0)">上一页</a></li>';
            				}else{
            					contentBar += '<li onclick="pageChange('+(pageNo-1)+')"><a href="#">上一页</a></li> ';
            				}
            				for(var i=1;i<=page.totalNo;i++){
            					contentBar += '<li';
            					if(pageNo==i){
            						contentBar += ' class="active"><a href="#">'+i+'</a></li>';
            					}else{
            						contentBar += ' onclick="pageChange('+i+')"><a href="#">'+i+'</a></li>';
            					}
            				}
            				if(page.pageNo>=page.totalNo){
            					contentBar += '<li class="disabled"><a href="javascript:void(0)">下一页</a></li>';
            				}else{
            					contentBar += '<li onclick="pageChange('+(pageNo+1)+')"><a href="#">下一页</a></li> ';
            				}
							$(".pagination").html(contentBar); */
							
            				// 创建分页
                       		$(".pagination").pagination(page.totalSize, {
                       			num_edge_entries: 2,//边缘页数
                       			num_display_entries: 1, //主体页数
                       			callback: queryPage,
                       			items_per_page: page.pageSize, //每页显示5项
                       			current_page: pageNo,//当前显示页
                       			prev_text: "上一页",
                       			next_text: "下一页"
                       		});
							
            			}else{
            				layer.msg(result.message, {time:1000, icon: 5, shift: 3});
            			}
            		},
            		error : function(){
            			layer.msg("数据加载失败", {time:1000, icon: 5, shift: 3});
            		}
            	});
            }
			$("#searchBtn").click(function(){
				str.content = $("#content").val();
				queryPage(0);
           	});
           	function deleteRole(id){
        		$.ajax({
        			type : "post",
        			url : "${APP_PATH}/role/deleteRole.do",
        			data : {
        				"id" : id
        			},
        			beforeSend : function(){
        				layerIndex = layer.msg("正在删除，请稍等...", {icon:8});
        				return true;
        			},
        			success : function(result){
        				layer.close(layerIndex);
        				if(result.success){
        					layer.msg("删除成功(-_-)", {time:1000, icon:6});
        					queryPage(0);
        				}else{
        					layer.msg(result.message, {time:1000, icon:5, shift: 5});
        				}
        			},
        			error : function(){
        				layer.msg("删除失败", {time:1000, icon:5, shift: 5});
        			}
        		});
        	}
           	
           	$("#allCheckbox").click(function(){
           		var checked = this.checked;
           		$("tbody tr td input[type=checkbox]").prop("checked", checked);
           	});
           	
           	$("#delBatch").click(function(){
           		var box = $("tbody tr td input:checked");
           		var str = "";
           		$.each(box, function(i,n){
           			if(i!=0){
           				str += "&";
           			}
           			str += "id="+n.id;
           		});
           		$.ajax({
           			type : "post",
           			url : "${APP_PATH}/role/delBatch.do",
           			data : str,
           			beforeSend : function(){
        				layerIndex = layer.msg("正在批量删除，请稍等...", {icon:8});
        				return true;
        			},
        			success : function(result){
        				layer.close(layerIndex);
        				if(result.success){
        					layer.msg("批量删除成功(-_-)", {time:1000, icon:6});
        					queryPage(0);
        				}else{
        					layer.msg(result.message, {time:1000, icon:5, shift: 5});
        				}
        			},
        			error : function(){
        				layer.msg("批量删除失败", {time:1000, icon:5, shift: 5});
        			}
        		});
           	});
        </script>
  </body>
</html>