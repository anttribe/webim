/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : webim

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-08-07 18:10:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_webim_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_webim_user`;
CREATE TABLE `t_webim_user` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id，唯一标志',
  `username` varchar(256) NOT NULL COMMENT '用户名称',
  `password` varchar(128) DEFAULT NULL COMMENT '用户密码',
  `nickname` varchar(256) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(64) DEFAULT NULL COMMENT '用户注册邮箱',
  `mobile` varchar(32) DEFAULT NULL COMMENT '用户联系电话',
  `avatar` varchar(256) DEFAULT NULL COMMENT '用户头像',
  `signature` varchar(256) DEFAULT NULL COMMENT '个人签名',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '数据更新时间',
  `latest_login_time` timestamp NULL DEFAULT NULL COMMENT '用户最后一次登录时间',
  `available` varchar(8) DEFAULT '1' COMMENT '是否启用 0:禁用; 1:启用',
  `hx_username` varchar(256) NOT NULL COMMENT '环信用户名称',
  `hx_password` varchar(128) NOT NULL COMMENT '环信用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_webim_user
-- ----------------------------
INSERT INTO `t_webim_user`(`id`, `username`, `password`, `nickname`, `email`, `mobile`, `avatar`, `signature`, `create_time`, `update_time`, `latest_login_time`, `available`, `hx_username`, `hx_password`) VALUES ('1', 'anttribe', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, null, null, null, null, '1', 'anttribe', '123456');
INSERT INTO `t_webim_user`(`id`, `username`, `password`, `nickname`, `email`, `mobile`, `avatar`, `signature`, `create_time`, `update_time`, `latest_login_time`, `available`, `hx_username`, `hx_password`) VALUES ('2', 'test', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, null, null, null, null, '1', 'test', '123456');

-- ----------------------------
-- Table structure for `t_webim_message`
-- ----------------------------
DROP TABLE IF EXISTS `t_webim_message`;
CREATE TABLE `t_webim_message` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `mfrom` varchar(256) NOT NULL,
  `mto` varchar(256) NOT NULL,
  `chat_type` varchar(32) DEFAULT NULL,
  `mtimestamp` longtext,
  `msg_bodies` varchar(2048) DEFAULT NULL,
  `ext_params` varchar(512) DEFAULT NULL,
  `hx_msg_id` varchar(128) DEFAULT NULL,
  `create_month` varchar(8),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_webim_messagebody`
-- ----------------------------
DROP TABLE IF EXISTS `t_webim_messagebody`;
CREATE TABLE `t_webim_messagebody` (
  `id` varchar(32) NOT NULL,
  `message_id` varchar(32) NOT NULL,
  `message_type` varchar(32) DEFAULT NULL,
  `msg` varchar(1024) DEFAULT NULL,
  `filename` varchar(256) DEFAULT NULL,
  `hx_file_url` varchar(1024) DEFAULT NULL,
  `duration` longtext,
  `file_length` longtext,
  `secret` varchar(256) DEFAULT NULL,
  `hx_thumb_url` varchar(1024) DEFAULT NULL,
  `thumb_secret` varchar(256) DEFAULT NULL,
  `filepath` varchar(512) DEFAULT NULL,
  `thumbpath` varchar(512) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  `lat` varchar(32) DEFAULT NULL,
  `lng` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
