package com.genersoft.iot.vmp.skyeye.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Description com.genersoft.iot.vmp.skyeye.domain
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/8/28 23:11
 */
@Schema(name = "信令服务配置")
@NoArgsConstructor
@Data
public class SipBaseConfig {

    @Schema(name = "HTTP接口鉴权")
    @JSONField(name = "APIAuth")
    private Boolean apiAuth;

    @Schema(name = "信令超时时间")
    @JSONField(name = "AckTimeout")
    private Integer ackTimeout;

    @Schema(name = "允许直播地址拉流")
    @JSONField(name = "AllowStreamStartByURL")
    private Boolean allowStreamStartByUrl;

    @Schema(name = "黑名单 IP(可选)")
    @JSONField(name = "BlackIPList")
    private String blackIpList;

    @Schema(name = "黑名单 UA(可选)")
    @JSONField(name = "BlackUAList")
    private String blackUAList;

    @Schema(name = "验证码")
    @JSONField(name = "Captcha")
    private Boolean captcha;

    @Schema(name = "设备统一接入密码")
    @JSONField(name = "DevicePassword")
    private String devicePassword;

    @Schema(name = "全局过滤通道类型")
    @JSONField(name = "DropChannelType")
    private String dropChannelType;

    @Schema(name = "全局通道开启音频")
    @JSONField(name = "GlobalChannelAudio")
    private Boolean globalChannelAudio;

    @Schema(name = "全局通道开启分享")
    @JSONField(name = "GlobalChannelShared")
    private Boolean globalChannelShared;

    @Schema(name = "全局订阅项目-报警订阅")
    @JSONField(name = "GlobalDeviceAlarmSubscribeInterval")
    private Boolean globalDeviceAlarmSubscribeInterval;

    @Schema(name = "全局订阅项目-目录订阅")
    @JSONField(name = "GlobalDeviceCatalogSubscribeInterval")
    private Boolean globalDeviceCatalogSubscribeInterval;

    @Schema(name = "全局订阅项目-位置订阅")
    @JSONField(name = "GlobalDevicePositionSubscribeInterval")
    private Boolean globalDevicePositionSubscribeInterval;

    @Schema(name = "HTTPS Cert 证书路径")
    @JSONField(name = "HTTPSCertFile")
    private String httpSCertFile;

    @Schema(name = "HTTPS Key 证书路径")
    @JSONField(name = "HTTPSKeyFile")
    private String httpsKeyFile;

    @Schema(name = "HTTPS 端口(可选)")
    @JSONField(name = "HTTPSPort")
    private Integer httpsPort;

    @Schema(name = "SIP Host")
    @JSONField(name = "Host")
    private String host;

    @Schema(name = "心跳超时时间")
    @JSONField(name = "KeepaliveTimeout")
    private Integer keepaliveTimeout;

    @Schema(name = "SIP 端口")
    @JSONField(name = "Port")
    private Integer port;

    @Schema(name = "首选直播格式")
    @JSONField(name = "PreferStreamFmt")
    private String preferStreamFmt;

    @Schema(name = "SIP 域")
    @JSONField(name = "Realm")
    private String realm;

    @Schema(name = "信令日志")
    @JSONField(name = "SIPLog")
    private Boolean sipLog;

    @Schema(name = "SIP ID")
    @JSONField(name = "Serial")
    private String serial;

    @Schema(name = "校时源(可选)")
    @JSONField(name = "TimeServer")
    private String timeServer;
}
