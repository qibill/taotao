package com.taotao.commom.pojo;

/**
 * @ClassName: PictureResult
 * @Description: 上传图片的返回结果
 * @author qiminghui
 * @date 2017年7月14日 下午5:20:49
 */ 
public class PictureResult {
	
	/** 0，成功   1，失败 */
	private int error;
	/** 成功时，返回的图片地址 */
	private String url;
	/** 失败时，返回的错误信息 */
	private String message;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
