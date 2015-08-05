-- 初始化数据库用户及实例

DROP DATABASE IF EXISTS ${schema.name};
DROP USER ${schema.user};

CREATE USER ${schema.user};
CREATE DATABASE ${schema.name} DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
-- grant 权限1,权限2,…权限n on 数据库名称.表名称 to 用户名@用户地址 identified by ‘连接口令’;
GRANT ALL PRIVILEGES ON ${schema.name}.* TO '${schema.user}'@'localhost' IDENTIFIED BY '${schema.password}';
FLUSH PRIVILEGES;

SELECT Now();

USE ${schema.name}