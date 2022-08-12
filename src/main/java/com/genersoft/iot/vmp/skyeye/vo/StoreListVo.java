package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 18:59
 */
@NoArgsConstructor
@Data
public class StoreListVo {
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("data")
    private List<StoreVo> data;
    @JsonProperty("msg")
    private String msg;

}