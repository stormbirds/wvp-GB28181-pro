package com.genersoft.iot.vmp.skyeye.constant;

/**
 * @Description com.genersoft.iot.vmp.skyeye.constant
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/11 11:35
 */
public class RedisCacheKey {
    public static String SMS_BASECONFIG_REDIS_PREFIX = "smsbaseconfig";
    public static String SIP_BASECONFIG_REDIS_PREFIX = "sipbaseconfig";

    /**
     * 当前正在推流的流信息 HASH数据结构
     * start_time 开始时间
     */
    public static final String KEY_STREAM_INFO_HASH = "STREAM_INFO:%s";

    public static String getKey(String keyFormater,Object ...p){
        return String.format(keyFormater,p);
    }
}
