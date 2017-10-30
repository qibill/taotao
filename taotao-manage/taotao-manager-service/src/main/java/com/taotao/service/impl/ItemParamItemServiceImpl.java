package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemParamItemService;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TaotaoResult getItemParamItem(Long itemId) {
		// 根据商品id查询规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		com.taotao.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		//执行查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list.isEmpty()) {			
			return null;
		}
		TbItemParamItem itemParamItem = list.get(0);
		return TaotaoResult.ok(itemParamItem);
	}

}
