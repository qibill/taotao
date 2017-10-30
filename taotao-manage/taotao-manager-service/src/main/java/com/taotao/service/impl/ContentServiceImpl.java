package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentmapper;
	
	@Override
	public EasyUIDataGridResult getContentList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		//执行查询
		List<TbContent> list = contentmapper.selectByExampleWithBLOBs(example);
		//取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		//返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);		
		return result;
	}

	@Override
	public TaotaoResult createContent(TbContent content) {
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		contentmapper.insert(content);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteContent(Long id) {
		TbContent content = contentmapper.selectByPrimaryKey(id);		
		contentmapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok(content);
	}
}
