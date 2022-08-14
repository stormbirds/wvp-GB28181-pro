package com.genersoft.iot.vmp.storager.dao;

import com.genersoft.iot.vmp.gb28181.bean.Device;
import com.genersoft.iot.vmp.gb28181.bean.DeviceChannel;
import com.genersoft.iot.vmp.gb28181.bean.DeviceChannelInPlatform;
import com.genersoft.iot.vmp.vmanager.gb28181.platform.bean.ChannelReduce;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用于存储设备通道信息
 */
@Mapper
@Repository
public interface DeviceChannelMapper {

    @Insert("INSERT INTO device_channel (channel_id, device_id, name, manufacture, model, owner, civil_code, block, " +
            "address, parental, parent_id, safety_way, register_way, cert_num, certifiable, err_code, secrecy, " +
            "ip_address, port, password, ptz_type, status, stream_id, longitude, latitude, longitudeGcj02, latitudeGcj02, " +
            "longitudeWgs84, latitudeWgs84, create_time, update_time, business_group_id, gps_time) " +
            "VALUES ('${channelId}', '${deviceId}', '${name}', '${manufacture}', '${model}', '${owner}', '${civilCode}', '${block}'," +
            "'${address}', ${parental}, '${parentId}', ${safetyWay}, ${registerWay}, '${certNum}', ${certifiable}, ${errCode}, '${secrecy}', " +
            "'${ipAddress}', ${port}, '${password}', ${PTZType}, ${status}, '${streamId}', ${longitude}, ${latitude}, ${longitudeGcj02}, " +
            "${latitudeGcj02}, ${longitudeWgs84}, ${latitudeWgs84},'${createTime}', '${updateTime}', '${businessGroupId}', '${gpsTime}')")
    int add(DeviceChannel channel);

    @Update(value = {" <script>" +
            "UPDATE device_channel " +
            "SET update_time='${updateTime}'" +
            "<if test='name != null'>, name='${name}'</if>" +
            "<if test='manufacture != null'>, manufacture='${manufacture}'</if>" +
            "<if test='model != null'>, model='${model}'</if>" +
            "<if test='owner != null'>, owner='${owner}'</if>" +
            "<if test='civilCode != null'>, civil_code='${civilCode}'</if>" +
            "<if test='block != null'>, block='${block}'</if>" +
            "<if test='address != null'>, address='${address}'</if>" +
            "<if test='parental != null'>, parental=${parental}</if>" +
            "<if test='parentId != null'>, parent_id='${parentId}'</if>" +
            "<if test='safetyWay != null'>, safety_way=${safetyWay}</if>" +
            "<if test='registerWay != null'>, register_way=${registerWay}</if>" +
            "<if test='certNum != null'>, cert_num='${certNum}'</if>" +
            "<if test='certifiable != null'>, certifiable=${certifiable}</if>" +
            "<if test='errCode != null'>, err_code=${errCode}</if>" +
            "<if test='secrecy != null'>, secrecy='${secrecy}'</if>" +
            "<if test='ipAddress != null'>, ip_address='${ipAddress}'</if>" +
            "<if test='port != null'>, port=${port}</if>" +
            "<if test='password != null'>, password='${password}'</if>" +
            "<if test='PTZType != null'>, ptz_type=${PTZType}</if>" +
            "<if test='status != null'>, status='${status}'</if>" +
            "<if test='streamId != null'>, stream_id='${streamId}'</if>" +
            "<if test='hasAudio != null'>, audio_enable=${hasAudio}</if>" +
            "<if test='longitude != null'>, longitude=${longitude}</if>" +
            "<if test='latitude != null'>, latitude=${latitude}</if>" +
            "<if test='longitudeGcj02 != null'>, longitudeGcj02=${longitudeGcj02}</if>" +
            "<if test='latitudeGcj02 != null'>, latitudeGcj02=${latitudeGcj02}</if>" +
            "<if test='longitudeWgs84 != null'>, longitudeWgs84=${longitudeWgs84}</if>" +
            "<if test='latitudeWgs84 != null'>, latitudeWgs84=${latitudeWgs84}</if>" +
            "<if test='businessGroupId != null'>, business_group_id=#{businessGroupId}</if>" +
            "<if test='gpsTime != null'>, gps_time=#{gpsTime}</if>" +
            "WHERE deviceId='${deviceId}' AND channel_id='${channelId}'"+
            " </script>"})
    int update(DeviceChannel channel);

