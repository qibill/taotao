package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.commom.untils.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.component.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_CONTENT_KEY}")
	private String REDIS_CONTENT_KEY;
	
	@Override
	public ItemCatResult getItemCatList() {
		//调用递归方法查询商品分类列表，参数类型为Long
		List catList = getItemCatList(0l);
		//返回结果
		ItemCatResult result = new ItemCatResult();
		result.setData(catList);
		return result;
	}

	private List getItemCatList(Long parentId) {
		//添加缓存
		//查询数据库之前先查询缓存，如果有直接返回
		try {
			//从redis中取缓存数据
			String json = jedisClient.hget(REDIS_CONTENT_KEY, "ItemCatList");
			if (!StringUtils.isBlank(json)) {
				//把json转换成List
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据parentId查询列表
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List resultList = new ArrayList<>();
		int index = 0;
		for (TbItemCat tbItemCat : list) {
			//判断第一级节点是否超标
			if (index >= 14) {
				break;
			}
			//如果是父节点
			if (tbItemCat.getIsParent()) {
				CatNode node = new CatNode();
				node.setUrl("/products/"+tbItemCat.getId()+".html");
				//如果当前节点为第一级节点
				if (tbItemCat.getParentId() == 0) {
					node.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
					//第一级节点不能超过14个元素，index为计数器。
					index++;
				} else {
					node.setName(tbItemCat.getName());
				}
				node.setItems(getItemCatList(tbItemCat.getId()));
				//把node添加到列表
				resultList.add(node);
			} else {
				//如果是叶子节点
				String item = "/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName();
				resultList.add(item);
			}
		}
		
		//返回结果之前，向缓存中添加数据
		try {
			//为了规范key可以使用hash
			//定义一个保存内容的key，hash中每个项就是cid
			//value是list，需要把list转换成json数据。
			jedisClient.hset(REDIS_CONTENT_KEY, "ItemCatList", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

}
