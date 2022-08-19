package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 23:41
 */
@NoArgsConstructor
@Data
public class DeviceRecordList {
    @JsonProperty("SN")
    private String sn;
    @JsonProperty("DeviceID")
    private String deviceID;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("SumNum")
    private Integer sumNum;
    @JsonProperty("RecordList")
    private List<DeviceRecord> recordList;

    @JsonProperty("RecordList")
    public List<DeviceRecord> getRecordList() {
        return recordList;
    }


}
