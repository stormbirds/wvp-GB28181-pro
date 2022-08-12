package com.genersoft.iot.vmp.skyeye.redis;

/**
 * @ Description cn.stormbirds.skyeye.redis
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/7/27 21:34
 */
public enum RedisTopicEnums {

    TOPIC_DEVICE("device","设备状态订阅主题"),
    TOPIC_ALARM("alarm","报警信息订阅主题"),
    TOPIC_MOBILEPOSITION("mobileposition","移动位置订阅主题")
    ;

    /**
     * 主题名称
     */
    private String topic;

    /**
     * 描述
     */
    private String description;

    RedisTopicEnums(String topic, String description) {
        this.topic = topic;
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }


    public String getDescription() {
        return description;
    }
}
