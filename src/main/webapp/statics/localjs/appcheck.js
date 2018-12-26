$("#back").on("click",function(){
	window.location.href = "list";
});
var downloadLink = $("#downloadLink").val();
var apkFileName=$("#apkFileName").val();
$("#apkFile").append("<p>"+apkFileName+
    "&nbsp;&nbsp;<a href=\""+downloadLink+"?m="+Math.random()+"\" >下载</a> &nbsp;&nbsp;" +
    "</p>");