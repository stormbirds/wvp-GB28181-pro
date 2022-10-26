package com.genersoft.iot.vmp.skyeye.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.genersoft.iot.vmp.common.StreamInfo;
import com.genersoft.iot.vmp.conf.DynamicTask;
import com.genersoft.iot.vmp.conf.UserSetting;
import com.genersoft.iot.vmp.conf.exception.ControllerException;
import com.genersoft.iot.vmp.gb28181.bean.Device;
import com.genersoft.iot.vmp.gb28181.bean.DeviceChannel;
import com.genersoft.iot.vmp.gb28181.event.SipSubscribe;
import com.genersoft.iot.vmp.gb28181.session.VideoStreamSessionManager;
import com.genersoft.iot.vmp.gb28181.transmit.callback.DeferredResultHolder;
import com.genersoft.iot.vmp.gb28181.transmit.callback.RequestMessage;
import com.genersoft.iot.vmp.gb28181.transmit.cmd.impl.SIPCommander;
import com.genersoft.iot.vmp.media.zlm.ZlmHttpHookSubscribe;
import com.genersoft.iot.vmp.media.zlm.ZLMRESTfulUtils;
import com.genersoft.iot.vmp.media.zlm.dto.HookSubscribeFactory;
import com.genersoft.iot.vmp.media.zlm.dto.HookSubscribeForStreamChange;
import com.genersoft.iot.vmp.media.zlm.dto.MediaServerItem;
import com.genersoft.iot.vmp.service.IMediaServerService;
import com.genersoft.iot.vmp.service.IMediaService;
import com.genersoft.iot.vmp.service.IPlayService;
import com.genersoft.iot.vmp.service.bean.InviteTimeOutCallback;
import com.genersoft.iot.vmp.service.bean.SSRCInfo;
import com.genersoft.iot.vmp.skyeye.domain.SkyEyeStreamInfo;
import com.genersoft.iot.vmp.skyeye.service.IStreamService;
import com.alibaba.fastjson.JSONObject;
import com.genersoft.iot.vmp.skyeye.vo.StreamInfoVo;
import com.genersoft.iot.vmp.storager.IRedisCatchStorage;
import com.genersoft.iot.vmp.storager.IVideoManagerStorage;
import com.genersoft.iot.vmp.storager.dao.DeviceChannelMapper;
import com.genersoft.iot.vmp.vmanager.bean.ErrorCode;
import com.genersoft.iot.vmp.vmanager.bean.WVPResult;
import com.genersoft.iot.vmp.vmanager.gb28181.play.bean.PlayResult;
import gov.nist.javax.sip.stack.SIPDialog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Mono;

