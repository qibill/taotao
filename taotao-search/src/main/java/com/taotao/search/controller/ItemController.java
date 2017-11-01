package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.commom.untils.ExceptionUtil;
import com.taotao.search.service.ItemService;

/**
 * @ClassName: ItemController
 * @Description: 向索引库导入数据
 * @author qiminghui
 * @date 2017年11月1日 下午3:31:12
 */ 
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/importall")
	@ResponseBody
	public TaotaoResult importAll() {
		try {
			TaotaoResult result = itemService.importItems();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
