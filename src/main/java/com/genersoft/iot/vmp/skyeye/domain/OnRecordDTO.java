package com.genersoft.iot.vmp.skyeye.domain;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.genersoft.iot.vmp.skyeye.enttity.Record;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Description com.genersoft.iot.vmp.skyeye.domain
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/8/20 0:53
 */
@NoArgsConstructor
@Data
public class OnRecordDTO {
    @JSONField(name = "app")
    private String app;
    @JSONField(name = "file_path")
    private String filePath;
    @JSONField(name = "start_time")
    private Long startTime;
    @JSONField(name = "time_len")
    private Double timeLen;
    @JSONField(name = "vhost")
    private String vhost;
    @JSONField(name = "folder")
    private String folder;
    @JSONField(name = "mediaServerId")
    private String mediaServerId;
    @JSONField(name = "stream")
    private String stream;
    @JSONField(name = "file_name")
    private String fileName;
    @JSONField(name = "file_size")
    private Integer fileSize;
    @JSONField(name = "url")
    private String url;

    public Record toRecord() {
        Record record = new Record();
        record.setApp(app);
        record.setFilePath(filePath);
        record.setStartTime(LocalDateTimeUtil.of(startTime*1000));
        record.setTimeLen(timeLen);
        record.setVhost(vhost);
        record.setFolder(folder);
        record.setMediaServerId(mediaServerId);
        record.setStream(stream);
        record.setFileName(fileName);
        record.setFileSize(fileSize);
        record.setUrl(url);
        return record;
    }
}
