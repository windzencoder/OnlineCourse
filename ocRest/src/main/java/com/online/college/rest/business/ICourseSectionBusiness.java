package com.online.college.rest.business;

import java.util.Map;

import com.online.college.rest.dto.CourseSectionDto;

/**
 * 课程章节业务接口
 * @author Miller
 *
 */
public interface ICourseSectionBusiness {

	//获取某一课程的所有章节
	Map<Long, CourseSectionDto> getAllCourseSections(Long courseId);
	
}
