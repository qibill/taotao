package com.taotao.service;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	/**
	 * <P> @Title: getItemById
	 * <P> @Description: 根据id查找商品
	 * @param itemId
	 * @return TbItem
	 */ 
	TbItem getItemById(Long itemId);
	
	/**
	 * <P> @Title: getItemList
	 * <P> @Description: 根据 页数 page 和 行数 rows 列出商品信息
	 * @param page 页数
	 * @param rows 行数
	 * @return EasyUIDataGridResult
	 */ 
	EasyUIDataGridResult getItemList(int page, int rows);
	
	/**
	 * <P> @Title: createItem
	 * <P> @Description: 添加新的商品
	 * @param item
	 * @param desc
	 * @return TaotaoResult
	 */ 
	TaotaoResult createItem(TbItem item, String desc, String itemParams);
	
	/**
	 * <P> @Title: updateItem
	 * <P> @Description: 修改商品
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @return TaotaoResult
	 */ 
	TaotaoResult updateItem(TbItem item, String desc, String itemParams);
	
	/**
	 * <P> @Title: updateItem
	 * <P> @Description: 修改商品
	 * @param item
	 * @return TaotaoResult
	 */ 
	TaotaoResult updateItem(TbItem item);
}
