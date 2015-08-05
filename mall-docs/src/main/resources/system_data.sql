-- 管理平台系统表 初始化数据
USE ${schema.name};

-- ----------------------------
-- Records of dict
-- ----------------------------
-- TRUNCATE TABLE `dict`;
INSERT INTO `dict` VALUES ('1', '正常', '0', 'user', '用户状态', '1', NULL, NULL);
INSERT INTO `dict` VALUES ('2', '禁用', '1', 'user', '用户状态', '2', NULL, NULL);
INSERT INTO `dict` VALUES ('4', '普通用户', '0', 'user', '用户类型', NULL, NULL, NULL);
INSERT INTO `dict` VALUES ('5', '会员用户', '1', 'user', '用户类型', NULL, NULL, NULL);

-- ----------------------------
-- Records of permission
-- ----------------------------
-- TRUNCATE TABLE `permission`;
INSERT INTO `permission` VALUES ('1', NULL, '系统管理', 'F', '1', '', '', 'icon-standard-cog', '', '');
INSERT INTO `permission`
VALUES ('2', '1', '角色管理', 'F', '3', 'system/role', '', 'icon-hamburg-my-account', 'closed', '');
INSERT INTO `permission` VALUES ('3', '1', '用户管理', 'F', '2', 'system/user', '', 'icon-hamburg-user', 'closed', '');
INSERT INTO `permission` VALUES ('4', '2', '添加', 'O', NULL, '', 'sys:role:add', '', '', '角色添加');
INSERT INTO `permission` VALUES ('5', '2', '删除', 'O', NULL, '', 'sys:role:delete', '', '', '角色删除');
INSERT INTO `permission` VALUES ('6', '2', '修改', 'O', NULL, '', 'sys:role:update', '', '', '角色修改');
INSERT INTO `permission` VALUES ('7', '3', '添加', 'O', NULL, '', 'sys:user:add', '', '', '用户添加');
INSERT INTO `permission` VALUES ('8', '3', '删除', 'O', NULL, '', 'sys:user:delete', '', '', '用户删除');
INSERT INTO `permission`
VALUES ('12', '1', '权限管理', 'F', '5', 'system/permission', '', 'icon-hamburg-login', 'closed', '');
INSERT INTO `permission` VALUES ('14', '15', '数据源监控', 'F', '6', 'druid', '', 'icon-hamburg-database', '', '');
INSERT INTO `permission` VALUES ('15', NULL, '系统监控', 'F', '5', '', '', 'icon-hamburg-graphic', '', '');
INSERT INTO `permission` VALUES ('16', '3', '修改', 'O', NULL, '', 'sys:user:update', '', '', '用户修改');
INSERT INTO `permission` VALUES ('20', '15', '日志管理', 'F', '7', 'system/log', '', 'icon-hamburg-archives', '', '');
INSERT INTO `permission` VALUES ('25', '12', '添加', 'O', NULL, '', 'sys:perm:add', '', '', '菜单添加');
INSERT INTO `permission` VALUES ('26', '12', '修改', 'O', NULL, '', 'sys:perm:update', '', '', '菜单修改');
INSERT INTO `permission` VALUES ('27', '12', '删除', 'O', NULL, '', 'sys:perm:delete', '', '', '菜单删除');
INSERT INTO `permission` VALUES ('28', '2', '查看', 'O', NULL, '', 'sys:role:view', '', '', '角色查看');
INSERT INTO `permission` VALUES ('29', '3', '查看', 'O', NULL, '', 'sys:user:view', '', NULL, '用户查看');
INSERT INTO `permission` VALUES ('30', '12', '查看', 'O', NULL, '', 'sys:perm:view', '', NULL, '权限查看');
INSERT INTO `permission` VALUES ('31', '20', '删除', 'O', NULL, '', 'sys:log:delete', '', NULL, '删除日志');
INSERT INTO `permission` VALUES ('32', '20', '导出excel', 'O', NULL, '', 'sys:log:exportExcel', '', NULL, '导出日志excel');
INSERT INTO `permission` VALUES ('33', '3', '查看用户角色', 'O', NULL, '', 'sys:user:roleView', '', NULL, '查看用户角色');
INSERT INTO `permission` VALUES ('34', '2', '保存授权', 'O', NULL, '', 'sys:role:permUpd', '', NULL, '保存修改的角色权限');
INSERT INTO `permission` VALUES ('35', '3', '修改用户角色', 'O', NULL, '', 'sys:user:roleUpd', '', NULL, '修改用户拥有的角色');
INSERT INTO `permission` VALUES ('36', '2', '查看角色权限', 'O', NULL, '', 'sys:role:permView', '', NULL, '查看角色拥有的权限');
INSERT INTO `permission`
VALUES ('37', '15', '定时任务管理', 'F', NULL, 'system/scheduleJob', '', 'icon-hamburg-full-time', NULL, '定时任务管理，支持集群');
INSERT INTO `permission`
VALUES ('38', '15', 'cron表达式生成', 'F', NULL, 'system/scheduleJob/quartzCron', '', 'icon-hamburg-future', NULL, '');
INSERT INTO `permission`
VALUES ('39', '1', '菜单管理', 'F', '4', 'system/permission/menu', '', 'icon-hamburg-old-versions', NULL, '');
INSERT INTO `permission`
VALUES ('40', '1', '字典管理', 'F', '6', 'system/dict', NULL, 'icon-hamburg-address', NULL, '数据字典管理');
INSERT INTO `permission` VALUES ('45', '39', '修改', 'O', NULL, '', 'sys:perm:update', NULL, NULL, '菜单管理');
INSERT INTO `permission` VALUES ('58', '39', '添加', 'O', NULL, '', 'sys:perm:add', NULL, NULL, '菜单管理');
INSERT INTO `permission` VALUES ('59', '39', '删除', 'O', NULL, '', 'sys:perm:delte', NULL, NULL, '菜单管理');
INSERT INTO `permission` VALUES ('61', '40', '添加', 'O', NULL, '', 'sys:dict:add', NULL, NULL, '字典管理');
INSERT INTO `permission` VALUES ('62', '40', '删除', 'O', NULL, '', 'sys:dict:delete', NULL, NULL, '字典管理');
INSERT INTO `permission` VALUES ('63', '40', '修改', 'O', NULL, '', 'sys:dict:update', NULL, NULL, '字典管理');
INSERT INTO `permission` VALUES ('68', '20', '查看', 'O', NULL, '', 'sys:log:view', NULL, NULL, '查看日志');
INSERT INTO `permission` VALUES ('69', '40', '查看', 'O', NULL, '', 'sys:dict:view', NULL, NULL, '字典管理');
INSERT INTO `permission` VALUES ('70', '39', '查看', 'O', NULL, '', 'sys:perm:menu:view', NULL, NULL, '菜单管理');
INSERT INTO `permission`
VALUES ('74', '1', '区域信息', 'F', '7', 'system/area', NULL, 'icon-hamburg-world', NULL, '管理行政区划');
INSERT INTO `permission`
VALUES ('75', '1', '机构管理', 'F', '8', 'system/organization', NULL, 'icon-cologne-home', NULL, '组织机构管理');
INSERT INTO `permission` VALUES ('76', '3', '查看用户机构', 'O', NULL, '', 'sys:user:orgView', NULL, NULL, '查看用户机构');
INSERT INTO `permission` VALUES ('77', '3', '修改用户机构', 'O', NULL, '', 'sys:user:orgUpd', NULL, NULL, '修改用户所在的机构');

