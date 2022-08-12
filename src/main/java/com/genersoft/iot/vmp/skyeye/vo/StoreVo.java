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
public class StoreVo {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Unit")
    private String unit;
    @JsonProperty("Size")
    private String size;
    @JsonProperty("FreeSpace")
    private String freeSpace;
    @JsonProperty("Used")
    private String used;
    @JsonProperty("Percent")
    private String percent;
    @JsonProperty("Threshold")
    private String threshold;
}