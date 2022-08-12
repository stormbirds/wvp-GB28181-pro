package com.genersoft.iot.vmp.skyeye.service;

import com.genersoft.iot.vmp.skyeye.vo.*;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @ Description cn.stormbirds.skyeye.service
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 18:58
 */
@Repository
public interface IDashBoardService {
    Mono<NetVo> net();

    TopVo top();

    StoreListVo store();

    Mono<AuthVo> auth();

    AuthDeviceStatus queryAllDeviceStatus();
}
