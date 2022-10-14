package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.genersoft.iot.vmp.gb28181.bean.DeviceChannel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 21:38
 */
@NoArgsConstructor
@Data
public class ChannelListVo {

    @ApiModelProperty(value = "通道数")
    @JsonProperty("ChannelCount")
    private Integer channelCount;


    @ApiModelProperty(value = "通道列表")
    @JsonProperty("ChannelList")
    private List<DeviceChannel> channelList;

    @JsonProperty("ChannelRelateCount")
    private Integer channelRelateCount;

    @JsonProperty("HasAllChannel")
    private Boolean hasAllChannel;

}