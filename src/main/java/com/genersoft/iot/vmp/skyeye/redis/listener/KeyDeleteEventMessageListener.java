package com.genersoft.iot.vmp.skyeye.redis.listener;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisKeyspaceEvent;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.lang.Nullable;

/**
 * @Description com.usmart.ipems.ipemsx.config
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2021/5/29 0:05
 */
public class KeyDeleteEventMessageListener extends KeyspaceEventMessageListener {
    private static final Topic KEYEVENT_DELETE_TOPIC = new PatternTopic("__keyevent@*__:del");

    private @Nullable
    ApplicationEventPublisher publisher;
    public KeyDeleteEventMessageListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    protected void doRegister(RedisMessageListenerContainer listenerContainer) {
        listenerContainer.addMessageListener(this, KEYEVENT_DELETE_TOPIC);
    }

    protected void doHandleMessage(Message message) {
        this.publishEvent(new RedisKeyspaceEvent(message.getBody()));
    }

    protected void publishEvent(RedisKeyspaceEvent event) {
        if (this.publisher != null) {
            this.publisher.publishEvent(event);
        }

    }

}