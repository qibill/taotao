package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemParamItemService;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item/param/item")
public class ItemParamItemController {
	
	@Autowired
	private ItemParamItemService itemParamItemService;
	@Autowired
	private ItemParamService itemParamService;
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/query/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParam(@PathVariable Long itemId) {
		TaotaoResult result = itemParamItemService.getItemParamItem(itemId);
		if (result == null) {
			TbItem item = itemService.getItemById(itemId);
			result = itemParamService.getItemParamByCid(item.getCid());
		}
		return result;
	}
}
