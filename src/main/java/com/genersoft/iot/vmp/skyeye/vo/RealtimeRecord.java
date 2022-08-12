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
 * @ Date 2021/12/15 23:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RealtimeRecord implements Serializable {

    private static final long serialVersionUID = -8210432837577290700L;
    @JsonProperty("StartTime")
    @JSONField(format = "yyyyMMddHHmmss")
    private LocalDateTime startTime;

    @JsonProperty("EndTime")
    @JSONField(format = "yyyyMMddHHmmss")
    private LocalDateTime endTime;

    @JsonProperty("DownloadURL")
    private String downloadUrl;



}