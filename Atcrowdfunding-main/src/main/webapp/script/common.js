function showMenu(){
	var href = window.location.href;
	var contextPath = window.location.host;
	var location = href.indexOf(contextPath);
	var path = href.substring(location+contextPath.length);
	var checked = $(".list-group-item a[href*='"+path+"']");
	checked.parent().parent().parent().removeClass("tree-closed");
	checked.parent().parent().show();
	checked.css("color", "red");
 }