    @Select(value = {" <script>" +
            "SELECT " +
            "dc.* " +
            "from " +
            "device_channel dc " +
            "WHERE " +
            "dc.device_id = #{deviceId} " +
            " <if test='query != null'> AND (dc.channel_id LIKE '%${query}%' OR dc.name LIKE '%${query}%' OR dc.name LIKE '%${query}%')</if> " +
            " <if test='parentChannelId != null'> AND dc.parent_id=#{parentChannelId} </if> " +
            " <if test='online == true' > AND dc.status=1</if>" +
            " <if test='online == false' > AND dc.status=0</if>" +
            " <if test='hasSubChannel == true' >  AND dc.sub_count > 0 </if>" +
            " <if test='hasSubChannel == false' >  AND dc.sub_count = 0 </if>" +
            "ORDER BY dc.channel_id " +
            " </script>"})
    List<DeviceChannel> queryChannels(String deviceId, String parentChannelId, String query, Boolean hasSubChannel, Boolean online);

    @Select("SELECT * FROM device_channel WHERE device_id=#{deviceId} AND channel_id=#{channelId}")
    DeviceChannel queryChannel(String deviceId, String channelId);

    @Delete("DELETE FROM device_channel WHERE device_id=#{deviceId}")
    int cleanChannelsByDeviceId(String deviceId);

    @Delete("DELETE FROM device_channel WHERE device_id=#{deviceId} AND channel_id=#{channelId}")
    int del(String deviceId, String channelId);

    @Update(value = {"UPDATE device_channel SET stream_id=null WHERE device_id=#{deviceId} AND channel_id=#{channelId}"})
    void stopPlay(String deviceId, String channelId);

    @Update(value = {"UPDATE device_channel SET stream_id=#{streamId} WHERE device_id=#{deviceId} AND channel_id=#{channelId}"})
    void startPlay(String deviceId, String channelId, String streamId);

    @Select(value = {" <script>" +
            "SELECT " +
            "    dc.id,\n" +
            "    dc.channel_id,\n" +
            "    dc.device_id,\n" +
            "    dc.name,\n" +
            "    de.manufacturer,\n" +
            "    de.hostAddress,\n" +
            "    dc.sub_count,\n" +
            "    pgc.platformId as platformId,\n" +
            "    pgc.catalogId as catalogId " +
            " FROM device_channel dc " +
            " LEFT JOIN device de ON dc.device_id = de.deviceId " +
            " LEFT JOIN platform_gb_channel pgc on pgc.deviceChannelId = dc.id " +
            " WHERE 1=1 " +
            " <if test='query != null'> AND (dc.channel_id LIKE '%${query}%' OR dc.name LIKE '%${query}%' OR dc.name LIKE '%${query}%')</if> " +
            " <if test='online == true' > AND dc.status=1</if> " +
            " <if test='online == false' > AND dc.status=0</if> " +
            " <if test='hasSubChannel!= null and hasSubChannel == true' >  AND dc.sub_count > 0</if> " +
            " <if test='hasSubChannel!= null and hasSubChannel == false' >  AND dc.sub_count = 0</if> " +
            " <if test='catalogId == null ' >  AND dc.id not in (select deviceChannelId from platform_gb_channel where platformId=#{platformId} ) </if> " +
            " <if test='catalogId != null ' >  AND pgc.platformId = #{platformId} and pgc.catalogId=#{catalogId} </if> " +
            " ORDER BY dc.device_id, dc.channel_id ASC" +
            " </script>"})
    List<ChannelReduce> queryChannelListInAll(String query, Boolean online, Boolean hasSubChannel, String platformId, String catalogId);

    @Select(value = {" <script>" +
            "SELECT " +
            "    dc.*,\n" +
            "    pgc.platformId as platformId,\n" +
            "    pgc.catalogId as catalogId " +
            " FROM device_channel dc " +
            " LEFT JOIN platform_gb_channel pgc on pgc.deviceChannelId = dc.id " +
            " WHERE pgc.platformId = #{platformId} " +
            " ORDER BY dc.device_id, dc.channel_id ASC" +
            " </script>"})
    List<DeviceChannelInPlatform> queryChannelByPlatformId(String platformId);


