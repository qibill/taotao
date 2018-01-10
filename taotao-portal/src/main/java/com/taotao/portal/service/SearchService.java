package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

public interface SearchService {

	/**   
	 * @Title: search   
	 * @Description: 根据关键字搜索
	 * @param keyword 关键字
	 * @param page 页数
	 * @param rows 
	 * @return SearchResult      
	 * @throws   
	 */
	SearchResult search(String keyword, int page, int rows);
}
