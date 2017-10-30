package com.taotao.service;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;

public interface ItemParamService {

	/**
	 * <P> @Title: getItemParamList
	 * <P> @Description: 根据 页数 page 和 行数 rows 列出参数模板信息
	 * @param page
	 * @param rows
	 * @return EasyUIDataGridResult
	 */ 
	EasyUIDataGridResult getItemParamList(int page, int rows);
	
	/**
	 * <P> @Title: getItemParamByCid
	 * <P> @Description: 查询该类目是否存在规格模板
	 * @param cid
	 * @return TaotaoResult
	 */ 
	TaotaoResult getItemParamByCid(Long cid);
	
	/**
	 * <P> @Title: insertItemParam
	 * <P> @Description: 添加新的规格模板
	 * @param cid
	 * @param paramData
	 * @return TaotaoResult
	 */ 
	TaotaoResult insertItemParam(Long cid, String paramData);
	
	/**
	 * <P> @Title: deleteItemParam
	 * <P> @Description: 删除规格模板
	 * @param cid
	 * @return TaotaoResult
	 */ 
	TaotaoResult deleteItemParam(Long ids);
}
