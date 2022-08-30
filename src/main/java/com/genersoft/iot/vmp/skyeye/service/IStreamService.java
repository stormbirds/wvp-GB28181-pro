package com.genersoft.iot.vmp.skyeye.service;

import com.genersoft.iot.vmp.common.StreamInfo;
import com.genersoft.iot.vmp.skyeye.domain.SkyEyeStreamInfo;
import com.genersoft.iot.vmp.skyeye.vo.StreamInfoVo;
import com.genersoft.iot.vmp.vmanager.gb28181.play.bean.PlayResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Mono;

/**
 * @ Description cn.stormbirds.skyeye.service
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/28 1:58
 */
public interface IStreamService {

    /**
     * 基于国标号获取平台流信息
     * @param streamName SRS中流媒体名字(设备ID_通道ID)
     * @return 平台流信息
     */
    Mono<SkyEyeStreamInfo> getStreamInfoByStreamName(String streamName);

    PlayResult play(String serial , Integer channel , String code, String sms_id, String sms_group_id, String cdn, String audio,
                    String transport , String transportmode, Boolean checkchannelstatus, Long timeout);

    /**
     * 获取平台流信息
     * @param streamid SRS流ID
     * @param touch
     * @return 平台流信息
     */
    Mono<SkyEyeStreamInfo> info(String streamid, Boolean touch);

    /**
     * 检测流中是否播放客户端为0则关闭流，如果有播放客户端则不操作
     * @param streamName 流名称
     */
    void closeSrsStreamIfNoClients(String streamName);

    /**
     * 直接关闭流
     * @param streamName 流名称
     */
    void closeSrsStream(String streamName);

    /**
     * 通过流名称获取流ID（SRS平台中流ID）
     * @param streamName 流名称
     * @return
     */
    Mono<String> getStreamIdByStreamName(String streamName);
}