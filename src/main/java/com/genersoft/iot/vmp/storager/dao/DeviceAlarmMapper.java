package com.genersoft.iot.vmp.storager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.genersoft.iot.vmp.gb28181.bean.DeviceAlarm;
import com.genersoft.iot.vmp.gb28181.bean.DeviceChannel;
import com.genersoft.iot.vmp.vmanager.gb28181.platform.bean.ChannelReduce;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用于存储设备的报警信息
 */
@Mapper
@Repository
public interface DeviceAlarmMapper extends BaseMapper<DeviceAlarm> {

    @Insert("INSERT INTO device_alarm (deviceId, channelId, alarmPriority, alarmMethod, alarmTime, alarmDescription, longitude, latitude, alarmType , createTime ) " +
            "VALUES ('${deviceId}', '${channelId}', '${alarmPriority}', '${alarmMethod}', '${alarmTime}', '${alarmDescription}', ${longitude}, ${latitude}, '${alarmType}', '${createTime}')")
    int add(DeviceAlarm alarm);


    @Select(value = {" <script>" +
            " SELECT * FROM device_alarm " +
            " WHERE 1=1 " +
            " <if test=\"deviceId != null and deviceId != ''\" >  AND deviceId = '${deviceId}'</if>" +
            " <if test=\"alarmPriority != null and alarmPriority != ''\" >  AND alarmPriority = '${alarmPriority}' </if>" +
            " <if test=\"alarmMethod != null and alarmMethod != ''\" >  AND alarmMethod = '${alarmMethod}' </if>" +
            " <if test=\"alarmType != null and alarmType != ''\" >  AND alarmType = '${alarmType}' </if>" +
            " <if test=\"startTime != null and startTime != ''\" >  AND alarmTime &gt;= '${startTime}' </if>" +
            " <if test=\"endTime != null and endTime != ''\" >  AND alarmTime &lt;= '${endTime}' </if>" +
            " ORDER BY alarmTime DESC " +
            " </script>"})
    List<DeviceAlarm> query(String deviceId, String alarmPriority, String alarmMethod,
                            String alarmType, String startTime, String endTime);


    @Delete(" <script>" +
            "DELETE FROM device_alarm WHERE 1=1 " +
            " <if test=\"deviceIdList != null and id == null \" > AND deviceId in " +
            "<foreach collection='deviceIdList'  item='item'  open='(' separator=',' close=')' > '${item}'</foreach>" +
            "</if>" +
            " <if test=\"time != null and id == null \" > AND alarmTime &lt;= '${time}'</if>" +
            " <if test=\"id != null\" > AND id = ${id}</if>" +
            " </script>"
            )
    int clearAlarmBeforeTime(Integer id, List<String> deviceIdList, String time);

    @Update("truncate table device_alarm")
    void truncate();
}
