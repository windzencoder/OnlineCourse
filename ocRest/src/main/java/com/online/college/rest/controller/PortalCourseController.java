package com.online.college.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.college.common.web.JsonView;
import com.online.college.rest.business.ICourseBusiness;

/**
 * 实战课程 推荐课程 controller
 * @author Miller
 *
 */

@Controller
@RequestMapping("/course")
public class PortalCourseController {

	@Autowired
	private ICourseBusiness courseBusiness;
	
	@RequestMapping("/getCourses")
	@ResponseBody
	public String getCourses(HttpServletRequest request){
		
		try {
			
			String result = JsonView.render(courseBusiness.getCourses());
			//跨域访问，客户端js是jsonp，数据返回的方式
			return request.getParameter("callback") + "(" + result + ")";
			
			//return JsonView.render(courseBusiness.getCourses());
			
		} catch (Exception e) {
			return JsonView.render(17000);
		}
		
		
	}
	
}
