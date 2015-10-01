package com.ishare.mall.common.base.constant.uri;

/**
 * Created by liaochenglei on 2015/9/30.
 * Description :
 * Version 1.0
 */
public interface APIURIConstant {
    /**
     * 
     * @author liaochenglei
     * 物流查询
     */
    interface Express {
    	String REQUEST_MAPPING ="/express";
    	String REQUEST_MAPPING_FIND ="/find/{order}/{id}";
    }
}
