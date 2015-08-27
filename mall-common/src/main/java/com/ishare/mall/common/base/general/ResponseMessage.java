package com.ishare.mall.common.base.general;

public enum ResponseMessage {
	
	  INVALID_ORDER(100001),
	  INVALID_ORDERITEM(100002);


	  private final int value;

	  private ResponseMessage(int value) {
	    this.value = value;
	  }


	  public int getValue() {
	    return value;
	  }


	  public static ResponseMessage findByValue(int value) { 
	    switch (value) {
	      case 100001:
	        return INVALID_ORDER;
	      case 100002:
	        return INVALID_ORDERITEM;
	      default:
	        return null;
	    }
	  }
	  
  @Override
  public String toString() {

      return String.valueOf(this.name());

      }
	  
}