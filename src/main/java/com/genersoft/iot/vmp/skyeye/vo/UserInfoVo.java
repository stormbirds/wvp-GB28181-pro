package com.genersoft.iot.vmp.skyeye.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ Description cn.stormbirds.skyeye.vo
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 17:24
 */
@NoArgsConstructor
@Data
public class UserInfoVo {
    @JsonProperty("Token")
    private String token;
    @JsonProperty("ID")
    private Integer id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Roles")
    private List<String> roles;
    @JsonProperty("HasAllChannel")
    private Boolean hasAllChannel;
    @JsonProperty("Cas")
    private Boolean cas;
    @JsonProperty("LoginAt")
    private String loginAt;
    @JsonProperty("RemoteIP")
    private String remoteIP;
}
