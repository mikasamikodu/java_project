var interval;

function startSecond() {
	interval = window.setInterval( "changeSecond()", 1000);
}

function changeSecond() {
	var second = document.getElementById("second");
	var svalue = second.innerHTML;
	svalue = svalue-1;
	if(svalue==0) {
		window.clearInterval( interval);
		location.href = "index.jsp";
		return;
	}
}

function getXMLHttpRequest() {
		var xmlHttp;
		if(window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}else {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlHttp;
}