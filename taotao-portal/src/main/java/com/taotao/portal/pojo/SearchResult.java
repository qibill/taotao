package com.taotao.portal.pojo;

import java.util.List;

public class SearchResult {
	
	//商品列表
	private List<SearchItem> itemList;
	//查询结果总记录数
	private Long recordCount;
	//查询结果的总页数
	private int pageCount;
	//当前页码
	private int curPage;
	
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	
}
