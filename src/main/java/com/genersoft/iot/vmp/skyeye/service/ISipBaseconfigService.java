package com.genersoft.iot.vmp.skyeye.service;

import com.genersoft.iot.vmp.skyeye.enttity.SipBaseconfig;
import reactor.core.publisher.Mono;

/**
 * @Description com.genersoft.iot.vmp.skyeye.service
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/11 11:30
 */
public interface ISipBaseconfigService {
    Mono get(String serial);

    Mono<String> update(SipBaseconfig baseconfig);
}
