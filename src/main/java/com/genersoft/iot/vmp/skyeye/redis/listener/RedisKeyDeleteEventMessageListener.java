package com.genersoft.iot.vmp.skyeye.redis.listener;

import com.genersoft.iot.vmp.common.VideoManagerConstants;
import com.genersoft.iot.vmp.conf.UserSetting;
import com.genersoft.iot.vmp.skyeye.service.IRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description com.genersoft.iot.vmp.skyeye.redis.listener
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/12 15:45
 */
@Slf4j
@Component
public class RedisKeyDeleteEventMessageListener implements MessageListener {

    private UserSetting userSetting;
    @Resource
    private IRecordService recordService;



    String mediaStreamAuthorityKeyPerfix ;

    public RedisKeyDeleteEventMessageListener(UserSetting userSetting) {
        this.userSetting = userSetting;
        mediaStreamAuthorityKeyPerfix =String.format("%s_rtp_", VideoManagerConstants.MEDIA_STREAM_AUTHORITY + userSetting.getServerId());
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("Redis Key Delete event: {}",message);
        if(message.toString().startsWith(mediaStreamAuthorityKeyPerfix)){
            String streamName = message.toString().substring(mediaStreamAuthorityKeyPerfix.length());
            recordService.stop(streamName);
        }
    }

}
