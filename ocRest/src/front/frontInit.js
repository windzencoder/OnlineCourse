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
			//alert(JSON.stringify(data));
			/*轮播*/
			var allClassify = {'allClassify': data.data}
			var classifyHTML = template("classify", allClassify);
			$('#classifyDIV').html(classifyHTML);
			
			
			//课程分类展示
			$(".category").popover({
	            trigger:'manual',
	            placement : 'right',
	            html: 'true',
	            content : '',
	            animation: false
	        }).on("mouseenter", function () {
	            var cid = $(this).attr('c-id');
	            $('#' + cid).show();
	            $('#' + cid).hover(function(){
	            		$('#' + cid).show();
	            },function(){
	            		$('#' + cid).hide();
				});
	        }).on("mouseleave", function () {
	            var cid = $(this).attr('c-id');
	            $('#' + cid).hide();
	        });
			
		},
		"error":function(d, msg){
			alert(msg);
		}
	});
	
	
	//加载课程
	//ajax请求 rest接口 获取实战课程 免费好课
	var courseUrl = "http://localhost:8080/ocRest/course/getCourses.html?callback=?";
	$.jsonp({
		"url":courseUrl,
		"success":function(data){
			//alert(JSON.stringify(data));
			/*实战课程 免费好课*/
			
			var coursesHTML = template("courses", data);
			$('#coursesDIV').html(coursesHTML);
		},
		"error":function(d, msg){
			alert(msg);
		}
	});
	

	
	

	
});