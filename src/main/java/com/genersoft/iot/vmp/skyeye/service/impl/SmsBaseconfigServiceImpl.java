package com.genersoft.iot.vmp.skyeye.service.impl;

import com.genersoft.iot.vmp.skyeye.enttity.SmsBaseconfig;
import com.genersoft.iot.vmp.skyeye.service.ISmsBaseconfigService;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

import static com.genersoft.iot.vmp.skyeye.constant.RedisCacheKey.SMS_BASECONFIG_REDIS_PREFIX;

/**
 * @ Description cn.stormbirds.skyeye.service.impl
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/16 0:33
 */
@Service
public class SmsBaseconfigServiceImpl implements ISmsBaseconfigService {

    @Resource
    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;

    @Override
    public Mono<SmsBaseconfig> getById(String serial) {
        ReactiveHashOperations<String, String, String> reactiveHashOperations = reactiveStringRedisTemplate.opsForHash();
        return reactiveHashOperations.get(SMS_BASECONFIG_REDIS_PREFIX, serial).map(s -> JSON.parseObject(s,SmsBaseconfig.class));
    }

    @Override
    public Mono<Boolean> update(SmsBaseconfig baseconfig) {
        ReactiveHashOperations<String, String, String> reactiveHashOperations = reactiveStringRedisTemplate.opsForHash();
        return reactiveHashOperations.put(SMS_BASECONFIG_REDIS_PREFIX, baseconfig.getSerial(), JSON.toJSONString(baseconfig) );
    }
}