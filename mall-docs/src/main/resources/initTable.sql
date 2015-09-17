/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.21-log : Database - mall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mall` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mall`;

/*Table structure for table `t_attribute` */

DROP TABLE IF EXISTS `t_attribute`;

CREATE TABLE `t_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attr_desc` varchar(255) DEFAULT NULL,
  `attr_name` varchar(255) DEFAULT NULL,
  `attr_group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7f63ces7attp066top5hj80pn` (`attr_group_id`),
  CONSTRAINT `FK_7f63ces7attp066top5hj80pn` FOREIGN KEY (`attr_group_id`) REFERENCES `t_attribute_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_attribute` */

/*Table structure for table `t_attribute_group` */

DROP TABLE IF EXISTS `t_attribute_group`;

CREATE TABLE `t_attribute_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ptkso83jr5qjc4ai4hscc3d7g` (`product_type_id`),
  CONSTRAINT `FK_ptkso83jr5qjc4ai4hscc3d7g` FOREIGN KEY (`product_type_id`) REFERENCES `t_product_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_attribute_group` */

/*Table structure for table `t_brand` */

DROP TABLE IF EXISTS `t_brand`;

CREATE TABLE `t_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_address_city` varchar(15) DEFAULT NULL,
  `brand_address_country` varchar(10) DEFAULT NULL,
  `brand_address_detail` varchar(50) DEFAULT NULL,
  `brand_address_district` varchar(15) DEFAULT NULL,
  `brand_logo_url` varchar(200) DEFAULT NULL,
  `brand_name` varchar(50) DEFAULT NULL,
  `brand_address_province` varchar(21) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_brand` */

/*Table structure for table `t_channel` */

DROP TABLE IF EXISTS `t_channel`;

CREATE TABLE `t_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_app_id` varchar(50) DEFAULT NULL,
  `channel_app_secret` varchar(50) DEFAULT NULL,
  `channel_business_scale` varchar(7) DEFAULT NULL,
  `channel_address_city` varchar(15) DEFAULT NULL,
  `channel_code` varchar(6) DEFAULT NULL,
  `channel_address_country` varchar(10) DEFAULT NULL,
  `member_create_by` varchar(15) DEFAULT NULL,
  `channel_create_time` datetime DEFAULT NULL,
  `channel_address_detail` varchar(50) DEFAULT NULL,
  `channel_address_district` varchar(15) DEFAULT NULL,
  `channel_industry` varchar(51) DEFAULT NULL,
  `channel_link_name` varchar(15) DEFAULT NULL,
  `channel_link_phone` varchar(11) DEFAULT NULL,
  `channel_name` varchar(30) DEFAULT NULL,
  `channel_phone` varchar(11) DEFAULT NULL,
  `channel_address_province` varchar(21) DEFAULT NULL,
  `channel_token` varchar(50) DEFAULT NULL,
  `member_update_by` varchar(15) DEFAULT NULL,
  `channel_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_channel` */

/*Table structure for table `t_logistics` */

DROP TABLE IF EXISTS `t_logistics`;

CREATE TABLE `t_logistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logistics_contact_name` varchar(15) DEFAULT NULL,
  `logistics_name` varchar(27) DEFAULT NULL,
  `logistics_query_url` varchar(200) DEFAULT NULL,
  `logistics_contact_tel` varchar(11) DEFAULT NULL,
  `logistics_type` varchar(9) DEFAULT NULL,
  `logistics_official_url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_logistics` */

/*Table structure for table `t_manufacturer` */

DROP TABLE IF EXISTS `t_manufacturer`;

CREATE TABLE `t_manufacturer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fact_address_city` varchar(15) DEFAULT NULL,
  `fact_address_country` varchar(10) DEFAULT NULL,
  `fact_address_detail` varchar(50) DEFAULT NULL,
  `fact_address_district` varchar(15) DEFAULT NULL,
  `fact_name` varchar(30) DEFAULT NULL,
  `fact_address_province` varchar(21) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_manufacturer` */

/*Table structure for table `t_member` */

DROP TABLE IF EXISTS `t_member`;

CREATE TABLE `t_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_account` varchar(50) DEFAULT NULL,
  `member_create_by` varchar(50) DEFAULT NULL,
  `member_create_time` datetime DEFAULT NULL,
  `member_type` varchar(6) NOT NULL,
  `member_mobile` varchar(11) DEFAULT NULL,
  `member_name` varchar(27) DEFAULT NULL,
  `member_password` varchar(50) DEFAULT NULL,
  `member_salt` varchar(32) DEFAULT NULL,
  `sex` varchar(5) NOT NULL,
  `member_update_by` varchar(50) DEFAULT NULL,
  `member_update_time` datetime DEFAULT NULL,
  `member_use` bit(1) DEFAULT NULL,
  `channel_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_futt874ggf6uxsj5b0jahqixi` (`channel_id`),
  CONSTRAINT `FK_futt874ggf6uxsj5b0jahqixi` FOREIGN KEY (`channel_id`) REFERENCES `t_channel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_member` */

