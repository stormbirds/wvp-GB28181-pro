package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 18:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CloudRecord implements Serializable {

    private static final long serialVersionUID = -8780468739060646372L;
    @JsonProperty("serial")
    private String serial;
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("custom_name")
    private String customName;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

}
