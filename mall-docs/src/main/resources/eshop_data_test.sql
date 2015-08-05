-- 陶瓷网业务逻辑表 测试数据
USE ${schema.name};

INSERT INTO `es_customer`
VALUES (NULL, 'test01', '123456', '654321', '测试01', 00000000, 'test01@test.com', '12345678910', NULL, 'Y');

INSERT INTO `es_manufacturer` VALUES (1, '测试厂商A');
INSERT INTO `es_manufacturer` VALUES (2, '测试厂商B');

INSERT INTO `es_brand` VALUES (1, '测试品牌A1', '/img/logo1.jpg', 1);
INSERT INTO `es_brand` VALUES (2, '测试品牌A2', '/img/logo2.jpg', 1);
INSERT INTO `es_brand` VALUES (3, '测试品牌B1', '/img/logo3.jpg', 2);
INSERT INTO `es_brand` VALUES (4, '测试品牌B2', '/img/logo4.jpg', 2);
INSERT INTO `es_brand` VALUES (5, '测试品牌B3', '/img/logo5.jpg', 2);

INSERT INTO `es_category` VALUES (1, '测试类目甲', NULL, 0);
INSERT INTO `es_category` VALUES (2, '测试类目甲A', 1, 0);
INSERT INTO `es_category` VALUES (3, '测试类目甲A1', 2, 1);
INSERT INTO `es_category` VALUES (4, '测试类目甲A2', 2, 2);
INSERT INTO `es_category` VALUES (5, '测试类目乙', NULL, 0);
INSERT INTO `es_category` VALUES (6, '测试类目乙B', 5, 0);
INSERT INTO `es_category` VALUES (7, '测试类目乙B1', 6, 1);
INSERT INTO `es_category` VALUES (8, '测试类目乙B2', 6, 2);
INSERT INTO `es_category` VALUES (9, '测试类目乙B3', 6, 3);

INSERT INTO `es_product`
VALUES
  (NULL, 1, '测试产品1', '测试产品1描述', 9.88, 5.08, 40.0, 99.8, 5.9, 6.5, 'CODE001', 'A', '红', '规格', '片', 'g', now(), 100, 'N',
   'N', 'N', 'N', 'N', 'N', 'N', 'N');
INSERT INTO `es_product`
VALUES
  (NULL, 1, '测试产品2', '测试产品2描述', 9.88, 5.08, 40.0, 99.8, 5.9, 6.5, 'CODE002', 'B', '橙', '规格', '片', 'g', now(), 100, 'N',
   'N', 'N', 'N', 'N', 'N', 'N', 'N');
INSERT INTO `es_product`
VALUES
  (NULL, 2, '测试产品3', '测试产品3描述', 9.88, 5.08, 40.0, 99.8, 5.9, 6.5, 'CODE003', 'C', '黄', '规格', '片', 'g', now(), 100, 'N',
   'N', 'N', 'N', 'N', 'N', 'N', 'N');
INSERT INTO `es_product`
VALUES
  (NULL, 2, '测试产品4', '测试产品4描述', 9.88, 5.08, 40.0, 99.8, 5.9, 6.5, 'CODE004', 'B', '橙', '规格', '片', 'g', now(), 100, 'N',
   'N', 'N', 'N', 'N', 'N', 'N', 'N');
INSERT INTO `es_product`
VALUES
  (NULL, 3, '测试产品5', '测试产品5描述', 9.88, 5.08, 40.0, 99.8, 5.9, 6.5, 'CODE005', 'A', '绿', '规格', '片', 'g', now(), 100, 'N',
   'N', 'N', 'N', 'N', 'N', 'N', 'N');
INSERT INTO `es_product`
VALUES
  (NULL, 3, '测试产品6', '测试产品6描述', 9.88, 5.08, 40.0, 99.8, 5.9, 6.5, 'CODE006', 'C', '青', '规格', '片', 'g', now(), 100, 'N',
   'N', 'N', 'N', 'N', 'N', 'N', 'N');
INSERT INTO `es_product`
VALUES
  (NULL, 4, '测试产品7', '测试产品7描述', 9.88, 5.08, 40.0, 99.8, 5.9, 6.5, 'CODE007', 'D', '紫', '规格', '片', 'g', now(), 100, 'N',
   'N', 'N', 'N', 'N', 'N', 'N', 'N');
INSERT INTO `es_product`
VALUES
  (NULL, 4, '测试产品8', '测试产品8描述', 9.88, 5.08, 40.0, 99.8, 5.9, 6.5, 'CODE008', 'E', '紫', '规格', '片', 'g', now(), 100, 'N',
   'N', 'N', 'N', 'N', 'N', 'N', 'N');
INSERT INTO `es_product`
VALUES
  (NULL, 5, '测试产品9', '测试产品9描述', 9.88, 5.08, 40.0, 99.8, 5.9, 6.5, 'CODE009', 'S', '黄金', '规格', '片', 'g', now(), 10, 'N',
   'N', 'N', 'N', 'N', 'N', 'N', 'N');
INSERT INTO `es_product`
VALUES
  (NULL, 5, '测试产品10', '测试产品10描述', 9.88, 5.08, 40.0, 99.8, 5.9, 6.5, 'CODE010', 'S', '白银', '规格', '片', 'g', now(), 10,
   'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N');

