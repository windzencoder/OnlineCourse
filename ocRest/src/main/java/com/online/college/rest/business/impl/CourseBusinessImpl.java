package com.online.college.rest.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.service.ICourseService;
import com.online.college.rest.business.ICourseBusiness;

/**
 * 免费课程&实战课程业务实现类
 * @author Miller
 *
 */
@Component
public class CourseBusinessImpl implements ICourseBusiness {

	@Autowired
	private ICourseService courseService;
	
	@Override
	public Map<String, List<Course>> getCourses() {
		
		Map<String, List<Course>> returnMap = new HashMap<String, List<Course>>();
		
		Course queryEntity = new Course();
		queryEntity.setFree(1);
		
		List<Course> freeCourses = courseService.getFiveCourses(queryEntity);
		for (Course item : freeCourses) {
			if (StringUtils.isNotEmpty(item.getPicture())) {//图片不为空
				item.setPicture(QiniuStorage.getUrl(item.getPicture()));//七牛图片
			}
		}
		
		//免费课程
		returnMap.put("freeCourses", courseService.getFiveCourses(queryEntity));
		
		
		//实战课程
		queryEntity.setFree(0);
		returnMap.put("actionCourses", courseService.getFiveCourses(queryEntity));
		
		return returnMap;
	}

}
