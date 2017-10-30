package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult getItemCatByCid(@PathVariable Long cid) {
		TaotaoResult result = itemParamService.getItemParamByCid(cid);
		return result;
	}

	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
		TaotaoResult result = itemParamService.insertItemParam(cid, paramData);
		return result;
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteItemParam(String ids) {
		String[] strings = ids.split(",");
		TaotaoResult result = TaotaoResult.ok();
		for (String string : strings) {
			long id = Long.parseLong(string);
			TaotaoResult taotaoResult = itemParamService.deleteItemParam(id);
			if (!taotaoResult.isOK()) {
				taotaoResult.setMsg("删除失败");
				return taotaoResult;
			}
		}
		return result;		
	}
	
}
