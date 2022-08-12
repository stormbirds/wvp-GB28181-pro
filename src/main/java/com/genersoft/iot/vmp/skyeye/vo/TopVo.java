package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 19:00
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TopVo {
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("msg")
    private String msg;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("cpuData")
        private List<CpuVo> cpuData;
        @JsonProperty("loadData")
        private List<LoadVo> loadData;
        @JsonProperty("memData")
        private List<MemVo> memData;
        @JsonProperty("netData")
        private List<NetVo> netData;

    }
}
