package com.taotao.service;

import java.util.List;

import com.taotao.commom.pojo.EasyUITreeNode;
import com.taotao.commom.pojo.TaotaoResult;

public interface ContentCatgoryService {

	 /**
	 * <P> @Title: getContentCatList
	 * <P> @Description: 获取内容分类
	 * @param parentId
	 * @return List<EasyUITreeNode>
	 */ 
	List<EasyUITreeNode> getContentCatList(Long parentId);
	
	/**
	 * <P> @Title: insertCatgory
	 * <P> @Description: 添加内容分类
	 * @param parentId
	 * @param name
	 * @return TaotaoResult
	 */ 
	TaotaoResult insertCatgory(Long parentId, String name);
	
	/**
	 * <P> @Title: updateCatgory
	 * <P> @Description: 添加内容分类
	 * @param id
	 * @param name
	 */ 
	void updateCatgory(Long id, String name);
	
	/**
	 * <P> @Title: deleteCatgory
	 * <P> @Description: 删除内容分类
	 * @param id void
	 */ 
	void deleteCatgory(Long id);
}
