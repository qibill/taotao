package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PageController
 * @Description: 页面跳转
 * @author qiminghui
 * @date 2017年11月6日 下午4:52:42
 */ 
@Controller
public class PageController {

	/**
	 * 展示登录页面
	 */
	@RequestMapping("/page/login")
	public String showLogin() {
		return "login";
	}
	/**
	 * 展示注册页面
	 */
	@RequestMapping("/page/register")
	public String showRegister() {
		return "register";
	}

}
