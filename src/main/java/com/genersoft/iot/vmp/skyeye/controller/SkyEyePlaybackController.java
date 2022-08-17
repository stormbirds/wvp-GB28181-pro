package com.genersoft.iot.vmp.skyeye.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.genersoft.iot.vmp.common.StreamInfo;
import com.genersoft.iot.vmp.gb28181.bean.Device;
import com.genersoft.iot.vmp.gb28181.bean.DeviceChannel;
import com.genersoft.iot.vmp.gb28181.bean.RecordInfo;
import com.genersoft.iot.vmp.gb28181.transmit.callback.DeferredResultHolder;
import com.genersoft.iot.vmp.gb28181.transmit.callback.RequestMessage;
import com.genersoft.iot.vmp.gb28181.transmit.cmd.impl.SIPCommander;
import com.genersoft.iot.vmp.service.IDeviceChannelService;
import com.genersoft.iot.vmp.service.IMediaService;
import com.genersoft.iot.vmp.service.IPlayService;
import com.genersoft.iot.vmp.skyeye.domain.PlayBackStreamInfo;
import com.genersoft.iot.vmp.skyeye.service.IPlaybackService;
import com.genersoft.iot.vmp.skyeye.vo.DeviceRecordList;
import com.genersoft.iot.vmp.storager.IVideoManagerStorage;
import com.genersoft.iot.vmp.vmanager.bean.WVPResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;

