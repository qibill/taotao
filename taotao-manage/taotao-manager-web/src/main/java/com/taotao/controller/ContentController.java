package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.commom.untils.HttpClientUtil;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(Integer page, Integer rows) {
		EasyUIDataGridResult result = contentService.getContentList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createContent(TbContent content) {
		TaotaoResult result = contentService.createContent(content);
		//调用taotao-rest发布的服务，同步缓存。
		HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL +content.getCategoryId());
		return result;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteContent(String ids) {
		String[] strings = ids.split(",");
		TaotaoResult result = TaotaoResult.ok();
		for (String string : strings) {
			long id = Long.parseLong(string);
			TaotaoResult taotaoResult = contentService.deleteContent(id);
			if (taotaoResult.isOK()) {
				TbContent content = (TbContent) taotaoResult.getData();
				HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
			} else {
				result.setStatus(500);
				result.setMsg("删除失败");
			} 
		}
		return result;
	}
}
