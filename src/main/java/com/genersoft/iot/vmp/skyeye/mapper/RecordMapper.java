package com.genersoft.iot.vmp.skyeye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.genersoft.iot.vmp.skyeye.enttity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ Description com.genersoft.iot.vmp.skyeye.mapper
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/8/14 17:25
 */
@Mapper
@Repository
public interface RecordMapper extends BaseMapper<Record> {
}
