package com.genersoft.iot.vmp.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.genersoft.iot.vmp.gb28181.bean.DeviceAlarm;
import com.genersoft.iot.vmp.media.zlm.dto.StreamProxyItem;
import com.genersoft.iot.vmp.service.IDeviceAlarmService;
import com.genersoft.iot.vmp.skyeye.GBUtils;
import com.genersoft.iot.vmp.skyeye.enttity.Alarms;
import com.genersoft.iot.vmp.skyeye.redis.RedisMsgPublisher;
import com.genersoft.iot.vmp.skyeye.redis.RedisTopicEnums;
import com.genersoft.iot.vmp.storager.dao.DeviceAlarmMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceAlarmServiceImpl extends ServiceImpl<DeviceAlarmMapper,DeviceAlarm> implements IDeviceAlarmService {

    @Autowired
    private DeviceAlarmMapper deviceAlarmMapper;
    @Resource
    private RedisMsgPublisher redisMsgPublisher;
    @Autowired
    private IDeviceAlarmService deviceAlarmService;

    @Override
    public PageInfo<DeviceAlarm> getAllAlarm(int page, int count, String deviceId, String alarmPriority, String alarmMethod, String alarmType, String startTime, String endTime) {
        PageHelper.startPage(page, count);
        List<DeviceAlarm> all = deviceAlarmMapper.query(deviceId, alarmPriority, alarmMethod, alarmType, startTime, endTime);
        return new PageInfo<>(all);
    }

    @Override
    public void add(DeviceAlarm deviceAlarm) {
        deviceAlarmMapper.add(deviceAlarm);
        int alarmMethod = Integer.parseInt(deviceAlarm.getAlarmMethod());
        Alarms.AlarmsBuilder alarms = Alarms.builder();
        if(alarmMethod== 4){
            alarms.alarmMethodName(GBUtils.getAlarmMethodNames(alarmMethod));
        }else if(alarmMethod== 5){
            Integer alarmType = Integer.parseInt(deviceAlarm.getAlarmType());
            alarms.alarmType(alarmType);
            alarms.alarmTypeName(GBUtils.getAlarmTypeVideoNames(alarmType));
//                    if(alarmType!=null && alarmType==6){
//                        Integer eventType = getInteger(rootElement.element("Info").element("AlarmTypeParam"), "EventType");
//                        alarms.alarmEventType(eventType);
//                    }
        }else if(alarmMethod== 2){
            Integer alarmType = Integer.parseInt(deviceAlarm.getAlarmType());
            alarms.alarmType(alarmType);
            alarms.alarmTypeName(GBUtils.getAlarmDeviceTypeNames(alarmType));
        }else if(alarmMethod== 6){
            Integer alarmType = Integer.parseInt(deviceAlarm.getAlarmType());
            alarms.alarmType(alarmType);
            alarms.alarmTypeName(GBUtils.getAlarmDeviceFailureTypeNames(alarmType));
        }
        alarms
                .id(deviceAlarm.getId())
                .deviceId(deviceAlarm.getDeviceId())
                .deviceName("")
                .channelId(deviceAlarm.getChannelId())
                .channelName("")
                .alarmPriority(Integer.parseInt(deviceAlarm.getAlarmPriority()) )
                .alarmPriorityName(GBUtils.getAlarmPriorityName(Integer.parseInt(deviceAlarm.getAlarmPriority()) ))
                .alarmMethod(Integer.parseInt(deviceAlarm.getAlarmMethod()))
                .alarmMethodName(GBUtils.getAlarmMethodNames(Integer.parseInt(deviceAlarm.getAlarmMethod())))
                .longitude(deviceAlarm.getLongitude())
                .latitude(deviceAlarm.getLatitude())
                .alarmDescription(deviceAlarm.getAlarmDescription())
                .extinfo("")
                .time(deviceAlarm.getAlarmTime())
                .createdAt(deviceAlarm.getCreateTime())
                .build();
        redisMsgPublisher.sendMsg(RedisTopicEnums.TOPIC_ALARM, JSON.toJSONString(alarms));
    }

    @Override
    public int clearAlarmBeforeTime(Integer id, List<String> deviceIdList, String time) {
        return deviceAlarmMapper.clearAlarmBeforeTime(id, deviceIdList, time);
    }

    @Override
    public void truncate() {
        baseMapper.truncate();
    }

    @Override
    public List<Alarms> list(String serial, String code, String starttime, String endtime, Integer priority, Integer method, Integer start, Integer limit, String q) {
        int page = (int) Math.ceil(start*1.0/limit);
        PageInfo<DeviceAlarm> allAlarm = deviceAlarmService.getAllAlarm(page, limit, null, String.valueOf(priority) ,String.valueOf(method) ,
                null, starttime, endtime);

//
//        QueryWrapper<DeviceAlarm> queryWrapper = new QueryWrapper<>();
//        queryWrapper
//                .like(StringUtils.hasText(q), "deviceId", q)
//                .or()
//                .like(StringUtils.hasText(q), "channelId", q)
//                .or()
//                .like(StringUtils.hasText(q), "alarmPriority", q)
//                .or()
//                .like(StringUtils.hasText(q), "alarmMethod", q)
//                .or()
//                .like(StringUtils.hasText(q), "alarmDescription", q)
//                .eq(StringUtils.hasText(serial),"deviceId",serial)
//                .eq(StringUtils.hasText(code),"channelId",code)
//                .ge(StringUtils.hasText(starttime),"alarmTime",starttime.replace("T",""))
//                .le(StringUtils.hasText(endtime),"alarmTime",endtime.replace("T",""))
//                .eq(priority!=null,"alarmPriority",priority)
//                .eq(method!=null,"alarmMethod",method);
//
//        List<DeviceAlarm> deviceAlarms = baseMapper.selectPage(
//                new Page<>(start, limit), queryWrapper).getRecords();
        return allAlarm.getList().stream().map(deviceAlarm -> {
            Integer alarmMethod = Integer.parseInt(deviceAlarm.getAlarmMethod());
            Alarms.AlarmsBuilder alarms = Alarms.builder();
            if(alarmMethod!=null){
                if(alarmMethod== 4){
                    alarms.alarmMethodName(GBUtils.getAlarmMethodNames(alarmMethod));
                    //TODO 存储GPS定位信息
                }else if(alarmMethod== 5){
                    Integer alarmType = Integer.parseInt(deviceAlarm.getAlarmType());
                    alarms.alarmType(alarmType);
                    alarms.alarmTypeName(GBUtils.getAlarmTypeVideoNames(alarmType));
//                    if(alarmType!=null && alarmType==6){
//                        Integer eventType = getInteger(rootElement.element("Info").element("AlarmTypeParam"), "EventType");
//                        alarms.alarmEventType(eventType);
//                    }
                }else if(alarmMethod== 2){
                    Integer alarmType = Integer.parseInt(deviceAlarm.getAlarmType());
                    alarms.alarmType(alarmType==null?2:alarmType);
                    alarms.alarmTypeName(GBUtils.getAlarmDeviceTypeNames(alarmType));
                }else if(alarmMethod== 6){
                    Integer alarmType = Integer.parseInt(deviceAlarm.getAlarmType());
                    alarms.alarmType(alarmType);
                    alarms.alarmTypeName(GBUtils.getAlarmDeviceFailureTypeNames(alarmType));
                }
            }else{
                alarms.alarmMethod(2);
            }
            return alarms
                    .id(deviceAlarm.getId())
                    .deviceId(deviceAlarm.getDeviceId())
                    .deviceName("")
                    .channelId(deviceAlarm.getChannelId())
                    .channelName("")
                    .alarmPriority(Integer.parseInt(deviceAlarm.getAlarmPriority()) )
                    .alarmPriorityName(GBUtils.getAlarmPriorityName(Integer.parseInt(deviceAlarm.getAlarmPriority()) ))
                    .alarmMethod(Integer.parseInt(deviceAlarm.getAlarmMethod()))
                    .alarmMethodName(GBUtils.getAlarmMethodNames(Integer.parseInt(deviceAlarm.getAlarmMethod())))
                    .longitude(deviceAlarm.getLongitude())
                    .latitude(deviceAlarm.getLatitude())
                    .alarmDescription(deviceAlarm.getAlarmDescription())
                    .extinfo("")
                    .time(deviceAlarm.getAlarmTime())
                    .createdAt(deviceAlarm.getCreateTime())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public int countList(String serial, String code, String starttime, String endtime, Integer priority, Integer method, String q) {
        return 0;
    }
}
