package com.online.college.portal.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.consts.service.IConstsClassifyService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;
import com.online.college.core.course.service.ICourseService;
import com.online.college.portal.business.IPortalBusiness;
import com.online.college.portal.vo.ConstsClassifyVO;

/**
 * 首页业务层
 */
@Service
public class PortalBusinessImpl implements IPortalBusiness {
	
	@Autowired
	private IConstsClassifyService constsClassifyService;
	
	@Autowired
	private ICourseService courseService;

	/**
	 * 获取所有课程分类，包括一级分类&二级分类
	 */
	public List<ConstsClassifyVO> queryAllClassify(){
		List<ConstsClassifyVO> resultList = new ArrayList<ConstsClassifyVO>();
		for(ConstsClassifyVO vo : this.queryAllClassifyMap().values()){
			resultList.add(vo);
		}
		return resultList;
	}
	
	/**
	 * 获取所有分类
	 * @return map
	 */
	public Map<String,ConstsClassifyVO> queryAllClassifyMap(){
		Map<String,ConstsClassifyVO> resultMap = new LinkedHashMap<String,ConstsClassifyVO>();
		Iterator<ConstsClassify> it = constsClassifyService.queryAll().iterator();
		while(it.hasNext()){
			ConstsClassify c = it.next();
			if("0".equals(c.getParentCode())){//一级分类
				ConstsClassifyVO vo = new ConstsClassifyVO();
				BeanUtils.copyProperties(c, vo);//bean的复制
				resultMap.put(vo.getCode(), vo);
			}else{//二级分类
				if(null != resultMap.get(c.getParentCode())){//map中存在一级分类
					resultMap.get(c.getParentCode()).getSubClassifyList().add(c);//添加到子分类集合中
				}
			}
		}
		return resultMap;
	}
	
	/**
	 * 为分类设置课程推荐
	 */
	public void prepareRecomdCourses(List<ConstsClassifyVO> classifyVoList){
		if(CollectionUtils.isNotEmpty(classifyVoList)){
			for(ConstsClassifyVO item : classifyVoList){
				CourseQueryDto queryEntity = new CourseQueryDto();
				queryEntity.setCount(5);//查5个
				queryEntity.descSortField("weight");//按weight排序
				queryEntity.setClassify(item.getCode());//分类code
				
				List<Course> tmpList = this.courseService.queryList(queryEntity);
				if(CollectionUtils.isNotEmpty(tmpList)){
					item.setRecomdCourseList(tmpList);//查到的5条放到推荐列表里
				}
			}
		}
	}
	
}