    @Select("SELECT * FROM device_channel WHERE channel_id=#{channelId}")
    List<DeviceChannel> queryChannelByChannelId( String channelId);

    @Update(value = {"UPDATE device_channel SET status=0 WHERE device_id=#{deviceId} AND channel_id=#{channelId}"})
    void offline(String deviceId,  String channelId);

    @Update(value = {"UPDATE device_channel SET status=0 WHERE device_id=#{deviceId}"})
    void offlineByDeviceId(String deviceId);

    @Update(value = {"UPDATE device_channel SET status=1 WHERE device_id=#{deviceId} AND channel_id=#{channelId}"})
    void online(String deviceId,  String channelId);

    @Insert("<script> " +
            "insert into device_channel " +
            "(channel_id, device_id, name, manufacture, model, owner, civil_code, block, sub_count, " +
            "  address, parental, parent_id, safety_way, register_way, cert_num, certifiable, err_code, secrecy, " +
            "  ip_address, port, password, ptz_type, status, stream_id, longitude, latitude, longitudeGcj02, latitudeGcj02, " +
            "  longitudeWgs84, latitudeWgs84, create_time, update_time, business_group_id, gps_time) " +
            "values " +
            "<foreach collection='addChannels' index='index' item='item' separator=','> " +
            "('${item.channelId}', '${item.deviceId}', '${item.name}', '${item.manufacture}', '${item.model}', " +
            "'${item.owner}', '${item.civilCode}', '${item.block}',${item.subCount}," +
            "'${item.address}', ${item.parental}, '${item.parentId}', ${item.safetyWay}, ${item.registerWay}, " +
            "'${item.certNum}', ${item.certifiable}, ${item.errCode}, '${item.secrecy}', " +
            "'${item.ipAddress}', ${item.port}, '${item.password}', ${item.PTZType}, ${item.status}, " +
            "'${item.streamId}', ${item.longitude}, ${item.latitude},${item.longitudeGcj02}, " +
            "${item.latitudeGcj02},${item.longitudeWgs84}, ${item.latitudeWgs84},'${item.createTime}', '${item.updateTime}', " +
            "'${item.businessGroupId}', '${item.gpsTime}') " +
            "</foreach> " +
            "ON DUPLICATE KEY UPDATE " +
            "update_time=VALUES(update_time), " +
            "name=VALUES(name), " +
            "manufacture=VALUES(manufacture), " +
            "model=VALUES(model), " +
            "owner=VALUES(owner), " +
            "civil_code=VALUES(civil_code), " +
            "block=VALUES(block), " +
            "sub_count=VALUES(sub_count), " +
            "address=VALUES(address), " +
            "parental=VALUES(parental), " +
            "parent_id=VALUES(parent_id), " +
            "safety_way=VALUES(safety_way), " +
            "register_way=VALUES(register_way), " +
            "cert_num=VALUES(cert_num), " +
            "certifiable=VALUES(certifiable), " +
            "err_code=VALUES(err_code), " +
            "secrecy=VALUES(secrecy), " +
            "ip_address=VALUES(ip_address), " +
            "port=VALUES(port), " +
            "password=VALUES(password), " +
            "ptz_type=VALUES(ptz_type), " +
            "status=VALUES(status), " +
            "stream_id=VALUES(stream_id), " +
            "longitude=VALUES(longitude), " +
            "latitude=VALUES(latitude), " +
            "longitudeGcj02=VALUES(longitudeGcj02), " +
            "latitudeGcj02=VALUES(latitudeGcj02), " +
            "longitudeWgs84=VALUES(longitudeWgs84), " +
            "latitudeWgs84=VALUES(latitudeWgs84), " +
            "business_group_id=VALUES(business_group_id), " +
            "gps_time=VALUES(gps_time)" +
            "</script>")
    int batchAdd(List<DeviceChannel> addChannels);

