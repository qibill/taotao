package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.commom.pojo.PictureResult;

public interface PictureService {

	/**
	 * <P> @Title: uploadPic
	 * <P> @Description: 上传图片
	 * @param picFile
	 * @return PictureResult
	 */ 
	PictureResult uploadPic(MultipartFile picFile);
}
