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
 * @ Date 2021/12/15 17:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServerInfo implements Serializable {

    private static final long serialVersionUID = -8880909657324364730L;
    @JsonProperty("APIAuth")
    private Integer apiauth;
    @JsonProperty("Authorization")
    private String authorization;
    @JsonProperty("ChannelCount")

    private Integer channelcount;
    @JsonProperty("CopyrightText")

    private String copyrighttext;
    @JsonProperty("Hardware")
    private String hardware;
    @JsonProperty("InterfaceVersion")

    private String interfaceversion;
    @JsonProperty("LogoMiniText")

    private String logominitext;
    @JsonProperty("LogoText")

    private String logotext;
    @JsonProperty("RemainDays")

    private Integer remaindays;
    @JsonProperty("RunningTime")

    private String runningtime;
    @JsonProperty("Server")
    private String server;
    @JsonProperty("ServerTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime servertime;
    @JsonProperty("ShowAbout")
    private Integer showabout;
    @JsonProperty("ShowShare")
    private String showshare;
    @JsonProperty("StartUpTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startuptime;
    @JsonProperty("VersionType")
    private String versiontype;


}