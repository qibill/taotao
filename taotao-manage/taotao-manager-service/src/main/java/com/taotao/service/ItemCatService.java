package com.taotao.service;

import java.util.List;

import com.taotao.commom.pojo.EasyUITreeNode;
import com.taotao.pojo.TbItemCat;

public interface ItemCatService {

	/**
	 * <P> @Title: getItemCatList
	 * <P> @Description: 获取类目
	 * @param parentId
	 * @return List<EasyUITreeNode>
	 */ 
	List<EasyUITreeNode> getItemCatList(long parentId);
	
	TbItemCat getItemCatById(long id);
}
