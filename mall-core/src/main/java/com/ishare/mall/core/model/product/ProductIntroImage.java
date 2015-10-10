package com.ishare.mall.core.model.product;

import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_PRODUCT_STYLE_IMAGE_NAME;

/**
 * Created by YinLin on 2015/10/10.
 * Description :
 * Version 1.0
 */
@Entity
@Table(name = TABLE_PRODUCT_STYLE_IMAGE_NAME)
public class ProductIntroImage extends BaseEntity {
}
