package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 18:59
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NetVo {
    @JsonProperty("recv")
    private Double recv;
    @JsonProperty("sent")
    private Double sent;
    @JsonProperty("time")
    private String time;
}
