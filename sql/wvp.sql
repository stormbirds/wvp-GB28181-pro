/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : wvp

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 29/08/2022 02:54:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `firmware` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transport` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `streamMode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `online` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `registerTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `keepaliveTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `updateTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `port` int(0) NOT NULL,
  `expires` int(0) NOT NULL,
  `subscribeCycleForCatalog` int(0) NOT NULL,
  `hostAddress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `charset` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `subscribeCycleForMobilePosition` int(0) NULL DEFAULT NULL,
  `mobilePositionSubmissionInterval` int(0) NULL DEFAULT 5,
  `subscribeCycleForAlarm` int(0) NULL DEFAULT NULL,
  `ssrcCheck` int(0) NULL DEFAULT 0,
  `geoCoordSys` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `treeType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `device_deviceId_uindex`(`deviceId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES (58, '34020000001320000010', 'IP DOME', 'Hikvision', 'DS-2DF82ABCDW-XYZL/VWS', 'V5.5.12', 'UDP', 'UDP', '1', '2022-08-29 02:42:17', '2022-08-29 02:53:19', '192.168.50.64', '2022-08-16 02:56:56', '2022-08-29 02:53:19', 5060, 3600, 0, '192.168.50.64:5060', 'GB2312', 0, 5, 0, 1, 'WGS84', 'CivilCode');

-- ----------------------------
-- Table structure for device_alarm
-- ----------------------------
DROP TABLE IF EXISTS `device_alarm`;
CREATE TABLE `device_alarm`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `channelId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `alarmPriority` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `alarmMethod` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `alarmTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `alarmDescription` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `longitude` double NULL DEFAULT NULL,
  `latitude` double NULL DEFAULT NULL,
  `alarmType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_alarm
-- ----------------------------

-- ----------------------------
-- Table structure for device_channel
-- ----------------------------
DROP TABLE IF EXISTS `device_channel`;
CREATE TABLE `device_channel`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '通道编号',
  `channel_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '通道ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通道名称',
  `manufacture` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通道设备厂家',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通道设备型号',
  `owner` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通道设备归属',
  `civil_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '行政区域',
  `block` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '警区',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '安装地址',
  `parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '直接上级编号',
  `safety_way` int(0) NULL DEFAULT NULL COMMENT '信令安全模式(可选)缺省为0; 0:不采用;2:S/MIME 签名方式;3:S/\r\nMIME加密签名同时采用方式;4:数字摘要方式',
  `register_way` int(0) NULL DEFAULT 1 COMMENT '注册方式, 缺省为1, 1-IETF RFC3261, 2-基于口令的双向认证, 3-基于数字证书的双向认证\r\n\r\n允许值: 1, 2, 3',
  `cert_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `certifiable` int(0) NULL DEFAULT NULL,
  `err_code` int(0) NULL DEFAULT NULL COMMENT '错误码',
  `end_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推流结束时间',
  `secrecy` int(0) NULL DEFAULT 0 COMMENT '保密属性, 缺省为0, 0-不涉密, 1-涉密\r\n\r\n允许值: 0, 1',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备/区域/系统IP地址',
  `port` int(0) NULL DEFAULT NULL COMMENT '设备/区域/系统端口',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通道密码',
  `ptz_type` int(0) NULL DEFAULT NULL COMMENT '云台类型, 0 - 未知, 1 - 球机, 2 - 半球, 3 - 固定枪机, 4 - 遥控枪机\r\n\r\n默认值: 0\r\n\r\n允许值: 0, 1, 2, 3, 4',
  `status` int(0) NULL DEFAULT NULL COMMENT '在线状态\r\n\r\n允许值: 1-ON, 0-OFF',
  `longitude` double NULL DEFAULT 0 COMMENT '经度\r\n\r\n默认值: 0',
  `latitude` double NULL DEFAULT 0 COMMENT '纬度\r\n\r\n默认值: 0',
  `stream_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '直播流ID, 有值表示正在直播',
  `device_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备编号',
  `audio_enable` bit(1) NULL DEFAULT b'0' COMMENT '音频开关配置\r\n\r\n默认值: false',
  `create_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新时间',
  `sub_count` int(0) NULL DEFAULT 0 COMMENT '子节点数, SubCount > 0 表示该通道为子目录',
  `longitudeGcj02` double NULL DEFAULT NULL COMMENT '国测局经度',
  `latitudeGcj02` double NULL DEFAULT NULL COMMENT '国测局维度',
  `longitudeWgs84` double NULL DEFAULT NULL COMMENT 'WGS84经度',
  `latitudeWgs84` double NULL DEFAULT NULL COMMENT 'WGS84维度',
  `business_group_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务分组ID',
  `gps_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GPS时间',
  `channel` int(0) NULL DEFAULT NULL COMMENT '通道序号',
  `custom_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通道自定义名称',
  `custom_block` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义警区',
  `custom` bit(1) NULL DEFAULT b'0' COMMENT '是否自定义通道',
  `snap_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通道快照链接',
  `custom_manufacturer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通道自定义设备厂家',
  `custom_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通道自定义设备型号',
  `custom_civil_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义行政区域',
  `custom_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义安装地址',
  `firmware` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '固件版本',
  `custom_firmware` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义固件版本',
  `serial_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备序列号',
  `custom_serial_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义设备序列号',
  `custom_ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义设备/区域/系统IP地址',
  `custom_port` int(0) NULL DEFAULT NULL COMMENT '自定义设备/区域/系统端口',
  `parental` int(0) NULL DEFAULT 0 COMMENT '当为通道设备时, 是否有通道子设备, 1-有,0-没有\r\n\r\n允许值: 0, 1',
  `custom_parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义直接上级编号',
  `custom_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义在线状态',
  `custom_longitude` double NULL DEFAULT 0 COMMENT '自定义经度\r\n\r\n默认值: 0',
  `custom_latitude` double NULL DEFAULT 0 COMMENT '自定义纬度\r\n\r\n默认值: 0',
  `altitude` double NULL DEFAULT 0 COMMENT '海拔高度, m\r\n\r\n默认值: 0',
  `speed` double NULL DEFAULT 0 COMMENT '移动速度, km/h\r\n\r\n默认值: 0',
  `direction` double NULL DEFAULT 0 COMMENT '与正北方的顺时针夹角,取值范围 0~360\r\n\r\n默认值: 0',
  `custom_ptz_type` double NULL DEFAULT 0 COMMENT '自定义云台类型, 0 - 未知, 1 - 球机, 2 - 半球, 3 - 固定枪机, 4 - 遥控枪机\r\n\r\n默认值: 0\r\n\r\n允许值: 0, 1, 2, 3, 4',
  `battery_level` tinyint(0) NULL DEFAULT 0 COMMENT '电量\r\n\r\n默认值: 0',
  `signal_level` float NULL DEFAULT 0 COMMENT '信号强度\r\n\r\n默认值: 0',
  `download_speed` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下载倍速范围',
  `ondemand` bit(1) NULL DEFAULT b'1' COMMENT '按需直播配置\r\n\r\n默认值: true',
  `cloud_record` bit(1) NULL DEFAULT b'0' COMMENT '云端录像开关配置\r\n\r\n默认值: false',
  `num_outputs` int(0) NULL DEFAULT 0 COMMENT '直播在线人数',
  `shared` bit(1) NULL DEFAULT b'1' COMMENT '是否开启分享',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '通道描述信息',
  `custom_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义通道ID',
  `quality` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通道质量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `device_channel_id_uindex`(`id`) USING BTREE,
  UNIQUE INDEX `device_channel_pk`(`channel_id`, `device_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19554 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_channel
-- ----------------------------
INSERT INTO `device_channel` VALUES (19543, '34020000001320000011', 'IPdome', 'Hikvision', 'IP Camera', 'Owner', 'CivilCode', '', 'Address', '34020000001320000010', 0, 1, '', 0, 0, NULL, 0, '', 0, '', 0, 1, 109.0145488, 34.305162, NULL, '34020000001320000010', b'0', '', '', 0, 0, 0, 109.0145488, 34.305162, '', '2022-08-28 10:39:13', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, b'1', b'0', 0, b'1', '', NULL, NULL);
INSERT INTO `device_channel` VALUES (19545, '34020000001340000011', '', 'Hikvision', 'AlarmIn', 'Owner', 'CivilCode', '', 'Address', '34020000001320000010', 0, 1, '', 0, 0, NULL, 0, '', 0, '', 0, 1, 109.0145488, 34.305162, NULL, '34020000001320000010', b'0', '', '', 0, 0, 0, 109.0145488, 34.305162, '', '2022-08-28 10:39:13', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, b'1', b'0', 0, b'1', '', NULL, NULL);

-- ----------------------------
-- Table structure for device_mobile_position
-- ----------------------------
DROP TABLE IF EXISTS `device_mobile_position`;
CREATE TABLE `device_mobile_position`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `channelId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deviceName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `altitude` double NULL DEFAULT NULL,
  `speed` double NULL DEFAULT NULL,
  `direction` double NULL DEFAULT NULL,
  `reportSource` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `longitudeGcj02` double NULL DEFAULT NULL,
  `latitudeGcj02` double NULL DEFAULT NULL,
  `longitudeWgs84` double NULL DEFAULT NULL,
  `latitudeWgs84` double NULL DEFAULT NULL,
  `createTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_mobile_position
-- ----------------------------