    @Update({"<script>" +
            "<foreach collection='updateChannels' item='item' separator=';'>" +
            " UPDATE" +
            " device_channel" +
            " SET update_time='${item.updateTime}'" +
            "<if test='item.name != null'>, name='${item.name}'</if>" +
            "<if test='item.manufacture != null'>, manufacture='${item.manufacture}'</if>" +
            "<if test='item.model != null'>, model='${item.model}'</if>" +
            "<if test='item.owner != null'>, owner='${item.owner}'</if>" +
            "<if test='item.civilCode != null'>, civil_code='${item.civilCode}'</if>" +
            "<if test='item.block != null'>, block='${item.block}'</if>" +
            "<if test='item.subCount != null'>, block=${item.subCount}</if>" +
            "<if test='item.address != null'>, address='${item.address}'</if>" +
            "<if test='item.parental != null'>, parental=${item.parental}</if>" +
            "<if test='item.parentId != null'>, parent_id='${item.parentId}'</if>" +
            "<if test='item.safetyWay != null'>, safety_way=${item.safetyWay}</if>" +
            "<if test='item.registerWay != null'>, register_way=${item.registerWay}</if>" +
            "<if test='item.certNum != null'>, cert_num='${item.certNum}'</if>" +
            "<if test='item.certifiable != null'>, certifiable=${item.certifiable}</if>" +
            "<if test='item.errCode != null'>, err_code=${item.errCode}</if>" +
            "<if test='item.secrecy != null'>, secrecy='${item.secrecy}'</if>" +
            "<if test='item.ipAddress != null'>, ip_address='${item.ipAddress}'</if>" +
            "<if test='item.port != null'>, port=${item.port}</if>" +
            "<if test='item.password != null'>, password='${item.password}'</if>" +
            "<if test='item.PTZType != null'>, ptz_type=${item.PTZType}</if>" +
            "<if test='item.status != null'>, status='${item.status}'</if>" +
            "<if test='item.streamId != null'>, stream_id='${item.streamId}'</if>" +
            "<if test='item.hasAudio != null'>, audio_enable=${item.hasAudio}</if>" +
            "<if test='item.longitude != null'>, longitude=${item.longitude}</if>" +
            "<if test='item.latitude != null'>, latitude=${item.latitude}</if>" +
            "<if test='item.longitudeGcj02 != null'>, longitudeGcj02=${item.longitudeGcj02}</if>" +
            "<if test='item.latitudeGcj02 != null'>, latitudeGcj02=${item.latitudeGcj02}</if>" +
            "<if test='item.longitudeWgs84 != null'>, longitudeWgs84=${item.longitudeWgs84}</if>" +
            "<if test='item.latitudeWgs84 != null'>, latitudeWgs84=${item.latitudeWgs84}</if>" +
            "<if test='item.businessGroupId != null'>, business_group_id=#{item.businessGroupId}</if>" +
            "<if test='item.gpsTime != null'>, gps_time=#{item.gpsTime}</if>" +
            "WHERE device_id='${item.deviceId}' AND channel_id='${item.channelId}'"+
            "</foreach>" +
            "</script>"})
    int batchUpdate(List<DeviceChannel> updateChannels);


    @Select(value = {" <script>" +
            "SELECT " +
            "dc1.* " +
            "from " +
            "device_channel dc1 " +
            "WHERE " +
            "dc1.device_id = #{deviceId} " +
            " <if test='query != null'> AND (dc1.channel_id LIKE '%${query}%' OR dc1.name LIKE '%${query}%' OR dc1.name LIKE '%${query}%')</if> " +
            " <if test='parentChannelId != null'> AND dc1.parent_id=#{parentChannelId} </if> " +
            " <if test='online == true' > AND dc1.status=1</if>" +
            " <if test='online == false' > AND dc1.status=0</if>" +
            " <if test='hasSubChannel == true' >  AND dc1.sub_count >0</if>" +
            " <if test='hasSubChannel == false' >  AND dc1.sub_count=0</if>" +
            "ORDER BY dc1.channel_id ASC " +
            "Limit #{limit} OFFSET #{start}" +
            " </script>"})
    List<DeviceChannel> queryChannelsByDeviceIdWithStartAndLimit(String deviceId, String parentChannelId, String query,
                                                                 Boolean hasSubChannel, Boolean online, int start, int limit);

    @Select("SELECT * FROM device_channel WHERE device_id=#{deviceId} AND status=1")
    List<DeviceChannel> queryOnlineChannelsByDeviceId(String deviceId);

