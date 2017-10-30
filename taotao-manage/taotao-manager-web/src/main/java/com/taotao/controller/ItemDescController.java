package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemDescService;

@Controller
@RequestMapping("/item/desc")
public class ItemDescController {

	@Autowired
	private ItemDescService itemDescService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	private TbItemDesc getItemById(@PathVariable Long itemId) {
		TbItemDesc itemDesc = itemDescService.getItemDescById(itemId);
		return itemDesc;
	}
}
