-- 2024-10-19
CREATE
DATABASE `ss_login_demo` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
-- 用户信息表
CREATE TABLE `ss_login_demo`.`sys_user`
(
    `user_id`  BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(100) NULL DEFAULT NULL COMMENT '用户账号' COLLATE 'utf8_general_ci',
    `nickname` VARCHAR(100) NULL DEFAULT NULL COMMENT '用户昵称' COLLATE 'utf8_general_ci',
    `password` VARCHAR(100) NULL DEFAULT NULL COMMENT '用户密码' COLLATE 'utf8_general_ci',
    `status`   TINYINT(1)   NULL DEFAULT '1' COMMENT '用户状态 0=停用 1=正常',
    `del_flag` TINYINT(1)   NULL DEFAULT '1' COMMENT '状态 0=删除 1=正常',
    PRIMARY KEY (`user_id`) USING BTREE
) COLLATE = 'utf8_general_ci'
  ENGINE = InnoDB
  ROW_FORMAT = DYNAMIC
  AUTO_INCREMENT = 1;

INSERT INTO `ss_login_demo`.`sys_user` (`user_id`, `username`, `nickname`, `password`, `status`, `del_flag`)
VALUES (1, 'admin', NULL, '$2a$10$kZlKYFje9Dcn9Aav5VTOFOQh7kpudpqiP39vBAw3r7FlMJY4h/Rme', 1, 1);

INSERT INTO `ss_login_demo`.`sys_user` (`user_id`, `username`, `nickname`, `password`, `status`, `del_flag`)
VALUES (2, 'test', NULL, '$2a$10$kZlKYFje9Dcn9Aav5VTOFOQh7kpudpqiP39vBAw3r7FlMJY4h/Rme', 1, 1);


-- 用户角色信息表
CREATE TABLE `ss_login_demo`.`sys_role`
(
    `role_id`   BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name` VARCHAR(30)  NOT NULL COMMENT '角色名称' COLLATE 'utf8_general_ci',
    `role_key`  VARCHAR(100) NOT NULL COMMENT '角色权限字符串' COLLATE 'utf8_general_ci',
    PRIMARY KEY (`role_id`) USING BTREE
) COMMENT ='角色信息表'
    COLLATE = 'utf8_general_ci'
    ENGINE = InnoDB
    ROW_FORMAT = DYNAMIC
    AUTO_INCREMENT = 1;
INSERT INTO `ss_login_demo`.`sys_role` (`role_id`, `role_name`, `role_key`)
VALUES (1, '超级管理员', 'admin');
INSERT INTO `ss_login_demo`.`sys_role` (`role_id`, `role_name`, `role_key`)
VALUES (2, '普通用户', 'common');

-- 用户角色关联表
CREATE TABLE `ss_login_demo`.`sys_user_role`
(
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) COMMENT ='用户和角色关联表'
    COLLATE = 'utf8_general_ci'
    ENGINE = InnoDB
    ROW_FORMAT = DYNAMIC
;
INSERT INTO `ss_login_demo`.`sys_user_role` (`user_id`, `role_id`)
VALUES (2, 2);


-- 角色权限信息表
CREATE TABLE `ss_login_demo`.`sys_menu`
(
    `menu_id` BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `perms`   VARCHAR(100) NULL DEFAULT NULL COMMENT '权限标识' COLLATE 'utf8_general_ci',
    PRIMARY KEY (`menu_id`) USING BTREE
) COMMENT ='菜单权限表'
    COLLATE = 'utf8_general_ci'
    ENGINE = InnoDB
    ROW_FORMAT = DYNAMIC
    AUTO_INCREMENT = 1001;

INSERT INTO `ss_login_demo`.`sys_menu` (`menu_id`, `perms`)
VALUES (1, 'system:demo:list');

-- 角色权限关联表
CREATE TABLE `ss_login_demo`.`sys_role_menu`
(
    `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
    `menu_id` BIGINT(20) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) COMMENT ='角色和菜单关联表'
    COLLATE = 'utf8_general_ci'
    ENGINE = InnoDB
    ROW_FORMAT = DYNAMIC
;
INSERT INTO `ss_login_demo`.`sys_role_menu` (`role_id`, `menu_id`)
VALUES (2, 1);


