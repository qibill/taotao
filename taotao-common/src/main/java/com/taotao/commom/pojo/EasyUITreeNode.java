package com.taotao.commom.pojo;

/**
 * @ClassName: EasyUITreeNode
 * @Description: EasyUI异步tree控件的返回类型
 * @author qiminghui
 * @date 2017年7月5日 上午10:13:12
 */ 
public class EasyUITreeNode {

/*    "id": 1,    
    "text": "Node 1",    
    "state": "closed"*/
//	如果当前节点为父节点，state应为“closed”、如果是叶子节点“open”
	private long id;
	private String text;
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
