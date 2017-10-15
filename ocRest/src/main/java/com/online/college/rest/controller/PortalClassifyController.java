package com.online.college.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.college.common.web.JsonView;
import com.online.college.rest.business.IClassifyBusiness;
import com.online.college.rest.dto.ClassifyDto;

/**
 * 首页分类的controller
 * @author Miller
 *
 */

@Controller
public class PortalClassifyController {

	@Autowired
	private IClassifyBusiness classifyBusiness;
	
	/**
	 * 获取课程分类
	 * @return
	 */
	@RequestMapping("/getClassifyJson")
	@ResponseBody
	public String getClassifyJson(){
		try {
			
			Map<String, ClassifyDto> map = classifyBusiness.getAllClassify();
			
			List<ClassifyDto> list = new ArrayList<ClassifyDto>();
			for (String key : map.keySet()) {
				list.add(map.get(key));
			}
			
			return JsonView.render(list);
		} catch (Exception e) {
			//出错
			return JsonView.render(1700);//代表什么错误
		}
		
	}
	
}
