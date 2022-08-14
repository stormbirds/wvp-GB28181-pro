package com.genersoft.iot.vmp.skyeye.enttity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author stormbirds
 * @since 2022-07-30
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@TableName("t_record_channels")
@ApiModel(value = "RecordChannels对象", description = "")
public class RecordChannels implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("device_id")
    private String deviceId;

    @TableField("channel_id")
    private String channelId;

    @TableField("created_at")
    private String createdAt;

    @TableField("updated_at")
    private String updatedAt;


}
