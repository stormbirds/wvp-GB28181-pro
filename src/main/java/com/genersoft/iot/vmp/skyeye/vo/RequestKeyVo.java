package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 17:13
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestKeyVo {
    @JsonProperty("RequestKey")
    private String requestKey;
    @JsonProperty("State")
    private String state;
}