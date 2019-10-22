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
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>

<form id="uploadForm" method="post" enctype="multipart/form-data">
	<input type="file" id="uploadFile" style="display:none" name="processDefinitionFile"></input>
</form>

<button type="button" id="uploadBtn" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-upload"></i> 上传流程定义文件</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr>
                  <th width="50">序号</th>
                  <th>流程定义名称</th>
                  <th>流程定义版本</th>
                  <th>流程定义Key</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody></tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination"></ul>
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
	<script src="${APP_PATH }/jquery/jquery-form.min.js"></script>
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
            
            function pageChange(pageNo){
				queryPageUser(pageNo);
            }
            
            var json = {
        			"pageNo" : 1,
        			"pageSize" : 5
        		};
            
            function queryPageUser(pageNo, jq){
            	var layerIndex = -1;
            	json.pageNo = pageNo;
            	$.ajax({
            		type : "post",
            		url : "${APP_PATH}/process/doIndex.do",
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
            				$.each(datas, function(i, n){
            					content += '<tr>';
            		            content += '<td>'+(i+1)+'</td>';
            		            content += '<td>'+n.name+'</td>';
            		            content += '<td>'+n.version+'</td>';
            		            content += '<td>'+n.key+'</td>';
            		            content += '<td>';
            		            content += '    <button type="button" onclick="window.location.href=\'${APP_PATH}/process/showImg.htm?id='+n.id+'+\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-eye-open"></i></button>';
            		            content += '    <button type="button" onclick="deleteProDe(\''+n.id+'\',\''+n.name+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
            					content += '  </td>';
            		          	content += '</tr>';
            				});
                        	$("tbody").empty().append(content);
                        	
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
            }
            
            $("#uploadBtn").click(function(){
            	$("#uploadFile").click();
            });
            
            $("#uploadFile").change(function(){
            	var options = {
       		 		url : "${APP_PATH}/process/upload.do",
       		 		beforeSubmit : function(){
       					layerIndex = layer.msg("正在部署，请稍等...", {icon:1, shift:2});
       					return true;
       		 		},
       		 		success : function(result){
       		 			layer.close(layerIndex);
       		 			if(result.success){
       		 				layer.msg("部署成功", {time:1000, icon: 6, shift:2});
       		 				queryPageUser(0);
       		 			}else{
       		 				layer.msg(result.message, {time:1000, icon: 5, shift:6});
       		 			}
       		 		}
        		};
       			$("#uploadForm").ajaxSubmit(options);	
       			return;
            });
            
            function deleteProDe(id, name){
            	layer.confirm("确定删除流程定义"+name+"吗？", {icon:8, shift:2}, function(index){
					var cindex = null;
            		$.ajax({
						type: "post",
						url: "${APP_PATH}/process/delete.do",
						data: {
							"id" : id  
						},
						beforeSend : function(){
							cindex = layer.msg("正在删除，请稍等...", {icon:6, shift:2});
							return true;
						},
						success : function(result){
							layer.close(cindex);
							if(result.success){
								layer.msg("删除成功！", {time:1000, icon:6, shift:2});
								queryPageUser(0);
							}else{
								layer.msg(result.message, {time:1000, icon:5, shift:7});
							}
						},
						error : function(){
							layer.msg("删除失败！", {time:1000, icon:5, shift:7});
						}
					});            		
            		layer.close(index);
            	}, function(index){
            		layer.close(index);
            	})
            }
        </script>
  </body>
</html>