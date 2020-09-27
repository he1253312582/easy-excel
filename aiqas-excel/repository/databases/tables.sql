CREATE TABLE `sys_menu` (
  `id` varchar(32) NOT NULL COMMENT '菜单Id',
  `parent_id` varchar(32) NOT NULL COMMENT '父级菜单ID',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(200) DEFAULT NULL COMMENT '菜单图标',
  `menu_level` varchar(32) DEFAULT NULL COMMENT '菜单链接地址',
  `sort` bigint(20) DEFAULT NULL COMMENT '菜单排序',
  `href` varchar(200) DEFAULT NULL COMMENT '链接地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单信息表';

CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL COMMENT '角色ID',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) DEFAULT NULL COMMENT '角色说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

CREATE TABLE `sys_role_menu` (
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单ID',
  KEY `role_id` (`role_id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '用户ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `pass_word` varchar(200) DEFAULT NULL COMMENT '用户密码',
  `account_Status` varchar(32) DEFAULT NULL COMMENT '账号状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

CREATE TABLE `attach_info` (
  `id` varchar(32) NOT NULL COMMENT '附件表主键',
  `attach_name` varchar(128) DEFAULT NULL COMMENT '文件名称',
  `attach_type` varchar(32) DEFAULT NULL COMMENT '文件类型',
  `attach_size` bigint(64) DEFAULT NULL COMMENT '文件大小',
  `abs_url` varchar(532) DEFAULT NULL COMMENT '文件绝对路径',
  `rel_url` varchar(532) DEFAULT NULL COMMENT '文件相对路径',
  `business_id` varchar(32) DEFAULT NULL COMMENT '业务Id',
  `creat_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详细信息表';

