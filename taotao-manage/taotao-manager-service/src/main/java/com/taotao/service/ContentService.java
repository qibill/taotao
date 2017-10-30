package com.taotao.service;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	EasyUIDataGridResult getContentList(int page, int rows);
	
	TaotaoResult createContent(TbContent content);
	
	TaotaoResult deleteContent(Long id);
	
}
