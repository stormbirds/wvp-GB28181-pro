package com.genersoft.iot.vmp.skyeye.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.genersoft.iot.vmp.skyeye.domain.OnRecordDTO;
import com.genersoft.iot.vmp.skyeye.enttity.Record;
import com.genersoft.iot.vmp.skyeye.vo.RecordListVo;

/**
 * @Description cn.stormbirds.skyeye.service
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/9 21:29
 */
public interface IRecordService extends IService<Record> {
    String start(String streamName, Integer interval);

    RecordListVo stop(String streamName);

    void onRecordMp4(Record recordDTO);

    void onRecordTs(Record recordDTO);
}