package com.genersoft.iot.vmp.skyeye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.genersoft.iot.vmp.skyeye.enttity.SipBaseconfig;
import com.genersoft.iot.vmp.skyeye.mapper.SipBaseconfigMapper;
import com.genersoft.iot.vmp.skyeye.service.ISipBaseconfigService;
import com.genersoft.iot.vmp.skyeye.service.ISmsBaseconfigService;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

import static com.genersoft.iot.vmp.skyeye.constant.RedisCacheKey.SIP_BASECONFIG_REDIS_PREFIX;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author stormbirds
 * @since 2021-09-17
 */
@Service
public class SipBaseconfigServiceImpl extends ServiceImpl<SipBaseconfigMapper, SipBaseconfig> implements ISipBaseconfigService {

    @Resource
    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;
    @Resource
    private ISmsBaseconfigService smsBaseconfigService;

    @Override
    public Mono get(String serial) {
        if (serial != null) {
            return smsBaseconfigService.getById(serial);
        }
        ReactiveHashOperations<String, String, String> reactiveHashOperations = reactiveStringRedisTemplate.opsForHash();
        return reactiveHashOperations.get(SIP_BASECONFIG_REDIS_PREFIX, "0");

    }

    @Override
    public Mono<String> update(SipBaseconfig baseconfig) {
        ReactiveHashOperations<String, String, String> reactiveHashOperations = reactiveStringRedisTemplate.opsForHash();
        return reactiveHashOperations.put(SIP_BASECONFIG_REDIS_PREFIX, "0", JSON.toJSONString(baseconfig) )
                .flatMap(ok -> {
                    if (ok) return Mono.justOrEmpty("ok");
                    else return Mono.justOrEmpty("failed");
                });
    }
}