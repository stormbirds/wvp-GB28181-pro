package com.genersoft.iot.vmp.gb28181.task.impl;

import com.genersoft.iot.vmp.conf.DynamicTask;
import com.genersoft.iot.vmp.gb28181.bean.Device;
import com.genersoft.iot.vmp.gb28181.task.ISubscribeTask;
import com.genersoft.iot.vmp.gb28181.transmit.cmd.ISIPCommander;
import lombok.extern.slf4j.Slf4j;

import javax.sip.Dialog;
import javax.sip.DialogState;

/**
 * @Description com.genersoft.iot.vmp.gb28181.task.impl
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/16 11:33
 */
@Slf4j
public class AlarmSubscribeTask implements ISubscribeTask {
    private Device device;
    private int expires;
    private String startPriority;
    private String endPriority;
    private String alarmMethod;
    private String alarmType;
    private String startTime;
    private String endTime;
    private ISIPCommander sipCommander;
    private Dialog dialog;
    private DynamicTask dynamicTask;
    private String taskKey = "alarm-subscribe-timeout";

    public AlarmSubscribeTask(Device device, ISIPCommander sipCommander, DynamicTask dynamicTask) {
        this.device = device;
        this.sipCommander = sipCommander;
        this.dynamicTask = dynamicTask;
    }

    @Override
    public void stop() {

    }

    @Override
    public DialogState getDialogState() {
        if (dialog == null) {
            return null;
        }
        return dialog.getState();
    }

    @Override
    public void run() {
        if (dynamicTask.get(taskKey) != null) {
            dynamicTask.stop(taskKey);
        }
        sipCommander.alarmSubscribe(device,expires,startPriority,endPriority,alarmMethod,alarmType,startTime,endTime);
    }
}
