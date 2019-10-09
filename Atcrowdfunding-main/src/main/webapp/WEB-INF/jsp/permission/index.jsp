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
	<link rel="stylesheet" href="${APP_PATH }/ztree/zTreeStyle.css">
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
           <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 许可维护</a></div>
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
              <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表 <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
                  <ul id="treeDemo" class="ztree"></ul>
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
	<script src="${APP_PATH }/ztree/jquery.ztree.all-3.5.min.js"></script>
	<script src="${APP_PATH }/script/common.js"></script>
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
				showMenu();
				
				/*var setting = {	};

				 var zNodes =[
					{ name:"父节点1 - 展开", open:true,
						children: [
							{ name:"父节点11 - 折叠",
								children: [
									{ name:"叶子节点111"},
									{ name:"叶子节点112"},
									{ name:"叶子节点113"},
									{ name:"叶子节点114"}
								]},
							{ name:"父节点12 - 折叠",
								children: [
									{ name:"叶子节点121"},
									{ name:"叶子节点122"},
									{ name:"叶子节点123"},
									{ name:"叶子节点124"}
								]},
							{ name:"父节点13 - 没有子节点", isParent:true}
						]},
					{ name:"父节点2 - 折叠",
						children: [
							{ name:"父节点21 - 展开", open:true,
								children: [
									{ name:"叶子节点211"},
									{ name:"叶子节点212"},
									{ name:"叶子节点213"},
									{ name:"叶子节点214"}
								]},
							{ name:"父节点22 - 折叠",
								children: [
									{ name:"叶子节点221"},
									{ name:"叶子节点222"},
									{ name:"叶子节点223"},
									{ name:"叶子节点224"}
								]},
							{ name:"父节点23 - 折叠",
								children: [
									{ name:"叶子节点231"},
									{ name:"叶子节点232"},
									{ name:"叶子节点233"},
									{ name:"叶子节点234"}
								]}
						]},
					{ name:"父节点3 - 没有子节点", isParent:true}

				]; */
	            loadData();
            });
            function loadData(){
            	var setting = {
    					view: {
    						selectedMulti: false,
    						addDiyDom: function(treeId, treeNode){//自定义图标
    							var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
    							if ( treeNode.icon ) {
    								icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
    							}
    						},
    						addHoverDom: function(treeId, treeNode){  //自定义按钮组，效果是鼠标悬停时可以在菜单后面出现增删改的按钮
    							var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
    							aObj.attr("href", "javascript:;");
    							if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
    							var s = '<span id="btnGroup'+treeNode.tId+'">';
    							if ( treeNode.level == 0 ) {//根节点
    								s += '<a class="btn btn-info dropdown-toggle btn-xs" onclick="window.location.href=\'${APP_PATH}/permission/add.htm?id='+treeNode.id+'\'" style="margin-left:10px;padding-top:0px;" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
    							} else if ( treeNode.level == 1 ) {//分支节点
    								s += '<a class="btn btn-info dropdown-toggle btn-xs" onclick="window.location.href=\'${APP_PATH}/permission/edit.htm?id='+treeNode.id+'\'" style="margin-left:10px;padding-top:0px;"  href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
    								if (treeNode.children.length == 0) {//叶子节点
    									s += '<a class="btn btn-info dropdown-toggle btn-xs" onclick="deleteNode('+treeNode.id+')" style="margin-left:10px;padding-top:0px;" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
    								}
    								s += '<a class="btn btn-info dropdown-toggle btn-xs" onclick="window.location.href=\'${APP_PATH}/permission/add.htm?id='+treeNode.id+'\'" style="margin-left:10px;padding-top:0px;" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
    							} else if ( treeNode.level == 2 ) {
    								s += '<a class="btn btn-info dropdown-toggle btn-xs" onclick="window.location.href=\'${APP_PATH}/permission/edit.htm?id='+treeNode.id+'\'" style="margin-left:10px;padding-top:0px;"  href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
    								s += '<a class="btn btn-info dropdown-toggle btn-xs" onclick="deleteNode('+treeNode.id+')" style="margin-left:10px;padding-top:0px;" href="#">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
    							}
    			
    							s += '</span>';
    							aObj.after(s);
    						},
    						removeHoverDom: function(treeId, treeNode){
    							$("#btnGroup"+treeNode.tId).remove();
    						}
    					},
    					async: {
    						enable: true,
    						url:"tree.txt",
    						autoParam:["id", "name=n", "level=lv"]
    					},
    					callback: {
    						onClick : function(event, treeId, json) {

    						}
    					}
    				};
    			var zNodes = [];
    			
    			$.ajax({
    				type : "post",
    				url : "${APP_PATH}/permission/loadData.do",
    				beforeSend : function(){
    					layerIndex = layer.msg("正在加载许可树，请稍等...", {icon:1, shift:4});
    					return true;
    				},
    				success : function(result){
    					layer.close(layerIndex);
    					if(result.success){
    						zNodes = result.datas;
    						$.fn.zTree.init($("#treeDemo"), setting, zNodes);
    					}else{
    						layer.msg(result.message, {time:1000, icon:5, shift:4});
    					}
    				},
    				error : function(){
    					layer.msg("加载许可树失败！", {time:1000, icon:5, shift:4});
    				}
    			});
            }
			
			function deleteNode(id){
				$.ajax({
					type : "post",
					url : "${APP_PATH}/permission/deleteNode.do",
					data : {
						"id" : id
					},
					beforeSend : function(){
						layerIndex = layer.msg("正在删除许可树节点，请稍等...", {icon:1, shift:4});
						return true;
					},
					success : function(result){
						layer.close(layerIndex);
						layer.msg("删除许可树节点成功", {time:1000, icon: 6, shift:4 });
	        			if(result.success){
	         				loadData();
						}else{
							layer.msg(result.message, {time:1000, icon:5, shift:4});
						}
					},
					error : function(){
						layer.msg("删除许可树节点失败！", {time:1000, icon:5, shift:4});
					}
				});
			};
			
        </script>
  </body>
</html>