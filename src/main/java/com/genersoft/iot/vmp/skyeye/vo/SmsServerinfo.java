package com.genersoft.iot.vmp.skyeye.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 17:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmsServerinfo implements Serializable {

    private static final long serialVersionUID = 4905471534146931526L;
    @JsonProperty("Authorization")
    private String authorization;
    @JsonProperty("ChannelCount")
    private Integer channelcount;
    @JsonProperty("Hardware")
    private String hardware;
    @JsonProperty("InterfaceVersion")
    private String interfaceversion;
    @JsonProperty("RemainDays")
    private Integer remaindays;
    @JsonProperty("RunningTime")
    private String runningtime;
    @JsonProperty("Server")
    private String server;
    @JsonProperty("ServerTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime servertime;
    @JsonProperty("StartUpTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startuptime;
    @JsonProperty("VersionType")
    private String versiontype;


}