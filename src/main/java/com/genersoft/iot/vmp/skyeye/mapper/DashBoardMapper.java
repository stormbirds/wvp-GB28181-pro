package com.genersoft.iot.vmp.skyeye.mapper;

import com.genersoft.iot.vmp.skyeye.vo.AuthDeviceStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Description com.genersoft.iot.vmp.skyeye.mapper
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/11 12:05
 */

@Mapper
@Repository
public interface DashBoardMapper {

    @Select("SELECT deviceOnline,deviceTotal,channelOnline,channelTotal,0 AS channelCount FROM\n" +
            "(SELECT COUNT(1) AS deviceOnline FROM device WHERE online = '1' )a,\n" +
            "(SELECT COUNT(1) AS deviceTotal FROM device )b,\n" +
            "(SELECT COUNT(1) AS channelOnline FROM device_channel WHERE status ='1' )c,\n" +
            "(SELECT COUNT(1) AS channelTotal FROM device_channel)d")
    AuthDeviceStatus queryAllDeviceStatus();
}
