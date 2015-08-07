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


/*Table structure for table `t_brand` */

DROP TABLE IF EXISTS `t_brand`;

CREATE TABLE `t_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_address_city` varchar(255) DEFAULT NULL,
  `brand_address_country` varchar(255) DEFAULT NULL,
  `brand_address_detail` varchar(255) DEFAULT NULL,
  `brand_address_district` varchar(255) DEFAULT NULL,
  `brand_logo_url` varchar(255) DEFAULT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `brand_address_province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_brand` */

insert into `t_brand` (`id`, `brand_address_city`, `brand_address_country`, `brand_address_detail`, `brand_address_district`, `brand_logo_url`, `brand_name`, `brand_address_province`) values('1','其他','其他','其他','其他','其他','其他','其他');

/*Table structure for table `t_channel` */

DROP TABLE IF EXISTS `t_channel`;

CREATE TABLE `t_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_app_id` varchar(255) DEFAULT NULL,
  `channel_app_secret` varchar(255) DEFAULT NULL,
  `channel_business_scale` varchar(255) DEFAULT NULL,
  `channel_address_city` varchar(255) DEFAULT NULL,
  `channel_code` varchar(255) DEFAULT NULL,
  `channel_address_country` varchar(255) DEFAULT NULL,
  `member_create_by` varchar(255) DEFAULT NULL,
  `channel_create_time` datetime DEFAULT NULL,
  `channel_address_detail` varchar(255) DEFAULT NULL,
  `channel_address_district` varchar(255) DEFAULT NULL,
  `channel_industry` varchar(255) DEFAULT NULL,
  `channel_link_name` varchar(255) DEFAULT NULL,
  `channel_link_phone` varchar(255) DEFAULT NULL,
  `channel_name` varchar(255) DEFAULT NULL,
  `channel_phone` varchar(255) DEFAULT NULL,
  `channel_address_province` varchar(255) DEFAULT NULL,
  `channel_token` varchar(255) DEFAULT NULL,
  `member_update_by` varchar(255) DEFAULT NULL,
  `channel_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_channel` */

insert into `t_channel` (`id`, `channel_app_id`, `channel_app_secret`, `channel_business_scale`, `channel_address_city`, `channel_code`, `channel_address_country`, `member_create_by`, `channel_create_time`, `channel_address_detail`, `channel_address_district`, `channel_industry`, `channel_link_name`, `channel_link_phone`, `channel_name`, `channel_phone`, `channel_address_province`, `channel_token`, `member_update_by`, `channel_update_time`) values('1','123','123','100-2000','成都','1001001','中国','admin','2015-08-07 09:35:53','孵化园','高新区','电商','殷林','15928972087','非渠道用户组','15928972087','四川','123456','admin','2015-08-07 09:37:00');

/*Table structure for table `t_logistics` */

DROP TABLE IF EXISTS `t_logistics`;

CREATE TABLE `t_logistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logistics_contact_name` varchar(255) DEFAULT NULL,
  `logistics_name` varchar(255) DEFAULT NULL,
  `logistics_query_url` varchar(255) DEFAULT NULL,
  `logistics_contact_tel` varchar(255) DEFAULT NULL,
  `logistics_type` varchar(255) DEFAULT NULL,
  `logistics_official_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_logistics` */

/*Table structure for table `t_manufacturer` */

DROP TABLE IF EXISTS `t_manufacturer`;

CREATE TABLE `t_manufacturer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fact_address_city` varchar(255) DEFAULT NULL,
  `fact_address_country` varchar(255) DEFAULT NULL,
  `fact_address_detail` varchar(255) DEFAULT NULL,
  `fact_address_district` varchar(255) DEFAULT NULL,
  `fact_name` varchar(255) DEFAULT NULL,
  `fact_address_province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_manufacturer` */

/*Table structure for table `t_member` */

DROP TABLE IF EXISTS `t_member`;

