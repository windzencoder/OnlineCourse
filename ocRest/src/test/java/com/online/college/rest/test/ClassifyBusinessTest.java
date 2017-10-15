package com.online.college.rest.test;

import java.io.IOException;
import java.util.Map;

import com.online.college.common.util.JsonUtil;
import com.online.college.common.web.SpringBeanFactory;
import com.online.college.rest.business.IClassifyBusiness;
import com.online.college.rest.dto.ClassifyDto;

import junit.framework.TestCase;

public class ClassifyBusinessTest extends TestCase {

	public void testGetClassify() {
		
		IClassifyBusiness bis = (IClassifyBusiness)SpringBeanFactory.getBean("classfiyBusinessImpl");
		Map<String, ClassifyDto> map = bis.getAllClassify();
		
		System.out.println("=======");
		try {
			System.out.println(JsonUtil.toJson(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
