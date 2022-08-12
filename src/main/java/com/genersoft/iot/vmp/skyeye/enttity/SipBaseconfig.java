package com.genersoft.iot.vmp.skyeye.enttity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author stormbirds
 * @since 2021-09-17
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SipBaseconfig对象", description = "")
public class SipBaseconfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "HTTP接口鉴权")
    @JsonProperty("APIAuth")
    private Boolean apiauth;

    @ApiModelProperty(value = "响应超时时间")

    @JsonProperty("AckTimeout")
    private Integer acktimeout;

    @ApiModelProperty(value = "允许直播地址拉流")

    @JsonProperty("AllowStreamStartByURL")
    private Boolean allowstreamstartbyurl;

    @ApiModelProperty(value = "黑名单 IP(可选)")

    @JsonProperty("BlackIPList")
    private String blackiplist;

    @ApiModelProperty(value = "黑名单 UA(可选)")

    @JsonProperty("BlackUAList")
    private String blackualist;

    @ApiModelProperty(value = "设备统一接入密码")

    @JsonProperty("DevicePassword")
    private String devicepassword;

    @ApiModelProperty(value = "HTTPS Cert 证书路径")

    @JsonProperty("HTTPSCertFile")
    private String httpscertfile;

    @ApiModelProperty(value = "HTTPS Key 私钥路径")

    @JsonProperty("HTTPSKeyFile")
    private String httpskeyfile;

    @ApiModelProperty(value = "HTTPS 端口(可选)")

    @JsonProperty("HTTPSPort")
    private Integer httpsport;

    @ApiModelProperty(value = "SIP Host")

    @JsonProperty("Host")
    private String host;

    @ApiModelProperty(value = "长连接超时时长")

    @JsonProperty("KeepaliveTimeout")
    private Integer keepalivetimeout;

    @ApiModelProperty(value = "SIP 端口")

    @JsonProperty("Port")
    private Integer port;

    @ApiModelProperty(value = "首选直播格式")

    @JsonProperty("PreferStreamFmt")
    private String preferstreamfmt;

    @ApiModelProperty(value = "SIP 域")

    @JsonProperty("Realm")
    private String realm;

    @ApiModelProperty(value = "开启信令日志")

    @JsonProperty("SIPLog")
    private Boolean siplog;

    @ApiModelProperty(value = "SIP ID")

    @JsonProperty("Serial")
    private String serial;

    @ApiModelProperty(value = "校时源(可选)")

    @JsonProperty("TimeServer")
    private String timeserver;


}