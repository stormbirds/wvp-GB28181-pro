package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.genersoft.iot.vmp.skyeye.enttity.Alarms;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 17:40
 */
@NoArgsConstructor
@Data
public class AlarmListVo {
    @JsonProperty("AlarmCount")
    private Integer alarmCount;
    @JsonProperty("AlarmList")
    private List<Alarms> alarmList;
    @JsonProperty("AlarmPublishToRedis")
    private Boolean alarmPublishToRedis;

}