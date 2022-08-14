package com.genersoft.iot.vmp.skyeye.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.genersoft.iot.vmp.skyeye.enttity.RecordChannels;
import com.genersoft.iot.vmp.skyeye.vo.CloudRecordListVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stormbirds
 * @since 2022-07-30
 */
public interface IRecordChannelsService extends IService<RecordChannels> {

    CloudRecordListVo querychannels(Integer start, Integer limit, String sort, String order, String q);

    String queryMonthly(String serial, String code, String period);

    JSONObject queryFlags(String serial, String code);
}
