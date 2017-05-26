package com.emin.igwmp.skm.facade.accepters;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/4/18.
 */
public interface ProductNoticeAccepter {
   /**
    * 产品修改通知
    * @param productId 修改的产品ID
    * @return JSONObject  {"success":true,"code":0}
    * */
    public void ProductUpdateNotice(Long productId)throws  Exception;
}