INSERT INTO `permission` VALUES ('78', NULL, '会员管理', 'F', NULL, '', '', NULL, '', '会员管理');
INSERT INTO `permission` VALUES ('79', '78', '会员查询', 'F', '1', 'customer/index', '', '', '', '会员查询');

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', 'admin', 'admin', '1', NULL);
INSERT INTO `role` VALUES ('2', 'guest', 'guest', 'guest', '2', NULL);
INSERT INTO `role` VALUES ('3', 'test', 'test', 'test', '3', NULL);

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '1', '2');
INSERT INTO `role_permission` VALUES ('3', '1', '3');
INSERT INTO `role_permission` VALUES ('4', '1', '4');
INSERT INTO `role_permission` VALUES ('5', '1', '5');
INSERT INTO `role_permission` VALUES ('6', '1', '6');
INSERT INTO `role_permission` VALUES ('7', '1', '7');
INSERT INTO `role_permission` VALUES ('8', '1', '8');
INSERT INTO `role_permission` VALUES ('9', '1', '9');
INSERT INTO `role_permission` VALUES ('10', '1', '10');
INSERT INTO `role_permission` VALUES ('11', '1', '11');
INSERT INTO `role_permission` VALUES ('12', '1', '12');
INSERT INTO `role_permission` VALUES ('13', '1', '13');
INSERT INTO `role_permission` VALUES ('14', '1', '14');
INSERT INTO `role_permission` VALUES ('15', '1', '15');
INSERT INTO `role_permission` VALUES ('16', '1', '16');
INSERT INTO `role_permission` VALUES ('17', '1', '17');
INSERT INTO `role_permission` VALUES ('18', '1', '18');
INSERT INTO `role_permission` VALUES ('19', '1', '19');
INSERT INTO `role_permission` VALUES ('20', '1', '20');
INSERT INTO `role_permission` VALUES ('21', '1', '21');
INSERT INTO `role_permission` VALUES ('22', '1', '22');
INSERT INTO `role_permission` VALUES ('23', '1', '23');
INSERT INTO `role_permission` VALUES ('24', '1', '24');
INSERT INTO `role_permission` VALUES ('25', '1', '25');
INSERT INTO `role_permission` VALUES ('26', '1', '26');
INSERT INTO `role_permission` VALUES ('27', '1', '27');
INSERT INTO `role_permission` VALUES ('28', '1', '28');
INSERT INTO `role_permission` VALUES ('29', '1', '29');
INSERT INTO `role_permission` VALUES ('30', '1', '30');
INSERT INTO `role_permission` VALUES ('31', '1', '31');
INSERT INTO `role_permission` VALUES ('32', '1', '32');
INSERT INTO `role_permission` VALUES ('33', '1', '33');
INSERT INTO `role_permission` VALUES ('34', '1', '34');
INSERT INTO `role_permission` VALUES ('35', '1', '35');
INSERT INTO `role_permission` VALUES ('36', '1', '36');
INSERT INTO `role_permission` VALUES ('37', '1', '37');
INSERT INTO `role_permission` VALUES ('38', '1', '38');
INSERT INTO `role_permission` VALUES ('39', '1', '39');
INSERT INTO `role_permission` VALUES ('40', '1', '40');
INSERT INTO `role_permission` VALUES ('41', '1', '41');
INSERT INTO `role_permission` VALUES ('42', '1', '42');
INSERT INTO `role_permission` VALUES ('43', '1', '43');
INSERT INTO `role_permission` VALUES ('44', '1', '44');
INSERT INTO `role_permission` VALUES ('45', '1', '45');
INSERT INTO `role_permission` VALUES ('46', '1', '46');
INSERT INTO `role_permission` VALUES ('47', '1', '47');
INSERT INTO `role_permission` VALUES ('48', '1', '48');
INSERT INTO `role_permission` VALUES ('49', '1', '49');
INSERT INTO `role_permission` VALUES ('50', '1', '50');
INSERT INTO `role_permission` VALUES ('51', '1', '51');
INSERT INTO `role_permission` VALUES ('52', '1', '52');
INSERT INTO `role_permission` VALUES ('53', '1', '53');
INSERT INTO `role_permission` VALUES ('54', '1', '54');
INSERT INTO `role_permission` VALUES ('55', '1', '55');
INSERT INTO `role_permission` VALUES ('56', '1', '56');
INSERT INTO `role_permission` VALUES ('57', '1', '57');
INSERT INTO `role_permission` VALUES ('58', '1', '58');
INSERT INTO `role_permission` VALUES ('59', '1', '59');
INSERT INTO `role_permission` VALUES ('60', '1', '60');
INSERT INTO `role_permission` VALUES ('61', '1', '61');
INSERT INTO `role_permission` VALUES ('62', '1', '62');
INSERT INTO `role_permission` VALUES ('63', '1', '63');
INSERT INTO `role_permission` VALUES ('64', '1', '64');
INSERT INTO `role_permission` VALUES ('65', '1', '65');
INSERT INTO `role_permission` VALUES ('66', '1', '66');
INSERT INTO `role_permission` VALUES ('67', '1', '67');
INSERT INTO `role_permission` VALUES ('68', '1', '68');
INSERT INTO `role_permission` VALUES ('69', '1', '69');
INSERT INTO `role_permission` VALUES ('70', '1', '70');
INSERT INTO `role_permission` VALUES ('71', '1', '71');
INSERT INTO `role_permission` VALUES ('72', '1', '72');
INSERT INTO `role_permission` VALUES ('73', '1', '73');
INSERT INTO `role_permission` VALUES ('74', '1', '74');
INSERT INTO `role_permission` VALUES ('75', '1', '75');
INSERT INTO `role_permission` VALUES ('76', '1', '76');
INSERT INTO `role_permission` VALUES ('77', '1', '77');
INSERT INTO `role_permission` VALUES ('78', '1', '78');
INSERT INTO `role_permission` VALUES ('79', '1', '79');
-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES
  ('1', 'admin', 'admin', '159ae5f48f14e89f3f9f54dc995f1f276d472b54', '3d06a5c14d010804', '2014-03-16 22:44:39', '1',
   '779205344@qq.com', '123456789', 'aaa', '2014-03-20 14:38:57', '0', NULL, '131', '2015-01-22 15:51:46',
   '2015-01-22 16:49:13', NULL);

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');

-- ----------------------------
-- Records of area_info
-- ----------------------------
INSERT INTO `area_info` VALUES ('1', '100000', '中国', NULL, '1');

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('1', '总部', NULL, '总部', '1', '1', '000000', NULL);

-- ----------------------------
-- Records of user_org
-- ----------------------------
INSERT INTO `user_org` VALUES ('8', '6', '1');

-- 菜单ID从：78开始
-- 系统菜单数据添加在此处
