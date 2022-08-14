package com.genersoft.iot.vmp.skyeye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.genersoft.iot.vmp.skyeye.enttity.StatusLogs;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stormbirds
 * @since 2021-12-12
 */
public interface IStatusLogsService extends IService<StatusLogs> {

    void deviceTimeoutOffline(String deviceId);

    void deviceOnRegister(String deviceId);

    void doUnRegister(String devicesId);

    void deviceHeartbeatOnline(String deviceId);
}