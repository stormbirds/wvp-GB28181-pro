package com.genersoft.iot.vmp.skyeye.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/8/6 23:09
 */
@NoArgsConstructor
@Data
public class AuthDeviceStatus {
    @JsonProperty("ChannelCount")
    @ApiModelProperty("属性未知")
    @JSONField(name = "ChannelCount")
    private Integer channelCount;
    @JsonProperty("ChannelOnline")
    @ApiModelProperty("在线通道数量")
    @JSONField(name = "ChannelOnline")
    private Integer channelOnline;
    @JsonProperty("ChannelTotal")
    @ApiModelProperty("所有通道数量")
    @JSONField(name = "ChannelTotal")
    private Integer channelTotal;
    @JsonProperty("DeviceOnline")
    @ApiModelProperty("在线设备数量")
    @JSONField(name = "DeviceOnline")
    private Integer deviceOnline;
    @JsonProperty("DeviceTotal")
    @ApiModelProperty("所有设备数量")
    @JSONField(name = "DeviceTotal")
    private Integer deviceTotal;
}
