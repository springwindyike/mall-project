-- 陶瓷网业务逻辑表
USE ${schema.name};

-- 表结构
/*
属性表
*/
DROP TABLE IF EXISTS `es_attribute`;
CREATE TABLE `es_attribute` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`group_id` INT(11) NOT NULL COMMENT '属性组ID',
`attr_name` VARCHAR(255) NOT NULL COMMENT '属性名称',
`attr_value` VARCHAR(255) NOT NULL COMMENT '属性值',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='属性表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*
属性组表
*/
DROP TABLE IF EXISTS `es_attribute_group`;
CREATE TABLE `es_attribute_group`(
`id` INT(11) NOT NULL AUTO_INCREMENT,
`group_name` VARCHAR(255) NOT NULL COMMENT '属性组名称',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='属性组表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*
品牌表
*/
DROP TABLE if EXISTS `es_brand`;
CREATE TABLE `es_brand`(
`id` INT(11) NOT NULL auto_increment,
`brand_name` VARCHAR(255) NOT NULL comment '品牌名称',
`logo_url` VARCHAR(255) NOT NULL comment '品牌LOGO',
`manufacturer_id` INT(11) NOT NULL comment '品牌所属厂商',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*
购物车
*/
DROP TABLE if EXISTS `es_cart`;
CREATE TABLE `es_cart`(
`id` INT(11) NOT NULL auto_increment,
`customer_id` INT(11) NOT NULL comment '购物车所属用户ID',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*
类目表
*/
DROP TABLE if EXISTS `es_category`;
CREATE TABLE `es_category`(
`id` INT(11) NOT NULL auto_increment,
`category_name` VARCHAR(255) NOT NULL comment '类目名称',
`parent_id` INT(11) comment '上级类目',
`sort_index` INT(11) comment '序号',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类目表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*类目-产品关联表*/
DROP TABLE IF EXISTS `es_products_categories`;
CREATE TABLE `es_products_categories` (
`category_id` INT(11) NOT NULL COMMENT '类目ID',
`product_id` INT(11) NOT NULL COMMENT '产品ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类目-产品关联表' COLLATE utf8_general_ci;

/*评论表*/
DROP TABLE if EXISTS `es_comment`;
CREATE TABLE `es_comment`(
`id` INT(11) NOT NULL auto_increment,
`customer_id` INT(11) NOT NULL comment '用户ID',
`comment_content` TEXT comment '评论内容',
`comment_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '评论时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*
会员（顾客）表
*/
DROP TABLE IF EXISTS `es_customer`;
CREATE TABLE `es_customer`(
`id` INT(11) NOT NULL AUTO_INCREMENT,
`account` VARCHAR(32) NOT NULL COMMENT '会员账号，登录用',
`passwd` VARCHAR(32) NOT NULL COMMENT '密码',
`salt` VARCHAR(32) NOT NULL COMMENT '32位hash盐，加密用',
`username` VARCHAR(255) NOT NULL COMMENT '会员实名',
`gender` BIT NOT NULL COMMENT '性别 0-男 1-女',
`email` VARCHAR(255) NOT NULL COMMENT '邮箱',
`mobile` VARCHAR(20) NOT NULL COMMENT '电话',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
`is_lock` CHAR(1) comment '是否锁定 Y/N',
PRIMARY KEY (`id`),
UNIQUE (`account`, `username`, `email`, `mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员（顾客）表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*配送（物流/收货）地址 */
DROP TABLE IF EXISTS `es_delivery_address`;
CREATE TABLE `es_delivery_address` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`customer_id` INT(11) NOT NULL COMMENT '会员ID',
`receiver` VARCHAR(255) NOT NULL COMMENT '收货人姓名',
`mobile` VARCHAR(20) NOT NULL COMMENT '联系电话，手机',
`phone` VARCHAR(20) NOT NULL COMMENT '联系电话，座机',
`detail_address` VARCHAR(512) NOT NULL COMMENT '收货地址',
`zip` VARCHAR(10) NOT NULL COMMENT '邮编',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送（物流/收货）地址表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*
 图片
*/
DROP TABLE IF EXISTS `es_image`;
CREATE TABLE `es_image` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`product_id` INT(11) NOT NULL COMMENT '产品ID',
`img_name` VARCHAR(20) COMMENT '图片名称',
`url` VARCHAR(255) NOT NULL COMMENT '图片URL',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*
物流公司
*/
DROP TABLE IF EXISTS `es_logistics`;
CREATE TABLE `es_logistics` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`logistics_name` VARCHAR(50) NOT NULL COMMENT '物流公司名称',
`contact` VARCHAR(20) NOT NULL COMMENT '联系人姓名',
`tel` VARCHAR(20) NOT NULL COMMENT '联系电话',
`logistics_type` VARCHAR(10) NOT NULL COMMENT '物流类型：快递、物流',
`query_url` VARCHAR(255) NOT NULL COMMENT '查询地址',
`official_url` VARCHAR(255) NOT NULL COMMENT '官网地址',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流公司表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*
生产（制造/供应）厂商表
*/
DROP TABLE if EXISTS `es_manufacturer`;
CREATE TABLE `es_manufacturer`(
`id` INT(11) NOT NULL auto_increment,
`manu_name` VARCHAR(255) NOT NULL comment '生产（制造/供应）厂商名称',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生产（制造/供应）厂商表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*
订单
*/
DROP TABLE if EXISTS `es_order`;
CREATE TABLE `es_order`(
`id` INT(11) NOT NULL auto_increment,
`code` VARCHAR(255) NOT NULL comment '订单流水号',
`price_total` DOUBLE comment '总金额',
`price_need_pay` DOUBLE comment '应付金额',
`price_discount` DOUBLE comment '优惠金额',
`price_shipment` DOUBLE comment '运费',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '订单创建时间',
`pay_time` TIMESTAMP comment '订单支付时间',
`handle_time` TIMESTAMP comment '订单处理时间',
`delivery_time` TIMESTAMP comment '订单，系统确认的物流发货时间',
`finish_time` TIMESTAMP comment '订单完成时间',
`customer_id` INT(11) NOT NULL comment '会员ID',
`order_status` VARCHAR(20) NOT NULL comment '订单状态',
`payment_type` VARCHAR(20) NOT NULL comment '支付类型',
`payment_status` VARCHAR(20) NOT NULL comment '支付状态',
`delivery_status` VARCHAR(20) NOT NULL comment '物流状态',
`delivery_address_id` INT(11) NOT NULL comment '送货地址ID',
`user_delivery_time` TIMESTAMP NOT NULL comment '用户填写的配送时间',
`remark` VARCHAR(255) comment '备注',
`express_name` VARCHAR(255) comment '快递名称',
`express_code` VARCHAR(255) comment '快递单号',
`express_type` VARCHAR(255) comment '快递类型',
`operator_id` INT(11) comment '订单处理员ID',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

/*
订单明细表
*/
DROP TABLE if EXISTS `es_order_item`;
CREATE TABLE `es_order_item`(
`id` INT(11) NOT NULL auto_increment,
`order_id` INT(11) NOT NULL comment '订单ID',
`product_id` INT(11) NOT NULL comment '产品ID',
`quantity` INT(11) NOT NULL DEFAULT 1 comment '购买商品数量',
`price` DOUBLE NOT NULL comment '购买商品数量合计金额',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单明细表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;


/*
商品
*/
DROP TABLE if EXISTS `es_product`;
CREATE TABLE `es_product`(
`id` INT(11) NOT NULL auto_increment,
`brand_id` INT(11) NOT NULL comment '品牌ID',
`product_name` VARCHAR(255) NOT NULL comment '商品名称',
`product_desc` VARCHAR(1024) comment '商品描述',
`ex_factory_price` DOUBLE NOT NULL comment '出厂价',
`unit_price` DOUBLE NOT NULL comment '单价',
`competitive_products_price` DOUBLE NOT NULL comment '竞品价',
`limit_purchase_price` DOUBLE NOT NULL comment '限购价',
`sale_price` DOUBLE NOT NULL comment '促销价',
`price` DOUBLE NOT NULL comment '市场价',
`code` VARCHAR(255) NOT NULL comment '货号',
`product_level` VARCHAR(255) NOT NULL comment '等级',
`color` VARCHAR(255) NOT NULL comment '色号',
`product_spec` VARCHAR(255) NOT NULL comment '规格',
`unit` VARCHAR(255) NOT NULL comment '单位',
`weight` VARCHAR(255) NOT NULL comment '重量',
`create_time` TIMESTAMP NOT NULL comment '生产日期',
`stock` INT(11) NOT NULL comment '库存',
`is_show` CHAR(1) DEFAULT 'N' comment '是否上架',
`is_competing` CHAR(1) DEFAULT 'N' comment '竞品',
`is_limit` CHAR(1) DEFAULT 'N' comment '限购',
`is_promotion` CHAR(1) DEFAULT 'N' comment '促销',
`is_best` CHAR(1) DEFAULT 'N' comment '精品',
`is_newest` CHAR(1) DEFAULT 'N' comment '新品',
`is_hot` CHAR(1) DEFAULT 'N' comment '热销',
`is_suit` CHAR(1) DEFAULT 'N' comment '套餐',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表' COLLATE utf8_general_ci AUTO_INCREMENT = 1000;

-- 所有表都创建好后，再建外键
ALTER TABLE `es_attribute` ADD CONSTRAINT `es_attribute_fk_es_attribute_group_id` FOREIGN KEY (`group_id`) REFERENCES `es_attribute_group` (`id`);
ALTER TABLE `es_brand` ADD CONSTRAINT `es_brand_fk_es_manufacturer_id` FOREIGN KEY (`manufacturer_id`) REFERENCES `es_manufacturer` (`id`);
ALTER TABLE `es_cart` ADD CONSTRAINT `es_cart_fk_es_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `es_customer` (`id`);
ALTER TABLE `es_comment` ADD CONSTRAINT `es_comment_fk_es_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `es_customer` (`id`);
ALTER TABLE `es_delivery_address` ADD CONSTRAINT `es_delivery_address_fk_es_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `es_customer` (`id`);
ALTER TABLE `es_image` ADD CONSTRAINT `es_image_fk_es_product_id` FOREIGN KEY (`product_id`) REFERENCES `es_product` (`id`);
ALTER TABLE `es_order_item` ADD CONSTRAINT `es_order_item_fk_es_order_id` FOREIGN KEY (`order_id`) REFERENCES `es_order` (`id`);
ALTER TABLE `es_order_item` ADD CONSTRAINT `es_order_item_fk_es_product_id` FOREIGN KEY (`product_id`) REFERENCES `es_product` (`id`);
ALTER TABLE `es_product` ADD CONSTRAINT `es_product_fk_es_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `es_brand` (`id`);

-- 索引