package com.genersoft.iot.vmp.skyeye.service;

import com.genersoft.iot.vmp.skyeye.vo.RecordListVo;

/**
 * @Description cn.stormbirds.skyeye.service
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/9 21:29
 */
public interface IRecordService {
    String start(String streamName, Integer interval);

    RecordListVo stop(String streamName);
}