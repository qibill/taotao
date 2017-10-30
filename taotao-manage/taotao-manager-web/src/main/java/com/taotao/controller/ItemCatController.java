package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EasyUITreeNode;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	//返回json数据
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(
			//EasyUI的请求参数"id" 第一次请求没有参数，需默认"0"
			@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<EasyUITreeNode> list = itemCatService.getItemCatList(parentId);
		return list;
	}

	@RequestMapping("{id}")
	@ResponseBody
	private TbItemCat getItemCatById(@PathVariable Long id) {
		 TbItemCat itemCat = itemCatService.getItemCatById(id);
		return itemCat;
	}
	
}
