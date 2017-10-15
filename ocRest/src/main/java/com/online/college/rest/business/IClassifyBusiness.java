package com.online.college.rest.business;

import java.util.Map;

import com.online.college.rest.dto.ClassifyDto;

public interface IClassifyBusiness {

	//获取课程分类
	Map<String, ClassifyDto> getAllClassify();
	
}
