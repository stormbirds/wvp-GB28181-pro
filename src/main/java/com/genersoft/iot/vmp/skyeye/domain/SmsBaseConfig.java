package com.genersoft.iot.vmp.skyeye.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Description com.genersoft.iot.vmp.skyeye.domain
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/8/28 23:40
 */
@NoArgsConstructor
@Data
public class SmsBaseConfig {
    @JSONField(name = "AckTimeout")
    private Integer ackTimeout;
    @JSONField(name = "CleanFreespacePercent")
    private Integer cleanFreespacePercent;
    @JSONField(name = "CleanFreespaceSize")
    private Integer cleanFreespaceSize;
    @JSONField(name = "CleanOverDays")
    private Integer cleanOverDays;
    @JSONField(name = "GOPCache")
    private Boolean gOPCache;
    @JSONField(name = "GroupID")
    private String groupID;
    @JSONField(name = "HLS")
    private Boolean hls;
    @JSONField(name = "Host")
    private String host;
    @JSONField(name = "KeepaliveTimeout")
    private Integer keepaliveTimeout;
    @JSONField(name = "Port")
    private Integer port;
    @JSONField(name = "RTCPortRange")
    private String rTCPortRange;
    @JSONField(name = "RTMPPort")
    private Integer rTMPPort;
    @JSONField(name = "RTSPPort")
    private Integer rTSPPort;
    @JSONField(name = "Realm")
    private String realm;
    @JSONField(name = "RecordDir")
    private String recordDir;
    @JSONField(name = "SIPLog")
    private Boolean sIPLog;
    @JSONField(name = "Serial")
    private String serial;
    @JSONField(name = "TCPPortRange")
    private String tCPPortRange;
    @JSONField(name = "UDPPortRange")
    private String uDPPortRange;
    @JSONField(name = "UseWanIPRecvStream")
    private Boolean useWanIPRecvStream;
    @JSONField(name = "WanIP")
    private String wanIP;
}
