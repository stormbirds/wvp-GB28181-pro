package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 19:01
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoadVo {
    @JsonProperty("time")
    private String time;
    @JsonProperty("load")
    private String load;
    @JsonProperty("serial")
    private String serial;
    @JsonProperty("name")
    private String name;
}