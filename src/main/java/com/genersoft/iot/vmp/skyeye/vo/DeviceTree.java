package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 22:21
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class DeviceTree implements Serializable {

    private static final long serialVersionUID = 3819247178897112349L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("custom")
    private Boolean custom;

    @JsonProperty("customName")
    private String customname;

    @JsonProperty("latitude")
    private Float latitude;

    @JsonProperty("longitude")
    private Float longitude;

    @JsonProperty("manufacturer")
    private String manufacturer;

    @JsonProperty("name")
    private String name;

    @JsonProperty("onlineSubCount")
    private Integer onlinesubcount;

    @JsonProperty("parental")
    private Boolean parental;

    @JsonProperty("ptzType")
    private Integer ptztype;

    @JsonProperty("serial")
    private String serial;

    @JsonProperty("status")
    private String status;

    @JsonProperty("subCount")
    private Integer subcount;

    @JsonProperty("subCountDevice")
    private Integer subcountdevice;


}