    @Delete(value = {" <script>" +
            "DELETE " +
            "from " +
            "device_channel " +
            "WHERE " +
            "device_id = #{deviceId} " +
            " AND channel_id NOT IN " +
            "<foreach collection='channels'  item='item'  open='(' separator=',' close=')' > #{item.channelId}</foreach>" +
            " </script>"})
    int cleanChannelsNotInList(String deviceId, List<DeviceChannel> channels);

    @Update(" update device_channel" +
            " set sub_count = (select *" +
            "                from (select count(0)" +
            "                      from device_channel" +
            "                      where device_id = #{deviceId} and parent_id = #{channelId}) as temp)" +
            " where device_id = #{deviceId} " +
            " and channel_id = #{channelId}")
    int updateChannelSubCount(String deviceId, String channelId);

    @Update(value = {" <script>" +
            "UPDATE device_channel " +
            "SET " +
            "latitude=${latitude}, " +
            "longitude=${longitude}, " +
            "longitudeGcj02=${longitudeGcj02}, " +
            "latitudeGcj02=${latitudeGcj02}, " +
            "longitudeWgs84=${longitudeWgs84}, " +
            "latitudeWgs84=${latitudeWgs84}, " +
            "gps_time='${gpsTime}' " +
            "WHERE device_id=#{deviceId} " +
            " <if test='channel_id != null' >  AND channel_id=#{channelId}</if>" +
            " </script>"})
    void updatePosition(DeviceChannel deviceChannel);

    @Select("SELECT * FROM device_channel WHERE length(trim(stream_id)) > 0")
    List<DeviceChannel> getAllChannelInPlay();

    @Select("SELECT * FROM device_channel "+
            " where device_id = #{deviceId} " +
            " and channel_id = #{channelId}")
    DeviceChannel getChannelById(String deviceId, String channelId);

    @Select("select * from device_channel where longitude*latitude > 0 and device_id = #{deviceId}")
    List<DeviceChannel> getAllChannelWithCoordinate(String deviceId);


    @Select(value = {" <script>" +
            "select * " +
            "from device_channel " +
            "where device_id=#{deviceId}" +
            " <if test='parentId != null' > and left(channel_id, ${parentId.length()}) = #{parentId}</if>" +
            " <if test='length != null' > and length(channel_id)=${length}</if>" +
            " </script>"})
    List<DeviceChannel> getChannelsWithCivilCodeAndLength(String deviceId, String parentId, Integer length);

    @Select(value = {" <script>" +
            "select * " +
            "from device_channel " +
            "where device_id=#{deviceId} and length(channel_id)>14 and civil_code=#{parentId}" +
            " </script>"})
    List<DeviceChannel> getChannelsByCivilCode(String deviceId, String parentId);

    @Select("select min(length(channel_id)) as minLength " +
            "from device_channel " +
            "where device_id=#{deviceId}")
    Integer getChannelMinLength(String deviceId);

    @Select("select * from device_channel where device_id=#{deviceId} and civil_code not in " +
            "(select civil_code from device_channel where device_id=#{deviceId} group by civil_code)")
    List<DeviceChannel> getChannelWithoutCiviCode(String deviceId);

    @Select("select * from device_channel where device_id=#{deviceId} and SUBSTRING(channel_id, 11, 3)=#{typeCode}")
    List<DeviceChannel> getBusinessGroups(String deviceId, String typeCode);

    @Select("select dc.id, dc.channel_id, dc.device_id, dc.name, dc.manufacture,dc.model,dc.owner, pc.civil_code,dc.block, " +
            " dc.address, '0' as parental,'0' as channel_type, pc.id as parent_id, dc.safety_way, dc.register_way,dc.cert_num, dc.certifiable,  " +
            " dc.err_code,dc.end_time, dc.secrecy,   dc.ip_address,  dc.port,  dc.ptz_type,  dc.password, dc.status, " +
            " dc.longitudeWgs84 as longitude, dc.latitudeWgs84 as latitude,  pc.business_group_id " +
            " from device_channel dc" +
            " left join platform_gb_channel pgc on  dc.id = pgc.deviceChannelId" +
            " left join platform_catalog pc on pgc.catalogId = pc.id and pgc.platformId = pc.platformId" +
            " where pgc.platformId=#{serverGBId}")
    List<DeviceChannel> queryChannelWithCatalog(String serverGBId);
}
