package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 23:44
 */
@Data
public class RecordListVo {
    @JsonProperty("StreamID")
    private String streamID = "";
    @JsonProperty("RecordCount")
    private Integer recordCount = 0;
    @JsonProperty("RecordList")
    private List<RealtimeRecord> recordList = new ArrayList<>();

}