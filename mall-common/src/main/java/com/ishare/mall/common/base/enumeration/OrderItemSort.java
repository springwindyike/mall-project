package com.ishare.mall.core.status;

public enum OrderItemSort {
	/*退货 */
    BACK {public String getName(){return "退货";}},
    /*换货 */
    CHANGE {public String getName(){return "换货";}};
  
    public abstract String getName();
}