-- ----------------------------
-- Table structure for gb_stream
-- ----------------------------
DROP TABLE IF EXISTS `gb_stream`;
CREATE TABLE `gb_stream`  (
  `gbStreamId` int(0) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `stream` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gbId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `longitude` double NULL DEFAULT NULL,
  `latitude` double NULL DEFAULT NULL,
  `streamType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mediaServerId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gpsTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`gbStreamId`) USING BTREE,
  UNIQUE INDEX `app`(`app`, `stream`) USING BTREE,
  UNIQUE INDEX `gbId`(`gbId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 301769 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gb_stream
-- ----------------------------

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `result` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `timing` bigint(0) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106477 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for media_server
-- ----------------------------
DROP TABLE IF EXISTS `media_server`;
CREATE TABLE `media_server`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hookIp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sdpIp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `streamIp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `httpPort` int(0) NOT NULL,
  `httpSSlPort` int(0) NOT NULL,
  `rtmpPort` int(0) NOT NULL,
  `rtmpSSlPort` int(0) NOT NULL,
  `rtpProxyPort` int(0) NOT NULL,
  `rtspPort` int(0) NOT NULL,
  `rtspSSLPort` int(0) NOT NULL,
  `autoConfig` int(0) NOT NULL,
  `secret` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `streamNoneReaderDelayMS` int(0) NOT NULL,
  `rtpEnable` int(0) NOT NULL,
  `rtpPortRange` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sendRtpPortRange` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `recordAssistPort` int(0) NOT NULL,
  `defaultServer` int(0) NOT NULL,
  `createTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `updateTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hookAliveInterval` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `media_server_i`(`ip`, `httpPort`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of media_server
-- ----------------------------
INSERT INTO `media_server` VALUES ('AIi7b9Hyz3i925jg', '192.168.50.50', '192.168.50.50', '192.168.50.50', '192.168.50.50', 9080, 443, 1935, 0, 9500, 554, 0, 1, '035c73f7-bb6b-4889-a715-d9eb2d1925cc', 10000, 1, '30000,30500', '30000,30500', 0, 1, '2022-08-29 02:10:24', '2022-08-29 02:10:24', 10);

-- ----------------------------
-- Table structure for parent_platform
-- ----------------------------
DROP TABLE IF EXISTS `parent_platform`;
CREATE TABLE `parent_platform`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `enable` int(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `serverGBId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `serverGBDomain` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `serverIP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `serverPort` int(0) NULL DEFAULT NULL,
  `deviceGBId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deviceIp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `devicePort` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expires` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `keepTimeout` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transport` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `characterSet` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `catalogId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ptz` int(0) NULL DEFAULT NULL,
  `rtcp` int(0) NULL DEFAULT NULL,
  `status` bit(1) NULL DEFAULT NULL,
  `startOfflinePush` int(0) NULL DEFAULT 0,
  `administrativeDivision` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `catalogGroup` int(0) NULL DEFAULT 1,
  `createTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `treeType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `parent_platform_id_uindex`(`id`) USING BTREE,
  UNIQUE INDEX `parent_platform_pk`(`serverGBId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parent_platform
-- ----------------------------

-- ----------------------------
-- Table structure for platform_catalog
-- ----------------------------
DROP TABLE IF EXISTS `platform_catalog`;
CREATE TABLE `platform_catalog`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `platformId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parentId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `civilCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `businessGroupId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_catalog
-- ----------------------------

-- ----------------------------
-- Table structure for platform_gb_channel
-- ----------------------------
DROP TABLE IF EXISTS `platform_gb_channel`;
CREATE TABLE `platform_gb_channel`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `platformId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `catalogId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deviceChannelId` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_gb_channel
-- ----------------------------

-- ----------------------------
-- Table structure for platform_gb_stream
-- ----------------------------
DROP TABLE IF EXISTS `platform_gb_stream`;
CREATE TABLE `platform_gb_stream`  (
  `platformId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `catalogId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gbStreamId` int(0) NOT NULL,
  `id` int(0) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `platform_gb_stream_pk`(`platformId`, `catalogId`, `gbStreamId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_gb_stream
-- ----------------------------

-- ----------------------------
-- Table structure for stream_proxy
-- ----------------------------
DROP TABLE IF EXISTS `stream_proxy`;
CREATE TABLE `stream_proxy`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `app` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `stream` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `src_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dst_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `timeout_ms` int(0) NULL DEFAULT NULL,
  `ffmpeg_cmd_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rtp_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mediaServerId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `enable_hls` bit(1) NULL DEFAULT NULL,
  `enable_mp4` bit(1) NULL DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `status` bit(1) NOT NULL,
  `enable_remove_none_reader` bit(1) NOT NULL,
  `createTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `stream_proxy_pk`(`app`, `stream`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stream_proxy
-- ----------------------------
INSERT INTO `stream_proxy` VALUES (68, 'ffmpeg', 'home', 'c6cn', '', 'rtsp://admin:WMOPMR@192.168.50.117:554/h264/ch1/main/av_stream', 'rtmp://127.0.0.1:1935/home/c6cn', 5000, 'ffmpeg.cmd', NULL, 'AIi7b9Hyz3i925jg', b'1', b'0', b'0', b'0', b'0', '2022-08-10 22:30:30', 'home', NULL);

-- ----------------------------
-- Table structure for stream_push
-- ----------------------------
DROP TABLE IF EXISTS `stream_push`;
CREATE TABLE `stream_push`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `stream` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `totalReaderCount` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `originType` int(0) NULL DEFAULT NULL,
  `originTypeStr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `aliveSecond` int(0) NULL DEFAULT NULL,
  `mediaServerId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `serverId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pushTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `pushIng` int(0) NULL DEFAULT NULL,
  `self` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `stream_push_pk`(`app`, `stream`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 305431 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stream_push
-- ----------------------------

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record`  (
  `app` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '录制的流应用名',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件绝对路径',
  `file_size` int(0) NULL DEFAULT NULL COMMENT '文件大小，单位字节',
  `folder` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件所在目录路径',
  `start_time` datetime(0) NOT NULL COMMENT '开始录制时间戳',
  `stream` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '录制的流ID',
  `time_len` double NULL DEFAULT NULL COMMENT '录制时长，单位秒',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'http/rtsp/rtmp点播相对url路径',
  `vhost` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流虚拟主机',
  `media_server_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务器id,通过配置文件设置',
  PRIMARY KEY (`media_server_id`, `app`, `stream`, `start_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES ('app', 'live_record.m3u8', 'E:/video//app/live/20220822/20220822020646/live_record.m3u8', 594332672, 'E:/dev/ZLMediaKit/release/windows/Debug/www/app/live', '2022-08-22 02:06:46', 'live', 1788.5780010223389, 'app/live/20220822/20220822020646/live_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('app', 'live_record.m3u8', 'E:/video//app/live/20220822/20220822023642/live_record.m3u8', 534316680, 'E:/dev/ZLMediaKit/release/windows/Debug/www/app/live', '2022-08-22 02:36:42', 'live', 1607.7720279693604, 'app/live/20220822/20220822023642/live_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('app', 'live_record.m3u8', 'E:/video//app/live/20220828/20220828195502/live_record.m3u8', 143721488, 'E:/dev/ZLMediaKit/release/windows/Debug/www/app/live', '2022-08-28 19:55:02', 'live', 431.9720039367676, 'app/live/20220828/20220828195502/live_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220816/20220816025701/34020000001320000010_34020000001320000011_record.m3u8', 22448704, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-16 02:57:01', '34020000001320000010_34020000001320000011', 89.07999992370605, 'rtp/34020000001320000010_34020000001320000011/20220816/20220816025701/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220816/20220816025920/34020000001320000010_34020000001320000011_record.m3u8', 406487960, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-16 02:59:20', '34020000001320000010_34020000001320000011', 1613.039999961853, 'rtp/34020000001320000010_34020000001320000011/20220816/20220816025920/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220816/20220816221234/34020000001320000010_34020000001320000011_record.m3u8', 70519176, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-16 22:12:34', '34020000001320000010_34020000001320000011', 279.960000038147, 'rtp/34020000001320000010_34020000001320000011/20220816/20220816221234/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220816/20220816221714/34020000001320000010_34020000001320000011_record.m3u8', 45625720, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-16 22:17:14', '34020000001320000010_34020000001320000011', 180.80000001192093, 'rtp/34020000001320000010_34020000001320000011/20220816/20220816221714/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220816/20220816233716/34020000001320000010_34020000001320000011_record.m3u8', 17768256, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-16 23:37:16', '34020000001320000010_34020000001320000011', 71.44000017642975, 'rtp/34020000001320000010_34020000001320000011/20220816/20220816233716/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220816/20220816234235/34020000001320000010_34020000001320000011_record.m3u8', 5158156, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-16 23:42:35', '34020000001320000010_34020000001320000011', 20, 'rtp/34020000001320000010_34020000001320000011/20220816/20220816234235/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220818/20220818002351/34020000001320000010_34020000001320000011_record.m3u8', 3209536, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-18 00:23:51', '34020000001320000010_34020000001320000011', 22.000000074505806, 'rtp/34020000001320000010_34020000001320000011/20220818/20220818002351/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220821/20220821130720/34020000001320000010_34020000001320000011_record.m3u8', 41286492, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-21 13:07:20', '34020000001320000010_34020000001320000011', 188.19999980926514, 'rtp/34020000001320000010_34020000001320000011/20220821/20220821130720/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220821/20220821131413/34020000001320000010_34020000001320000011_record.m3u8', 87391424, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-21 13:14:13', '34020000001320000010_34020000001320000011', 373.12000036239624, 'rtp/34020000001320000010_34020000001320000011/20220821/20220821131413/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220822/20220822014559/34020000001320000010_34020000001320000011_record.m3u8', 285941232, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-22 01:45:59', '34020000001320000010_34020000001320000011', 1263.6000001430511, 'rtp/34020000001320000010_34020000001320000011/20220822/20220822014559/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220825/20220825024126/34020000001320000010_34020000001320000011_record.m3u8', 14569436, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-25 02:41:26', '34020000001320000010_34020000001320000011', 73.00000047683716, 'rtp/34020000001320000010_34020000001320000011/20220825/20220825024126/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220825/20220825033402/34020000001320000010_34020000001320000011_record.m3u8', 1339876, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-25 03:34:02', '34020000001320000010_34020000001320000011', 37.84000015258789, 'rtp/34020000001320000010_34020000001320000011/20220825/20220825033402/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220826/20220826212706/34020000001320000010_34020000001320000011_record.m3u8', 77563536, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-26 21:27:06', '34020000001320000010_34020000001320000011', 339.7200003042817, 'rtp/34020000001320000010_34020000001320000011/20220826/20220826212706/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220826/20220826213517/34020000001320000010_34020000001320000011_record.m3u8', 202819476, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-26 21:35:17', '34020000001320000010_34020000001320000011', 892.039999961853, 'rtp/34020000001320000010_34020000001320000011/20220826/20220826213517/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220826/20220826220312/34020000001320000010_34020000001320000011_record.m3u8', 4569340, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-26 22:03:12', '34020000001320000010_34020000001320000011', 20.080000035464764, 'rtp/34020000001320000010_34020000001320000011/20220826/20220826220312/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220826/20220826220417/34020000001320000010_34020000001320000011_record.m3u8', 5172632, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-26 22:04:17', '34020000001320000010_34020000001320000011', 22.319999933242798, 'rtp/34020000001320000010_34020000001320000011/20220826/20220826220417/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220826/20220826222105/34020000001320000010_34020000001320000011_record.m3u8', 9234184, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-26 22:21:05', '34020000001320000010_34020000001320000011', 40.1200000718236, 'rtp/34020000001320000010_34020000001320000011/20220826/20220826222105/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220826/20220826231202/34020000001320000010_34020000001320000011_record.m3u8', 4555804, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-26 23:12:02', '34020000001320000010_34020000001320000011', 20.000000037252903, 'rtp/34020000001320000010_34020000001320000011/20220826/20220826231202/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220826/20220826231252/34020000001320000010_34020000001320000011_record.m3u8', 4675748, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-26 23:12:52', '34020000001320000010_34020000001320000011', 20.040000036358833, 'rtp/34020000001320000010_34020000001320000011/20220826/20220826231252/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220826/20220826232842/34020000001320000010_34020000001320000011_record.m3u8', 5682488, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-26 23:28:42', '34020000001320000010_34020000001320000011', 24.519999980926514, 'rtp/34020000001320000010_34020000001320000011/20220826/20220826232842/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827001627/34020000001320000010_34020000001320000011_record.m3u8', 401847368, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 00:16:27', '34020000001320000010_34020000001320000011', 1799.960000038147, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827001627/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827004627/34020000001320000010_34020000001320000011_record.m3u8', 149400216, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 00:46:27', '34020000001320000010_34020000001320000011', 670, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827004627/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827124419/34020000001320000010_34020000001320000011_record.m3u8', 69150912, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 12:44:19', '34020000001320000010_34020000001320000011', 322.24000000953674, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827124419/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827134249/34020000001320000010_34020000001320000011_record.m3u8', 39518164, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 13:42:49', '34020000001320000010_34020000001320000011', 230.96000003814697, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827134249/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827135354/34020000001320000010_34020000001320000011_record.m3u8', 10893848, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 13:53:54', '34020000001320000010_34020000001320000011', 50.320000648498535, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827135354/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827141106/34020000001320000010_34020000001320000011_record.m3u8', 103913428, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 14:11:06', '34020000001320000010_34020000001320000011', 465.80000019073486, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827141106/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827151528/34020000001320000010_34020000001320000011_record.m3u8', 8410744, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 15:15:28', '34020000001320000010_34020000001320000011', 42.44000035524368, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827151528/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827154239/34020000001320000010_34020000001320000011_record.m3u8', 248873460, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 15:42:39', '34020000001320000010_34020000001320000011', 1219.960000038147, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827154239/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827160309/34020000001320000010_34020000001320000011_record.m3u8', 1861952, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:03:09', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827160309/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827160319/34020000001320000010_34020000001320000011_record.m3u8', 5840032, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:03:19', '34020000001320000010_34020000001320000011', 30, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827160319/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827160339/34020000001320000010_34020000001320000011_record.m3u8', 2192268, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:03:39', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827160339/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827160349/34020000001320000010_34020000001320000011_record.m3u8', 2158804, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:03:49', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827160349/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827160359/34020000001320000010_34020000001320000011_record.m3u8', 1797092, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:03:59', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827160359/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162703/34020000001320000010_34020000001320000011_record.m3u8', 6684340, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:27:03', '34020000001320000010_34020000001320000011', 29.960000038146973, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162703/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162733/34020000001320000010_34020000001320000011_record.m3u8', 2195652, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:27:33', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162733/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162743/34020000001320000010_34020000001320000011_record.m3u8', 2213888, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:27:43', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162743/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162803/34020000001320000010_34020000001320000011_record.m3u8', 2294916, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:28:03', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162803/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162813/34020000001320000010_34020000001320000011_record.m3u8', 2216144, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:28:13', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162813/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162823/34020000001320000010_34020000001320000011_record.m3u8', 4466692, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:28:23', '34020000001320000010_34020000001320000011', 20, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162823/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162833/34020000001320000010_34020000001320000011_record.m3u8', 2186064, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:28:33', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162833/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162843/34020000001320000010_34020000001320000011_record.m3u8', 2257128, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:28:43', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162843/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162903/34020000001320000010_34020000001320000011_record.m3u8', 4464436, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:29:03', '34020000001320000010_34020000001320000011', 20, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162903/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162913/34020000001320000010_34020000001320000011_record.m3u8', 2238704, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:29:13', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162913/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162923/34020000001320000010_34020000001320000011_record.m3u8', 2228552, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:29:23', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162923/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162933/34020000001320000010_34020000001320000011_record.m3u8', 2254308, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:29:33', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162933/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162943/34020000001320000010_34020000001320000011_record.m3u8', 2203548, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:29:43', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162943/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827162953/34020000001320000010_34020000001320000011_record.m3u8', 2198660, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:29:53', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827162953/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163003/34020000001320000010_34020000001320000011_record.m3u8', 8908944, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:30:03', '34020000001320000010_34020000001320000011', 40, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163003/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163013/34020000001320000010_34020000001320000011_record.m3u8', 2185500, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:30:13', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163013/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163023/34020000001320000010_34020000001320000011_record.m3u8', 2235132, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:30:23', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163023/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163033/34020000001320000010_34020000001320000011_record.m3u8', 2190388, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:30:33', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163033/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163043/34020000001320000010_34020000001320000011_record.m3u8', 2237388, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:30:43', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163043/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163053/34020000001320000010_34020000001320000011_record.m3u8', 2237388, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:30:53', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163053/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163103/34020000001320000010_34020000001320000011_record.m3u8', 2230620, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:31:03', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163103/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163133/34020000001320000010_34020000001320000011_record.m3u8', 2246224, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:31:33', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163133/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163143/34020000001320000010_34020000001320000011_record.m3u8', 53613276, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:31:43', '34020000001320000010_34020000001320000011', 240, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163143/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163153/34020000001320000010_34020000001320000011_record.m3u8', 2255060, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:31:53', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163153/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163203/34020000001320000010_34020000001320000011_record.m3u8', 2207120, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:32:03', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163203/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163213/34020000001320000010_34020000001320000011_record.m3u8', 2222912, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:32:13', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163213/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163223/34020000001320000010_34020000001320000011_record.m3u8', 2226672, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:32:23', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163223/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163233/34020000001320000010_34020000001320000011_record.m3u8', 2242276, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:32:33', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163233/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163243/34020000001320000010_34020000001320000011_record.m3u8', 2227236, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:32:43', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163243/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163253/34020000001320000010_34020000001320000011_record.m3u8', 2224228, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:32:53', '34020000001320000010_34020000001320000011', 10, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163253/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827163723/34020000001320000010_34020000001320000011_record.m3u8', 139559544, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:37:23', '34020000001320000010_34020000001320000011', 640, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827163723/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827164803/34020000001320000010_34020000001320000011_record.m3u8', 28568104, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:48:03', '34020000001320000010_34020000001320000011', 140, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827164803/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827165023/34020000001320000010_34020000001320000011_record.m3u8', 22451148, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:50:23', '34020000001320000010_34020000001320000011', 109.48000060766935, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827165023/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827165226/34020000001320000010_34020000001320000011_record.m3u8', 8386868, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 16:52:26', '34020000001320000010_34020000001320000011', 37.59999990463257, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827165226/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220827/20220827172423/34020000001320000010_34020000001320000011_record.m3u8', 5989304, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-27 17:24:23', '34020000001320000010_34020000001320000011', 26.7999999076128, 'rtp/34020000001320000010_34020000001320000011/20220827/20220827172423/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220828/20220828104235/34020000001320000010_34020000001320000011_record.m3u8', 18215884, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-28 10:42:35', '34020000001320000010_34020000001320000011', 104.84000015258789, 'rtp/34020000001320000010_34020000001320000011/20220828/20220828104235/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220828/20220828144906/34020000001320000010_34020000001320000011_record.m3u8', 4300124, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-28 14:49:06', '34020000001320000010_34020000001320000011', 19.800000190734863, 'rtp/34020000001320000010_34020000001320000011/20220828/20220828144906/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220828/20220828145508/34020000001320000010_34020000001320000011_record.m3u8', 5301224, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-28 14:55:08', '34020000001320000010_34020000001320000011', 26.240000247955322, 'rtp/34020000001320000010_34020000001320000011/20220828/20220828145508/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220828/20220828223035/34020000001320000010_34020000001320000011_record.m3u8', 8066140, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-28 22:30:35', '34020000001320000010_34020000001320000011', 24.84000015258789, 'rtp/34020000001320000010_34020000001320000011/20220828/20220828223035/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220828/20220828223608/34020000001320000010_34020000001320000011_record.m3u8', 6579624, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-28 22:36:08', '34020000001320000010_34020000001320000011', 21.35999969393015, 'rtp/34020000001320000010_34020000001320000011/20220828/20220828223608/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001320000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001320000011/20220828/20220828224457/34020000001320000010_34020000001320000011_record.m3u8', 19876676, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001320000011', '2022-08-28 22:44:57', '34020000001320000010_34020000001320000011', 65.95999956130981, 'rtp/34020000001320000010_34020000001320000011/20220828/20220828224457/34020000001320000010_34020000001320000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001340000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001340000011/20220818/20220818002355/34020000001320000010_34020000001340000011_record.m3u8', 1078368, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001340000011', '2022-08-18 00:23:55', '34020000001320000010_34020000001340000011', 7, 'rtp/34020000001320000010_34020000001340000011/20220818/20220818002355/34020000001320000010_34020000001340000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001340000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001340000011/20220819/20220819231044/34020000001320000010_34020000001340000011_record.m3u8', 10874860, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001340000011', '2022-08-19 23:10:44', '34020000001320000010_34020000001340000011', 52.44000041484833, 'rtp/34020000001320000010_34020000001340000011/20220819/20220819231044/34020000001320000010_34020000001340000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001340000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001340000011/20220827/20220827135430/34020000001320000010_34020000001340000011_record.m3u8', 30376664, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001340000011', '2022-08-27 13:54:30', '34020000001320000010_34020000001340000011', 136.76000022888184, 'rtp/34020000001320000010_34020000001340000011/20220827/20220827135430/34020000001320000010_34020000001340000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001340000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001340000011/20220827/20220827151539/34020000001320000010_34020000001340000011_record.m3u8', 15646864, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001340000011', '2022-08-27 15:15:39', '34020000001320000010_34020000001340000011', 81.92000007629395, 'rtp/34020000001320000010_34020000001340000011/20220827/20220827151539/34020000001320000010_34020000001340000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001340000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001340000011/20220827/20220827165108/34020000001320000010_34020000001340000011_record.m3u8', 8577500, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001340000011', '2022-08-27 16:51:08', '34020000001320000010_34020000001340000011', 39.920000076293945, 'rtp/34020000001320000010_34020000001340000011/20220827/20220827165108/34020000001320000010_34020000001340000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001340000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001340000011/20220827/20220827165228/34020000001320000010_34020000001340000011_record.m3u8', 13127664, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001340000011', '2022-08-27 16:52:28', '34020000001320000010_34020000001340000011', 58.76000026613474, 'rtp/34020000001320000010_34020000001340000011/20220827/20220827165228/34020000001320000010_34020000001340000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001340000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001340000011/20220827/20220827172455/34020000001320000010_34020000001340000011_record.m3u8', 2594964, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001340000011', '2022-08-27 17:24:55', '34020000001320000010_34020000001340000011', 11.800000071525574, 'rtp/34020000001320000010_34020000001340000011/20220827/20220827172455/34020000001320000010_34020000001340000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001340000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001340000011/20220828/20220828223609/34020000001320000010_34020000001340000011_record.m3u8', 6148540, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001340000011', '2022-08-28 22:36:09', '34020000001320000010_34020000001340000011', 20.040000036358833, 'rtp/34020000001320000010_34020000001340000011/20220828/20220828223609/34020000001320000010_34020000001340000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '34020000001320000010_34020000001340000011_record.m3u8', 'E:/video//rtp/34020000001320000010_34020000001340000011/20220828/20220828224458/34020000001320000010_34020000001340000011_record.m3u8', 16752116, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/34020000001320000010_34020000001340000011', '2022-08-28 22:44:58', '34020000001320000010_34020000001340000011', 55.760000228881836, 'rtp/34020000001320000010_34020000001340000011/20220828/20220828224458/34020000001320000010_34020000001340000011_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190AC25_record.m3u8', 'E:/video//rtp/4190AC25/20220817/20220817024933/4190AC25_record.m3u8', 5219820, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190AC25', '2022-08-17 02:49:33', '4190AC25', 10.079999923706055, 'rtp/4190AC25/20220817/20220817024933/4190AC25_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190ACAE_record.m3u8', 'E:/video//rtp/4190ACAE/20220827/20220827151708/4190ACAE_record.m3u8', 206766724, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190ACAE', '2022-08-27 15:17:08', '4190ACAE', 903.8800001144409, 'rtp/4190ACAE/20220827/20220827151708/4190ACAE_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190ADDF_record.m3u8', 'E:/video//rtp/4190ADDF/20220817/20220817021145/4190ADDF_record.m3u8', 2312024, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190ADDF', '2022-08-17 02:11:45', '4190ADDF', 4.28000020980835, 'rtp/4190ADDF/20220817/20220817021145/4190ADDF_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B2BC_record.m3u8', 'E:/video//rtp/4190B2BC/20220817/20220817023543/4190B2BC_record.m3u8', 10398656, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B2BC', '2022-08-17 02:35:43', '4190B2BC', 20.120000034570694, 'rtp/4190B2BC/20220817/20220817023543/4190B2BC_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818011900/4190B44B_record.m3u8', 55991476, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:19:00', '4190B44B', 390.7200002670288, 'rtp/4190B44B/20220818/20220818011900/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012038/4190B44B_record.m3u8', 2845004, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:20:38', '4190B44B', 20, 'rtp/4190B44B/20220818/20220818012038/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012040/4190B44B_record.m3u8', 1431996, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:20:40', '4190B44B', 10, 'rtp/4190B44B/20220818/20220818012040/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012045/4190B44B_record.m3u8', 1421280, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:20:45', '4190B44B', 10, 'rtp/4190B44B/20220818/20220818012045/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012048/4190B44B_record.m3u8', 2868880, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:20:48', '4190B44B', 20, 'rtp/4190B44B/20220818/20220818012048/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012058/4190B44B_record.m3u8', 1436696, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:20:58', '4190B44B', 10, 'rtp/4190B44B/20220818/20220818012058/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012100/4190B44B_record.m3u8', 1431620, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:21:00', '4190B44B', 10, 'rtp/4190B44B/20220818/20220818012100/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012103/4190B44B_record.m3u8', 1427296, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:21:03', '4190B44B', 10, 'rtp/4190B44B/20220818/20220818012103/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012105/4190B44B_record.m3u8', 1427484, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:21:05', '4190B44B', 10, 'rtp/4190B44B/20220818/20220818012105/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012108/4190B44B_record.m3u8', 1439140, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:21:08', '4190B44B', 10, 'rtp/4190B44B/20220818/20220818012108/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012110/4190B44B_record.m3u8', 1427108, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:21:10', '4190B44B', 10, 'rtp/4190B44B/20220818/20220818012110/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012115/4190B44B_record.m3u8', 1453240, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:21:15', '4190B44B', 10, 'rtp/4190B44B/20220818/20220818012115/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B44B_record.m3u8', 'E:/video//rtp/4190B44B/20220818/20220818012118/4190B44B_record.m3u8', 1427860, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B44B', '2022-08-18 01:21:18', '4190B44B', 10, 'rtp/4190B44B/20220818/20220818012118/4190B44B_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B4A1_record.m3u8', 'E:/video//rtp/4190B4A1/20220818/20220818011855/4190B4A1_record.m3u8', 464924, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B4A1', '2022-08-18 01:18:55', '4190B4A1', 2.9600000381469727, 'rtp/4190B4A1/20220818/20220818011855/4190B4A1_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B4A6_record.m3u8', 'E:/video//rtp/4190B4A6/20220818/20220818225339/4190B4A6_record.m3u8', 88013140, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B4A6', '2022-08-18 22:53:39', '4190B4A6', 614.6400003433228, 'rtp/4190B4A6/20220818/20220818225339/4190B4A6_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B4E3_record.m3u8', 'E:/video//rtp/4190B4E3/20220817/20220817025001/4190B4E3_record.m3u8', 10663548, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B4E3', '2022-08-17 02:50:01', '4190B4E3', 20.55999957770109, 'rtp/4190B4E3/20220817/20220817025001/4190B4E3_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B524_record.m3u8', 'E:/video//rtp/4190B524/20220818/20220818030827/4190B524_record.m3u8', 1324084, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B524', '2022-08-18 03:08:27', '4190B524', 9.600000381469727, 'rtp/4190B524/20220818/20220818030827/4190B524_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B555_record.m3u8', 'E:/video//rtp/4190B555/20220817/20220817025425/4190B555_record.m3u8', 964064, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B555', '2022-08-17 02:54:25', '4190B555', 3.759999990463257, 'rtp/4190B555/20220817/20220817025425/4190B555_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B581_record.m3u8', 'E:/video//rtp/4190B581/20220817/20220817024924/4190B581_record.m3u8', 3263304, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B581', '2022-08-17 02:49:24', '4190B581', 6.239999771118164, 'rtp/4190B581/20220817/20220817024924/4190B581_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B590_record.m3u8', 'E:/video//rtp/4190B590/20220818/20220818025407/4190B590_record.m3u8', 88013140, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B590', '2022-08-18 02:54:07', '4190B590', 614.6400003433228, 'rtp/4190B590/20220818/20220818025407/4190B590_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B5BE_record.m3u8', 'E:/video//rtp/4190B5BE/20220825/20220825024144/4190B5BE_record.m3u8', 1702340, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B5BE', '2022-08-25 02:41:44', '4190B5BE', 7.119999885559082, 'rtp/4190B5BE/20220825/20220825024144/4190B5BE_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B6BB_record.m3u8', 'E:/video//rtp/4190B6BB/20220827/20220827151705/4190B6BB_record.m3u8', 129908, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B6BB', '2022-08-27 15:17:05', '4190B6BB', 0.3199999928474426, 'rtp/4190B6BB/20220827/20220827151705/4190B6BB_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B6DD_record.m3u8', 'E:/video//rtp/4190B6DD/20220818/20220818003542/4190B6DD_record.m3u8', 2445692, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B6DD', '2022-08-18 00:35:42', '4190B6DD', 16.880000114440918, 'rtp/4190B6DD/20220818/20220818003542/4190B6DD_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B81A_record.m3u8', 'E:/video//rtp/4190B81A/20220817/20220817025436/4190B81A_record.m3u8', 2705132, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B81A', '2022-08-17 02:54:36', '4190B81A', 10.240000039339066, 'rtp/4190B81A/20220817/20220817025436/4190B81A_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190B8FE_record.m3u8', 'E:/video//rtp/4190B8FE/20220817/20220817025418/4190B8FE_record.m3u8', 747488, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190B8FE', '2022-08-17 02:54:18', '4190B8FE', 6.599999904632568, 'rtp/4190B8FE/20220817/20220817025418/4190B8FE_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BAD8_record.m3u8', 'E:/video//rtp/4190BAD8/20220818/20220818030802/4190BAD8_record.m3u8', 1847288, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BAD8', '2022-08-18 03:08:02', '4190BAD8', 13.360000133514404, 'rtp/4190BAD8/20220818/20220818030802/4190BAD8_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BB65_record.m3u8', 'E:/video//rtp/4190BB65/20220818/20220818013719/4190BB65_record.m3u8', 88013140, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BB65', '2022-08-18 01:37:19', '4190BB65', 614.6400003433228, 'rtp/4190BB65/20220818/20220818013719/4190BB65_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BBAE_record.m3u8', 'E:/video//rtp/4190BBAE/20220817/20220817024859/4190BBAE_record.m3u8', 5652972, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BBAE', '2022-08-17 02:48:59', '4190BBAE', 10.840000033378601, 'rtp/4190BBAE/20220817/20220817024859/4190BBAE_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BC65_record.m3u8', 'E:/video//rtp/4190BC65/20220818/20220818015514/4190BC65_record.m3u8', 1621688, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BC65', '2022-08-18 01:55:14', '4190BC65', 10.720000267028809, 'rtp/4190BC65/20220818/20220818015514/4190BC65_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BC65_record.m3u8', 'E:/video//rtp/4190BC65/20220818/20220818015516/4190BC65_record.m3u8', 1428612, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BC65', '2022-08-18 01:55:16', '4190BC65', 10, 'rtp/4190BC65/20220818/20220818015516/4190BC65_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BC65_record.m3u8', 'E:/video//rtp/4190BC65/20220818/20220818015519/4190BC65_record.m3u8', 35703268, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BC65', '2022-08-18 01:55:19', '4190BC65', 250, 'rtp/4190BC65/20220818/20220818015519/4190BC65_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BC65_record.m3u8', 'E:/video//rtp/4190BC65/20220818/20220818015629/4190BC65_record.m3u8', 39238608, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BC65', '2022-08-18 01:56:29', '4190BC65', 273.92000007629395, 'rtp/4190BC65/20220818/20220818015629/4190BC65_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BC65_record.m3u8', 'E:/video//rtp/4190BC65/20220818/20220818015651/4190BC65_record.m3u8', 1424288, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BC65', '2022-08-18 01:56:51', '4190BC65', 10, 'rtp/4190BC65/20220818/20220818015651/4190BC65_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013422/4190BD19_record.m3u8', 3038268, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:22', '4190BD19', 20.72000026702881, 'rtp/4190BD19/20220818/20220818013422/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013425/4190BD19_record.m3u8', 1428612, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:25', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013425/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013427/4190BD19_record.m3u8', 1422784, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:27', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013427/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013430/4190BD19_record.m3u8', 1425416, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:30', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013430/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013432/4190BD19_record.m3u8', 1413760, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:32', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013432/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013435/4190BD19_record.m3u8', 1421844, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:35', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013435/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013437/4190BD19_record.m3u8', 1423348, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:37', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013437/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013440/4190BD19_record.m3u8', 1417144, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:40', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013440/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013442/4190BD19_record.m3u8', 1425228, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:42', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013442/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013445/4190BD19_record.m3u8', 1423724, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:45', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013445/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013447/4190BD19_record.m3u8', 1423912, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:47', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013447/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013450/4190BD19_record.m3u8', 1420340, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:50', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013450/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013452/4190BD19_record.m3u8', 1416580, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:52', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013452/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013455/4190BD19_record.m3u8', 1429552, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:55', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013455/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013457/4190BD19_record.m3u8', 1431056, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:34:57', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013457/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013502/4190BD19_record.m3u8', 1421280, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:35:02', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013502/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013507/4190BD19_record.m3u8', 1456436, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:35:07', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013507/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013510/4190BD19_record.m3u8', 1428612, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:35:10', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013510/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013512/4190BD19_record.m3u8', 1437072, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:35:12', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013512/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013515/4190BD19_record.m3u8', 1424664, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:35:15', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013515/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013520/4190BD19_record.m3u8', 1461136, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:35:20', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013520/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013552/4190BD19_record.m3u8', 1430492, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:35:52', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013552/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013615/4190BD19_record.m3u8', 1420716, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:36:15', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013615/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013622/4190BD19_record.m3u8', 1431620, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:36:22', '4190BD19', 10, 'rtp/4190BD19/20220818/20220818013622/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BD19_record.m3u8', 'E:/video//rtp/4190BD19/20220818/20220818013647/4190BD19_record.m3u8', 17744004, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BD19', '2022-08-18 01:36:47', '4190BD19', 123.92000007629395, 'rtp/4190BD19/20220818/20220818013647/4190BD19_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BFB8_record.m3u8', 'E:/video//rtp/4190BFB8/20220818/20220818015349/4190BFB8_record.m3u8', 1420716, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BFB8', '2022-08-18 01:53:49', '4190BFB8', 10, 'rtp/4190BFB8/20220818/20220818015349/4190BFB8_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190BFB8_record.m3u8', 'E:/video//rtp/4190BFB8/20220818/20220818015414/4190BFB8_record.m3u8', 36294904, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190BFB8', '2022-08-18 01:54:14', '4190BFB8', 253.92000007629395, 'rtp/4190BFB8/20220818/20220818015414/4190BFB8_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190C047_record.m3u8', 'E:/video//rtp/4190C047/20220818/20220818012928/4190C047_record.m3u8', 255492, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190C047', '2022-08-18 01:29:28', '4190C047', 1.7599999904632568, 'rtp/4190C047/20220818/20220818012928/4190C047_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190C30D_record.m3u8', 'E:/video//rtp/4190C30D/20220818/20220818003710/4190C30D_record.m3u8', 1420904, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190C30D', '2022-08-18 00:37:10', '4190C30D', 9.359999656677246, 'rtp/4190C30D/20220818/20220818003710/4190C30D_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190C3E1_record.m3u8', 'E:/video//rtp/4190C3E1/20220817/20220817025429/4190C3E1_record.m3u8', 883788, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190C3E1', '2022-08-17 02:54:29', '4190C3E1', 3.440000057220459, 'rtp/4190C3E1/20220817/20220817025429/4190C3E1_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190C69A_record.m3u8', 'E:/video//rtp/4190C69A/20220817/20220817001622/4190C69A_record.m3u8', 265455436, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190C69A', '2022-08-17 00:16:22', '4190C69A', 521.8799999952316, 'rtp/4190C69A/20220817/20220817001622/4190C69A_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190C785_record.m3u8', 'E:/video//rtp/4190C785/20220818/20220818003628/4190C785_record.m3u8', 521324, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190C785', '2022-08-18 00:36:28', '4190C785', 3.759999990463257, 'rtp/4190C785/20220818/20220818003628/4190C785_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190C9F1_record.m3u8', 'E:/video//rtp/4190C9F1/20220818/20220818012937/4190C9F1_record.m3u8', 88013140, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190C9F1', '2022-08-18 01:29:37', '4190C9F1', 614.6400003433228, 'rtp/4190C9F1/20220818/20220818012937/4190C9F1_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190CB6C_record.m3u8', 'E:/video//rtp/4190CB6C/20220818/20220818025744/4190CB6C_record.m3u8', 496132, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190CB6C', '2022-08-18 02:57:44', '4190CB6C', 3.359999895095825, 'rtp/4190CB6C/20220818/20220818025744/4190CB6C_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190CB92_record.m3u8', 'E:/video//rtp/4190CB92/20220820/20220820002512/4190CB92_record.m3u8', 68430684, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190CB92', '2022-08-20 00:25:12', '4190CB92', 351.48000049591064, 'rtp/4190CB92/20220820/20220820002512/4190CB92_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190CCCB_record.m3u8', 'E:/video//rtp/4190CCCB/20220817/20220817025456/4190CCCB_record.m3u8', 2312024, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190CCCB', '2022-08-17 02:54:56', '4190CCCB', 4.28000020980835, 'rtp/4190CCCB/20220817/20220817025456/4190CCCB_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190CD01_record.m3u8', 'E:/video//rtp/4190CD01/20220818/20220818012040/4190CD01_record.m3u8', 3038268, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190CD01', '2022-08-18 01:20:40', '4190CD01', 20.72000026702881, 'rtp/4190CD01/20220818/20220818012040/4190CD01_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190CD01_record.m3u8', 'E:/video//rtp/4190CD01/20220818/20220818012042/4190CD01_record.m3u8', 2854028, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190CD01', '2022-08-18 01:20:42', '4190CD01', 20, 'rtp/4190CD01/20220818/20220818012042/4190CD01_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190CD01_record.m3u8', 'E:/video//rtp/4190CD01/20220818/20220818012102/4190CD01_record.m3u8', 1423724, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190CD01', '2022-08-18 01:21:02', '4190CD01', 10, 'rtp/4190CD01/20220818/20220818012102/4190CD01_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190CD01_record.m3u8', 'E:/video//rtp/4190CD01/20220818/20220818012112/4190CD01_record.m3u8', 1429552, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190CD01', '2022-08-18 01:21:12', '4190CD01', 10, 'rtp/4190CD01/20220818/20220818012112/4190CD01_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190CD01_record.m3u8', 'E:/video//rtp/4190CD01/20220818/20220818012115/4190CD01_record.m3u8', 1431056, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190CD01', '2022-08-18 01:21:15', '4190CD01', 10, 'rtp/4190CD01/20220818/20220818012115/4190CD01_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190CF5E_record.m3u8', 'E:/video//rtp/4190CF5E/20220817/20220817001602/4190CF5E_record.m3u8', 10011376, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190CF5E', '2022-08-17 00:16:02', '4190CF5E', 19.399999618530273, 'rtp/4190CF5E/20220817/20220817001602/4190CF5E_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190CF8E_record.m3u8', 'E:/video//rtp/4190CF8E/20220817/20220817025432/4190CF8E_record.m3u8', 829832, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190CF8E', '2022-08-17 02:54:32', '4190CF8E', 3.119999885559082, 'rtp/4190CF8E/20220817/20220817025432/4190CF8E_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190D06F_record.m3u8', 'E:/video//rtp/4190D06F/20220819/20220819231134/4190D06F_record.m3u8', 410968, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190D06F', '2022-08-19 23:11:34', '4190D06F', 2.799999952316284, 'rtp/4190D06F/20220819/20220819231134/4190D06F_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190D0A5_record.m3u8', 'E:/video//rtp/4190D0A5/20220818/20220818015706/4190D0A5_record.m3u8', 88013140, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190D0A5', '2022-08-18 01:57:06', '4190D0A5', 614.6400003433228, 'rtp/4190D0A5/20220818/20220818015706/4190D0A5_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4190D171_record.m3u8', 'E:/video//rtp/4190D171/20220819/20220819232729/4190D171_record.m3u8', 238636860, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4190D171', '2022-08-19 23:27:29', '4190D171', 1411.039999961853, 'rtp/4190D171/20220819/20220819232729/4190D171_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '44010200492000000002_44010200491310000002_record.m3u8', 'E:/video/rtp/44010200492000000002_44010200491310000002/20220814/20220814230203/44010200492000000002_44010200491310000002_record.m3u8', 5220384, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/44010200492000000002_44010200491310000002', '2022-08-14 23:02:03', '44010200492000000002_44010200491310000002', 22.04599928855896, 'rtp/44010200492000000002_44010200491310000002/20220814/20220814230203/44010200492000000002_44010200491310000002_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '44010200492000000002_44010200491310000002_record.m3u8', 'E:/video/rtp/44010200492000000002_44010200491310000002/20220814/20220814233101/44010200492000000002_44010200491310000002_record.m3u8', 5220384, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/44010200492000000002_44010200491310000002', '2022-08-14 23:31:01', '44010200492000000002_44010200491310000002', 22.04599928855896, 'rtp/44010200492000000002_44010200491310000002/20220814/20220814233101/44010200492000000002_44010200491310000002_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '44010200492000000002_44010200491310000002_record.m3u8', 'E:/video/rtp/44010200492000000002_44010200491310000002/20220814/20220814233152/44010200492000000002_44010200491310000002_record.m3u8', 5220384, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/44010200492000000002_44010200491310000002', '2022-08-14 23:31:52', '44010200492000000002_44010200491310000002', 22.04599928855896, 'rtp/44010200492000000002_44010200491310000002/20220814/20220814233152/44010200492000000002_44010200491310000002_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '44010200492000000002_44010200491310000002_record.m3u8', 'E:/video/rtp/44010200492000000002_44010200491310000002/20220814/20220814233243/44010200492000000002_44010200491310000002_record.m3u8', 5220384, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/44010200492000000002_44010200491310000002', '2022-08-14 23:32:43', '44010200492000000002_44010200491310000002', 22.04599928855896, 'rtp/44010200492000000002_44010200491310000002/20220814/20220814233243/44010200492000000002_44010200491310000002_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '44010200492000000002_44010200491310000002_record.m3u8', 'E:/video/rtp/44010200492000000002_44010200491310000002/20220814/20220814233333/44010200492000000002_44010200491310000002_record.m3u8', 5220384, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/44010200492000000002_44010200491310000002', '2022-08-14 23:33:33', '44010200492000000002_44010200491310000002', 22.04599928855896, 'rtp/44010200492000000002_44010200491310000002/20220814/20220814233333/44010200492000000002_44010200491310000002_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '44010200492000000002_44010200491310000002_record.m3u8', 'E:/video/rtp/44010200492000000002_44010200491310000002/20220814/20220814234150/44010200492000000002_44010200491310000002_record.m3u8', 12137280, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/44010200492000000002_44010200491310000002', '2022-08-14 23:41:50', '44010200492000000002_44010200491310000002', 50.745999336242676, 'rtp/44010200492000000002_44010200491310000002/20220814/20220814234150/44010200492000000002_44010200491310000002_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '44010200492000000002_44010200491310000002_record.m3u8', 'E:/video/rtp/44010200492000000002_44010200491310000002/20220814/20220814234241/44010200492000000002_44010200491310000002_record.m3u8', 14335564, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/44010200492000000002_44010200491310000002', '2022-08-14 23:42:41', '44010200492000000002_44010200491310000002', 59.821001052856445, 'rtp/44010200492000000002_44010200491310000002/20220814/20220814234241/44010200492000000002_44010200491310000002_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '44010200492000000002_44010200491310000002_record.m3u8', 'E:/video/rtp/44010200492000000002_44010200491310000002/20220814/20220814235106/44010200492000000002_44010200491310000002_record.m3u8', 16881272, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/44010200492000000002_44010200491310000002', '2022-08-14 23:51:06', '44010200492000000002_44010200491310000002', 71.75899976491928, 'rtp/44010200492000000002_44010200491310000002/20220814/20220814235106/44010200492000000002_44010200491310000002_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '44010200492000000002_44010200491310000002_record.m3u8', 'E:/video//rtp/44010200492000000002_44010200491310000002/20220815/20220815010026/44010200492000000002_44010200491310000002_record.m3u8', 13277876, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/44010200492000000002_44010200491310000002', '2022-08-15 01:00:26', '44010200492000000002_44010200491310000002', 56.58999967575073, 'rtp/44010200492000000002_44010200491310000002/20220815/20220815010026/44010200492000000002_44010200491310000002_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '47868CF0_record.m3u8', 'E:/video//rtp/47868CF0/20220828/20220828223122/47868CF0_record.m3u8', 10596244, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/47868CF0', '2022-08-28 22:31:22', '47868CF0', 34.96000003814697, 'rtp/47868CF0/20220828/20220828223122/47868CF0_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '478694B4_record.m3u8', 'E:/video//rtp/478694B4/20220828/20220828223539/478694B4_record.m3u8', 2425388, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/478694B4', '2022-08-28 22:35:39', '478694B4', 7.519999980926514, 'rtp/478694B4/20220828/20220828223539/478694B4_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '47869501_record.m3u8', 'E:/video//rtp/47869501/20220828/20220828223518/47869501_record.m3u8', 45872, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/47869501', '2022-08-28 22:35:18', '47869501', 5.599999904632568, 'rtp/47869501/20220828/20220828223518/47869501_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '478698E3_record.m3u8', 'E:/video//rtp/478698E3/20220828/20220828150328/478698E3_record.m3u8', 2033784, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/478698E3', '2022-08-28 15:03:28', '478698E3', 9.960000038146973, 'rtp/478698E3/20220828/20220828150328/478698E3_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '478698E3_record.m3u8', 'E:/video//rtp/478698E3/20220828/20220828150338/478698E3_record.m3u8', 2260700, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/478698E3', '2022-08-28 15:03:38', '478698E3', 10, 'rtp/478698E3/20220828/20220828150338/478698E3_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '47869BE1_record.m3u8', 'E:/video//rtp/47869BE1/20220828/20220828223158/47869BE1_record.m3u8', 1115592, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/47869BE1', '2022-08-28 22:31:58', '47869BE1', 3.9200000762939453, 'rtp/47869BE1/20220828/20220828223158/47869BE1_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A032_record.m3u8', 'E:/video//rtp/4786A032/20220828/20220828150522/4786A032_record.m3u8', 4477596, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A032', '2022-08-28 15:05:22', '4786A032', 19.920000076293945, 'rtp/4786A032/20220828/20220828150522/4786A032_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145710/4786A3AA_record.m3u8', 4060988, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:57:10', '4786A3AA', 19.960000038146973, 'rtp/4786A3AA/20220828/20220828145710/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145720/4786A3AA_record.m3u8', 2078340, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:57:20', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145720/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145730/4786A3AA_record.m3u8', 2074204, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:57:30', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145730/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145740/4786A3AA_record.m3u8', 2041304, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:57:40', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145740/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145750/4786A3AA_record.m3u8', 2005960, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:57:50', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145750/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145800/4786A3AA_record.m3u8', 1971932, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:58:00', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145800/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145810/4786A3AA_record.m3u8', 1974000, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:58:10', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145810/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145820/4786A3AA_record.m3u8', 1972496, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:58:20', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145820/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145830/4786A3AA_record.m3u8', 1961216, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:58:30', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145830/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145840/4786A3AA_record.m3u8', 1928316, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:58:40', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145840/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145900/4786A3AA_record.m3u8', 1956140, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:59:00', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145900/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145910/4786A3AA_record.m3u8', 1930008, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:59:10', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145910/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145920/4786A3AA_record.m3u8', 1927376, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:59:20', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145920/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145930/4786A3AA_record.m3u8', 2017428, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:59:30', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145930/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145940/4786A3AA_record.m3u8', 1978512, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:59:40', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145940/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828145950/4786A3AA_record.m3u8', 1983212, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 14:59:50', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828145950/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828150000/4786A3AA_record.m3u8', 2047884, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 15:00:00', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828150000/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828150010/4786A3AA_record.m3u8', 2059164, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 15:00:10', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828150010/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828150020/4786A3AA_record.m3u8', 2049388, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 15:00:20', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828150020/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828150030/4786A3AA_record.m3u8', 2053900, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 15:00:30', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828150030/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828150040/4786A3AA_record.m3u8', 2109172, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 15:00:40', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828150040/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828150050/4786A3AA_record.m3u8', 2127784, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 15:00:50', '4786A3AA', 10, 'rtp/4786A3AA/20220828/20220828150050/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A3AA_record.m3u8', 'E:/video//rtp/4786A3AA/20220828/20220828150110/4786A3AA_record.m3u8', 1025164, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A3AA', '2022-08-28 15:01:10', '4786A3AA', 4.119999885559082, 'rtp/4786A3AA/20220828/20220828150110/4786A3AA_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A553_record.m3u8', 'E:/video//rtp/4786A553/20220828/20220828171121/4786A553_record.m3u8', 9023436, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A553', '2022-08-28 17:11:21', '4786A553', 39.03999996185303, 'rtp/4786A553/20220828/20220828171121/4786A553_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A651_record.m3u8', 'E:/video//rtp/4786A651/20220828/20220828145556/4786A651_record.m3u8', 4401268, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A651', '2022-08-28 14:55:56', '4786A651', 20.000000037252903, 'rtp/4786A651/20220828/20220828145556/4786A651_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A8D6_record.m3u8', 'E:/video//rtp/4786A8D6/20220828/20220828170738/4786A8D6_record.m3u8', 2109172, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A8D6', '2022-08-28 17:07:38', '4786A8D6', 9.960000038146973, 'rtp/4786A8D6/20220828/20220828170738/4786A8D6_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A8D6_record.m3u8', 'E:/video//rtp/4786A8D6/20220828/20220828170748/4786A8D6_record.m3u8', 2027956, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A8D6', '2022-08-28 17:07:48', '4786A8D6', 10, 'rtp/4786A8D6/20220828/20220828170748/4786A8D6_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A8D6_record.m3u8', 'E:/video//rtp/4786A8D6/20220828/20220828170758/4786A8D6_record.m3u8', 2139252, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A8D6', '2022-08-28 17:07:58', '4786A8D6', 10, 'rtp/4786A8D6/20220828/20220828170758/4786A8D6_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A8D6_record.m3u8', 'E:/video//rtp/4786A8D6/20220828/20220828170808/4786A8D6_record.m3u8', 2233064, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A8D6', '2022-08-28 17:08:08', '4786A8D6', 10, 'rtp/4786A8D6/20220828/20220828170808/4786A8D6_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A8D6_record.m3u8', 'E:/video//rtp/4786A8D6/20220828/20220828170818/4786A8D6_record.m3u8', 2092816, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A8D6', '2022-08-28 17:08:18', '4786A8D6', 10, 'rtp/4786A8D6/20220828/20220828170818/4786A8D6_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786A8D6_record.m3u8', 'E:/video//rtp/4786A8D6/20220828/20220828170828/4786A8D6_record.m3u8', 2029648, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786A8D6', '2022-08-28 17:08:28', '4786A8D6', 9.640000343322754, 'rtp/4786A8D6/20220828/20220828170828/4786A8D6_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786ACBC_record.m3u8', 'E:/video//rtp/4786ACBC/20220828/20220828223550/4786ACBC_record.m3u8', 2190200, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786ACBC', '2022-08-28 22:35:50', '4786ACBC', 5.519999980926514, 'rtp/4786ACBC/20220828/20220828223550/4786ACBC_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786ACF2_record.m3u8', 'E:/video//rtp/4786ACF2/20220828/20220828223528/4786ACF2_record.m3u8', 42300, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786ACF2', '2022-08-28 22:35:28', '4786ACF2', 5.039999961853027, 'rtp/4786ACF2/20220828/20220828223528/4786ACF2_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786AF44_record.m3u8', 'E:/video//rtp/4786AF44/20220828/20220828150315/4786AF44_record.m3u8', 2033784, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786AF44', '2022-08-28 15:03:15', '4786AF44', 9.960000038146973, 'rtp/4786AF44/20220828/20220828150315/4786AF44_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786AF44_record.m3u8', 'E:/video//rtp/4786AF44/20220828/20220828150325/4786AF44_record.m3u8', 2260700, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786AF44', '2022-08-28 15:03:25', '4786AF44', 10, 'rtp/4786AF44/20220828/20220828150325/4786AF44_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786AF44_record.m3u8', 'E:/video//rtp/4786AF44/20220828/20220828150335/4786AF44_record.m3u8', 2031716, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786AF44', '2022-08-28 15:03:35', '4786AF44', 10, 'rtp/4786AF44/20220828/20220828150335/4786AF44_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786AF44_record.m3u8', 'E:/video//rtp/4786AF44/20220828/20220828150345/4786AF44_record.m3u8', 2027016, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786AF44', '2022-08-28 15:03:45', '4786AF44', 10, 'rtp/4786AF44/20220828/20220828150345/4786AF44_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786AF44_record.m3u8', 'E:/video//rtp/4786AF44/20220828/20220828150355/4786AF44_record.m3u8', 2069316, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786AF44', '2022-08-28 15:03:55', '4786AF44', 10, 'rtp/4786AF44/20220828/20220828150355/4786AF44_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786AF44_record.m3u8', 'E:/video//rtp/4786AF44/20220828/20220828150405/4786AF44_record.m3u8', 2007276, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786AF44', '2022-08-28 15:04:05', '4786AF44', 10, 'rtp/4786AF44/20220828/20220828150405/4786AF44_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786AFB3_record.m3u8', 'E:/video//rtp/4786AFB3/20220828/20220828150544/4786AFB3_record.m3u8', 4221352, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786AFB3', '2022-08-28 15:05:44', '4786AFB3', 20.080000035464764, 'rtp/4786AFB3/20220828/20220828150544/4786AFB3_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786B1C1_record.m3u8', 'E:/video//rtp/4786B1C1/20220828/20220828170838/4786B1C1_record.m3u8', 4333024, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786B1C1', '2022-08-28 17:08:38', '4786B1C1', 20.000000037252903, 'rtp/4786B1C1/20220828/20220828170838/4786B1C1_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');
INSERT INTO `t_record` VALUES ('rtp', '4786B2C5_record.m3u8', 'E:/video//rtp/4786B2C5/20220828/20220828170840/4786B2C5_record.m3u8', 4333024, 'E:/dev/ZLMediaKit/release/windows/Debug/www/rtp/4786B2C5', '2022-08-28 17:08:40', '4786B2C5', 20.000000037252903, 'rtp/4786B2C5/20220828/20220828170840/4786B2C5_record.m3u8', '__defaultVhost__', 'AIi7b9Hyz3i925jg');

-- ----------------------------
-- Table structure for t_record_channels
-- ----------------------------
DROP TABLE IF EXISTS `t_record_channels`;
CREATE TABLE `t_record_channels`  (
  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `channel_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`device_id`, `channel_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_record_channels
-- ----------------------------
INSERT INTO `t_record_channels` VALUES ('34020000001320000010', '34020000001320000011', '2022-08-28 22:44:57', '2022-08-28 22:45:08');
INSERT INTO `t_record_channels` VALUES ('44010200492000000002', '44010200491310000002', '2022-08-15 01:00:26', '2022-08-15 01:00:36');

-- ----------------------------
-- Table structure for t_sip_baseconfig
-- ----------------------------
DROP TABLE IF EXISTS `t_sip_baseconfig`;
CREATE TABLE `t_sip_baseconfig`  (
  `api_auth` tinyint(1) NULL DEFAULT NULL COMMENT 'HTTP接口鉴权',
  `ack_timeout` int(0) NULL DEFAULT NULL COMMENT '信令超时时间',
  `allow_stream_start_by_url` tinyint(1) NULL DEFAULT NULL COMMENT '允许直播地址拉流',
  `black_ip_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '黑名单 IP(可选)',
  `black_ua_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '黑名单 UA(可选)',
  `captcha` tinyint(1) NULL DEFAULT NULL COMMENT '验证码',
  `device_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备统一接入密码',
  `drop_channel_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '全局过滤通道类型',
  `global_channel_audio` tinyint(1) NULL DEFAULT NULL COMMENT '全局通道开启音频',
  `global_channel_shared` tinyint(1) NULL DEFAULT NULL COMMENT '全局通道开启分享',
  `global_device_alarm_subscribeInterval` tinyint(1) NULL DEFAULT NULL COMMENT '全局订阅项目-报警订阅',
  `global_device_catalog_subscribeInterval` tinyint(1) NULL DEFAULT NULL COMMENT '全局订阅项目-目录订阅',
  `global_device_position_subscribeInterval` tinyint(1) NULL DEFAULT NULL COMMENT '全局订阅项目-位置订阅',
  `https_cert_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'HTTPS Cert 证书路径',
  `https_key_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'HTTPS Key 证书路径',
  `https_port` int(0) NULL DEFAULT NULL COMMENT 'HTTPS 端口(可选)',
  `host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SIP Host',
  `keepalive_timeout` int(0) NULL DEFAULT NULL COMMENT '心跳超时时间',
  `port` int(0) NULL DEFAULT NULL COMMENT 'SIP 端口',
  `prefer_stream_fmt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '首选直播格式',
  `realm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SIP 域',
  `sip_log` tinyint(1) NULL DEFAULT NULL COMMENT '信令日志',
  `serial` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'SIP ID',
  `time_server` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '校时源(可选)',
  PRIMARY KEY (`serial`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sip_baseconfig
-- ----------------------------
INSERT INTO `t_sip_baseconfig` VALUES (1, 15, 0, '***', '***', 0, '***', '', 0, 0, 0, 0, 0, '/etc/letsencrypt/live/liveqing.com/fullchain.pem', '/etc/letsencrypt/live/liveqing.com/privkey.pem', 10010, '192.168.50.50', 300, 5060, 'WEBRTC', '3402000000', 1, '34020000002000000001', '');

-- ----------------------------
-- Table structure for t_sms_baseconfig
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_baseconfig`;
CREATE TABLE `t_sms_baseconfig`  (
  `ack_timeout` int(0) NULL DEFAULT NULL COMMENT '超时时间',
  `clean_freespace_percent` int(0) NULL DEFAULT NULL COMMENT '存储清理阀值(%)',
  `clean_freespace_size` int(0) NULL DEFAULT NULL COMMENT '存储清理阀值(MB)',
  `clean_over_days` int(0) NULL DEFAULT NULL COMMENT '录像保留(天数)',
  `gop_cache` tinyint(0) NULL DEFAULT NULL COMMENT 'GOP缓冲',
  `group_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组 ID(可选)',
  `hls` tinyint(0) NULL DEFAULT NULL COMMENT '开启HLS',
  `host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本地|内网 IP',
  `keepalive_timeout` int(0) NULL DEFAULT NULL COMMENT '连接保持超时时间',
  `port` int(0) NULL DEFAULT NULL COMMENT 'SMS 端口',
  `rtc_port_range` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'WebRTC端口区间(UDP)',
  `rtmp_port` int(0) NULL DEFAULT NULL COMMENT 'RTMP 端口',
  `rtsp_port` int(0) NULL DEFAULT NULL COMMENT 'RTSP 端口(可选)',
  `realm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SMS 域',
  `record_dir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '云录像目录',
  `sip_log` tinyint(0) NULL DEFAULT NULL COMMENT '信令日志',
  `serial` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'SMS ID',
  `tcp_port_range` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收流端口区间(TCP)',
  `udp_port_range` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收流端口区间(UDP)',
  `use_wanip_recv_stream` tinyint(0) NULL DEFAULT NULL COMMENT '外网IP收流',
  `wan_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外网 IP(可选)',
  PRIMARY KEY (`serial`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sms_baseconfig
-- ----------------------------
INSERT INTO `t_sms_baseconfig` VALUES (15, 5, 5120, 3, 0, '', 1, '127.0.0.1', 300, 5070, '30250,30500', 11935, 554, '3402000000', '/data/livegbs/record', 1, '34020000002020000001', '30000,30249', '30000,30249', 1, '39.98.57.187');

-- ----------------------------
-- Table structure for t_status_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_status_logs`;
CREATE TABLE `t_status_logs`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `serial` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_t_status_logs_code`(`code`) USING BTREE,
  INDEX `idx_t_status_logs_created_at`(`created_at`) USING BTREE,
  INDEX `idx_t_status_logs_serial`(`serial`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_status_logs
-- ----------------------------
INSERT INTO `t_status_logs` VALUES ('-pVr7GV4Y', '34020000001320000010', '*', 'ON', '设备心跳上线 ON', '2022-08-16 22:11:56');
INSERT INTO `t_status_logs` VALUES ('0ECZfNCJO', '44010200491320000001', '*', 'ON', '设备注册上线 ON', '2022-08-15 23:24:53');
INSERT INTO `t_status_logs` VALUES ('2O-MpX_KM', '34020000001320000010', '*', 'ON', '设备心跳上线 ON', '2022-08-19 23:09:14');
INSERT INTO `t_status_logs` VALUES ('50MizgWdr', '44010200491320000001', '*', 'OFF', '设备主动离线 OFF', '2022-08-16 00:24:06');
INSERT INTO `t_status_logs` VALUES ('5nW0bpIT7', '44010200491320000001', '*', 'ON', '设备心跳上线 ON', '2022-08-15 23:40:51');
INSERT INTO `t_status_logs` VALUES ('6G5Zn12fC', '34020000001320000010', '*', 'ON', '设备注册上线 ON', '2022-08-16 02:56:56');
INSERT INTO `t_status_logs` VALUES ('9oHJiUuE1', '34020000001320000010', '*', 'ON', '设备心跳上线 ON', '2022-08-17 01:48:48');
INSERT INTO `t_status_logs` VALUES ('aasoXvKRu', '34020000001320000001', '*', 'ON', '设备注册上线 ON', '2022-08-15 23:19:37');
INSERT INTO `t_status_logs` VALUES ('FGTLyrMEG', '34020000001320000010', '*', 'ON', '设备心跳上线 ON', '2022-08-28 10:39:09');
INSERT INTO `t_status_logs` VALUES ('fkFJtaZTF', '34020000001320000010', '*', 'OFF', '设备超时离线 OFF', '2022-08-21 13:06:12');
INSERT INTO `t_status_logs` VALUES ('Hvd8Fbtrs', '34020000001320000010', '*', 'ON', '设备心跳上线 ON', '2022-08-21 13:07:06');
INSERT INTO `t_status_logs` VALUES ('hwhOwI7bu', '34020000001320000010', '*', 'OFF', '设备超时离线 OFF', '2022-08-17 01:30:45');
INSERT INTO `t_status_logs` VALUES ('LdyZkd3co', '44010200491320000010', '*', 'OFF', '设备主动离线 OFF', '2022-08-16 01:14:16');
INSERT INTO `t_status_logs` VALUES ('lRER19WZR', '34020000001320000010', '*', 'ON', '设备心跳上线 ON', '2022-08-18 22:53:16');
INSERT INTO `t_status_logs` VALUES ('lS-BFTT_d', '34020000001320000010', '*', 'ON', '设备心跳上线 ON', '2022-08-18 00:29:07');
INSERT INTO `t_status_logs` VALUES ('msV1FJoA3', '34020000001320000010', '*', 'OFF', '设备超时离线 OFF', '2022-08-27 19:42:04');
INSERT INTO `t_status_logs` VALUES ('n0-TO3blp', '44010200491320000001', '*', 'OFF', '设备主动离线 OFF', '2022-08-15 23:40:40');
INSERT INTO `t_status_logs` VALUES ('n2ESahCv8', '34020000001320000010', '*', 'OFF', '设备超时离线 OFF', '2022-08-19 23:09:09');
INSERT INTO `t_status_logs` VALUES ('PdATkCtPV', '34020000001320000010', '*', 'OFF', '设备超时离线 OFF', '2022-08-26 21:19:48');
INSERT INTO `t_status_logs` VALUES ('SrIQ-spN6', '44010200491320000010', '*', 'ON', '设备注册上线 ON', '2022-08-16 00:24:06');
INSERT INTO `t_status_logs` VALUES ('TZYxkquuq', '34020000001320000010', '*', 'OFF', '设备超时离线 OFF', '2022-08-16 22:11:51');
INSERT INTO `t_status_logs` VALUES ('U7WYdJx2-', '34020000001320000001', '*', 'OFF', '设备主动离线 OFF', '2022-08-15 23:24:53');
INSERT INTO `t_status_logs` VALUES ('wF4Lq3XoO', '34020000001320000010', '*', 'ON', '设备心跳上线 ON', '2022-08-26 21:20:05');
INSERT INTO `t_status_logs` VALUES ('WfuRDTzR1', '34020000001320000010', '*', 'ON', '设备心跳上线 ON', '2022-08-17 01:30:46');
INSERT INTO `t_status_logs` VALUES ('Y4bIlgTgm', '34020000001320000010', '*', 'OFF', '设备超时离线 OFF', '2022-08-18 22:52:52');
INSERT INTO `t_status_logs` VALUES ('YLBUO7OKD', '34020000001320000010', '*', 'OFF', '设备主动离线 OFF', '2022-08-18 00:29:02');
INSERT INTO `t_status_logs` VALUES ('z-f8bTW_l', '34020000001320000010', '*', 'OFF', '设备主动离线 OFF', '2022-08-17 01:47:12');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `roleId` int(0) NOT NULL,
  `createTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `updateTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pushKey` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_username_uindex`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, '2021 - 04 - 13 14:14:57', '2021 - 04 - 13 14:14:57', '01685cb9573ae25ec6c52142402da7c5');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `authority` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `updateTime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 'admin', '0 ', '2021 - 04 - 13 14:14:57', '2021 - 04 - 13 14:14:57');

SET FOREIGN_KEY_CHECKS = 1;
