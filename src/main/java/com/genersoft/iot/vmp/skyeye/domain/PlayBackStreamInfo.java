package com.genersoft.iot.vmp.skyeye.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @ Description cn.stormbirds.skyeye.domain
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/7/28 21:58
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class PlayBackStreamInfo {
    @Schema(name = "是否开启音频")
    @JsonProperty("AudioEnable")
    private Boolean audioEnable;

    @Schema(name = "转推 CDN 地址")
    @JsonProperty("CDN")
    private String cdn;

    @Schema(name = "级联数")
    @JsonProperty("CascadeSize")
    private Integer cascadeSize;

    @Schema(name = "通道自定义名称")
    @JsonProperty("ChannelCustomName")
    private String channelCustomName;

    @Schema(name = "通道编号")
    @JsonProperty("ChannelID")
    private String channelID;

    @Schema(name = "通道名称")
    @JsonProperty("ChannelName")
    private String channelName;

    @Schema(name = "设备编号")
    @JsonProperty("DeviceID")
    private String deviceID;

    @Schema(name = "持续时间(秒)")
    @JsonProperty("Duration")
    private Integer duration;

    @Schema(name = "HTTP-FLV 播放地址")
    @JsonProperty("FLV")
    private String flv;

    @Schema(name = "HLS(M3U8) 播放地址")
    @JsonProperty("HLS")
    private String hls;

    @Schema(name = "收流平均码率(Kbps)")
    @JsonProperty("InBitRate")
    private Integer inBitRate;

    @Schema(name = "收流字节大小(Byte)")
    @JsonProperty("InBytes")
    private Integer inBytes;

    @Schema(name = "在线人数")
    @JsonProperty("NumOutputs")
    private Integer numOutputs;

    @Schema(name = "是否按需直播")
    @JsonProperty("Ondemand")
    private Boolean ondemand;

    @Schema(name = "分发流字节大小(Byte)")
    @JsonProperty("OutBytes")
    private Integer outBytes;

    @Schema(name = "回放文件总时长(秒)")
    @JsonProperty("PlaybackDuration")
    private Integer playbackDuration;

    @Schema(name = "下载文件链接, playback stop 之后可用")
    @JsonProperty("PlaybackFileURL")
    private String playbackFileURL;

    @Schema(name = "回放进度([0-1])")
    @JsonProperty("Progress")
    private Integer progress;

    @Schema(name = "RTMP 播放地址")
    @JsonProperty("RTMP")
    private String rtmp;

    @Schema(name = "收包数")
    @JsonProperty("RTPCount")
    private Integer rTPCount;

    @Schema(name = "丢包数")
    @JsonProperty("RTPLostCount")
    private Integer rTPLostCount;

    @Schema(name = "丢包率百分比")
    @JsonProperty("RTPLostRate")
    private Integer rTPLostRate;

    @Schema(name = "RTSP 播放地址， 需要 SMS 配置 RTSP 端口开启 RTSP 服务")
    @JsonProperty("RTSP")
    private String rtsp;

    @Schema(name = "实时录像开始时间")
    @JsonProperty("RecordStartAt")
    private String recordStartAt;

    @Schema(name = "流媒体编号")
    @JsonProperty("SMSID")
    private String smsid;

    @Schema(name = "快照地址")
    @JsonProperty("SnapURL")
    private String snapURL;

    @Schema(name = "原始音频编码")
    @JsonProperty("SourceAudioCodecName")
    private String sourceAudioCodecName;

    @Schema(name = "原始音频采样率")
    @JsonProperty("SourceAudioSampleRate")
    private Integer sourceAudioSampleRate;

    @Schema(name = "原始视频编码")
    @JsonProperty("SourceVideoCodecName")
    private String sourceVideoCodecName;

    @Schema(name = "原始视频帧率")
    @JsonProperty("SourceVideoFrameRate")
    private Integer sourceVideoFrameRate;

    @Schema(name = "原始视频高")
    @JsonProperty("SourceVideoHeight")
    private Integer sourceVideoHeight;

    @Schema(name = "原始视频宽")
    @JsonProperty("SourceVideoWidth")
    private Integer sourceVideoWidth;

    @Schema(name = "开始时间")
    @JsonProperty("StartAt")
    private String startAt;

    @Schema(name = "回放流ID")
    @JsonProperty("StreamID")
    private String streamID;

    @Schema(name = "当前回放时长(秒)")
    @JsonProperty("TimestampSec")
    private Integer timestampSec;

    @Schema(name = "流传输模式\n" +
            "\n" +
            "允许值: UDP, TCP")
    @JsonProperty("Transport")
    private String transport;

    @Schema(name = "视频帧数")
    @JsonProperty("VideoFrameCount")
    private Integer videoFrameCount;

    @Schema(name = "WEBRTC 播放地址")
    @JsonProperty("WEBRTC")
    private String webrtc;

    @Schema(name = "Websocket-FLV 播放地址")
    @JsonProperty("WS_FLV")
    private String wsFlv;
}