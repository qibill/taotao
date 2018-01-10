package com.taotao.portal.service;

import com.taotao.commom.pojo.TaotaoResult;

public interface StaticPageService {

    /**   
     * @Title: genItemHtml   
     * @Description: 根据itemId生成html页面
     * @param itemId
     * @return TaotaoResult
     * @throws Exception 
     */
    TaotaoResult genItemHtml(Long itemId) throws Exception;
}
