package com.genersoft.iot.vmp.skyeye.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author stormbirds
 * @since 2021-09-14
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class SkyEyeStreamInfo implements Serializable {

    private static final long serialVersionUID = -7132538169020897684L;
    @JsonProperty("AudioEnable")
    private Integer audioEnable;

    @JsonProperty("CDN")
    private String cdn;

    @JsonProperty("CascadeSize")
    private Integer cascadeSize;

    @JsonProperty("ChannelCustomName")
    private String channelCustomName;

    @JsonProperty("ChannelID")
    private String channelID;

    @JsonProperty("ChannelName")
    private String channelName;

    @JsonProperty("ChannelPTZType")
    private Integer channelPTZType;

    @JsonProperty("DeviceID")
    private String deviceID;

    @JsonProperty("Duration")
    private Long duration;

    @JsonProperty("FLV")
    private String flv;

    @JsonProperty("HLS")
    private String hls;

    @JsonProperty("InBitRate")
    private Long inBitRate;

    @JsonProperty("InBytes")
    private Long inBytes;

    @JsonProperty("NumOutputs")
    private Integer numOutputs;

    @JsonProperty("Ondemand")
    private Integer ondemand;

    @JsonProperty("OutBytes")
    private Long outBytes;

    @JsonProperty("RTMP")
    private String rtmp;

    @JsonProperty("RTPCount")
    private Integer rTPCount;

    @JsonProperty("RTPLostCount")
    private Integer rTPLostCount;

    @JsonProperty("RTPLostRate")
    private Integer rTPLostRate;

    @JsonProperty("RTSP")
    private String rtsp;

    @JsonProperty("RecordStartAt")
    private String recordStartAt;

    @JsonProperty("RelaySize")
    private Integer relaysize;

    @JsonProperty("SMSID")
    private String smsid;

    @JsonProperty("SnapURL")
    private String snapURL;

    @JsonProperty("SourceAudioCodecName")
    private String sourceAudioCodecName;

    @JsonProperty("SourceAudioSampleRate")
    private Integer sourceAudioSampleRate;

    @JsonProperty("SourceVideoCodecName")
    private String sourceVideoCodecName;

    @JsonProperty("SourceVideoFrameRate")
    private Long sourceVideoFrameRate;

    @JsonProperty("SourceVideoHeight")
    private Integer sourceVideoHeight;

    @JsonProperty("SourceVideoWidth")
    private Integer sourceVideoWidth;

    @JsonProperty("StartAt")
    private String startAt;

    @JsonProperty("StreamID")
    private String streamID;

    @JsonProperty("Transport")
    private String transport;

    @JsonProperty("VideoFrameCount")
    private Long videoFrameCount;

    @JsonProperty("WEBRTC")
    private String webrtc;

    @JsonProperty("WS_FLV")
    private String wsFlv;

    @JsonProperty("PlaybackDuration")
    private Integer playbackDuration;

    @JsonProperty("PlaybackFileURL")
    private String playbackFileURL;

    @JsonProperty("Progress")
    private Float progress;

    @JsonProperty("TimestampSec")
    private Integer timestampSec;


}
