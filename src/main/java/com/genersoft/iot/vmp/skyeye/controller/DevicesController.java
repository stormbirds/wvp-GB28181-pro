package com.genersoft.iot.vmp.skyeye.controller;

import com.genersoft.iot.vmp.service.IDeviceService;
import com.genersoft.iot.vmp.skyeye.vo.DeviceTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private IDeviceService deviceService;
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

    @GetMapping("/channeltree")
    public List<DeviceTree> channeltree(@ApiParam(required = false) Boolean subfetch,
                                        @ApiParam(required = false) String serial,
                                        @ApiParam(required = false) String pcode,
                                        @ApiParam(required = false) Integer limit) {
        subfetch = subfetch == null ? false : subfetch;
        limit = limit == null ? 1000 : limit;
        return deviceService.channeltree(serial, subfetch, pcode, limit);
    }


    @GetMapping("/grouptree")
    public List<DeviceTree> grouptree(@ApiParam(required = false) Boolean subfetch,
                                      @ApiParam(required = false) String serial,
                                      @ApiParam(required = false) String pcode,
                                      @ApiParam(required = false) Integer limit) {
        return new ArrayList<>();
    }

    @GetMapping("/remove")
    public String remove(@ApiParam String serial) {
        return deviceService.removeDeviceById(serial)?"ok":"failure";
    }
}
