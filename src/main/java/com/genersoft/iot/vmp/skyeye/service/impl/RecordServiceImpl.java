package com.genersoft.iot.vmp.skyeye.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.genersoft.iot.vmp.common.StreamInfo;
import com.genersoft.iot.vmp.media.zlm.ZLMRESTfulUtils;
import com.genersoft.iot.vmp.media.zlm.dto.MediaServerItem;
import com.genersoft.iot.vmp.service.IMediaServerService;
import com.genersoft.iot.vmp.skyeye.constant.RedisCacheKey;
import com.genersoft.iot.vmp.skyeye.service.IRecordService;
import com.genersoft.iot.vmp.skyeye.vo.RealtimeRecord;
import com.genersoft.iot.vmp.skyeye.vo.RecordListVo;
import com.genersoft.iot.vmp.storager.IRedisCatchStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static com.genersoft.iot.vmp.skyeye.constant.RedisCacheKey.KEY_STREAM_INFO_HASH;

/**
 * @Description cn.stormbirds.skyeye.service.impl
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/9 21:29
 */
@Slf4j
@Service
public class RecordServiceImpl implements IRecordService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private IRedisCatchStorage redisCatchStorage;
    @Autowired
    private IMediaServerService mediaServerService;
    @Value(value = "${snap.path}")
    private String snapPath;
    @Value("${sip.ip}")
    private String sipIp;
    @Value("${server.port}")
    private int serverPort;

    @Resource
    private ZLMRESTfulUtils zlmresTfulUtils;


    @Override
    public String start(String streamName, Integer interval) {
        FileUtil.del(FileUtil.file(FileUtil.getTmpDirPath(),streamName));
        StreamInfo streamInfo = redisCatchStorage.queryPlayByStreamId(streamName);
        MediaServerItem mediaInfo = mediaServerService.getOne(streamInfo.getMediaServerId());
        Map<String, Object> param = new HashMap<>();
        param.put("type",1);//0为hls，1为mp4
        param.put("vhost", "__defaultVhost__");
        param.put("app", streamInfo.getApp());
        param.put("stream", streamName);
        param.put("customized_path",FileUtil.file(snapPath,streamName).getAbsolutePath() );//录像保存目录
        param.put("max_second",interval);//mp4录像切片时间大小,单位秒，置0则采用配置项
        JSONObject jsonObject = zlmresTfulUtils.startRecord(mediaInfo,param);
        if(jsonObject.getIntValue("code")==0 && jsonObject.getBooleanValue("result")){
            streamInfo.setRecordStartAt(LocalDateTimeUtil.now().format(DatePattern.NORM_DATETIME_FORMATTER));
            redisCatchStorage.startPlay(streamInfo);
            return "OK";
        }
        else return "FAIL";
    }

    @Override
    public RecordListVo stop(String streamName) {
        StreamInfo streamInfo = redisCatchStorage.queryPlayByStreamId(streamName);
        MediaServerItem mediaInfo = mediaServerService.getOne(streamInfo.getMediaServerId());
        Map<String, Object> param = new HashMap<>();
        param.put("type",1);//0为hls，1为mp4
        param.put("vhost", "__defaultVhost__");
        param.put("app", streamInfo.getApp());
        param.put("stream", streamName);
        JSONObject jsonObject = zlmresTfulUtils.stopRecord(mediaInfo,param);
        RecordListVo recordListVo = new RecordListVo();
        if(jsonObject.getIntValue("code")==0 && jsonObject.getBooleanValue("result")){
            String recordStartTime = streamInfo.getRecordStartAt();
            streamInfo.setRecordStartAt("");
            redisCatchStorage.startPlay(streamInfo);
            LocalDateTime endTime = LocalDateTime.now();
            List<RealtimeRecord> recordList = new ArrayList<>();
            FileUtil.loopFiles(FileUtil.file(snapPath,streamName) ,1,pathname -> {
                RealtimeRecord realtimeRecord = new RealtimeRecord();
                realtimeRecord.setStartTime(LocalDateTimeUtil.parse(recordStartTime, DatePattern.NORM_DATETIME_FORMATTER));
                realtimeRecord.setEndTime(endTime);
                realtimeRecord.setDownloadUrl(String.format("http://%s:%s/snap/%s/%s", sipIp, serverPort, streamName,FileUtil.getName(pathname.getPath())));
                recordList.add(realtimeRecord);
                return pathname.isDirectory();
            });
            recordListVo.setRecordList(recordList);
        }

        return recordListVo;
    }
}
