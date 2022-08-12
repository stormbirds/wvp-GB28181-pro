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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SmsBaseconfig对象", description = "")
public class SmsBaseconfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "相应超时")
    @JsonProperty("AckTimeout")
    private Integer acktimeout;

    @JsonProperty("GOPCache")
    @ApiModelProperty(value = "是否开启直播秒开")
    private Boolean gopcache;

    @JsonProperty("GroupID")
    @ApiModelProperty(value = "分组 ID(可选)")
    private String groupid;

    @JsonProperty("HLS")
    @ApiModelProperty(value = "是否开启HLS")
    private Boolean hls;

    @JsonProperty("Host")
    @ApiModelProperty(value = "主机")
    private String host;

    @JsonProperty("KeepaliveTimeout")
    @ApiModelProperty(value = "超时时间")
    private Integer keepalivetimeout;

    @JsonProperty("Port")
    @ApiModelProperty(value = "SIP 端口")
    private Integer port;

    @JsonProperty("RTMPPort")
    @ApiModelProperty(value = "RTMP 端口")
    private Integer rtmpport;

    @JsonProperty("RTSPPort")
    @ApiModelProperty(value = "RTSP 端口(可选)")
    private Integer rtspport;

    @JsonProperty("Realm")
    @ApiModelProperty(value = "SIP 域")
    private String realm;

    @JsonProperty("RecordDir")
    @ApiModelProperty(value = "云录像目录")
    private String recorddir;

    @JsonProperty("SIPLog")
    @ApiModelProperty(value = "信令日志")
    private Boolean siplog;

    @JsonProperty("Serial")
    @ApiModelProperty(value = "SIP ID")
    private String serial;

    @JsonProperty("UseWanIPRecvStream")
    @ApiModelProperty(value = "外网IP收流")
    private Boolean usewaniprecvstream;

    @JsonProperty("WanIP")
    @ApiModelProperty(value = "外网 IP(可选)")
    private String wanip;


}
