package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemDescService;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	private TbItemDescMapper itemdescmapper;
	
	@Override
	public TbItemDesc getItemDescById(long id) {
		TbItemDesc itemDesc = itemdescmapper.selectByPrimaryKey(id);
		return itemDesc;
	}

}
