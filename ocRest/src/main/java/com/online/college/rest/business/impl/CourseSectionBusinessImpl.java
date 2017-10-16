package com.online.college.rest.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.online.college.core.course.domain.CourseSection;
import com.online.college.core.course.service.ICourseSectionService;
import com.online.college.rest.business.ICourseSectionBusiness;
import com.online.college.rest.dto.CourseSectionDto;

/**
 * 课程章节业务接口实现类
 * @author Miller
 *
 */

@Component
public class CourseSectionBusinessImpl implements ICourseSectionBusiness {

	@Autowired
	private ICourseSectionService courseSectionService;
	
	@Override
	public Map<Long, CourseSectionDto> getAllCourseSections(Long courseId) {
		
		CourseSection queryEntity = new CourseSection();
		queryEntity.setCourseId(courseId);
		List<CourseSection> list =  courseSectionService.queryAll(queryEntity);
		
		Map<Long, CourseSectionDto> returnMap = new HashMap<Long, CourseSectionDto>();
		for (CourseSection item : list) {
			
			if (Long.valueOf(0).equals(item.getParentId())) {//章
				CourseSectionDto dto = new CourseSectionDto();
				BeanUtils.copyProperties(item, dto);
				returnMap.put(dto.getId(), dto);//把章的信息放到map中
			}else{//节
				if (null != returnMap.get(item.getParentId())) {
					returnMap.get(item.getParentId()).getSectionList().add(item);
				}
			}
			
		}
		
		
		return returnMap;
	}

}
