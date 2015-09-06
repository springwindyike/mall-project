package com.ishare.mall.core.form.product;

import org.hibernate.validator.constraints.NotEmpty;

public class AddProductForm {
	
	@NotEmpty(message = "产品标题不能为空")
    /* 产品名称 */
    private String productName;
    //物品类型 便于search 10010010001
	@NotEmpty(message = "产品类型code不能为空")
    private String typeCode;
    //商品进价
	@NotEmpty(message = "产品进价不能为空")
    private Float basePrice;
    //市场价格
	@NotEmpty(message = "市场价格不能为空")
    private Float marketPrice;
  
    //描述
	@NotEmpty(message = "产品描述不能为空")
    private String description;
 
    //购买须知
	@NotEmpty(message = "产品购买须知不能为空")
    private String buyExplain;
	
	@NotEmpty(message = "库存不能为空")
    private Integer inventory;
}
