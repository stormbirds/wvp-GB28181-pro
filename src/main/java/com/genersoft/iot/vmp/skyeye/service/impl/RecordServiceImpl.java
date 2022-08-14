package com.genersoft.iot.vmp.skyeye.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.genersoft.iot.vmp.common.StreamInfo;
import com.genersoft.iot.vmp.conf.UserSetting;
import com.genersoft.iot.vmp.media.zlm.ZLMRESTfulUtils;
import com.genersoft.iot.vmp.media.zlm.dto.MediaServerItem;
import com.genersoft.iot.vmp.service.IMediaServerService;
import com.genersoft.iot.vmp.skyeye.constant.RealTimeRecordTask;
import com.genersoft.iot.vmp.skyeye.enttity.Record;
import com.genersoft.iot.vmp.skyeye.enttity.RecordCache;
import com.genersoft.iot.vmp.skyeye.enttity.RecordChannels;
import com.genersoft.iot.vmp.skyeye.mapper.RecordMapper;
import com.genersoft.iot.vmp.skyeye.service.IRecordChannelsService;
import com.genersoft.iot.vmp.skyeye.service.IRecordService;
import com.genersoft.iot.vmp.skyeye.vo.RealtimeRecord;
import com.genersoft.iot.vmp.skyeye.vo.RecordListVo;
import com.genersoft.iot.vmp.storager.IRedisCatchStorage;
import io.lindstrom.m3u8.model.MediaPlaylist;
import io.lindstrom.m3u8.model.MediaSegment;
import io.lindstrom.m3u8.parser.MediaPlaylistParser;
import io.lindstrom.m3u8.parser.PlaylistParserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static cn.hutool.core.date.DatePattern.*;
import static cn.hutool.core.date.DatePattern.PURE_DATETIME_FORMATTER;
import static com.genersoft.iot.vmp.skyeye.constant.RedisCacheKey.KEY_RECORD_STREAM_HASH;

/**
 * @Description cn.stormbirds.skyeye.service.impl
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/9 21:29
 */
