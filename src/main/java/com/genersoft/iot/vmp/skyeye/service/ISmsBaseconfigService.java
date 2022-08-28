package com.genersoft.iot.vmp.skyeye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.genersoft.iot.vmp.skyeye.enttity.SmsBaseconfig;
import reactor.core.publisher.Mono;

/**
 * @Description com.genersoft.iot.vmp.skyeye.service
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/11 11:33
 */
public interface ISmsBaseconfigService extends IService<SmsBaseconfig> {
    Mono<SmsBaseconfig> getById(String serial);

    Mono<Boolean> update(SmsBaseconfig baseconfig);
}
