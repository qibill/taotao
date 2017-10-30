package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	private TbItem getItemById(@PathVariable Long itemId) {
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item, String desc, String itemParams) {
		TaotaoResult result = itemService.createItem(item, desc, itemParams);
		return result;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateItem(TbItem item, String desc, String itemParams) {
		TaotaoResult result = itemService.updateItem(item, desc, itemParams);
		return result;
	}
	
	@RequestMapping(value="/instock", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult instockItem(String ids) {
		String[] strings = ids.split(",");
		for (String string : strings) {
			long id = Long.parseLong(string);
			TbItem item =new TbItem();
			item.setId(id);
			//商品状态，1-正常，2-下架，3-删除
			item.setStatus((byte)2);
			TaotaoResult result = itemService.updateItem(item);
			if (!result.isOK()) {
				return result;
			}
		}
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value="/reshelf", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult reshelfItem(String ids) {
		String[] strings = ids.split(",");
		for (String string : strings) {
			long id = Long.parseLong(string);
			TbItem item =new TbItem();
			item.setId(id);
			//商品状态，1-正常，2-下架，3-删除
			item.setStatus((byte)1);
			TaotaoResult result = itemService.updateItem(item);
			if (!result.isOK()) {
				return result;
			}
		}
		return TaotaoResult.ok();
	}

}