@Slf4j
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private IRedisCatchStorage redisCatchStorage;
    @Autowired
    private IMediaServerService mediaServerService;
    @Value(value = "${snap.path}")
    private String snapPath;
    @Autowired
    private UserSetting userSetting;
    @Value("${media.ip}")
    private String mediaIp;
    @Value("${media.http-port}")
    private Integer mediaHttpPort;
    @Resource
    private IRecordChannelsService recordChannelsService;

    @Resource
    private ZLMRESTfulUtils zlmresTfulUtils;

    /**
     * ts文件名生成规则
     * example： 34020000002000000002_34020000001320000004-20220727163316-2.ts
     */
    private final String tsFilePathFormater = "%s_%d.ts";
    private final DateTimeFormatter tsNameFormatter = DatePattern.createFormatter("mm-ss");

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
//        param.put("customized_path",FileUtil.file(snapPath,streamName).getAbsolutePath() );//录像保存目录
        param.put("max_second",interval);//mp4录像切片时间大小,单位秒，置0则采用配置项
        log.info("开始实时录像，参数 [{}]",param);
        RealTimeRecordTask.recordCompletableFutureMap.put(streamName,new CompletableFuture<>());
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
        if(streamInfo==null) return new RecordListVo();
        try {
            MediaServerItem mediaInfo = mediaServerService.getOne(streamInfo.getMediaServerId());
            Map<String, Object> param = new HashMap<>();
            param.put("type", 1);//0为hls，1为mp4
            param.put("vhost", "__defaultVhost__");
            param.put("app", streamInfo.getApp());
            param.put("stream", streamName);
            JSONObject jsonObject = zlmresTfulUtils.stopRecord(mediaInfo, param);

            if (jsonObject.getIntValue("code") == 0 && jsonObject.getBooleanValue("result")) {
                streamInfo.setRecordStartAt("");
                CompletableFuture<RecordListVo> completableFuture = RealTimeRecordTask.recordCompletableFutureMap.get(streamName);
                return completableFuture.get(60, TimeUnit.SECONDS);
            }

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            redisCatchStorage.startPlay(streamInfo);
            RealTimeRecordTask.recordCompletableFutureMap.remove(streamName);
        }
        return new RecordListVo();
    }

    @Override
    public void onRecordMp4(JSONObject json) {

        String streamName = json.getString("stream");

        if(RealTimeRecordTask.recordCompletableFutureMap.containsKey(streamName)){
            CompletableFuture<RecordListVo> completableFuture = RealTimeRecordTask.recordCompletableFutureMap.get(streamName);
            RecordListVo recordListVo = new RecordListVo();
            List<RealtimeRecord> recordList = new ArrayList<>();
            RealtimeRecord realtimeRecord = new RealtimeRecord();
            realtimeRecord.setDownloadUrl(String.format("http://%s:%d/%s",mediaIp,mediaHttpPort,json.getString("url")));
            realtimeRecord.setStartTime(LocalDateTimeUtil.of(json.getLong("start_time")*1000));
            realtimeRecord.setEndTime(LocalDateTimeUtil.of((json.getLong("start_time")+json.getLong("time_len"))*1000));
            recordList.add(realtimeRecord);
            recordListVo.setRecordList(recordList);
            recordListVo.setRecordCount(recordList.size());
            recordListVo.setStreamID(streamName);
            completableFuture.complete(recordListVo);
        }

    }

    @Override
    public void onRecordTs(JSONObject json) {
        json.put("start_time",LocalDateTimeUtil.of(json.getLong("start_time")*1000).format(NORM_DATETIME_FORMATTER));
        String streamName = json.getString("stream");
        MediaServerItem mediaInfo = mediaServerService.getOne(json.getString("mediaServerId"));
        String tsUrl = String.format("http://%s:%d/%s",mediaInfo.getIp(),mediaInfo.getHttpPort(),json.getString("url")) ;
        LocalDateTime tsLocalDateTime = LocalDateTimeUtil.parse(json.getString("start_time"),NORM_DATETIME_FORMATTER);
        MediaPlaylistParser parser = new MediaPlaylistParser();
        MediaPlaylist mediaPlaylist = null;
        String tsNewFileName;
        String recordRealPath ;
        RecordCache recordCache = null;
        if(Boolean.TRUE.equals(stringRedisTemplate.hasKey(String.format(KEY_RECORD_STREAM_HASH,json.getString("app"),streamName)))){
            recordCache = JSON.parseObject(stringRedisTemplate.opsForValue().get(String.format(KEY_RECORD_STREAM_HASH,json.getString("app"),json.getString("stream"))),RecordCache.class );
            if(recordCache==null){
                stringRedisTemplate.delete(String.format(KEY_RECORD_STREAM_HASH,json.getString("app"),json.getString("stream")));
            }
        }
        if(Boolean.TRUE.equals(stringRedisTemplate.hasKey(String.format(KEY_RECORD_STREAM_HASH,json.getString("app"),json.getString("stream"))))
                && recordCache!=null
                && LocalDateTimeUtil.between(recordCache.getCreatedAt(),LocalDateTime.now()).getSeconds()<=1800
                && recordCache.getCount()<179)
        {
            recordRealPath = recordCache.getPath();
            recordCache.setCount(recordCache.getCount()+1);
            recordCache.setDuration(json.getDouble("time_len")+recordCache.getDuration());
            recordCache.setFileSize(json.getInteger("file_size")+recordCache.getFileSize());
            tsNewFileName =String.format(tsFilePathFormater,tsLocalDateTime.format(tsNameFormatter),recordCache.getCount());

            try {
                mediaPlaylist = parser.readPlaylist(recordCache.getM3u8());
                List<MediaSegment> mediaSegments = new ArrayList<>(mediaPlaylist.mediaSegments());
                mediaSegments.add(MediaSegment.builder()
                        .duration(json.getDouble("time_len"))
                        .uri(tsNewFileName)
                        .build());
                mediaPlaylist = MediaPlaylist.builder()
                        .version(3)
                        .targetDuration((int) Math.ceil(Math.max(mediaPlaylist.targetDuration(),json.getDouble("time_len")) ))
                        .mediaSequence(0)
                        .ongoing(false)
                        .addAllMediaSegments(mediaSegments)
                        .build();
            } catch (PlaylistParserException e) {
                e.printStackTrace();
            }
        }else{
            //生成录像目录（E:/dev/ZLMediaKit/release/windows/Debug/www / rtp / 44010200492000000002_44010200491310000002 / 20220727 / 20220727191511）
            recordRealPath = String.format("%s/%s/%s/%s/%s",
                    userSetting.getCloudRecordPath(),
                    json.getString("app"),
                    streamName,
                    tsLocalDateTime.format(PURE_DATE_FORMATTER),
                    tsLocalDateTime.format(PURE_DATETIME_FORMATTER));
            recordCache = new RecordCache();
            recordCache.setCount(0);
            recordCache.setApp(json.getString("app"));
            recordCache.setStream(json.getString("stream"));
            recordCache.setPath(recordRealPath);
            recordCache.setCreatedAt(tsLocalDateTime);
            recordCache.setDuration(json.getDouble("time_len"));
            recordCache.setFileSize(json.getInteger("file_size"));
            recordCache.setUrl(String.format("%s/%s/%s/%s/%s_record.m3u8",
                    json.getString("app"),
                    streamName,
                    tsLocalDateTime.format(PURE_DATE_FORMATTER),
                    tsLocalDateTime.format(PURE_DATETIME_FORMATTER),streamName));
            FileUtil.mkdir(recordRealPath);
            tsNewFileName =String.format(tsFilePathFormater,tsLocalDateTime.format(tsNameFormatter) ,recordCache.getCount());
            mediaPlaylist = MediaPlaylist.builder()
                    .version(3)
                    .targetDuration((int) Math.ceil(recordCache.getDuration()))
                    .mediaSequence(0)
                    .ongoing(false)
                    .addMediaSegments(
                            MediaSegment.builder()
                                    .duration(recordCache.getDuration())
                                    .uri(tsNewFileName)
                                    .build())
                    .build();
            if(streamName.matches("[0-9]{20}_[0-9]{20}")){
                RecordChannels recordChannels = RecordChannels.builder()
                        .deviceId(streamName.split("_")[0])
                        .channelId(streamName.split("_")[1])
                        .createdAt(tsLocalDateTime.format(NORM_DATETIME_FORMATTER))
                        .updatedAt(LocalDateTime.now().format(NORM_DATETIME_FORMATTER))
                        .build();
                recordChannelsService.saveOrUpdate(recordChannels);
            }
        }
        if(mediaPlaylist==null){
            return ;
        }
        //更新录制记录中的m3u8
        recordCache.setM3u8(parser.writePlaylistAsString(mediaPlaylist));
        //修改并写入m3u8文件
        FileUtil.writeString(recordCache.getM3u8(),String.format("%s/%s_record.m3u8", recordRealPath,json.getString("stream")), "UTF-8");
        //将ts文件写入指定录制目录
        HttpUtil.downloadFileFromUrl(tsUrl,FileUtil.file(recordRealPath,tsNewFileName).getAbsolutePath());

        stringRedisTemplate.opsForValue().set(String.format(KEY_RECORD_STREAM_HASH,json.getString("app"),json.getString("stream")) ,JSON.toJSONString(recordCache), Duration.ofSeconds(30));

        Record record = json.toJavaObject(Record.class);
        record.setFileName(String.format("%s_record.m3u8", streamName));
        record.setFilePath(String.format("%s/%s_record.m3u8", recordRealPath,json.getString("stream")));
        record.setStartTime( recordCache.getCreatedAt()) ;
        record.setTimeLen(recordCache.getDuration());
        record.setFileSize(recordCache.getFileSize());
        record.setUrl(recordCache.getUrl());
        if(baseMapper.exists(Wrappers.<Record>lambdaQuery()
                .eq(Record::getMediaServerId,json.getString("mediaServerId"))
                .eq(Record::getApp,json.getString("app"))
                .eq(Record::getStream,streamName)
                .eq(Record::getStartTime,recordCache.getCreatedAt())
        )){
            update(Wrappers.<Record>lambdaUpdate()
                    .set(Record::getTimeLen,recordCache.getDuration())
                    .set(Record::getFileSize,recordCache.getFileSize())
                    .eq(Record::getMediaServerId,json.getString("mediaServerId"))
                    .eq(Record::getApp,json.getString("app"))
                    .eq(Record::getStream,streamName)
                    .eq(Record::getStartTime,recordCache.getCreatedAt())
            );
        }else{
            save(record);
        }

    }


}
