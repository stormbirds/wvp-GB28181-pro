package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 19:00
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthVo {
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("data")
    private AuthDeviceStatus data;
    @JsonProperty("msg")
    private String msg;

}