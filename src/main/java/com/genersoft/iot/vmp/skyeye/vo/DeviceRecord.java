package com.genersoft.iot.vmp.skyeye.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 23:42
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class DeviceRecord implements Serializable {

    private static final long serialVersionUID = 4747261036322956723L;

    @JsonProperty("DeviceID")
    private String deviceid;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("FilePath")
    private String filepath;

    @JsonProperty("FileSize")
    private Double filesize;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("StartTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String starttime;

    @JsonProperty("EndTime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String endtime;

    @JsonProperty("Secrecy")
    private Integer secrecy;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("RecorderID")
    private String recorderid;


}
