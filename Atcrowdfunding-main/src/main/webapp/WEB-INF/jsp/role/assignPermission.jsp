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
              <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限分配列表<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
                  <button id="assignPermissionBtn" class="btn btn-success">分配许可</button>
                  <br><br>
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
				
				loadData();
            });
            
            function loadData(){
            	var setting = {
                       check : {
                           enable : true 
                       },
   					view: {
   						selectedMulti: false,
   						addDiyDom: function(treeId, treeNode){
   							var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
   							if ( treeNode.icon ) {
   								icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
   							}
   						},
   					},
   					async: {
   						enable: true,
   						url:"${APP_PATH}/role/loadData.do?roleid=${param.id}",
   						autoParam:["id", "name", "level"]
   					},
   					callback: {
   						onClick : function(event, treeId, json) {

   						}
   					}
   				};
   				$.fn.zTree.init($("#treeDemo"), setting); //异步访问数据
            }
            
            $("#assignPermissionBtn").click(function(){
            	var str = {
            		"roleid" : "${param.id}"
            	};
            	//var checked = $("#treeDemo span.checkbox_true_full"); 	
            	var tree = $.fn.zTree.getZTreeObj("treeDemo");
            	var checked = tree.getCheckedNodes();
            	$.each(checked, function(i,n){
            		//var id = n.id.split("_")[1];
            		str["ids["+i+"]"] = n.id; 
            	});
            	$.ajax({
            		type : "post",
            		url : "${APP_PATH}/role/doAssignPermission.do",
            		data : str,
            		beforeSend : function(){
        				layerIndex = layer.msg("正在分配，请稍等...", {icon:8});
        				return true;
        			},
        			success : function(result){
        				layer.close(layerIndex);
        				if(result.success){
        					layer.msg("分配成功(-_-)", {time:1000, icon:6});
        					window.location.href = "${APP_PATH}/role/index.htm";
        				}else{
        					layer.msg(result.message, {time:1000, icon:5, shift: 5});
        				}
        			},
        			error : function(){
        				layer.msg("分配失败", {time:1000, icon:5, shift: 5});
        			}
            	});
            });
        </script>
  </body>
</html>