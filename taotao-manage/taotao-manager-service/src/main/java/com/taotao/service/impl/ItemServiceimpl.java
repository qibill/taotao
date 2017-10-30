package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.commom.untils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
@Service
public class ItemServiceimpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TbItem getItemById(Long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);		
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParams) {
		// 生成商品id
		Long itemId = IDUtils.genItemId();
		// 补全TbItem属性
		item.setId(itemId);
		// '商品状态，1-正常，2-下架，3-删除'
		item.setStatus((byte) 1);
		// 创建时间和更新时间
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		// 插入商品表
		itemMapper.insert(item);
		// 商品描述
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		// 插入商品描述数据
		itemDescMapper.insert(itemDesc);
		
		//添加商品规格参数
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParams);
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		//插入数据
		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateItem(TbItem item, String desc, String itemParams) {
		// 更新时间
		Date date = new Date();
		item.setUpdated(date);
		// 插入商品表
		itemMapper.updateByPrimaryKeySelective(item);
		// 商品描述
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(date);
		// 插入商品描述数据
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		
		//添加商品规格参数
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(item.getId());
		itemParamItem.setParamData(itemParams);
		itemParamItem.setUpdated(date);
		//插入数据
		itemParamItemMapper.updateByPrimaryKeySelective(itemParamItem);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateItem(TbItem item) {
		// 更新时间
		Date date = new Date();
		item.setUpdated(date);
		// 插入商品表
		itemMapper.updateByPrimaryKeySelective(item);
		return TaotaoResult.ok();
	}

}