/**
 * @ Description cn.stormbirds.skyeye.controller
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 23:32
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/playback")
public class SkyEyePlaybackController {

    @Autowired
    private DeferredResultHolder resultHolder;

    @Resource
    private IPlaybackService playbackService;
@Autowired
private IPlayService playService;
    @Resource
    private IDeviceChannelService deviceChannelService;
    @Autowired
    private IVideoManagerStorage storager;

    @Autowired
    private IMediaService mediaService;
    @Autowired
    private SIPCommander cmder;

    @GetMapping("/recordlist")
    public DeferredResult<DeviceRecordList> recordlist(@RequestParam String serial,
                                             @RequestParam(required = false) String channel,
                                             @RequestParam(required = false) String code,
                                             @RequestParam(required = true) String starttime,
                                             @RequestParam(required = false) String endtime,
                                             @RequestParam(required = false) String type,
                                             @RequestParam(required = false) Boolean center,
                                             @RequestParam(required = false) Boolean indistinct,
                                             @RequestParam(required = false) Integer timeout) {
        DeferredResult<DeviceRecordList> result = new DeferredResult<>();
        Device device = storager.queryVideoDevice(serial);
        DeviceChannel deviceChannel ;
        if(StringUtils.hasText(code)){
            deviceChannel = deviceChannelService.getOne(Wrappers.<DeviceChannel>lambdaQuery()
                    .eq(DeviceChannel::getDeviceId,serial)
                    .eq(DeviceChannel::getChannelId,code));
        }else if(StringUtils.hasText(channel)){
            deviceChannel = deviceChannelService.getOne(Wrappers.<DeviceChannel>lambdaQuery()
                    .eq(DeviceChannel::getDeviceId,serial)
                    .eq(DeviceChannel::getChannel,channel));
        }else {
            result.setErrorResult("缺少必要参数channel 或 code");
            return result;
        }
        if(!StringUtils.hasText(endtime)){
            endtime = LocalDateTimeUtil.now().format(DatePattern.NORM_DATETIME_FORMATTER);
        }else{
            endtime = LocalDateTimeUtil.parse(endtime,DatePattern.UTC_SIMPLE_PATTERN).format(DatePattern.NORM_DATETIME_FORMATTER);
        }
        starttime = LocalDateTimeUtil.parse(starttime,DatePattern.UTC_SIMPLE_PATTERN).format(DatePattern.NORM_DATETIME_FORMATTER);
        int sn = (int)((Math.random()*9+1)*10000000);
        String key = DeferredResultHolder.CALLBACK_CMD_RECORDINFO + serial + sn;
        RequestMessage msg = new RequestMessage();
        String queryId = IdUtil.nanoId(9);
        msg.setId(queryId);
        msg.setKey(key);
        cmder.recordInfoQuery(device, deviceChannel.getChannelId(), starttime, endtime, sn, null, type, null, (eventResult -> {
            WVPResult<RecordInfo> wvpResult = new WVPResult<>();
            wvpResult.setCode(-1);
            wvpResult.setMsg("查询录像失败, status: " +  eventResult.statusCode + ", message: " + eventResult.msg);
            msg.setData(wvpResult);
            resultHolder.invokeResult(msg);
        }));

        // 录像查询以channelId作为deviceId查询
        resultHolder.put(key, queryId, result);
        result.onTimeout(()->{
            msg.setData("timeout");
            WVPResult<RecordInfo> wvpResult = new WVPResult<>();
            wvpResult.setCode(-1);
            wvpResult.setMsg("timeout");
            msg.setData(wvpResult);
            resultHolder.invokeResult(msg);
        });
        return result;
    }

    @GetMapping("/start")
    public DeferredResult<ResponseEntity<String>> start(@RequestParam String serial,
                                          @RequestParam(required = false) Integer channel,
                                          @RequestParam(required = false) String code,
                                          @RequestParam(required = false) String starttime,
                                          @RequestParam(required = false) String endtime,
                                          @RequestParam(required = false) Boolean download,
                                          @RequestParam(required = false) Integer download_speed,
                                          @RequestParam(required = false) String sms_id,
                                          @RequestParam(required = false) String sms_group_id,
                                          @RequestParam(required = false) String cdn,
                                          @RequestParam(required = false) String audio,
                                          @RequestParam(required = false) String transport,
                                          @RequestParam(required = false) String transport_mode,
                                          @RequestParam(required = false) Integer timeout) {
        if(endtime.contains("T"))
            endtime = LocalDateTimeUtil.parse(endtime,DatePattern.UTC_SIMPLE_PATTERN).format(DatePattern.NORM_DATETIME_FORMATTER);
        if(starttime.contains("T"))
            starttime = LocalDateTimeUtil.parse(starttime,DatePattern.UTC_SIMPLE_PATTERN).format(DatePattern.NORM_DATETIME_FORMATTER);
        if(download!=null && download){
            return playbackService.download(serial, code, starttime, endtime, download_speed);
        }
        return playService.playBack(serial, code, starttime, endtime, null, wvpResult->{
            String jsonStr = String.valueOf(wvpResult.getData().getData());
            JSONObject streamInfo = JSON.parseObject(jsonStr,JSONObject.class);
            streamInfo.put("StreamID",streamInfo.get("stream"));
            streamInfo.put("DeviceID",streamInfo.get("deviceID"));
            streamInfo.put("ChannelID",streamInfo.get("channelId"));
            streamInfo.put("WEBRTC",streamInfo.get("rtc"));
            streamInfo.put("FLV",streamInfo.get("flv"));
            streamInfo.put("WS_FLV",streamInfo.get("ws_flv"));
            streamInfo.put("RTMP",streamInfo.get("rtmp"));
            streamInfo.put("HLS",streamInfo.get("hls"));
            wvpResult.getData().setData(streamInfo);
            resultHolder.invokeResult(wvpResult.getData());
        });
    }

    @GetMapping("/stop")
    public String stop(@RequestParam String streamid) {
        log.info("手动停止回放推流");

        StreamInfo streamInfo = mediaService. getStreamInfoByAppAndStreamWithCheck("rtp",streamid,null,true);
        cmder.streamByeCmd(streamInfo.getDeviceID(), streamInfo.getChannelId(), streamInfo.getStream(), null);
        return "";
    }

    @GetMapping("/control")
    public String control(@RequestParam String streamid,
                          @RequestParam Integer command,
                          @RequestParam(required = false) String range,
                          @RequestParam(required = false) Integer scale) {
        return "ok";
    }

    @GetMapping("/streaminfo")
    public JSONObject streaminfo(@RequestParam String streamid) {
        StreamInfo streamInfoTmp = mediaService.getStreamInfoByAppAndStreamWithCheck("rtp", streamid, null, true);
        if(streamInfoTmp==null){
            JSONObject json = new JSONObject();
            json.put("error","该流不存在");
            return json;
        }
        StreamInfo streamInfo = playService.getDownLoadInfo(streamInfoTmp.getDeviceID(), streamInfoTmp.getChannelId(), streamid);
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
        return jsonResult;
    }

    @GetMapping("/touch")
    public String touch(@RequestParam String streamid,
                        @RequestParam(required = false) String token) {
        return "ok";
    }
}