/*Table structure for table `t_member_role` */

DROP TABLE IF EXISTS `t_member_role`;

CREATE TABLE `t_member_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rcgphfx1m6nqqpijn85yiq4is` (`member_id`),
  KEY `FK_q04getgot5fkpo6ha4nh3jtpj` (`role_id`),
  CONSTRAINT `FK_q04getgot5fkpo6ha4nh3jtpj` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_rcgphfx1m6nqqpijn85yiq4is` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_member_role` */

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `order_id` varchar(14) NOT NULL,
  `order_create_time` datetime NOT NULL,
  `order_deliver_fee` float NOT NULL,
  `express_id` varchar(15) NOT NULL,
  `express_order` varchar(15) NOT NULL,
  `order_lock_member` varchar(20) DEFAULT NULL,
  `order_note` varchar(100) DEFAULT NULL,
  `order_payable_fee` float NOT NULL,
  `order_payment_state` bit(1) NOT NULL,
  `order_payment_way` varchar(20) NOT NULL,
  `order_product_total_price` float NOT NULL,
  `order_state` varchar(16) NOT NULL,
  `order_total_price` float NOT NULL,
  `order_update_time` datetime NOT NULL,
  `channel_id` int(11) NOT NULL,
  `create_by_member_id` int(11) NOT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `deliver_id` int(11) DEFAULT NULL,
  `update_by_member_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_bau6dd0ntbfubf7d5u98st7fd` (`channel_id`),
  KEY `FK_ndch2jt7hrb5o6j4k0cxlh7oc` (`create_by_member_id`),
  KEY `FK_df4brxpo8wjasr4n0e17jp616` (`contact_id`),
  KEY `FK_drc8eoj32mnownsfd89ywse7b` (`deliver_id`),
  KEY `FK_g8v6eos6vp7js42w8wkwctwks` (`update_by_member_id`),
  CONSTRAINT `FK_bau6dd0ntbfubf7d5u98st7fd` FOREIGN KEY (`channel_id`) REFERENCES `t_channel` (`id`),
  CONSTRAINT `FK_df4brxpo8wjasr4n0e17jp616` FOREIGN KEY (`contact_id`) REFERENCES `t_order_contact_info` (`id`),
  CONSTRAINT `FK_drc8eoj32mnownsfd89ywse7b` FOREIGN KEY (`deliver_id`) REFERENCES `t_order_deliver_info` (`id`),
  CONSTRAINT `FK_g8v6eos6vp7js42w8wkwctwks` FOREIGN KEY (`update_by_member_id`) REFERENCES `t_member` (`id`),
  CONSTRAINT `FK_ndch2jt7hrb5o6j4k0cxlh7oc` FOREIGN KEY (`create_by_member_id`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

/*Table structure for table `t_order_contact_info` */

DROP TABLE IF EXISTS `t_order_contact_info`;

CREATE TABLE `t_order_contact_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_buyer_name` varchar(25) NOT NULL,
  `order_buyer_address_city` varchar(15) DEFAULT NULL,
  `order_buyer_address_country` varchar(10) DEFAULT NULL,
  `order_buyer_address_detail` varchar(50) DEFAULT NULL,
  `order_buyer_address_district` varchar(15) DEFAULT NULL,
  `order_buyer_email` varchar(40) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `order_buyer_mobile` varchar(11) DEFAULT NULL,
  `order_buyer_postal_code` varchar(6) DEFAULT NULL,
  `order_buyer_address_province` varchar(21) DEFAULT NULL,
  `order_buyer_tel` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order_contact_info` */

/*Table structure for table `t_order_deliver_info` */

DROP TABLE IF EXISTS `t_order_deliver_info`;

CREATE TABLE `t_order_deliver_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deliver_address_city` varchar(15) DEFAULT NULL,
  `deliver_address_country` varchar(10) DEFAULT NULL,
  `deliver_way` varchar(23) NOT NULL,
  `deliver_address_detail` varchar(50) DEFAULT NULL,
  `deliver_address_district` varchar(15) DEFAULT NULL,
  `deliver_email` varchar(20) DEFAULT NULL,
  `gender` varchar(5) NOT NULL,
  `deliver_mobile` varchar(11) DEFAULT NULL,
  `deliver_postal_code` varchar(6) DEFAULT NULL,
  `deliver_address_province` varchar(21) DEFAULT NULL,
  `deliver_recipients` varchar(25) NOT NULL,
  `requirement` varchar(30) DEFAULT NULL,
  `deliver_tel` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order_deliver_info` */

/*Table structure for table `t_order_id` */

DROP TABLE IF EXISTS `t_order_id`;

CREATE TABLE `t_order_id` (
  `id` varchar(5) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order_id` */

/*Table structure for table `t_order_item` */

DROP TABLE IF EXISTS `t_order_item`;

CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_product_amount` int(11) NOT NULL,
  `create_by` varchar(16) DEFAULT NULL,
  `item_exchange_or_back` varchar(6) DEFAULT NULL,
  `item_product_image_url` varchar(200) NOT NULL,
  `item_product_id` int(11) NOT NULL,
  `item_product_name` varchar(50) NOT NULL,
  `item_product_price` float NOT NULL,
  `item_state` varchar(16) DEFAULT NULL,
  `item_product_style_id` bigint(20) NOT NULL,
  `item_product_style_name` varchar(30) NOT NULL,
  `order_id` varchar(14) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tr4wwliho4mtheslf3eugb9km` (`order_id`),
  CONSTRAINT `FK_tr4wwliho4mtheslf3eugb9km` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order_item` */

/*Table structure for table `t_order_message` */

DROP TABLE IF EXISTS `t_order_message`;

CREATE TABLE `t_order_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_message_content` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL,
  `member_id` int(11) NOT NULL,
  `order_id` varchar(14) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nac95qxnsrwvb5eqscb4qugl3` (`member_id`),
  KEY `FK_h2gpjjxv9a3wc79uouoypyejr` (`order_id`),
  CONSTRAINT `FK_h2gpjjxv9a3wc79uouoypyejr` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`),
  CONSTRAINT `FK_nac95qxnsrwvb5eqscb4qugl3` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order_message` */

/*Table structure for table `t_order_pay_log` */

DROP TABLE IF EXISTS `t_order_pay_log`;

CREATE TABLE `t_order_pay_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_member_account` varchar(15) DEFAULT NULL,
  `pay_amount` decimal(19,2) DEFAULT NULL,
  `pay_channel_id` int(11) DEFAULT NULL,
  `pay_cost_type` varchar(7) NOT NULL,
  `pay_log_create_time` datetime NOT NULL,
  `pay_log_finish_time` datetime NOT NULL,
  `pay_order_id` varchar(14) DEFAULT NULL,
  `pay_log_type` varchar(7) NOT NULL,
  `pay_log_tans_id` varchar(15) DEFAULT NULL,
  `pay_log_update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order_pay_log` */

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_permission` */

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_base_price` float DEFAULT NULL,
  `product_buy_explain` varchar(150) DEFAULT NULL,
  `product_click_count` int(11) DEFAULT NULL,
  `product_code` varchar(15) DEFAULT NULL,
  `product_commend` bit(1) DEFAULT NULL,
  `product_create_time` datetime DEFAULT NULL,
  `default_img_url` varchar(200) DEFAULT NULL,
  `product_description` varchar(150) DEFAULT NULL,
  `product_inventory` int(11) DEFAULT NULL,
  `product_market_price` float DEFAULT NULL,
  `product_model` varchar(25) DEFAULT NULL,
  `product_name` varchar(27) DEFAULT NULL,
  `product_sell_count` int(11) DEFAULT NULL,
  `product_sell_price` float DEFAULT NULL,
  `product_type_code` varchar(15) DEFAULT NULL,
  `product_update_time` datetime DEFAULT NULL,
  `product_visible` bit(1) DEFAULT NULL,
  `product_weight` int(11) DEFAULT NULL,
  `product_brand_id` int(11) NOT NULL,
  `product_channel_id` int(11) NOT NULL,
  `product_create_by` int(11) NOT NULL,
  `product_type_id` int(11) NOT NULL,
  `product_update_by` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2bajldf6wfl0lkhyj8mi9k61m` (`product_brand_id`),
  KEY `FK_56olua72fx9rxddhc3pd6t58u` (`product_channel_id`),
  KEY `FK_pd6fy4ikt586122b4eakck1r1` (`product_create_by`),
  KEY `FK_nps4xac4nqhms2dxq2jii4lc4` (`product_type_id`),
  KEY `FK_g7vdv7ky9bkromll3h7mirxgk` (`product_update_by`),
  CONSTRAINT `FK_2bajldf6wfl0lkhyj8mi9k61m` FOREIGN KEY (`product_brand_id`) REFERENCES `t_brand` (`id`),
  CONSTRAINT `FK_56olua72fx9rxddhc3pd6t58u` FOREIGN KEY (`product_channel_id`) REFERENCES `t_channel` (`id`),
  CONSTRAINT `FK_g7vdv7ky9bkromll3h7mirxgk` FOREIGN KEY (`product_update_by`) REFERENCES `t_member` (`id`),
  CONSTRAINT `FK_nps4xac4nqhms2dxq2jii4lc4` FOREIGN KEY (`product_type_id`) REFERENCES `t_product_type` (`id`),
  CONSTRAINT `FK_pd6fy4ikt586122b4eakck1r1` FOREIGN KEY (`product_create_by`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_product` */

/*Table structure for table `t_product_attribute` */

DROP TABLE IF EXISTS `t_product_attribute`;

CREATE TABLE `t_product_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attr_add_fee` decimal(19,2) DEFAULT NULL,
  `attr_name` varchar(26) DEFAULT NULL,
  `type` varchar(6) NOT NULL,
  `attr_value` varchar(26) DEFAULT NULL,
  `attr_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8neoeiwwoar3uxrmlc2kqm632` (`attr_id`),
  KEY `FK_weelp6d3rh0r5bjauq0iw2lh` (`product_id`),
  CONSTRAINT `FK_8neoeiwwoar3uxrmlc2kqm632` FOREIGN KEY (`attr_id`) REFERENCES `t_attribute` (`id`),
  CONSTRAINT `FK_weelp6d3rh0r5bjauq0iw2lh` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_product_attribute` */

/*Table structure for table `t_product_style` */

DROP TABLE IF EXISTS `t_product_style`;

CREATE TABLE `t_product_style` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_create_time` datetime DEFAULT NULL,
  `product_image_url` varchar(200) NOT NULL,
  `product_style_name` varchar(32) NOT NULL,
  `product_update_time` datetime DEFAULT NULL,
  `product_style_visible` bit(1) NOT NULL,
  `product_create_by` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_update_by` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5v4ssjnsi0iei8skkbo9ym84w` (`product_create_by`),
  KEY `FK_d47aryt5a0hrklfe271dpw7s5` (`product_id`),
  KEY `FK_oh68xg0li8ukhi11f4d2gybr9` (`product_update_by`),
  CONSTRAINT `FK_5v4ssjnsi0iei8skkbo9ym84w` FOREIGN KEY (`product_create_by`) REFERENCES `t_member` (`id`),
  CONSTRAINT `FK_d47aryt5a0hrklfe271dpw7s5` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`),
  CONSTRAINT `FK_oh68xg0li8ukhi11f4d2gybr9` FOREIGN KEY (`product_update_by`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_product_style` */

/*Table structure for table `t_product_style_image` */

DROP TABLE IF EXISTS `t_product_style_image`;

CREATE TABLE `t_product_style_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_create_time` datetime DEFAULT NULL,
  `product_update_time` datetime DEFAULT NULL,
  `product_image_url` varchar(200) NOT NULL,
  `product_create_by` int(11) NOT NULL,
  `product_style_id` bigint(20) NOT NULL,
  `product_update_by` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_oxongo9gg8w55q32hqcw6ihvb` (`product_create_by`),
  KEY `FK_jy8g08g4y2rsrvkyql1l13vad` (`product_style_id`),
  KEY `FK_2vexqwa1mlytcfkpvj129wqb0` (`product_update_by`),
  CONSTRAINT `FK_2vexqwa1mlytcfkpvj129wqb0` FOREIGN KEY (`product_update_by`) REFERENCES `t_member` (`id`),
  CONSTRAINT `FK_jy8g08g4y2rsrvkyql1l13vad` FOREIGN KEY (`product_style_id`) REFERENCES `t_product_style` (`id`),
  CONSTRAINT `FK_oxongo9gg8w55q32hqcw6ihvb` FOREIGN KEY (`product_create_by`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_product_style_image` */

/*Table structure for table `t_product_type` */

DROP TABLE IF EXISTS `t_product_type`;

CREATE TABLE `t_product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_code` varchar(20) NOT NULL,
  `type_level` int(11) NOT NULL,
  `type_name` varchar(36) NOT NULL,
  `type_note` varchar(36) NOT NULL,
  `type_parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_aejs232tqamdnw311e1i0qjf4` (`type_parent_id`),
  CONSTRAINT `FK_aejs232tqamdnw311e1i0qjf4` FOREIGN KEY (`type_parent_id`) REFERENCES `t_product_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_product_type` */

/*Table structure for table `t_region` */

DROP TABLE IF EXISTS `t_region`;

CREATE TABLE `t_region` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `region_name` varchar(255) DEFAULT NULL,
  `region_type` int(11) DEFAULT NULL,
  `parent_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1qecmpw1yaxb8thsyfxn4xox3` (`parent_id`),
  CONSTRAINT `FK_1qecmpw1yaxb8thsyfxn4xox3` FOREIGN KEY (`parent_id`) REFERENCES `t_region` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_region` */

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nkhhl7rlqqsu5goufwn1udr0e` (`permission_id`),
  KEY `FK_n0gk0jwxlfbi5vbmf43r0kcwl` (`role_id`),
  CONSTRAINT `FK_n0gk0jwxlfbi5vbmf43r0kcwl` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_nkhhl7rlqqsu5goufwn1udr0e` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_permission` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
