package com.online.college.rest.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.online.college.common.util.JsonUtil;
import com.online.college.common.web.SpringBeanFactory;
import com.online.college.core.course.domain.Course;
import com.online.college.rest.business.IClassifyBusiness;
import com.online.college.rest.business.ICourseBusiness;
import com.online.college.rest.business.ICourseSectionBusiness;
import com.online.college.rest.dto.ClassifyDto;
import com.online.college.rest.dto.CourseSectionDto;

import junit.framework.TestCase;

/**
 * 课程章节测试用例
 * @author Miller
 *
 */
public class CourseSectionBusinessTest extends TestCase {

	public void testGetCourses() {
		
		ICourseSectionBusiness bis = (ICourseSectionBusiness)SpringBeanFactory.getBean("courseSectionBusinessImpl");
		Map<Long, CourseSectionDto>  map = bis.getAllCourseSections(1L);
		
		System.out.println("=======");
		try {
			System.out.println(JsonUtil.toJson(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
