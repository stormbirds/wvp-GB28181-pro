package com.genersoft.iot.vmp.skyeye.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.genersoft.iot.vmp.skyeye.enttity.StatusLogs;
import com.genersoft.iot.vmp.skyeye.mapper.StatusLogsMapper;
import com.genersoft.iot.vmp.skyeye.service.IStatusLogsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_FORMATTER;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stormbirds
 * @since 2021-12-12
 */
@Service
public class StatusLogsServiceImpl extends ServiceImpl<StatusLogsMapper, StatusLogs> implements IStatusLogsService {


    @Override
    public void deviceTimeoutOffline(String deviceId) {
        StatusLogs statusLogs = StatusLogs.builder()
                .id(IdUtil.nanoId(9))
                .serial(deviceId)
                .code("*")
                .status("OFF")
                .description("设备超时离线 OFF")
                .createdAt(LocalDateTime.now().format(NORM_DATETIME_FORMATTER))
                .build();
        save(statusLogs);
    }

    @Override
    public void deviceOnRegister(String deviceId) {
        StatusLogs statusLogs = StatusLogs.builder()
                .id(IdUtil.nanoId(9))
                .serial(deviceId)
                .code("*")
                .status("ON")
                .description("设备注册上线 ON")
                .createdAt(LocalDateTime.now().format(NORM_DATETIME_FORMATTER))
                .build();
        save(statusLogs);
    }

    @Override
    public void doUnRegister(String devicesId) {
        StatusLogs statusLogs = StatusLogs.builder()
                .id(IdUtil.nanoId(9))
                .serial(devicesId)
                .code("*")
                .status("OFF")
                .description("设备主动离线 OFF")
                .createdAt(LocalDateTime.now().format(NORM_DATETIME_FORMATTER))
                .build();
        save(statusLogs);
    }

    @Override
    public void deviceHeartbeatOnline(String deviceId) {
        StatusLogs statusLogs = StatusLogs.builder()
                .id(IdUtil.nanoId(9))
                .serial(deviceId)
                .code("*")
                .status("ON")
                .description("设备心跳上线 ON")
                .createdAt(LocalDateTime.now().format(NORM_DATETIME_FORMATTER))
                .build();
        save(statusLogs);
    }
}
