package com.genersoft.iot.vmp.skyeye.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.PathUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.genersoft.iot.vmp.conf.UserSetting;
import com.genersoft.iot.vmp.media.zlm.ZLMRESTfulUtils;
import com.genersoft.iot.vmp.media.zlm.dto.MediaServerItem;
import com.genersoft.iot.vmp.service.IMediaServerService;
import com.genersoft.iot.vmp.skyeye.enttity.RecordChannels;
import com.genersoft.iot.vmp.skyeye.mapper.RecordChannelsMapper;
import com.genersoft.iot.vmp.skyeye.service.IRecordChannelsService;
import com.genersoft.iot.vmp.skyeye.vo.CloudRecord;
import com.genersoft.iot.vmp.skyeye.vo.CloudRecordListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

import static cn.hutool.core.date.DatePattern.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stormbirds
 * @since 2022-07-30
 */
@Service
public class RecordChannelsServiceImpl extends ServiceImpl<RecordChannelsMapper, RecordChannels> implements IRecordChannelsService {

    @Autowired
    private IMediaServerService mediaServerService;
    @Autowired
    private ZLMRESTfulUtils zlmresTfulUtils;
    @Autowired
    private UserSetting userSetting;

    @Override
    public CloudRecordListVo querychannels(Integer start, Integer limit, String sort, String order, String q) {
        if (limit == null) limit = 15;

        if (start == null){
            start = 0;
        }else if(start == 0){
            start = start*limit;
        }else if(start>0){
            start = start*limit-1;
        }
        QueryWrapper<RecordChannels> wrapper = new QueryWrapper<>();
        wrapper.orderBy(StringUtils.hasText(sort), !StringUtils.hasText(order) || "asc".equalsIgnoreCase(order),"");
        String orderBy = "";
        if(StringUtils.hasText(sort)){
            orderBy = sort.concat(" ").concat(order);
        }
        List<CloudRecord> cloudRecordList = baseMapper.querychannels(start,limit,orderBy,q);
        CloudRecordListVo result = new CloudRecordListVo();
        result.setTotal(baseMapper.querychannelsCount(q));
        result.setRows(cloudRecordList);
        return result;
    }

    @Override
    public String queryMonthly(String serial, String code, String period) {
        int lengthOfMonth = LocalDateTimeUtil.parse(period, SIMPLE_MONTH_FORMATTER).toLocalDate().lengthOfMonth();
        int[] result = new int[lengthOfMonth];
        FileUtil.loopFiles(FileUtil.file(userSetting.getCloudRecordPath(),"rtp",serial.concat("_").concat(code)) , 1, pathname -> {
            if(pathname.isDirectory() && PathUtil.getLastPathEle(pathname.toPath()).toString().startsWith(period)){
                int dayOfMonth = DateUtil.dayOfMonth( DateUtil.parse(PathUtil.getLastPathEle(pathname.toPath()).toString(),PURE_DATE_FORMATTER));
                result[dayOfMonth-1]=1;
            }
            return pathname.isDirectory();
        });
        return ArrayUtil.join(result,"");
    }

    @Override
    public JSONObject queryFlags(String serial, String code) {
        JSONObject result = new JSONObject();
        FileUtil.loopFiles(FileUtil.file(userSetting.getCloudRecordPath(),"rtp",serial.concat("_").concat(code)) , 1, pathname -> {
            if(pathname.isDirectory()){
                String dateFolderName = PathUtil.getLastPathEle(pathname.toPath()).toString();
                String monthStr = dateFolderName.substring(0,6);
                int dayOfMonth = DateUtil.dayOfMonth( DateUtil.parse(dateFolderName,PURE_DATE_FORMATTER));
                if(result.containsKey(monthStr)){
                    StringBuilder curMonthResult = new StringBuilder(result.getString(monthStr));
                    curMonthResult.replace(dayOfMonth-1,dayOfMonth,"1");
                    result.put(monthStr,curMonthResult.toString());
                }else{
                    StringBuilder curMonthResult = new StringBuilder();

                    int lengthOfMonth = LocalDateTimeUtil.parse(monthStr, SIMPLE_MONTH_FORMATTER).toLocalDate().lengthOfMonth();
                    for (int i = 0; i < lengthOfMonth; i++) {
                        if(i==(dayOfMonth-1))
                            curMonthResult.append("1");
                        else curMonthResult.append("0");
                    }
                    result.put(monthStr,curMonthResult.toString());
                }
            }
            return pathname.isDirectory();
        });

        return result;
    }
}
