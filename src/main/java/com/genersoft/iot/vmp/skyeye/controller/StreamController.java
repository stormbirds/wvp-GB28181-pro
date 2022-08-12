package com.genersoft.iot.vmp.skyeye.controller;

import com.genersoft.iot.vmp.skyeye.domain.SkyEyeStreamInfo;
import com.genersoft.iot.vmp.skyeye.service.IStreamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @ Description cn.stormbirds.skyeye.controller
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/12 23:05
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/stream")
public class StreamController {

    @Resource
    private IStreamService streamService;

    @GetMapping("/info")
    public Mono<SkyEyeStreamInfo> info(@RequestParam String streamid,
                                       @RequestParam(required = false) Boolean touch) {
        return streamService.info(streamid,touch);
    }
}
