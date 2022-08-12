package com.genersoft.iot.vmp.skyeye.config;

import com.genersoft.iot.vmp.skyeye.service.IDashBoardService;
import com.genersoft.iot.vmp.skyeye.vo.*;
import com.google.common.collect.EvictingQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.NetworkIF;
import oshi.software.os.OSFileStore;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @ Description cn.stormbirds.skyeye.boot
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 20:08
 */
@Configuration
@Slf4j
public class SysMonitorConfig {


    private SystemInfo systemInfo = new SystemInfo();
    @Resource
    private IDashBoardService dashBoardService;

    @Value("${monitor.mac:}")
    private String mac;

    private Queue<NetVo> netQueue = EvictingQueue.create(30);
    private Queue<CpuVo> cpuQueue = EvictingQueue.create(30);
    private Queue<MemVo> memQueue = EvictingQueue.create(30);
    private Queue<LoadVo> loadQueue = EvictingQueue.create(30);

    private long receiveBytes1 = 0L;
    private long sendBytes1 = 0L;
    private long receiveBytesCur = 0L;
    private long sendBytesCur = 0L;

    private long[] oldTicks;
    private long[][] oldProcTicks;

    @Scheduled(fixedRate = 3000)
    public void initSystemInfo() {

        CentralProcessor cpu = systemInfo.getHardware().getProcessor();
        oldTicks = new long[CentralProcessor.TickType.values().length];
        oldProcTicks = new long[cpu.getLogicalProcessorCount()][CentralProcessor.TickType.values().length];

        GlobalMemory memory = systemInfo.getHardware().getMemory();
        MemVo memVo = new MemVo(getTimeString(),
                (memory.getTotal() - memory.getAvailable()) * 1.00 / memory.getTotal());
        memQueue.add(memVo);
        CpuVo cpuVo = new CpuVo(getTimeString(), cpuData(cpu));
        cpuQueue.add(cpuVo);
        getNet(systemInfo);
//                    getLoad();
//                    getGbChannels();
    }

    public Queue<NetVo> getNetQueue() {
        return netQueue;
    }

    public Queue<CpuVo> getCpuQueue() {
        return cpuQueue;
    }

    public Queue<MemVo> getMemQueue() {
        return memQueue;
    }

    public Queue<LoadVo> getLoadQueue() {
        return loadQueue;
    }

    public Double gerRxBytes() {
        return receiveBytesCur * 8 / 1024 / 1024.000;
    }

    public Double getTxBytes() {
        return sendBytesCur * 8 / 1024 / 1024.000;
    }

    private void getNet(SystemInfo systemInfo) {
        List<NetworkIF> ifNames = systemInfo.getHardware().getNetworkIFs(false);
        long rxBytes = 0L;
        long txBytes = 0L;

        for (NetworkIF ifconfig : ifNames) {
            if (!mac.isEmpty()) {
                String[] macs = mac.split(",");
                if (mac.toLowerCase().equals(ifconfig.getMacaddr())) {
                    //这里只获取我们指定的mac地址对应的物理网卡。

                    rxBytes += ifconfig.getBytesRecv();
                    txBytes += ifconfig.getBytesSent();
                    if (macs.length == 1) {
                        break;
                    }
                }

            } else {
                rxBytes += ifconfig.getBytesRecv();
                txBytes += ifconfig.getBytesSent();
            }


        }

        receiveBytesCur = rxBytes - receiveBytes1;
        sendBytesCur = txBytes - sendBytes1;

        receiveBytes1 = rxBytes;      //获取接受的字节数量
        sendBytes1 = txBytes;      //获取发送的字节数量

        netQueue.add(new NetVo(receiveBytesCur * 8 / 1024 / 1024.000,
                sendBytesCur * 8 / 1024 / 1024.000, getTimeString()));

    }

    public StoreListVo getStore() {

        List<OSFileStore> list = systemInfo.getOperatingSystem().getFileSystem().getFileStores();
        StoreListVo storeListVo = new StoreListVo();
        storeListVo.setCode(200);
        storeListVo.setMsg("ok");
        List<StoreVo> storeVos = new ArrayList<>();
        list.forEach(osFileStore -> {
            StoreVo storeVo = new StoreVo();
            storeVo.setName(osFileStore.getName());
            storeVo.setFreeSpace(formatByte(osFileStore.getFreeSpace()));
            storeVo.setSize(formatByte(osFileStore.getTotalSpace()));
            storeVo.setPercent(String.valueOf(osFileStore.getFreeSpace() * 1.00 / osFileStore.getTotalSpace() * 100 / 100.00));
            storeVo.setUnit("G");
            storeVo.setThreshold("");
            storeVo.setUsed(formatByte(osFileStore.getTotalSpace() - osFileStore.getFreeSpace()));
            storeVos.add(storeVo);
        });
        storeListVo.setData(storeVos);
        return storeListVo;
    }

    private String formatByte(long bytes) {
        return String.format("%.2f", bytes * 1.00 / 1024 / 1024 / 1024 * 100 / 100.00);
    }

    private String getTimeString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private double cpuData(CentralProcessor proc) {
        double d = proc.getSystemCpuLoadBetweenTicks(oldTicks);
        oldTicks = proc.getSystemCpuLoadTicks();
        return d;
    }

    public AuthVo getAuth() {
        AuthDeviceStatus dataDTO = dashBoardService.queryAllDeviceStatus();
        return new AuthVo(200,
                dataDTO,
                "Success");
    }
}
