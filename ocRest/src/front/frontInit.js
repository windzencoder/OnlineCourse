$(function() {
	/* 加载header */
	var headerHTML = template("header", {
		name : '哈哈'
	});//header是动态获取的
	$('#headerDIV').html(headerHTML);

	/*轮播*/
	var classifyHTML = template("classify");
	$('#classifyDIV').html(classifyHTML);
	
});