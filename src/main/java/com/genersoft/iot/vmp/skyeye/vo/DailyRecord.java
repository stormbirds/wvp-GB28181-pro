package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description cn.stormbirds.skyeye.vo
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/5 17:45
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DailyRecord {
    @JsonProperty("name")
    private String name;
    @JsonProperty("startAt")
    private String startAt;
    @JsonProperty("duration")
    private Double duration;
    @JsonProperty("hls")
    private String hls;
    @JsonProperty("important")
    private Boolean important;
}
