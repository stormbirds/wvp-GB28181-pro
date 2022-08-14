package com.genersoft.iot.vmp.skyeye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.genersoft.iot.vmp.skyeye.enttity.RecordChannels;
import com.genersoft.iot.vmp.skyeye.vo.CloudRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stormbirds
 * @since 2022-07-30
 */
@Mapper
@Repository
public interface RecordChannelsMapper extends BaseMapper<RecordChannels> {
    List<CloudRecord> querychannels(@Param(value = "start")Integer start,
                                    @Param(value = "limit")Integer limit,
                                    @Param(value = "order")String order,
                                    @Param(value = "q")String q);
    int querychannelsCount(String q);
}
