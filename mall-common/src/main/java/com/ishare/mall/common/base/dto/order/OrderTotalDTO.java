package com.ishare.mall.common.base.dto.order;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.ishare.mall.common.base.object.BaseObject;

/**
 * Created by liaochenglei on 2015/8/18.
 * Description: productDTO
 * Version 1.0
 */
@JsonAutoDetect
public class OrderTotalDTO implements BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public float totalMoney;

	public float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

}
