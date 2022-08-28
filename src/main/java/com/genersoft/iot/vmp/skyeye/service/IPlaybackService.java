package com.genersoft.iot.vmp.skyeye.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @ Description com.genersoft.iot.vmp.skyeye.service
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/8/17 0:39
 */
public interface IPlaybackService {
    DeferredResult<String> download(String serial, String code, String starttime, String endtime, Integer download_speed);
}
