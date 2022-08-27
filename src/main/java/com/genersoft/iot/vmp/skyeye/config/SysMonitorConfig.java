package com.genersoft.iot.vmp.skyeye.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.genersoft.iot.vmp.common.StreamInfo;
import com.genersoft.iot.vmp.media.zlm.ZLMRESTfulUtils;
import com.genersoft.iot.vmp.media.zlm.dto.MediaItem;
import com.genersoft.iot.vmp.media.zlm.dto.MediaServerItem;
import com.genersoft.iot.vmp.service.IMediaServerService;
import com.genersoft.iot.vmp.skyeye.GBUtils;
import com.genersoft.iot.vmp.skyeye.service.IDashBoardService;
import com.genersoft.iot.vmp.skyeye.vo.*;
import com.google.common.collect.EvictingQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.NetworkIF;
import oshi.software.os.OSFileStore;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    @Lazy
    @Resource
    private IDashBoardService dashBoardService;
    @Autowired
    private IMediaServerService mediaServerService;
    @Autowired
    private ZLMRESTfulUtils zlmresTfulUtils;

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

    LoadVo liveLoad = new LoadVo(getTimeString(), "0" , "", "直播");
    LoadVo playBackLoad = new LoadVo(getTimeString(), "0", "", "回放");
    LoadVo recordLoad = new LoadVo(getTimeString(), "0", "", "录像");
    LoadVo h265Load = new LoadVo(getTimeString(), "0", "", "H265");
    LoadVo casLoad = new LoadVo(getTimeString(), "0", "", "级联");
    LoadVo playLoad = new LoadVo(getTimeString(),"0","","播放");

    AtomicInteger  liveCount = new AtomicInteger();
    AtomicInteger  playbackCount =  new AtomicInteger();
    AtomicInteger  recordCount = new AtomicInteger();
    AtomicInteger  h265Count = new AtomicInteger();
    AtomicInteger playCount = new AtomicInteger();

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
                    getLoad();
//                    getGbChannels();
    }

    private void getLoad() {
        List<MediaServerItem> mediaServerItems = mediaServerService.getAllOnline();
        if(mediaServerItems==null || mediaServerItems.isEmpty()){
            liveLoad = new LoadVo(getTimeString(), "0" , "", "直播");
            playBackLoad = new LoadVo(getTimeString(), "0", "", "回放");
            recordLoad = new LoadVo(getTimeString(), "0", "", "录像");
            h265Load = new LoadVo(getTimeString(), "0", "", "H265");
            casLoad = new LoadVo(getTimeString(), "0", "", "级联");
            playLoad = new LoadVo(getTimeString(),"0","","播放");
            loadQueue.clear();
            loadQueue.add(liveLoad);
            loadQueue.add(playBackLoad);
            loadQueue.add(recordLoad);
            loadQueue.add(h265Load);
            loadQueue.add(casLoad);
            loadQueue.add(playLoad);
            return;
        }else{
            liveCount = new AtomicInteger();
            playbackCount =  new AtomicInteger();
            recordCount = new AtomicInteger();
            h265Count = new AtomicInteger();
            playCount = new AtomicInteger();
            for (MediaServerItem mediaServerItem : mediaServerItems) {
                JSONObject jsonObject = zlmresTfulUtils.getMediaList(mediaServerItem,null);
                if (jsonObject.getInteger("code") == 0) {
                    JSONArray data = jsonObject.getJSONArray("data");
                    if (data != null && data.size() > 0) {
                        for (int i = 0; i < data.size(); i++) {
                            JSONObject streamJSONObj = data.getJSONObject(i);
                            if ("rtmp".equals(streamJSONObj.getString("schema"))) {
                                int totalReaderCount = streamJSONObj.getInteger("totalReaderCount");
                                String app = streamJSONObj.getString("app");
                                String stream = streamJSONObj.getString("stream");
                                List<MediaItem.MediaTrack> tracks = streamJSONObj.getObject("tracks", new TypeReference<List<MediaItem.MediaTrack>>() {
                                });
                                tracks.stream().filter(mediaTrack -> mediaTrack.getCodecType() == 0 && mediaTrack.getCodecId() == 1)
                                        .findFirst().ifPresent(m -> h265Count.addAndGet(totalReaderCount));
                                if("rtp".equals(app) && !GBUtils.checkStreamGb(stream)) {
                                    playbackCount.addAndGet(totalReaderCount);
                                }else if(!GBUtils.checkStreamGb(stream)){
                                    liveCount.addAndGet(1);
                                }
                                if(streamJSONObj.getBoolean("isRecordingHLS") || streamJSONObj.getBoolean("isRecordingMP4")){
                                    recordCount.addAndGet(1);
                                }
                                playCount.addAndGet(totalReaderCount);
                            }
                        }
                    }
                }

            }
            liveLoad.setLoad(liveCount.toString());
            playBackLoad.setLoad(playbackCount.toString());
            recordLoad.setLoad(recordCount.toString());
            h265Load.setLoad(h265Count.toString());
            playLoad.setLoad(playCount.toString());
            loadQueue.clear();
            loadQueue.add(liveLoad);
            loadQueue.add(playBackLoad);
            loadQueue.add(recordLoad);
            loadQueue.add(h265Load);
            loadQueue.add(casLoad);
            loadQueue.add(playLoad);
        }
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
