CREATE TABLE `t_employee` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `is_deleted` tinyint(3) unsigned DEFAULT '0',
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(45) DEFAULT '',
  `password` varchar(100) DEFAULT '',
  `salt` varchar(50) DEFAULT '',
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='后台账号表';

CREATE TABLE `t_sys_employee_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `is_deleted` tinyint(3) unsigned DEFAULT '0',
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `employee_id` bigint(20) DEFAULT '0',
  `role_id` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='账号，角色（职位）关联表';

CREATE TABLE `t_sys_permission` (
  `id` bigint(20) unsigned NOT NULL,
  `is_deleted` tinyint(3) unsigned DEFAULT '0',
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `sref` varchar(45) DEFAULT '' COMMENT '菜单地址',
  `parent_id` bigint(20) DEFAULT '0',
  `text` varchar(45) DEFAULT '' COMMENT '菜单名称，权限名称',
  `icon` varchar(45) DEFAULT '' COMMENT '图标',
  `label` varchar(45) DEFAULT '' COMMENT '图标',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表，菜单表';

CREATE TABLE `t_sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `is_deleted` tinyint(3) unsigned DEFAULT '0',
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `role_name` varchar(45) DEFAULT '' COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='角色表，职位表';

CREATE TABLE `t_sys_role_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `is_deleted` tinyint(3) unsigned DEFAULT '0',
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `role_id` bigint(20) DEFAULT '0',
  `permission_id` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='角色（职位），权限关联表';

插入数据，初始账号
INSERT INTO `personal`.`t_employee`
(`name`,`password`,`salt`)
VALUES
('shanejim', '13320B2BDFAAEB001BF8152D2BC53B7FCE7AD12CA0917A03C7266C4A079B3FA3', '37ed2c4a-82e0-4b1b-b3ba-8dca379d5676');

SELECT * FROM personal.t_sys_permission;
INSERT INTO `personal`.`t_sys_permission`
(`id`,`is_deleted`,`add_time`,`modified_time`,`sref`,`parent_id`,`text`,`icon`,`label`,`sort`)
VALUES
('1', '0', '2018-11-02 13:06:52', '2018-11-02 13:06:52', '#', '0', '人员管理', 'icon-users', '', '0'
'2', '0', '2018-11-02 13:07:47', '2018-11-02 13:07:47', 'app.employee', '1', '员工管理', '', '', '0'
'3', '0', '2018-11-02 13:07:56', '2018-11-02 13:07:56', 'app.sysRole', '1', '职位管理', '', '', '0'
);