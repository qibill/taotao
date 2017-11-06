package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.commom.pojo.TaotaoResult;

public interface LoginService {

	/**
	 * <P> @Title: login
	 * <P> @Description:登入
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return TaotaoResult
	 */ 
	TaotaoResult login(String username, String password, HttpServletRequest request,
			HttpServletResponse response);
	
	/**
	 * <P> @Title: getUserByToken
	 * <P> @Description:通过token查询用户信息
	 * @param token
	 * @return TaotaoResult
	 */ 
	TaotaoResult getUserByToken(String token);
	
	/**
	 * <P> @Title: signout
	 * <P> @Description: 退出
	 * @param token
	 * @return TaotaoResult
	 */ 
	TaotaoResult  signOut (String token);
}
