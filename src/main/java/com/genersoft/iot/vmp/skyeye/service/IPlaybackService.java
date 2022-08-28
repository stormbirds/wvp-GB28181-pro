package com.genersoft.iot.vmp.skyeye.service;

import com.genersoft.iot.vmp.common.StreamInfo;
import com.genersoft.iot.vmp.vmanager.bean.WVPResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @ Description com.genersoft.iot.vmp.skyeye.service
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/8/17 0:39
 */
public interface IPlaybackService {
    DeferredResult<WVPResult<StreamInfo>> download(String serial, String code, String starttime, String endtime, Integer download_speed);
}
