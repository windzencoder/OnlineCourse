package com.online.college.opt.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.JsonView;
import com.online.college.core.consts.domain.ConstsCollege;
import com.online.college.core.consts.service.IConstsCollegeService;

/**
 * 网校管理
 */
@Controller
@RequestMapping("/college")
public class CollegeController{

	@Autowired
	private IConstsCollegeService entityService;
	
	/**
	 * 分页 查询网校列表
	 * @param queryEntity
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/queryPageList")
	public  ModelAndView queryPage(ConstsCollege queryEntity , TailPage<ConstsCollege> page){
		ModelAndView mv = new ModelAndView("cms/college/collegePageList");
		mv.addObject("curNav", "college");
		
		if(StringUtils.isNotEmpty(queryEntity.getName())){
			queryEntity.setName(queryEntity.getName().trim());
		}else{
			queryEntity.setName(null);
		}
		
		page.setPageSize(2);//设置每页2个
		
		page = entityService.queryPage(queryEntity,page);
		
		mv.addObject("page",page);
		mv.addObject("queryEntity",queryEntity);
		return mv;
	}
	
	/**
	 * 根据id获取网校
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getById")
	@ResponseBody
	public String getById(Long id){
		return JsonView.render(entityService.getById(id));
	}
	
	/**
	 * id为null就创建
	 * 不为null就更新
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/doMerge")
	@ResponseBody
	public String doMerge(ConstsCollege entity){
		if(entity.getId() == null){
			ConstsCollege tmpEntity = entityService.getByCode(entity.getCode());
			if(tmpEntity != null){
				return JsonView.render(1, "此编码已存在");
			}
			entityService.createSelectivity(entity);
		}else{
			ConstsCollege tmpEntity = entityService.getByCode(entity.getCode());
			if(tmpEntity != null && !tmpEntity.getId().equals(entity.getId())){
				return JsonView.render(1, "此编码已存在");
			}
			entityService.updateSelectivity(entity);
		}
		return new JsonView().toString();
	}

	/**
	 * 逻辑删除
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/deleteLogic")
	@ResponseBody
	public String deleteLogic(ConstsCollege entity){
		entityService.deleteLogic(entity);
		return new JsonView().toString();
	}
	
}

