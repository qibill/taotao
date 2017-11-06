package com.taotao.sso.service;

import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface RegisterService {
	
	/**
	 * <P> @Title: checkData
	 * <P> @Description: 检查数据
	 * @param param
	 * @param type
	 * @return TaotaoResult
	 */ 
	TaotaoResult checkData(String param, int type);
	
	/**
	 * <P> @Title: register
	 * <P> @Description: 用户注册
	 * @param user
	 * @return TaotaoResult
	 */ 
	TaotaoResult register(TbUser user);
}
