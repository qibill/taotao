package com.taotao.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.commom.pojo.TaotaoResult;

public interface CartService {

	TaotaoResult addCart(Long itemId, Integer num, 
			HttpServletRequest request, HttpServletResponse response);

}
