package com.genersoft.iot.vmp.skyeye.enttity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_sms_baseconfig")
@ApiModel(value = "SmsBaseconfig对象", description = "")
public class SmsBaseconfig implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "相应超时")
    @JsonProperty("AckTimeout")
    @TableField(value = "ack_timeout")
    @JSONField(name = "AckTimeout")
    private Integer ackTimeout;

    @JSONField(name = "CleanFreespacePercent")
    @TableField(value = "clean_freespace_percent")
    private Integer cleanFreespacePercent;

    @JSONField(name = "CleanFreespaceSize")
    @TableField(value = "clean_freespace_size")
    private Integer cleanFreespaceSize;

    @JSONField(name = "CleanOverDays")
    @TableField(value = "clean_over_days")
    private Integer cleanOverDays;

    @JsonProperty("GOPCache")
    @ApiModelProperty(value = "是否开启直播秒开")
    @TableField(value = "gop_cache")
    @JSONField(name = "GOPCache")
    private Boolean gopCache;

    @JsonProperty("GroupID")
    @ApiModelProperty(value = "分组 ID(可选)")
    @TableField(value = "group_id")
    @JSONField(name = "GroupID")
    private String groupID;

    @JsonProperty("HLS")
    @ApiModelProperty(value = "是否开启HLS")
    @TableField(value = "hls")
    @JSONField(name = "HLS")
    private Boolean hls;

    @JsonProperty("Host")
    @ApiModelProperty(value = "主机")
    @TableField(value = "host")
    @JSONField(name = "Host")
    private String host;

    @JsonProperty("KeepaliveTimeout")
    @ApiModelProperty(value = "超时时间")
    @TableField(value = "keepalive_timeout")
    @JSONField(name = "KeepaliveTimeout")
    private Integer keepaliveTimeout;

    @JsonProperty("Port")
    @ApiModelProperty(value = "SIP 端口")
    @TableField(value = "port")
    @JSONField(name = "Port")
    private Integer port;

    @TableField(value = "rtc_port_range")
    @JSONField(name = "RTCPortRange")
    private String rtcPortRange;

    @JsonProperty("RTMPPort")
    @ApiModelProperty(value = "RTMP 端口")
    @TableField(value = "rtmp_port")
    @JSONField(name = "RTMPPort")
    private Integer rtmpPort;

    @JsonProperty("RTSPPort")
    @ApiModelProperty(value = "RTSP 端口(可选)")
    @TableField(value = "rtsp_port")
    @JSONField(name = "RTSPPort")
    private Integer rtspPort;

    @JsonProperty("Realm")
    @ApiModelProperty(value = "SIP 域")
    @TableField(value = "realm")
    @JSONField(name = "Realm")
    private String realm;

    @JsonProperty("RecordDir")
    @ApiModelProperty(value = "云录像目录")
    @TableField(value = "record_dir")
    @JSONField(name = "RecordDir")
    private String recordDir;

    @JsonProperty("SIPLog")
    @ApiModelProperty(value = "信令日志")
    @TableField(value = "sip_log")
    @JSONField(name = "SIPLog")
    private Boolean sipLog;

    @JsonProperty("Serial")
    @ApiModelProperty(value = "SIP ID")
    @TableId
    @TableField(value = "serial")
    @JSONField(name = "Serial")
    private String serial;

    @TableField(value = "tcp_port_range")
    @JSONField(name = "TCPPortRange")
    private String tCPPortRange;

    @TableField(value = "udp_port_range")
    @JSONField(name = "UDPPortRange")
    private String uDPPortRange;

    @JsonProperty("UseWanIPRecvStream")
    @ApiModelProperty(value = "外网IP收流")
    @TableField(value = "use_wanip_recv_stream")
    @JSONField(name = "UseWanIPRecvStream")
    private Boolean useWanIPRecvStream;

    @JsonProperty("WanIP")
    @ApiModelProperty(value = "外网 IP(可选)")
    @TableField(value = "wan_ip")
    @JSONField(name = "WanIP")
    private String wanIP;
}
