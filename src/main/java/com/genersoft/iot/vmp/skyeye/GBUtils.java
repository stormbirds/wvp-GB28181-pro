package com.genersoft.iot.vmp.skyeye;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.hutool.core.date.DatePattern.PURE_DATETIME_FORMATTER;

/**
 * @ Description cn.stormbirds.skyeye.utils
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2022/2/5 2:37
 */
public class GBUtils {
    private static final String GB_PATTERN = "[0-9]{20}_[0-9]{20}";
    public static final String GB_PlayBack_pattern ="[0-9]{20}_[0-9]{20}_[0-9]+";
    public static final String TS_DATETIME_PATTERN = "[^0-9]{1}[0-9]{14}[^0-9]{1}";

    public static boolean checkStreamGb(String stream){
        return Pattern.matches(GB_PATTERN,stream);
    }

    public static boolean checkStreamPlayBack(String streamName){
        return Pattern.matches(GB_PlayBack_pattern,streamName);
    }
    private static final String[] AlarmPriorityNames = {"一级警情","二级警情","三级警情","四级警情"};
    private static final String[] AlarmMethodNames = {"电话报警","设备报警","短信报警","GPS报警","视频报警","设备故障报警","其他报警"};
    private static final String[] AlarmTypeDeviceNames = {"视频丢失报警","设备防拆报警","存储设备磁盘满报警","设备高温报警","设备低温报警"};

    private static final String[] AlarmTypeVideoNames = {"人工视频报警","运动目标检测报警","遗留物检测报警","物体移除检测报警",
            "绊线检测报警","入侵检测报警","逆行检测报警","徘徊检测报警","流量统计报警","密度检测报警","视频异常检测报警","快速移动报警"};
    private static final String[] AlarmEventTypeNames = {"进入区域","离开区域"};

    private static final String[] AlarmTypeDeviceFailureNames = {"存储设备磁盘故障报警","存储设备风扇故障报警"};

    public static String[] getDeviceIdAndChannelIdByStream(String stream){
        if(checkStreamGb(stream)){
            return stream.split("_");
        }
        return new String[2];
    }

    public static String getAlarmPriorityName(int alarmPriority){
        if(alarmPriority<1 || alarmPriority>4) return "";
        return AlarmPriorityNames[alarmPriority-1];
    }

    public static String getAlarmMethodNames(int alarmMethod){
        if(alarmMethod<1 || alarmMethod>7) return "";
        return AlarmMethodNames[alarmMethod-1];
    }

    public static String getAlarmDeviceTypeNames(Integer alarmType){
        if(alarmType!=null && alarmType>=1 && alarmType<=5){
            return AlarmTypeDeviceNames[alarmType-1];
        }
        return "设备报警";

    }

    public static String getAlarmTypeVideoNames(Integer alarmType) {
        if(alarmType!=null && alarmType>=1 && alarmType<=12){
            return AlarmTypeVideoNames[alarmType-1];
        }
        return "";
    }

    public static String getAlarmDeviceFailureTypeNames(Integer alarmType) {
        if(alarmType!=null && alarmType>=1 && alarmType<=2){
            return AlarmTypeDeviceFailureNames[alarmType-1];
        }
        return "";
    }

    public static String getAlarmEventTypeNames(Integer eventType){
        return AlarmEventTypeNames[eventType-1];
    }

    public static LocalDateTime parseDateFromTsUrl(String tsUrl){
        Pattern p = Pattern.compile(TS_DATETIME_PATTERN);
        Matcher m = p.matcher(tsUrl);
        String s = "";
        if(m.find()) {
            s+=m.group().substring(1,15);
        }
        return LocalDateTime.parse(s,PURE_DATETIME_FORMATTER);
    }

    public static void main(String[] args) {
        String testUrl1 = "34020000002000000002_34020000001320000004/34020000002000000002_34020000001320000004-20220806173755-0.ts";
        System.out.println(parseDateFromTsUrl(testUrl1));
    }
}
