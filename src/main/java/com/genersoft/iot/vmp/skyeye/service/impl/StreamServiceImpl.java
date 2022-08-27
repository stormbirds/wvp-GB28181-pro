package com.genersoft.iot.vmp.skyeye.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.genersoft.iot.vmp.common.StreamInfo;
import com.genersoft.iot.vmp.gb28181.bean.Device;
import com.genersoft.iot.vmp.gb28181.bean.DeviceChannel;
import com.genersoft.iot.vmp.media.zlm.ZLMRESTfulUtils;
import com.genersoft.iot.vmp.media.zlm.dto.MediaServerItem;
import com.genersoft.iot.vmp.service.IMediaServerService;
import com.genersoft.iot.vmp.skyeye.domain.SkyEyeStreamInfo;
import com.genersoft.iot.vmp.skyeye.service.IStreamService;
import com.alibaba.fastjson.JSONObject;
import com.genersoft.iot.vmp.storager.IRedisCatchStorage;
import com.genersoft.iot.vmp.storager.IVideoManagerStorage;
import com.genersoft.iot.vmp.storager.dao.DeviceChannelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @ Description cn.stormbirds.skyeye.service.impl
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/28 1:59
 */
@Slf4j
@Service
public class StreamServiceImpl implements IStreamService {

    @Autowired
    private IRedisCatchStorage redisCatchStorage;
    @Autowired
    private ZLMRESTfulUtils zlmresTfulUtils;
    @Autowired
    private IMediaServerService mediaServerService;
    @Autowired
    private IVideoManagerStorage storager;
    @Autowired
    private DeviceChannelMapper channelMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${sip.ip}")
    private String sipIp;
    @Value(value = "${snap.path}")
    private String snapPath;
    @Value("${server.port}")
    private int serverPort;

    @Override
    public Mono<SkyEyeStreamInfo> getStreamInfoByStreamName(String streamName) {
        return null;
    }

    @Override
    public Mono<SkyEyeStreamInfo> info(String streamid, Boolean touch) {
        StreamInfo streamInfo = redisCatchStorage.queryPlayByStreamId(streamid);
        if(streamInfo==null){
            return Mono.justOrEmpty(new SkyEyeStreamInfo());
        }
        MediaServerItem mediaInfo = mediaServerService.getOne(streamInfo.getMediaServerId());
        if(mediaInfo==null){
            return Mono.justOrEmpty(new SkyEyeStreamInfo());
        }
        JSONObject mediaList = zlmresTfulUtils.getMediaList(mediaInfo, streamInfo.getApp(), streamid);
        Device device = storager.queryVideoDevice(streamInfo.getDeviceID());
        DeviceChannel channel = channelMapper.getChannelById(streamInfo.getDeviceID(),streamInfo.getChannelId());
        if (mediaList != null) {
            if (mediaList.getInteger("code") == 0) {
                JSONArray data = mediaList.getJSONArray("data");
                if (data == null) {
                    return Mono.justOrEmpty(new SkyEyeStreamInfo());
                }

                JSONObject mediaJSON = JSON.parseObject(JSON.toJSONString(data.get(0)), JSONObject.class);
                JSONArray tracks = mediaJSON.getJSONArray("tracks");
                SkyEyeStreamInfo.SkyEyeStreamInfoBuilder skyEyeStreamInfo = SkyEyeStreamInfo.builder();
                skyEyeStreamInfo.streamID(streamid)
                        .smsid(streamInfo.getMediaServerId())
                        .deviceID(streamInfo.getDeviceID())
                        .channelID(streamInfo.getChannelId())
                        .channelName(channel.getName())
                        .channelCustomName(channel.getName())
                        .webrtc(streamInfo.getRtc())
                        .flv(streamInfo.getFlv())
                        .wsFlv(streamInfo.getWs_flv())
                        .rtmp(streamInfo.getRtmp())
                        .hls(streamInfo.getHls())
                        .rtsp(streamInfo.getRtsp())
                        .cdn(streamInfo.getRtmp())
                        .snapURL(String.format("http://%s:%s/snap/%s.jpg", sipIp, serverPort, streamid))
                        .transport(device.getTransport())
                        .startAt(DateUtil.date(mediaJSON.getLong("createStamp")*1000).toString(DatePattern.NORM_DATETIME_PATTERN) )
                        .recordStartAt(streamInfo.getRecordStartAt())
                        .duration(mediaJSON.getLong("aliveSecond"))
                        .rTPCount(0)
                        .rTPLostCount(0)
                        .rTPLostRate(0)
                        .ondemand(1)
                        .inBytes(0L)
                        .inBitRate(mediaJSON.getLong("bytesSpeed")/1024)
                        .outBytes(0L)
                        .numOutputs(mediaJSON.getInteger("totalReaderCount"))
                        .cascadeSize(0)
                        .relaysize(0)
                        .channelPTZType(channel.getPtzType());
                tracks.forEach(o -> {if(((JSONObject) o).getIntValue("codec_type")==1){
                    skyEyeStreamInfo.audioEnable(1);
                    skyEyeStreamInfo.sourceAudioCodecName(((JSONObject) o).getString("codec_id_name"));
                    skyEyeStreamInfo.sourceAudioSampleRate(((JSONObject) o).getIntValue("sample_rate"));
                }else if(((JSONObject) o).getIntValue("codec_type")==0){
                    skyEyeStreamInfo.videoFrameCount(((JSONObject) o).getLong("fps"));
                    skyEyeStreamInfo.sourceVideoCodecName(((JSONObject) o).getString("codec_id_name"));
                    skyEyeStreamInfo.sourceVideoFrameRate(((JSONObject) o).getLong("fps"));
                    skyEyeStreamInfo.sourceVideoHeight(((JSONObject) o).getInteger("height"));
                    skyEyeStreamInfo.sourceVideoWidth(((JSONObject) o).getInteger("width"));
                }
                });
                return Mono.justOrEmpty(skyEyeStreamInfo.build());
            }
        }
        return Mono.justOrEmpty(new SkyEyeStreamInfo());
    }

    @Override
    public void closeSrsStreamIfNoClients(String streamName) {

    }

    @Override
    public void closeSrsStream(String streamName) {

    }

    @Override
    public Mono<String> getStreamIdByStreamName(String streamName) {
        return null;
    }
}
