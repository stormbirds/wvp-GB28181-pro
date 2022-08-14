package com.genersoft.iot.vmp.skyeye.enttity;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ Description com.genersoft.iot.vmp.skyeye.enttity
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/8/14 17:21
 */
@NoArgsConstructor
@Data
@TableName("t_record")
public class Record {

    @TableField("media_server_id")
    @JSONField(name = "mediaServerId")
    private String mediaServerId;
    @JSONField(name = "app")
    private String app;
    @JSONField(name = "file_name")
    private String fileName;
    @JSONField(name = "file_path")
    private String filePath;
    @JSONField(name = "file_size")
    private Integer fileSize;
    @JSONField(name = "folder")
    private String folder;

    @TableField("start_time")
    @JSONField(name = "start_time")
    private LocalDateTime startTime;
    @JSONField(name = "stream")
    private String stream;
    @JSONField(name = "time_len")
    private Double timeLen;
    @JSONField(name = "url")
    private String url;
    @JSONField(name = "vhost")
    private String vhost;

}
