$(function() {
	/* 加载header */
	var headerHTML = template("header", {
		name : '哈哈'
	});//header是动态获取的
	$('#headerDIV').html(headerHTML);

	//ajax请求 rest接口
	var url = "http://localhost:8080/ocRest/getClassifyJson.html?callback=?";
	$.jsonp({
		"url":url,
		"success":function(data){
			alert(JSON.stringify(data))
		},
		"error":function(d, msg){
			alert(msg);
		}
	});
	
	/*轮播*/
	var classifyHTML = template("classify");
	$('#classifyDIV').html(classifyHTML);
	
});