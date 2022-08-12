package com.genersoft.iot.vmp.skyeye.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @ Description cn.stormbirds.skyeye.redis
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/7/28 0:52
 */
@Slf4j
@Component
public class RedisMsgListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("Redis received: {}",message.toString());
    }
}
