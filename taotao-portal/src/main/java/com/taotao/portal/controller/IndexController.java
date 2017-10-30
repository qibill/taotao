package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.ContentService;

@Controller
public class IndexController {

	@Autowired
	private ContentService contentService;
	
	/**
	 * <P> @Title: showIndex
	 * <P> @Description: 跳转主页
	 * @return String
	 */ 
	@RequestMapping("/index")
	public String showIndex(Model model) {
		//取打广告位内容
		String json = contentService.getAd1List();
		//传递给页面
		model.addAttribute("ad1", json);
		return "index";
	}

}
