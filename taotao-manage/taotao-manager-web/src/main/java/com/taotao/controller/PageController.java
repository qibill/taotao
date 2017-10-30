package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	/**
	 * <P> @Title: showndex
	 * <P> @Description: 跳转首页
	 * @return String
	 */ 
	@RequestMapping("/")
	private String showIndex() {
		return "index";
	}
	
	/**
	 * <P> @Title: showPage
	 * <P> @Description: 跳转页面
	 * @param page
	 * @return String
	 */ 
	@RequestMapping(value={"/{page}", "/page/{page}"})
	private String showPage(@PathVariable String page) {
		return page;
	}

}
