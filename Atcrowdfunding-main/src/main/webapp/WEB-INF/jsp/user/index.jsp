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
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
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
      <input id='inputText' class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" id='serchBtn' class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" id="delBatchBtn" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/user/toAdd.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input id="allCheckbox" type="checkbox"></th>
                  <th>账号</th>
                  <th>名称</th>
                  <th>邮箱地址</th>
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
			    queryPageUser(0);
            });
          
            function deleteUserById(id, name){
            	layer.confirm("是否删除"+name+"用户？", {icon: 3, shift: 1}, function(index){
        			$.ajax({
                		type : "post",
                		url : "${APP_PATH}/user/deleteUserById.do",
                		data : {
                			"id" : id
                		},
                		beforeSend : function(){
                			return true;
                		},
                		success : function(result){
                			layer.msg("删除成功", {time:500, icon: 6, shift: 4});
                			if(result.success){
                 				window.location.href = "${APP_PATH}/user/index.htm";
                			}else{
                				layer.msg(result.message, {time:1000, icon: 6});
                			}
                		},
                		error : function(){
                			layer.msg("删除失败", {time:1000, icon: 6});
                		}
                	});
        		}, function(index){
        			layer.close(index);
        		}); 
        	}
            
            /* function pageChange(pageNo){ */
/*             	window.location.href="index.do?pageNo="+pageNo;
 */
 			/* 	queryPageUser(pageNo);
            } */
            
            var json = {
        			"pageSize" : 10
        		};
            
            function queryPageUser(pageNo, jq){
            	json.pageNo = pageNo;
            	$.ajax({
            		type : "post",
            		url : "${APP_PATH}/user/doIndex.do",
            		data : json,
            		beforeSend : function(){
            			layerIndex = layer.load("正在加载数据...", {icon:6});
            			return true;
            		},
            		success : function(result){
            			layer.close(layerIndex);
            			var page = result.page;
        				var datas = page.datas;
            			if(result.success){
            				var content = '';
            				$.each(datas, function(i, user){
	                            content += '<tr>';
	                            content += '  <td>'+(i+1)+'</td>';
	            				content += '  <td><input type="checkbox" id="'+user.id+'" name="'+user.loginacct+'"></td>';
	                            content += '  <td>'+user.loginacct+'</td>';
	                            content += '  <td>'+user.username+'</td>';
	                            content += '  <td>'+user.email+'</td>';
	                            content += '  <td>';
	            				content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/user/assignRole.htm?id='+user.id+'\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
	            				content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/user/toEdit.htm?id='+user.id+'\'" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
	            				content += '	  <button type="button" onclick="deleteUserById('+user.id+',\''+user.loginacct+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
	            				content += '  </td>';
	                            content += '</tr>';
            				});
                        	$("tbody").empty().append(content);
                        	
/*                         	var contentBar = '';
                       		if(page.pageNo <= 1){
                       			contentBar += '<li class="disabled"><a href="#">上一页</a></li>';
                       		}else{
                       			contentBar += '<li><a href="#" onClick="pageChange('+(page.pageNo-1)+')">上一页</a></li>';
                       		}
                        	for(var i=1;i<=page.totalNo;i++){
                        		contentBar += '<li ';
                        		if(page.pageNo == i){
                        			contentBar += ' class="active" ';
                        		}
                        		contentBar += ' ><a href="#" onClick="pageChange('+i+')">'+i+'</a></li>';
                        	}
                        	if(page.pageNo==page.totalNo){
                       			contentBar += '<li class="disabled"><a href="#">下一页</a></li>';
                       		}else{
                       			contentBar += '<li><a href="#" onClick="pageChange('+(page.pageNo+1)+')">下一页</a></li>';
                       		}
                        	$(".pagination").html(contentBar);
 */                        	
                       		// 创建分页
                       		$(".pagination").pagination(page.totalSize, {
                       			num_edge_entries: 1,//边缘页数
                       			num_display_entries: 3, //主体页数
                       			callback: queryPageUser,
                       			items_per_page: page.pageSize, //每页显示1项
                       			current_page: pageNo,
                       			prev_text: "上一页",
                       			next_text: "下一页"
                       		});
                        	 
            			}else{
            				layer.msg(result.message, {time:1000, icon:5});
            			}
            		},
            		error : function(){
            			layer.msg("加载数据失败...", {time:1000, icon:5});
            		}
            	});
           	
            	$("#serchBtn").click(function(){
            		var input = $("#inputText").val();
            		json.input = input;
            		queryPageUser(0);
            	});
            
            	$("#allCheckbox").click(function(){
            		var checked = this.checked;
            		var box = $("tbody tr td input:checkbox");
            		box.prop("checked", checked);
            	});
            	
            	$("#delBatchBtn").click(function(){
            		var box = $("tbody tr td input:checked");
            		var str = [];
            		if(box.length==0){
            			layer.msg("尚未有数据被选中！", {time: 1000, icon:5, shift:6});
            			return false;
            		}
            		layer.confirm("确认删除吗？", {icon:7, shift:2}, function(index){
            			layer.close(index);
	            		var jsonobj = {};
	            		$.each(box, function(i, n){
	            			/* if(i!=0){
	            				str += "&";
	            			}
	            			str += "id="+n.id; */
	            			jsonobj["datas["+i+"].id"] = n.id;
	            			jsonobj["datas["+i+"].loginacct"] = n.name;
	            		});
	            		$.ajax({
	            			type : "post",
	            			url : "${APP_PATH}/user/delBatch.do",
	            			data: jsonobj,
	/*             			data: str, */
	            			beforeSend : function(){
	            				layerIndex = layer.msg("正在删除，请稍等...", {icon:4, shift:2});
	            				return true;
	            			},
	            			success : function(result){
	            				layer.close(layerIndex);
	            				if(result.success){
	            					layer.msg("删除成功", {time:1000, icon:6, shift:2});
	            					window.location.href = "${APP_PATH}/user/index.htm";
	            				}else{
	                				layer.msg(result.message, {time:1000, icon:5});
	                			}
	                		},
	                		error : function(){
	                			layer.msg("批量删除失败...", {time:1000, icon:5});
	                		}
	            		});
    				}, function(index){
    					layer.close(index);
    				});
	            });
            }
        </script>
  </body>
</html>
