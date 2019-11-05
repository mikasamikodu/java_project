<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="GB18030">
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
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 广告管理</a></div>
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
      <input id="input" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" id="searchBtn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button id="deleteBatchBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/advertisement/add.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th width="30"><input type="checkbox" id="allCheckbox"/></th>
                  <th>广告描述</th>
                  <th>状态</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="4" align="center">
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
            
          var json = {
        		  "pageSize": 3
          }
		  function queryPage(pageNo,jq){
			  json.pageNo = pageNo;
			  $.ajax({
				  type: "post",
				  url: "${APP_PATH}/advertisement/doIndex.do",
				  data: json,
				  beforeSend: function(){
					  layerIndex = layer.msg("正在加载数据，请稍等...",{icon:6,shift:2});
					  return true;
				  },
				  success: function(result){
					  layer.close(layerIndex);
					  if(result.success){
						  var content = '';
						  var page = result.page;
						  var datas = page.datas;
						  $.each(datas, function(i,n){
							  var status = '';
							  if(n.status==0){
								  status = '草稿';
							  }else if(n.status==1){
								  status = '未审核';
							  }else if(n.status==2){
								  status = '审核完成';
							  }else{
								  status = '发布';
							  }
							  content += '<tr>';
					          content += '  <td>'+(i+1)+'</td>';
							  content += '  <td><input type="checkbox" id="'+n.id+'"/></td>';
					          content += '  <td>'+n.name+'</td>';
					          content += '  <td>'+status+'</td>';
					          content += '  <td>';
					          content += '      <button type="button" class="btn btn-success btn-xs"><i class="glyphicon glyphicon-check"></i></button>';
					          content += '      <button onclick="window.location.href=\'${APP_PATH}/advertisement/edit.htm?id='+n.id+'\'" type="button" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-pencil"></i></button>';
					          content += '      <button onclick="deleteData('+n.id+',\''+n.name+'\')" type="button" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-remove"></i></button>';
							  content += '  </td>';
					          content += '</tr>';
						  });
						  $("tbody").html(content);
						  
						  $(".pagination").pagination(page.totalSize, {
                     			num_edge_entries: 1,//边缘页数
                     			num_display_entries: 3, //主体页数
                     			callback: queryPage,
                     			items_per_page: page.pageSize, //每页显示1项
                     			current_page: pageNo,
                     			prev_text: "上一页",
                     			next_text: "下一页"
                     	  });
					  }else{
						  layer.msg(result.message, {time:1000,icon:5,shift:2});
					  }
				  },
				  error: function(){
					  layer.msg("数据加载失败", {time:1000,icon:5,shift:2});
				  }
			  });
		  }
          $("#searchBtn").click(function(){
        	  json.input = $("#input").val();
        	  queryPage(0);
          });
          function deleteData(id,name){
        	  layer.confirm("确认要删除广告"+name+"吗？",{icon:3,shift:2},function(index){
        		  $.ajax({
        			  type: "post",
        			  url: "${APP_PATH}/advertisement/doDelete.do",
        			  data: {
        				  "id": id
        			  },
        			  success: function(result){
        				  if(result.success){
        					  queryPage(0);
        				  }else{
        					  layer.msg(result.message, {time:1000, icon:5, shift:6});
        				  }
        			  }
        		  });
        		  layer.close(index);
        	  },function(index){
        		  layer.close(index);
        	  });
          }
          $("#allCheckbox").click(function(){
        	  var checked = this.checked;
        	  $("tbody tr td input:checkbox").prop("checked", checked);
          });
          $("#deleteBatchBtn").click(function(){
        	  var checked = $("tbody tr td input:checked");
        	  var json = null;
        	  $.each(checked,function(i,n){
        		  if(i!=0){
        			  json += "&";
        		  }
        		  json += "id="+checked[i].id
        	  });
        	  layer.confirm("是否确认删除这些数据？",{icon:3,shift:2},function(index){
        		  $.ajax({
        			  type: "post",
        			  url: "${APP_PATH}/advertisement/doDeleteBatch.do",
        			  data: json,
        			  success: function(result){
        				  if(result.success){
        				       queryPage(0);
        			 	  }else{
        					   layer.msg(result.message, {time:1000,icon:5,shift:6});
        			  	  }
        			  }
        		  });
        		  layer.close(index);
        	  },function(index){
        		  layer.close(index);
        	  })
          });
        </script>
  </body>
</html>