CREATE TABLE `t_member` (
  `member_account` varchar(255) NOT NULL,
  `member_create_by` varchar(255) DEFAULT NULL,
  `member_create_time` datetime DEFAULT NULL,
  `member_type` varchar(6) NOT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `member_password` varchar(255) DEFAULT NULL,
  `sex` varchar(5) NOT NULL,
  `member_update_by` varchar(255) DEFAULT NULL,
  `member_update_time` datetime DEFAULT NULL,
  `member_use` bit(1) DEFAULT NULL,
  `channel_id` int(11) NOT NULL,
  PRIMARY KEY (`member_account`),
  KEY `FK_futt874ggf6uxsj5b0jahqixi` (`channel_id`),
  CONSTRAINT `FK_futt874ggf6uxsj5b0jahqixi` FOREIGN KEY (`channel_id`) REFERENCES `t_channel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_member` */

insert  into `t_member`(`member_account`,`member_create_by`,`member_create_time`,`member_type`,`member_name`,`member_password`,`sex`,`member_update_by`,`member_update_time`,`member_use`,`channel_id`) values ('13885268940','13885268940','2015-08-07 09:00:31','ADMIN','123','123','MAN','13885268940','2015-08-07 09:00:53','',1);

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `order_id` varchar(14) NOT NULL,
  `order_create_time` datetime NOT NULL,
  `order_deliver_fee` float NOT NULL,
  `order_lock_member` varchar(20) DEFAULT NULL,
  `order_note` varchar(100) DEFAULT NULL,
  `order_payable_fee` float NOT NULL,
  `order_payment_state` bit(1) NOT NULL,
  `order_payment_way` varchar(20) NOT NULL,
  `order_product_total_price` float NOT NULL,
  `order_state` varchar(16) NOT NULL,
  `order_total_price` float NOT NULL,
  `order_update_time` datetime NOT NULL,
  `order_create_by` varchar(255) NOT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `deliver_id` int(11) DEFAULT NULL,
  `order_update_by` varchar(255) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_glkpauoyk4vpm0eeqvg7n2utx` (`order_create_by`),
  KEY `FK_df4brxpo8wjasr4n0e17jp616` (`contact_id`),
  KEY `FK_drc8eoj32mnownsfd89ywse7b` (`deliver_id`),
  KEY `FK_8sov79a2248japcaunr084bvc` (`order_update_by`),
  CONSTRAINT `FK_8sov79a2248japcaunr084bvc` FOREIGN KEY (`order_update_by`) REFERENCES `t_member` (`member_account`),
  CONSTRAINT `FK_df4brxpo8wjasr4n0e17jp616` FOREIGN KEY (`contact_id`) REFERENCES `t_order_contact_info` (`id`),
  CONSTRAINT `FK_drc8eoj32mnownsfd89ywse7b` FOREIGN KEY (`deliver_id`) REFERENCES `t_order_deliver_info` (`id`),
  CONSTRAINT `FK_glkpauoyk4vpm0eeqvg7n2utx` FOREIGN KEY (`order_create_by`) REFERENCES `t_member` (`member_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

/*Table structure for table `t_order_contact_info` */

DROP TABLE IF EXISTS `t_order_contact_info`;

CREATE TABLE `t_order_contact_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_buyer_name` varchar(25) NOT NULL,
  `order_buyer_address_city` varchar(255) DEFAULT NULL,
  `order_buyer_address_country` varchar(255) DEFAULT NULL,
  `order_buyer_address_detail` varchar(255) DEFAULT NULL,
  `order_buyer_address_district` varchar(255) DEFAULT NULL,
  `order_buyer_email` varchar(40) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `order_buyer_mobile` varchar(11) DEFAULT NULL,
  `order_buyer_postal_code` varchar(6) DEFAULT NULL,
  `order_buyer_address_province` varchar(255) DEFAULT NULL,
  `order_buyer_tel` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order_contact_info` */

/*Table structure for table `t_order_deliver_info` */

DROP TABLE IF EXISTS `t_order_deliver_info`;

CREATE TABLE `t_order_deliver_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deliver_address_city` varchar(255) DEFAULT NULL,
  `deliver_address_country` varchar(255) DEFAULT NULL,
  `deliver_way` varchar(23) NOT NULL,
  `deliver_address_detail` varchar(255) DEFAULT NULL,
  `deliver_address_district` varchar(255) DEFAULT NULL,
  `deliver_email` varchar(20) DEFAULT NULL,
  `gender` varchar(5) NOT NULL,
  `deliver_mobile` varchar(11) DEFAULT NULL,
  `deliver_postal_code` varchar(6) DEFAULT NULL,
  `deliver_address_province` varchar(255) DEFAULT NULL,
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
  `item_product_image_url` varchar(255) NOT NULL,
  `item_product_id` int(11) NOT NULL,
  `item_product_name` varchar(50) NOT NULL,
  `item_product_price` float NOT NULL,
  `item_product_style_id` int(11) NOT NULL,
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
  `member_id` varchar(255) NOT NULL,
  `order_id` varchar(14) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nac95qxnsrwvb5eqscb4qugl3` (`member_id`),
  KEY `FK_h2gpjjxv9a3wc79uouoypyejr` (`order_id`),
  CONSTRAINT `FK_h2gpjjxv9a3wc79uouoypyejr` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`),
  CONSTRAINT `FK_nac95qxnsrwvb5eqscb4qugl3` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_order_message` */

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_base_price` float DEFAULT NULL,
  `product_buy_explain` varchar(255) DEFAULT NULL,
  `product_click_count` int(11) DEFAULT NULL,
  `product_code` varchar(255) DEFAULT NULL,
  `product_commend` bit(1) DEFAULT NULL,
  `product_create_time` datetime DEFAULT NULL,
  `product_description` varchar(255) DEFAULT NULL,
  `product_market_price` float DEFAULT NULL,
  `product_model` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_sell_count` int(11) DEFAULT NULL,
  `product_sell_price` float DEFAULT NULL,
  `product_type_code` varchar(255) DEFAULT NULL,
  `product_update_time` datetime DEFAULT NULL,
  `product_visible` bit(1) DEFAULT NULL,
  `product_weight` int(11) DEFAULT NULL,
  `product_brand_id` int(11) NOT NULL,
  `product_channel_id` int(11) NOT NULL,
  `product_create_by` varchar(255) NOT NULL,
  `product_type_id` int(11) NOT NULL,
  `product_update_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2bajldf6wfl0lkhyj8mi9k61m` (`product_brand_id`),
  KEY `FK_56olua72fx9rxddhc3pd6t58u` (`product_channel_id`),
  KEY `FK_pd6fy4ikt586122b4eakck1r1` (`product_create_by`),
  KEY `FK_nps4xac4nqhms2dxq2jii4lc4` (`product_type_id`),
  KEY `FK_g7vdv7ky9bkromll3h7mirxgk` (`product_update_by`),
  CONSTRAINT `FK_2bajldf6wfl0lkhyj8mi9k61m` FOREIGN KEY (`product_brand_id`) REFERENCES `t_brand` (`id`),
  CONSTRAINT `FK_56olua72fx9rxddhc3pd6t58u` FOREIGN KEY (`product_channel_id`) REFERENCES `t_channel` (`id`),
  CONSTRAINT `FK_g7vdv7ky9bkromll3h7mirxgk` FOREIGN KEY (`product_update_by`) REFERENCES `t_member` (`member_account`),
  CONSTRAINT `FK_nps4xac4nqhms2dxq2jii4lc4` FOREIGN KEY (`product_type_id`) REFERENCES `t_product_type` (`id`),
  CONSTRAINT `FK_pd6fy4ikt586122b4eakck1r1` FOREIGN KEY (`product_create_by`) REFERENCES `t_member` (`member_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_product` */

/*Table structure for table `t_product_style` */

DROP TABLE IF EXISTS `t_product_style`;

CREATE TABLE `t_product_style` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_create_time` datetime DEFAULT NULL,
  `product_image_url` varchar(160) NOT NULL,
  `product_style_name` varchar(32) NOT NULL,
  `product_update_time` datetime DEFAULT NULL,
  `product_style_visible` bit(1) NOT NULL,
  `product_create_by` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_update_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5v4ssjnsi0iei8skkbo9ym84w` (`product_create_by`),
  KEY `FK_d47aryt5a0hrklfe271dpw7s5` (`product_id`),
  KEY `FK_oh68xg0li8ukhi11f4d2gybr9` (`product_update_by`),
  CONSTRAINT `FK_5v4ssjnsi0iei8skkbo9ym84w` FOREIGN KEY (`product_create_by`) REFERENCES `t_member` (`member_account`),
  CONSTRAINT `FK_d47aryt5a0hrklfe271dpw7s5` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`),
  CONSTRAINT `FK_oh68xg0li8ukhi11f4d2gybr9` FOREIGN KEY (`product_update_by`) REFERENCES `t_member` (`member_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_product_style` */

/*Table structure for table `t_product_style_image` */

DROP TABLE IF EXISTS `t_product_style_image`;

CREATE TABLE `t_product_style_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_create_time` datetime DEFAULT NULL,
  `product_update_time` datetime DEFAULT NULL,
  `product_image_url` varchar(160) NOT NULL,
  `product_create_by` varchar(255) NOT NULL,
  `product_style_id` int(11) NOT NULL,
  `product_update_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_oxongo9gg8w55q32hqcw6ihvb` (`product_create_by`),
  KEY `FK_jy8g08g4y2rsrvkyql1l13vad` (`product_style_id`),
  KEY `FK_2vexqwa1mlytcfkpvj129wqb0` (`product_update_by`),
  CONSTRAINT `FK_2vexqwa1mlytcfkpvj129wqb0` FOREIGN KEY (`product_update_by`) REFERENCES `t_member` (`member_account`),
  CONSTRAINT `FK_jy8g08g4y2rsrvkyql1l13vad` FOREIGN KEY (`product_style_id`) REFERENCES `t_product_style` (`id`),
  CONSTRAINT `FK_oxongo9gg8w55q32hqcw6ihvb` FOREIGN KEY (`product_create_by`) REFERENCES `t_member` (`member_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_product_style_image` */

/*Table structure for table `t_product_type` */

DROP TABLE IF EXISTS `t_product_type`;

CREATE TABLE `t_product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_code` varchar(20) NOT NULL,
  `type_name` varchar(36) NOT NULL,
  `type_note` varchar(36) NOT NULL,
  `type_parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_aejs232tqamdnw311e1i0qjf4` (`type_parent_id`),
  CONSTRAINT `FK_aejs232tqamdnw311e1i0qjf4` FOREIGN KEY (`type_parent_id`) REFERENCES `t_product_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `t_product_type` (`id`, `type_code`, `type_name`, `type_note`, `type_parent_id`) values('1','1','其他','其他',NULL);

/*Data for the table `t_product_type` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
