package com.ishare.mall.common.base.enumeration;
/**
 * 送货方式
 * @author keyo
 *
 */
public enum DeliverWay {
	/**平邮**/
	GENERAL_POST{
		public String getName(){return "平邮";}
	},
	/**快递送货上门**/
	EXPRESS_DELIVERY{
		public String getName(){return "快递送货上门";}
	},
	/**加急快递送货上门**/
	EXIGENCE_EXPRESS_DELIVERY{
		public String getName(){return "加急快递送货上门";}
	},
	/**国内特快专递EMS**/
	EMS{
		public String getName(){return "国内特快专递EMS";}
	};
	public abstract String getName();
}
