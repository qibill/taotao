package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.commom.pojo.PictureResult;
import com.taotao.commom.untils.JsonUtils;
import com.taotao.service.PictureService;

@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		PictureResult result = pictureService.uploadPic(uploadFile);
		//解决浏览器的兼容问题
		//需要把java对象手动转换成json对象。
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}

