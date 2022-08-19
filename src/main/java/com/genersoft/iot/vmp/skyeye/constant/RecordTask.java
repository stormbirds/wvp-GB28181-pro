package com.genersoft.iot.vmp.skyeye.constant;

import com.alibaba.fastjson.JSONObject;
import com.genersoft.iot.vmp.skyeye.vo.RecordListVo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @ Description com.genersoft.iot.vmp.skyeye.constant
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/8/13 16:14
 */
public class RecordTask {
    public static Map<String, CompletableFuture<RecordListVo>> realtimeRecordCompletableFutureMap = new HashMap<>();
    public static Map<String,CompletableFuture<JSONObject>> playbackRecordDownloadCompletableFutureMap = new HashMap<>();
}
