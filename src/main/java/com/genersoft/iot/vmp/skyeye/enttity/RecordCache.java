package com.genersoft.iot.vmp.skyeye.enttity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ Description cn.stormbirds.skyeye.entity
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/7/27 17:15
 */
@Data
public class RecordCache {
    private String app;
    private String stream;
    private String path;
    private int count = 0;
    private String m3u8;
    private Double duration;
    private Integer fileSize;
    private String url;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}