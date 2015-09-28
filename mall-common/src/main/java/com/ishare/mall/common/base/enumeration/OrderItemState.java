package com.ishare.mall.common.base.enumeration;

public enum OrderItemState {
	/** 待审核 */
    WAIT_VERIFY {public String getName(){return "已取消";}},
    /** 审核中 */
    VERIFING {public String getName(){return "待审核";}},
    /** 完成 */
    COMPLETE_VERIFY {public String getName(){return "完成";}};
  
    public abstract String getName();
}
