package com.genersoft.iot.vmp.skyeye.controller;

import com.genersoft.iot.vmp.skyeye.service.IRecordService;
import com.genersoft.iot.vmp.skyeye.vo.RecordListVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ Description 实时直播界面（设备通道界面点击播放弹出的弹窗播放界面）-实时录像接口
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 23:43
 */
@RestController
@RequestMapping("/api/v1/record")
public class RecordController {

    @Resource
    private IRecordService recordService;
    /**
     * 实时录像 - 实时录像开始
     *
     * @param streamid 流标识, 来自 开始直播(/api/v1/stream/start) 或 开始回放(/api/v1/playback/start) 接口返回的 StreamID
     * @param interval 录像分段周期(秒), 默认为 0 表示录像不分段
     *                 默认值: 0
     * @return
     */
    @GetMapping("/start")
    public String start(@RequestParam String streamid,
                        @RequestParam(required = false, defaultValue = "0") Integer interval) {
        return recordService.start(streamid,interval);
    }

    /**
     * 实时录像 - 实时录像停止
     *
     * @param streamid 流标识, 来自 开始直播(/api/v1/stream/start) 或 开始回放(/api/v1/playback/start) 接口返回的 StreamID
     * @return
     */
    @GetMapping("/stop")
    public RecordListVo stop(@RequestParam String streamid) {
        return recordService.stop(streamid);
    }

    /**
     * 实时录像 - 实时录像查询
     *
     * @param streamid 流标识, 来自 开始直播(/api/v1/stream/start) 或 开始回放(/api/v1/playback/start) 接口返回的 StreamID
     * @return
     */
    @GetMapping("/query")
    public RecordListVo query(@RequestParam String streamid) {
        return new RecordListVo();
    }
}