import javax.sip.ResponseEvent;
import java.util.Objects;
import java.util.UUID;

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

    @Autowired
    private DeferredResultHolder resultHolder;
    @Autowired
    private SIPCommander cmder;

    @Autowired
    private IPlayService playService;
    @Autowired
    private UserSetting userSetting;
    @Autowired
    private IMediaService mediaService;
    @Autowired
    private DynamicTask dynamicTask;
    @Autowired
    private VideoStreamSessionManager streamSession;

    @Qualifier("taskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private ZlmHttpHookSubscribe subscribe;

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
    public PlayResult play(String serial, Integer channel, String channel_id, String sms_id, String sms_group_id,
                                             String cdn, String audio, String transport, String transportmode,  Boolean checkchannelstatus, Long timeout) {
        Device device = storager.queryVideoDevice(serial);
        if (device == null ) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "device[ " + serial + " ]未找到");
        }else if (device.getOnline() == 0) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "device[ " + channel_id + " ]offline");
        }
        DeviceChannel deviceChannel = storager.queryChannel(serial, channel_id);
        if (deviceChannel == null) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "channel[ " + channel_id + " ]未找到");
        }else if (deviceChannel.getStatus() == 0) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "channel[ " + channel_id + " ]offline");
        }
        MediaServerItem mediaServerItem = playService.getNewMediaServerItem(device);
        if (mediaServerItem == null) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "未找到可用的zlm");
        }
        PlayResult playResult = new PlayResult();
        RequestMessage msg = new RequestMessage();
        String key = DeferredResultHolder.CALLBACK_CMD_PLAY + serial + channel_id;
        msg.setKey(key);
        String uuid = UUID.randomUUID().toString();
        msg.setId(uuid);
        playResult.setUuid(uuid);
        DeferredResult<WVPResult<String>> result = new DeferredResult<>(userSetting.getPlayTimeout().longValue());
        playResult.setResult(result);
        // 录像查询以channelId作为deviceId查询
        resultHolder.put(key, uuid, result);

        StreamInfo streamInfo = redisCatchStorage.queryPlayByDevice(serial, channel_id);
        playResult.setDevice(device);

        result.onCompletion(()->{
            // 点播结束时调用截图接口
            taskExecutor.execute(()->{
                // TODO 应该在上流时调用更好，结束也可能是错误结束
                String path =  "snap";
                String fileName =  serial + "_" + channel_id + ".jpg";
                if(result.getResult() instanceof WVPResult){
                    WVPResult wvpResult =  (WVPResult)result.getResult();
                    if (Objects.requireNonNull(wvpResult).getCode() == 0) {
                        StreamInfo streamInfoForSuccess = (StreamInfo)wvpResult.getData();
                        MediaServerItem mediaInfo = mediaServerService.getOne(streamInfoForSuccess.getMediaServerId());
                        String streamUrl = streamInfoForSuccess.getFmp4();
                        // 请求截图
                        log.info("[请求截图]: " + fileName);
                        zlmresTfulUtils.getSnap(mediaInfo, streamUrl, 15, 1, path, fileName);
                    }
                }else {
                    StreamInfoVo wvpResult =  (StreamInfoVo)result.getResult();
                    if (Objects.requireNonNull(wvpResult).getStreamID() !=null) {
                        MediaServerItem mediaInfo = mediaServerService.getOne(wvpResult.getSmsid());
                        String streamUrl = wvpResult.getFlv();
                        // 请求截图
                        log.info("[请求截图]: " + fileName);
                        zlmresTfulUtils.getSnap(mediaInfo, streamUrl, 15, 1, path, fileName);
                    }
                }
            });
        });
        if (streamInfo != null) {
            String streamId = streamInfo.getStream();
            if (streamId == null) {
                WVPResult wvpResult = new WVPResult();
                wvpResult.setCode(ErrorCode.ERROR100.getCode());
                wvpResult.setMsg("点播失败， redis缓存streamId等于null");
                msg.setData(wvpResult);
                resultHolder.invokeAllResult(msg);
                return playResult;
            }
            String mediaServerId = streamInfo.getMediaServerId();
            MediaServerItem mediaInfo = mediaServerService.getOne(mediaServerId);

            JSONObject rtpInfo = zlmresTfulUtils.getRtpInfo(mediaInfo, streamId);
            if(rtpInfo.getInteger("code") == 0){
                if (rtpInfo.getBoolean("exist")) {
                    StreamInfoVo streamInfoVo = new StreamInfoVo(streamInfo);
                    streamInfoVo.setChannelName(deviceChannel.getName());
                    streamInfoVo.setSnapURL(String.format("http://%s:%s/snap/%s.jpg", sipIp, serverPort, streamInfo.getStream()));
                    streamInfoVo.setTransport(device.getTransport());
                    streamInfoVo.setChannelPTZType(0);

//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put("StreamID", streamInfo.getStream());
//                    jsonObject.put("DeviceID", device.getDeviceId());
//                    jsonObject.put("ChannelID", channel_id);
//                    jsonObject.put("ChannelName", deviceChannel.getName());
//                    jsonObject.put("ChannelCustomName", "");
//                    jsonObject.put("FLV", streamInfo.getFlv());
//                    jsonObject.put("WS_FLV", streamInfo.getWs_flv());
//                    jsonObject.put("RTMP", streamInfo.getRtmp());
//                    jsonObject.put("HLS", streamInfo.getHls());
//                    jsonObject.put("RTSP", streamInfo.getRtsp());
//                    jsonObject.put("WEBRTC", streamInfo.getRtc());
//                    jsonObject.put("CDN", "");
//                    jsonObject.put("SnapURL", String.format("http://%s:%s/snap/%s.jpg", sipIp, serverPort, streamInfo.getStream()));
//                    jsonObject.put("Transport", device.getTransport());
//                    jsonObject.put("StartAt", "");
//                    jsonObject.put("RecordStartAt", streamInfo.getRecordStartAt());
//                    jsonObject.put("SMSID",streamInfo.getMediaServerId());
//                    jsonObject.put("Duration", "");
//                    jsonObject.put("SourceVideoCodecName", "");
//                    jsonObject.put("SourceVideoWidth", "");
//                    jsonObject.put("SourceVideoHeight", "");
//                    jsonObject.put("SourceVideoFrameRate", "");
//                    jsonObject.put("SourceAudioCodecName", "");
//                    jsonObject.put("SourceAudioSampleRate", "");
//                    jsonObject.put("AudioEnable", "");
//                    jsonObject.put("Ondemand", "");
//                    jsonObject.put("InBytes", "");
//                    jsonObject.put("InBitRate", "");
//                    jsonObject.put("OutBytes", "");
//                    jsonObject.put("NumOutputs", "");
//                    jsonObject.put("CascadeSize", "");
//                    jsonObject.put("RelaySize", "");
//                    jsonObject.put("ChannelPTZType", "0");
                    msg.setData(streamInfoVo);

                    resultHolder.invokeAllResult(msg);
//                    if (hookEvent != null) {
//                        hookEvent.response(mediaServerItem, JSONObject.parseObject(JSON.toJSONString(streamInfo)));
//                    }
                }else {
                    redisCatchStorage.stopPlay(streamInfo);
                    storager.stopPlay(streamInfo.getDeviceID(), streamInfo.getChannelId());
                    streamInfo = null;
                }
            }else {
                //zlm连接失败
                redisCatchStorage.stopPlay(streamInfo);
                storager.stopPlay(streamInfo.getDeviceID(), streamInfo.getChannelId());
                streamInfo = null;

            }

        }
        if (streamInfo == null) {
            String streamId = null;
            if (mediaServerItem.isRtpEnable()) {
                streamId = String.format("%s_%s", device.getDeviceId(), channel_id);
            }
            SSRCInfo ssrcInfo = mediaServerService.openRTPServer(mediaServerItem, streamId, device.isSsrcCheck(), false);
            log.info(JSONObject.toJSONString(ssrcInfo));
            play(mediaServerItem, ssrcInfo, device, channel_id, (mediaServerItemInUse, response)->{
//                if (hookEvent != null) {
//                    hookEvent.response(mediaServerItem, response);
//                }
            }, event -> {
                // sip error错误
                WVPResult wvpResult = new WVPResult();
                wvpResult.setCode(ErrorCode.ERROR100.getCode());
//                wvpResult.setMsg(String.format("点播失败， 错误码： %s, %s", event.statusCode, event.msg));
                wvpResult.setMsg("点播失败");

                msg.setData(wvpResult);
                resultHolder.invokeAllResult(msg);
//                if (errorEvent != null) {
//                    errorEvent.response(event);
//                }
            }, (code, msgStr)->{
                // invite点播超时
                WVPResult wvpResult = new WVPResult();
                wvpResult.setCode(ErrorCode.ERROR100.getCode());
                if (code == 0) {
                    wvpResult.setMsg("点播超时，请稍候重试");
                }else if (code == 1) {
                    wvpResult.setMsg("收流超时，请稍候重试");
                }
                msg.setData(wvpResult);
                // 回复之前所有的点播请求
                resultHolder.invokeAllResult(msg);
            }, uuid);
        }
        return playResult;
    }

    public void play(MediaServerItem mediaServerItem, SSRCInfo ssrcInfo, Device device, String channelId,
                     ZlmHttpHookSubscribe.Event hookEvent, SipSubscribe.Event errorEvent,
                     InviteTimeOutCallback timeoutCallback, String uuid) {

        String streamId = null;
        if (mediaServerItem.isRtpEnable()) {
            streamId = String.format("%s_%s", device.getDeviceId(), channelId);
        }
        if (ssrcInfo == null) {
            ssrcInfo = mediaServerService.openRTPServer(mediaServerItem, streamId, device.isSsrcCheck(), false);
        }
        log.info("[点播开始] deviceId: {}, channelId: {}, SSRC: {}", device.getDeviceId(), channelId, ssrcInfo.getSsrc() );
        // 超时处理
        String timeOutTaskKey = UUID.randomUUID().toString();
        SSRCInfo finalSsrcInfo = ssrcInfo;
        dynamicTask.startDelay( timeOutTaskKey,()->{

            SIPDialog dialog = streamSession.getDialogByStream(device.getDeviceId(), channelId, finalSsrcInfo.getStream());
            if (dialog != null) {
                log.info("[点播超时] 收流超时 deviceId: {}, channelId: {}", device.getDeviceId(), channelId);
                timeoutCallback.run(1, "收流超时");
                // 点播超时回复BYE 同时释放ssrc以及此次点播的资源
                cmder.streamByeCmd(device.getDeviceId(), channelId, finalSsrcInfo.getStream(), null);
            }else {
                log.info("[点播超时] 消息未响应 deviceId: {}, channelId: {}", device.getDeviceId(), channelId);
                timeoutCallback.run(0, "点播超时");
                mediaServerService.releaseSsrc(mediaServerItem.getId(), finalSsrcInfo.getSsrc());
                mediaServerService.closeRTPServer(device.getDeviceId(), channelId, finalSsrcInfo.getStream());
                streamSession.remove(device.getDeviceId(), channelId, finalSsrcInfo.getStream());
            }
        }, userSetting.getPlayTimeout());
        final String ssrc = ssrcInfo.getSsrc();
        final String stream = ssrcInfo.getStream();
        //端口获取失败的ssrcInfo 没有必要发送点播指令
        if(ssrcInfo.getPort() <= 0){
            log.info("[点播端口分配异常]，deviceId={},channelId={},ssrcInfo={}", device.getDeviceId(), channelId, ssrcInfo);
            return;
        }
        cmder.playStreamCmd(mediaServerItem, ssrcInfo, device, channelId, (MediaServerItem mediaServerItemInuse, JSONObject response) -> {
            log.info("收到订阅消息： " + response.toJSONString());
            dynamicTask.stop(timeOutTaskKey);
            // hook响应
            onPublishHandlerForPlay(mediaServerItemInuse, response, device.getDeviceId(), channelId, uuid);
            hookEvent.response(mediaServerItemInuse, response);
            log.info("[点播成功] deviceId: {}, channelId: {}", device.getDeviceId(), channelId);

        }, (event) -> {
            ResponseEvent responseEvent = (ResponseEvent)event.event;
            String contentString = new String(responseEvent.getResponse().getRawContent());
            // 获取ssrc
            int ssrcIndex = contentString.indexOf("y=");
            // 检查是否有y字段
            if (ssrcIndex >= 0) {
                //ssrc规定长度为10字节，不取余下长度以避免后续还有“f=”字段 TODO 后续对不规范的非10位ssrc兼容
                String ssrcInResponse = contentString.substring(ssrcIndex + 2, ssrcIndex + 12);
                // 查询到ssrc不一致且开启了ssrc校验则需要针对处理
                if (ssrc.equals(ssrcInResponse)) {
                    return;
                }
                log.info("[点播消息] 收到invite 200, 发现下级自定义了ssrc: {}", ssrcInResponse );
                if (!mediaServerItem.isRtpEnable() || device.isSsrcCheck()) {
                    log.info("[SIP 消息] SSRC修正 {}->{}", ssrc, ssrcInResponse);

                    if (!mediaServerItem.getSsrcConfig().checkSsrc(ssrcInResponse)) {
                        // ssrc 不可用
                        // 释放ssrc
                        mediaServerService.releaseSsrc(mediaServerItem.getId(), finalSsrcInfo.getSsrc());
                        streamSession.remove(device.getDeviceId(), channelId, finalSsrcInfo.getStream());
                        event.msg = "下级自定义了ssrc,但是此ssrc不可用";
                        event.statusCode = 400;
                        errorEvent.response(event);
                        return;
                    }

                    // 单端口模式streamId也有变化，需要重新设置监听
                    if (!mediaServerItem.isRtpEnable()) {
                        // 添加订阅
                        HookSubscribeForStreamChange hookSubscribe = HookSubscribeFactory.on_stream_changed("rtp", stream, true, "rtsp", mediaServerItem.getId());
                        subscribe.removeSubscribe(hookSubscribe);
                        hookSubscribe.getContent().put("stream", String.format("%08x", Integer.parseInt(ssrcInResponse)).toUpperCase());
                        subscribe.addSubscribe(hookSubscribe, (MediaServerItem mediaServerItemInUse, JSONObject response)->{
                            log.info("[ZLM HOOK] ssrc修正后收到订阅消息： " + response.toJSONString());
                            dynamicTask.stop(timeOutTaskKey);
                            // hook响应
                            onPublishHandlerForPlay(mediaServerItemInUse, response, device.getDeviceId(), channelId, uuid);
                            hookEvent.response(mediaServerItemInUse, response);
                        });
                    }
                    // 关闭rtp server
                    mediaServerService.closeRTPServer(device.getDeviceId(), channelId, finalSsrcInfo.getStream());
                    // 重新开启ssrc server
                    mediaServerService.openRTPServer(mediaServerItem, finalSsrcInfo.getStream(), ssrcInResponse, device.isSsrcCheck(), false, finalSsrcInfo.getPort());

                }
            }
        }, (event) -> {
            dynamicTask.stop(timeOutTaskKey);
            mediaServerService.closeRTPServer(device.getDeviceId(), channelId, finalSsrcInfo.getStream());
            // 释放ssrc
            mediaServerService.releaseSsrc(mediaServerItem.getId(), finalSsrcInfo.getSsrc());

            streamSession.remove(device.getDeviceId(), channelId, finalSsrcInfo.getStream());
            errorEvent.response(event);
        });
    }

    private void onPublishHandlerForPlay(MediaServerItem mediaServerItem, JSONObject response, String deviceId, String channelId, String uuid) {
        RequestMessage msg = new RequestMessage();
        if (uuid != null) {
            msg.setId(uuid);
        }
        msg.setKey(DeferredResultHolder.CALLBACK_CMD_PLAY + deviceId + channelId);
        StreamInfo streamInfo = onPublishHandler(mediaServerItem, response, deviceId, channelId);
        if (streamInfo != null) {
            DeviceChannel deviceChannel = storager.queryChannel(deviceId, channelId);
            if (deviceChannel != null) {
                deviceChannel.setStreamId(streamInfo.getStream());
                storager.startPlay(deviceId, channelId, streamInfo.getStream());
            }
            redisCatchStorage.startPlay(streamInfo);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("StreamID", streamInfo.getStream());
            jsonObject.put("DeviceID", deviceId);
            jsonObject.put("ChannelID", channelId);
            jsonObject.put("ChannelName", deviceChannel.getName());
            jsonObject.put("ChannelCustomName", "");
            jsonObject.put("FLV", streamInfo.getFlv());
            jsonObject.put("WS_FLV", streamInfo.getWs_flv());
            jsonObject.put("RTMP", streamInfo.getRtmp());
            jsonObject.put("HLS", streamInfo.getHls());
            jsonObject.put("RTSP", streamInfo.getRtsp());
            jsonObject.put("WEBRTC", streamInfo.getRtc());
            jsonObject.put("CDN", "");
            jsonObject.put("SnapURL", String.format("http://%s:%s/snap/%s.jpg", sipIp, serverPort, streamInfo.getStream()));
            jsonObject.put("Transport", "");
            jsonObject.put("StartAt", "");
            jsonObject.put("RecordStartAt", streamInfo.getRecordStartAt());
            jsonObject.put("SMSID",streamInfo.getMediaServerId());
            jsonObject.put("Duration", "");
            jsonObject.put("SourceVideoCodecName", "");
            jsonObject.put("SourceVideoWidth", "");
            jsonObject.put("SourceVideoHeight", "");
            jsonObject.put("SourceVideoFrameRate", "");
            jsonObject.put("SourceAudioCodecName", "");
            jsonObject.put("SourceAudioSampleRate", "");
            jsonObject.put("AudioEnable", "");
            jsonObject.put("Ondemand", "");
            jsonObject.put("InBytes", "");
            jsonObject.put("InBitRate", "");
            jsonObject.put("OutBytes", "");
            jsonObject.put("NumOutputs", "");
            jsonObject.put("CascadeSize", "");
            jsonObject.put("RelaySize", "");
            jsonObject.put("ChannelPTZType", "0");
            Device device = storager.queryVideoDevice(deviceId);
            StreamInfoVo streamInfoVo = new StreamInfoVo(streamInfo);
            streamInfoVo.setChannelName(deviceChannel.getName());
            streamInfoVo.setSnapURL(String.format("http://%s:%s/snap/%s.jpg", sipIp, serverPort, streamInfo.getStream()));
            streamInfoVo.setTransport(device.getTransport());
            streamInfoVo.setChannelPTZType(0);
            msg.setData(streamInfoVo);

            resultHolder.invokeAllResult(msg);
        } else {
            log.warn("设备预览API调用失败！");
            msg.setData(WVPResult.fail(ErrorCode.ERROR100.getCode(), "设备预览API调用失败！"));
            resultHolder.invokeAllResult(msg);
        }
    }

    public StreamInfo onPublishHandler(MediaServerItem mediaServerItem, JSONObject resonse, String deviceId, String channelId) {
        String streamId = resonse.getString("stream");
        JSONArray tracks = resonse.getJSONArray("tracks");
        StreamInfo streamInfo = mediaService.getStreamInfoByAppAndStream(mediaServerItem,"rtp", streamId, tracks, null);
        streamInfo.setDeviceID(deviceId);
        streamInfo.setChannelId(channelId);
        return streamInfo;
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
