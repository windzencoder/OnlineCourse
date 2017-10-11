package com.online.college.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.web.JsonView;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;

/**
 * 用户登录&注册
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private IAuthUserService authUserService;
	
	@RequestMapping("/register")
	public ModelAndView register(){
		//auth/register表示跳转的页面
		return new ModelAndView("auth/register");
	}
	
	/**
	 * 实现注册
	 */
	@RequestMapping(value="/doRegister")
	@ResponseBody
	public String doRegister(AuthUser authUser){
		AuthUser tmpUser = authUserService.getByUsername(authUser.getUsername());
		if (tmpUser != null) {//用户名已存在
			return JsonView.render(1);//将对象转为json字符串
		}else{//用户名不存在 就注册
			authUserService.createSelectivity(authUser);
			return JsonView.render(0);
		}
	}
	
	
}
