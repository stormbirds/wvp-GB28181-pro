package com.genersoft.iot.vmp.skyeye.enttity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stormbirds
 * @since 2021-12-12
 */
@Builder
@Getter
@Setter
@TableName("t_status_logs")
@ApiModel(value = "StatusLogs对象", description = "")
public class StatusLogs implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("ID")
    @TableId("id")
    private String id;

    @JsonProperty("Serial")
    @TableField("serial")
    private String serial;

    @JsonProperty("Code")
    @TableField("code")
    private String code;

    @JsonProperty("Status")
    @TableField("status")
    private String status;

    @JsonProperty("Description")
    @TableField("description")
    private String description;

    @JsonProperty("CreatedAt")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("created_at")
    private String createdAt;


}