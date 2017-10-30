package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.pojo.TbItemParamResult;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public EasyUIDataGridResult getItemParamList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		TbItemParamExample example = new TbItemParamExample();
		//执行查询
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		List<TbItemParamResult> listResult = new ArrayList<TbItemParamResult>();
		for (TbItemParam tbItemParam : list) {
			TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(tbItemParam.getItemCatId());
			TbItemParamResult itemParamResult = new TbItemParamResult();
			itemParamResult.setId(tbItemParam.getId());
			itemParamResult.setItemCatId(tbItemParam.getItemCatId());
			itemParamResult.setParamData(tbItemParam.getParamData());
			itemParamResult.setCreated(tbItemParam.getCreated());
			itemParamResult.setUpdated(tbItemParam.getUpdated());
			itemParamResult.setItemCatName(itemCat.getName());
			listResult.add(itemParamResult);
		}
		//取分页信息
		PageInfo<TbItemParamResult> pageInfo = new PageInfo<TbItemParamResult>(listResult);
		//返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(listResult);
		return result;
	}

	@Override
	public TaotaoResult getItemParamByCid(Long cid) {
		//根据cid查询规格参数模板
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		//执行查询
		List<TbItemParam>list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if (list != null&&list.size() > 0) {
			TbItemParam itemParam = list.get(0);
			return TaotaoResult.ok(itemParam);
		}
		return TaotaoResult.ok();

	}

	@Override
	public TaotaoResult insertItemParam(Long cid, String paramData) {
		//创建一个pojo
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入记录
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteItemParam(Long ids) {
		itemParamMapper.deleteByPrimaryKey(ids);
		return TaotaoResult.ok();
	}
}
