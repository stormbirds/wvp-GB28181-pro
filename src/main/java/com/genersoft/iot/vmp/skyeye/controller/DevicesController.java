package com.genersoft.iot.vmp.skyeye.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stormbirds
 * @since 2021-12-12
 */
@Slf4j
@Api(tags = "设备信息")
@RestController
@RequestMapping("/api/v1/device")
public class DevicesController {

    /**
     * 设备信息 - 查询单条设备信息
     *
     * @param serial 设备编号
     * @return
     */
//    @ApiOperation(value = "设备信息 - 查询单条设备信息")
//    @GetMapping("/info")
//    public Devices info(@ApiParam(value = "设备编号\n" +
//            "\n",required = true) String serial) {
//        return devicesService.getById(serial);
//    }
}
