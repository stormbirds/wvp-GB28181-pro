package com.genersoft.iot.vmp.skyeye.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ Description cn.stormbirds.skyeye.redis
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/7/28 0:48
 */
@Component
public class RedisMsgPublisher {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void sendMsg(RedisTopicEnums topicEnums, String msg){
        stringRedisTemplate.convertAndSend(topicEnums.getTopic(),msg);
    }


}
