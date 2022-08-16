package com.genersoft.iot.vmp.skyeye.enttity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stormbirds
 * @since 2021-12-12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
@TableName("t_alarms")
@ApiModel(value = "设备报警", description = "")
public class Alarms implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    @JsonProperty("ID")
    private String id;

    /**
     * 设备国标编号
     */
    @JsonProperty("DeviceID")
    @ApiModelProperty("设备国标编号")
    @TableField("device_id")
    private String deviceId;

    /**
     * 设备名称
     */
    @JsonProperty("DeviceName")
    @ApiModelProperty("设备名称")
    @TableField("device_name")
    private String deviceName;

    /**
     * 通道国标编号
     */
    @JsonProperty("ChannelID")
    @ApiModelProperty("通道国标编号")
    @TableField("channel_id")
    private String channelId;

    /**
     * 通道名称
     */
    @JsonProperty("ChannelName")
    @ApiModelProperty("通道名称")
    @TableField("channel_name")
    private String channelName;

    /**
     * 报警级别(1-一级警情,2-二级警情,3-三级警情,4-四级警情)		允许值: 1, 2, 3, 4
     */
    @JsonProperty("AlarmPriority")
    @ApiModelProperty("报警级别(1-一级警情,2-二级警情,3-三级警情,4-四级警情)\t\t允许值: 1, 2, 3, 4")
    @TableField("alarm_priority")
    private Integer alarmPriority;

    /**
     * 报警级别别称
     */
    @JsonProperty("AlarmPriorityName")
    @ApiModelProperty("报警级别别称")
    @TableField("alarm_priority_name")
    private String alarmPriorityName;

    /**
     * 报警方式(1-电话报警,2-设备报警,3-短信报警,4-GPS报警,5-视频报警,6-设备故障报警,7-其他报警)		允许值: 1, 2, 3, 4, 5, 6, 7
     */
    @JsonProperty("AlarmMethod")
    @ApiModelProperty("报警方式(1-电话报警,2-设备报警,3-短信报警,4-GPS报警,5-视频报警,6-设备故障报警,7-其他报警)\t\t允许值: 1, 2, 3, 4, 5, 6, 7")
    @TableField("alarm_method")
    private Integer alarmMethod;

    /**
     * 报警名字
     */
    @JsonProperty("AlarmMethodName")
    @ApiModelProperty("报警名字")
    @TableField("alarm_method_name")
    private String alarmMethodName;

    /**
     * 经度
     */
    @JsonProperty("Longitude")
    @ApiModelProperty("经度")
    @TableField("longitude")
    private Double longitude;

    /**
     * 维度
     */
    @JsonProperty("Latitude")
    @ApiModelProperty("维度")
    @TableField("latitude")
    private Double latitude;

    /**
     * 报警描述
     */
    @JsonProperty("AlarmDescription")
    @ApiModelProperty("报警描述")
    @TableField("alarm_description")
    private String alarmDescription;

    /**
     * 扩展信息
     */
    @ApiModelProperty("扩展信息")
    @TableField("info")
    @JsonProperty("ExtInfo")
    private String extinfo;

    /**
     * 报警类型	报警方式为2时,不携带 AlarmType 为默认的设备报警,携带 AlarmType 取值对应报警类型如下:1-视频丢失报警,2-设备防拆报警,3-存储设备磁盘满报警,4-设备高温报警,5-设备低温报警;	报警方式为5时,取值如下:1-人工视频报警,2-运动目标检测报警,3-遗留物检测报警,4-物体移除检测报警,5-绊线检测报警,6-入侵检测报警,7-逆行检测报警,8-徘徊检测报警,9-流量统计报警,10-密度检测报警,11-视频异常检测报警,12-快速移动报警	报警方式为6时,取值如下:1-存储设备磁盘故障报警,2-存储设备风扇故障报警
     */
    @JsonProperty("AlarmType")
    @ApiModelProperty("报警类型\n\t" +
            "报警方式为2时,不携带 AlarmType 为默认的设备报警,携带 AlarmType 取值对应报警类型如下:\n" +
            "1-视频丢失报警,2-设备防拆报警,3-存储设备磁盘满报警,4-设备高温报警,5-设备低温报警;" +
            "\n\t报警方式为5时,取值如下:1-人工视频报警,2-运动目标检测报警,3-遗留物检测报警,4-物体移除检测报警," +
            "5-绊线检测报警,6-入侵检测报警,7-逆行检测报警,8-徘徊检测报警,9-流量统计报警,10-密度检测报警,11-视频异常检测报警,12-快速移动报警" +
            "\n\t报警方式为6时,取值如下:1-存储设备磁盘故障报警,2-存储设备风扇故障报警")
    @TableField("alarm_type")
    private Integer alarmType;

    /**
     * 报警类型名称
     */
    @JsonProperty("AlarmTypeName")
    @ApiModelProperty("报警类型名称")
    @TableField("alarm_type_name")
    private String alarmTypeName;

    /**
     * 入侵报警事件类型(1-进入区域,2-离开区域)		允许值: 1, 2
     */
    @ApiModelProperty("入侵报警事件类型(1-进入区域,2-离开区域)\n\t\t允许值: 1, 2")
    @TableField("alarm_event_type")
    private Integer alarmEventType;

    /**
     * 报警时间
     */
    @JsonProperty("Time")
    @ApiModelProperty("报警时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("time")
    private String time;

    @TableField("record_url")
    private String recordUrl;

    /**
     * 创建时间
     */
    @JsonProperty("CreatedAt")
    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("created_at")
    private String createdAt;


    @ApiModelProperty("报警预案关联录像路径")
    @JsonProperty("RecordPath")
    private String recordPath;

    @ApiModelProperty("报警预案关联快照路径")
    @JsonProperty("SnapPath")
    private String snapPath;

    @JsonProperty("plan_description")
    private String planDescription;
}
