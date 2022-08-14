package com.genersoft.iot.vmp.skyeye.controller;

import com.genersoft.iot.vmp.service.IDeviceAlarmService;
import com.genersoft.iot.vmp.skyeye.enttity.Alarms;
import com.genersoft.iot.vmp.skyeye.vo.AlarmListVo;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stormbirds
 * @since 2021-12-12
 */
@RestController
@RequestMapping("/api/v1/alarm")
public class AlarmsController {

    @Resource
    private IDeviceAlarmService alarmsService;

    @GetMapping("/report")
    public String report(@ApiParam()String device,
                         @ApiParam()String channel,
                         @ApiParam(required = false)String level,
                         @ApiParam(required = false)Integer method,
                         @ApiParam(required = false)String alarmType,
                         @ApiParam(required = false)String alarmTime){
        return "ok";
    }


    /**
     * 设备报警 - 查询报警列表
     *
     * @param serial    设备编号
     * @param code      通道编号,通过 /api/v1/device/channellist 获取的 ChannelList.ID
     * @param starttime 开始时间, YYYY-MM-DDTHH:mm:ss
     * @param endtime   结束时间, YYYY-MM-DDTHH:mm:ss
     * @param priority  报警级别(1-一级警情,2-二级警情,3-三级警情,4-四级警情)
     *                  允许值: 1, 2, 3, 4
     * @param method    报警方式(1-电话报警,2-设备报警,3-短信报警,4-GPS报警,5-视频报警,6-设备故障报警,7-其他报警)
     *                  允许值: 1, 2, 3, 4, 5, 6, 7
     * @param start     分页开始,从零开始
     * @param limit     分页大小
     * @param q         搜索关键字
     * @return
     */
    @GetMapping("/list")
    public AlarmListVo list(@ApiParam(required = false) String serial,
                                  @ApiParam(required = false) String code,
                                  @ApiParam(required = false) String starttime,
                                  @ApiParam(required = false) String endtime,
                                  @ApiParam(required = false) Integer priority,
                                  @ApiParam(required = false) Integer method,
                                  @ApiParam(required = false) Integer start,
                                  @ApiParam(required = false) Integer limit,
                                  @ApiParam(required = false) String q) {

        if(limit==null) limit=15;
        if(start==null) start =1;
        AlarmListVo alarmListVo = new AlarmListVo();
        List<Alarms> alarms = alarmsService.list(serial,code,starttime,endtime,priority,method,start,limit,q);

        alarmListVo.setAlarmCount(alarmsService.countList(serial,code,starttime,endtime,priority,method,q));
        alarmListVo.setAlarmList(alarms);
        alarmListVo.setAlarmPublishToRedis(true);
        return alarmListVo ;
    }

    /**
     * 设备报警 - 删除报警
     *
     * @param id 报警ID, 多个以逗号间隔
     * @return
     */
    @GetMapping("/remove")
    public String remove(@ApiParam String id) {
        return alarmsService.removeById(id) ? "ok" : "failure";
    }

    /**
     * 设备报警 - 清空报警
     *
     * @return
     */
    @GetMapping("/clear")
    public String clear() {
        alarmsService.truncate();
        return "ok";
    }
}
