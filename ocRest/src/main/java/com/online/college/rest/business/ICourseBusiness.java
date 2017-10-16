package com.online.college.rest.business;

import java.util.List;
import java.util.Map;

import com.online.college.core.course.domain.Course;

/**
 * 课程业务接口
 * @author Miller
 *
 */
public interface ICourseBusiness {

	Map<String, List<Course>> getCourses();
	
}
