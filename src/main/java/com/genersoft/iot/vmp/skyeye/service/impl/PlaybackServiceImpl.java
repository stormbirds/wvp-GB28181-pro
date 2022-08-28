package com.genersoft.iot.vmp.skyeye.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.genersoft.iot.vmp.common.StreamInfo;
import com.genersoft.iot.vmp.gb28181.transmit.callback.DeferredResultHolder;
import com.genersoft.iot.vmp.service.IPlayService;
import com.genersoft.iot.vmp.skyeye.service.IPlaybackService;
import com.genersoft.iot.vmp.vmanager.bean.WVPResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @ Description com.genersoft.iot.vmp.skyeye.service.impl
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/8/18 1:10
 */
@Slf4j
@Service
public class PlaybackServiceImpl implements IPlaybackService {
    @Autowired
    private DeferredResultHolder resultHolder;

    @Autowired
    private IPlayService playService;

    @Override
    public DeferredResult<WVPResult<StreamInfo>> download(String serial, String code, String starttime, String endtime, Integer download_speed) {
        if(download_speed==null){
            download_speed = 4;
        }

        return playService.download(serial, code, starttime, endtime, download_speed, inviteStreamInfo -> {
            if(inviteStreamInfo!=null)
                log.info("inviteStreamInfo [CallId {},App {},Stream {},MediaServerItem {},Response {}]",inviteStreamInfo.getCallId(),inviteStreamInfo.getApp(),
                    inviteStreamInfo.getStream(),JSON.toJSONString( inviteStreamInfo.getMediaServerItem()),
                    inviteStreamInfo.getResponse());
        }, hookCallBack->{
            if(hookCallBack.getCode()==0){
                WVPResult<StreamInfo> wvpResult = (WVPResult<StreamInfo>) hookCallBack.getData().getData();
                StreamInfo streamInfo = wvpResult.getData();
                JSONObject jsonResult = JSON.parseObject(JSON.toJSONString(streamInfo) ,JSONObject.class);
                jsonResult.put("StreamID", jsonResult.get("stream"));
                jsonResult.put("DeviceID", jsonResult.get("deviceID"));
                jsonResult.put("ChannelID", jsonResult.get("channelId"));
                jsonResult.put("WEBRTC", jsonResult.get("rtc"));
                jsonResult.put("FLV", jsonResult.get("flv"));
                jsonResult.put("WS_FLV", jsonResult.get("ws_flv"));
                jsonResult.put("RTMP", jsonResult.get("rtmp"));
                jsonResult.put("HLS", jsonResult.get("hls"));
                jsonResult.put("Progress",streamInfo.getProgress());

                hookCallBack.getData().setData(jsonResult);
            }
            resultHolder.invokeResult(hookCallBack.getData());
        });
    }
}
