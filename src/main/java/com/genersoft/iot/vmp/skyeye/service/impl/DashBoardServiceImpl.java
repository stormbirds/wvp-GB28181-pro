package com.genersoft.iot.vmp.skyeye.service.impl;

import com.genersoft.iot.vmp.skyeye.config.SysMonitorConfig;
import com.genersoft.iot.vmp.skyeye.mapper.DashBoardMapper;
import com.genersoft.iot.vmp.skyeye.service.IDashBoardService;
import com.genersoft.iot.vmp.skyeye.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @ Description cn.stormbirds.skyeye.service.impl
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 19:55
 */
@Slf4j
@Service
public class DashBoardServiceImpl implements IDashBoardService {

    @Resource
    private SysMonitorConfig sysMonitorConfig;
    @Resource
    private DashBoardMapper dashBoardMapper;

    @Override
    public Mono<NetVo> net() {
        return Mono.just(new NetVo(sysMonitorConfig.gerRxBytes(),
                sysMonitorConfig.getTxBytes(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))));

    }

    @Override
    public TopVo top() {
        return new TopVo(200,
                new TopVo.DataDTO(new ArrayList<>(sysMonitorConfig.getCpuQueue()),
                        new ArrayList<>(sysMonitorConfig.getLoadQueue()),
                        new ArrayList<>(sysMonitorConfig.getMemQueue()),
                        new ArrayList<>(sysMonitorConfig.getNetQueue())), "Success");
    }

    @Override
    public StoreListVo store() {
        return sysMonitorConfig.getStore();
    }

    @Override
    public Mono<AuthVo> auth() {
        return Mono.justOrEmpty(sysMonitorConfig.getAuth());
    }

    @Override
    public AuthDeviceStatus queryAllDeviceStatus() {
        return dashBoardMapper.queryAllDeviceStatus();
    }

}