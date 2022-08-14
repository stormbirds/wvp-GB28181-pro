package com.genersoft.iot.vmp.skyeye.controller;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.genersoft.iot.vmp.conf.UserSetting;
import com.genersoft.iot.vmp.gb28181.bean.DeviceChannel;
import com.genersoft.iot.vmp.service.IDeviceChannelService;
import com.genersoft.iot.vmp.skyeye.service.IRecordChannelsService;
import com.genersoft.iot.vmp.skyeye.vo.CloudRecordListVo;
import com.genersoft.iot.vmp.skyeye.vo.DailyRecord;
import com.genersoft.iot.vmp.storager.dao.DeviceChannelMapper;
import io.lindstrom.m3u8.model.MediaPlaylist;
import io.lindstrom.m3u8.model.MediaSegment;
import io.lindstrom.m3u8.parser.MediaPlaylistParser;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stormbirds
 * @since 2022-07-30
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/cloudrecord")
public class RecordChannelsController {

    @Resource
    private IRecordChannelsService recordChannelsService;
    @Autowired
    private DeviceChannelMapper channelMapper;

    @Autowired
    private UserSetting userSetting;

    @Value("${sip.ip}")
    private String sipIp;
    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/querychannels")
    public CloudRecordListVo querychannels(@ApiParam(required = true,value = "分页开始,从零开始") Integer start,
                                           @ApiParam(required = true,value = "分页大小") Integer limit,
                                           @ApiParam(required = false,value = "搜索关键字") String q,
                                           @ApiParam(required = false,value = "排序字段") String sort,
                                           @ApiParam(required = false,value = "排序顺序\n" +
                                                   "\n" +
                                                   "允许值: asc, desc") String order) {
        return recordChannelsService.querychannels(start,limit,sort,order,q);
    }


    @Operation(summary = "云端服务器录像 - 按通道统计所有录像",description = "字段\t类型\t描述\n" +
            "key\tString\t\n" +
            "月份, YYYYMM\n" +
            "\n" +
            "value\tString\t\n" +
            "标记当月每一天是否有录像, 0 - 没有录像, 1 - 有录像" +
            "成功示例 {201803: \"0000000011000000000000000000000\"}")
    @GetMapping("/queryflags")
    public JSONObject queryflags(@RequestParam @ApiParam(required = true,value = "设备国标编号") String serial,
                                 @RequestParam(required = true) @ApiParam(required = true,value = "通道国标编号") String code){
        return recordChannelsService.queryFlags(serial,code);
    }

    @GetMapping("/querymonthly")
    public String querymonthly(@RequestParam @ApiParam(required = true,value = "设备国标编号") String serial,
                               @RequestParam(required = true) @ApiParam(required = true,value = "通道国标编号") String code,
                               @RequestParam(required = true) @ApiParam(required = true,value = "月份, YYYYMM") String period){
        return recordChannelsService.queryMonthly(serial,code,period);
    }

    @GetMapping("/querydaily")
    public JSONObject  querydaily(@RequestParam @ApiParam(required = true,value = "设备国标编号") String serial,
                                  @RequestParam(required = true) @ApiParam(required = true,value = "通道国标编号") String code,
                                  @RequestParam(required = true) @ApiParam(required = true,value = "月份, YYYYMMDD") String period){
        DeviceChannel channel = channelMapper.queryChannel(serial,code);
        JSONObject result = new JSONObject();
        result.put("name",channel.getName());
        List<DailyRecord> dailyRecordList = new ArrayList<>();
        result.put("list",dailyRecordList);

        String[] recordFolderList = FileUtil.file(userSetting.getCloudRecordPath(),"rtp",serial.concat("_").concat(code),period).list();
        if(recordFolderList==null || recordFolderList.length<=0){
            return result;
        }
        MediaPlaylistParser parser = new MediaPlaylistParser();
        for (int i = 0; i < recordFolderList.length; i++) {
            Path m3u8File = FileUtil.file(userSetting.getCloudRecordPath(),
                    "rtp",
                    serial.concat("_").concat(code),
                    period,
                    recordFolderList[i],
                    serial.concat("_").concat(code).concat("_record.m3u8")).toPath();
            MediaPlaylist mediaPlaylist = null;
            try {
                mediaPlaylist = parser.readPlaylist(m3u8File);
                dailyRecordList.add(DailyRecord.builder()
                        .name(channel.getName())
                        .startAt(recordFolderList[i])
                        .duration(mediaPlaylist.mediaSegments().stream().mapToDouble(MediaSegment::duration).sum())
                        .hls(String.format("http://%s:%s/record/rtp/%s/%s/%s/%s_record.m3u8", sipIp,serverPort,
                                serial.concat("_").concat(code),
                                period,
                                recordFolderList[i],
                                serial.concat("_").concat(code)))
                        .important(false)
                        .build());
            } catch (IOException e) {
                log.error("解析录像文件出错",e);
            }
        }
        result.put("list",dailyRecordList);

        return result;
    }


}