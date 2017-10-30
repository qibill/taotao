package com.taotao.commom.pojo;

import java.util.List;

/**
 * @ClassName: EasyUIDataGridResult
 * @Description: EasyUI的返回类型
 * @author qiminghui
 * @date 2017年7月4日 下午5:28:20
 */ 
public class EasyUIDataGridResult {
	private long total;
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
