package com.genersoft.iot.vmp.skyeye.controller;

import com.alibaba.fastjson.JSON;
import com.genersoft.iot.vmp.VManageBootstrap;
import com.genersoft.iot.vmp.conf.exception.ControllerException;
import com.genersoft.iot.vmp.skyeye.enttity.SmsBaseconfig;
import com.genersoft.iot.vmp.skyeye.service.ISmsBaseconfigService;
import com.genersoft.iot.vmp.skyeye.vo.RequestKeyVo;
import com.genersoft.iot.vmp.skyeye.vo.SmsServerinfo;
import com.genersoft.iot.vmp.utils.SpringBeanFactory;
import com.genersoft.iot.vmp.vmanager.bean.ErrorCode;
import gov.nist.javax.sip.SipStackImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.sip.ListeningPoint;
import javax.sip.ObjectInUseException;
import javax.sip.SipProvider;
import java.util.Iterator;
import java.util.List;

/**
 * @ Description cn.stormbirds.skyeye.controller
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 16:54
 */

@RestController
@RequestMapping("/api/v1/sms")
public class SmsController {
    @Resource
    private ISmsBaseconfigService smsBaseconfigService;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @GetMapping("/getbaseconfig")
    public Mono<SmsBaseconfig> getbaseconfig(@ApiParam String serial) {
        return smsBaseconfigService.getById(serial);
    }


    @GetMapping("/getrequestkey")
    public RequestKeyVo getrequestkey() {
        return new RequestKeyVo("240059B49C568F716A9589D678FBE5C0", "0");
    }

    @GetMapping("/getserverinfo")
    public SmsServerinfo getserverinfo(@ApiParam String serial) {
        return JSON.parseObject("{\n" +
                "  \"Authorization\": \"Users\",\n" +
                "  \"ChannelCount\": 16,\n" +
                "  \"Hardware\": \"AMD64\",\n" +
                "  \"InterfaceVersion\": \"v1\",\n" +
                "  \"RemainDays\": 24,\n" +
                "  \"RunningTime\": \"3 Days 8 Hours 32 Mins 39 Secs\",\n" +
                "  \"Server\": \"BigBirds-SMS/v0.0.1 (Build/2021.0827.181317; Platform/Linux;)\",\n" +
                "  \"ServerTime\": \"2021-09-03 00:33:16\",\n" +
                "  \"StartUpTime\": \"2021-08-30 16:00:36\",\n" +
                "  \"VersionType\": \"旗舰版\"\n" +
                "}", SmsServerinfo.class);
    }

    @GetMapping("/list")
    public List<SmsBaseconfig> list() {
        return smsBaseconfigService.list();
    }

    @GetMapping("/restart")
    public String restart() {
        taskExecutor.execute(()-> {
            try {
                Thread.sleep(3000);
                SipProvider up = (SipProvider) SpringBeanFactory.getBean("udpSipProvider");
                SipStackImpl stack = (SipStackImpl) up.getSipStack();
                stack.stop();
                Iterator listener = stack.getListeningPoints();
                while (listener.hasNext()) {
                    stack.deleteListeningPoint((ListeningPoint) listener.next());
                }
                Iterator providers = stack.getSipProviders();
                while (providers.hasNext()) {
                    stack.deleteSipProvider((SipProvider) providers.next());
                }
                VManageBootstrap.restart();
            } catch (InterruptedException | ObjectInUseException e) {
                throw new ControllerException(ErrorCode.ERROR100.getCode(), e.getMessage());
            }
        });
        return "ok";
    }

    @GetMapping("/setbaseconfig")
    public Mono<String> setbaseconfig(@ApiParam Integer AckTimeout,
                                      @ApiParam Boolean GOPCache,
                                      @ApiParam String GroupID,
                                      @ApiParam Boolean HLS,
                                      @ApiParam String Host,
                                      @ApiParam Integer KeepaliveTimeout,
                                      @ApiParam Integer Port,
                                      @ApiParam Integer RTMPPort,
                                      @ApiParam Integer RTSPPort,
                                      @ApiParam String Realm,
                                      @ApiParam String RecordDir,
                                      @ApiParam Boolean SIPLog,
                                      @ApiParam String Serial,
                                      @ApiParam Boolean UseWanIPRecvStream,
                                      @ApiParam String WanIP) {
        return smsBaseconfigService.getById(Serial).flatMap(baseconfig -> {
            baseconfig.setAckTimeout(AckTimeout);
            baseconfig.setGopCache(GOPCache);
            baseconfig.setGroupID(GroupID);
            baseconfig.setHls(HLS);
            baseconfig.setHost(Host);
            baseconfig.setKeepaliveTimeout(KeepaliveTimeout);
            baseconfig.setPort(Port);
            baseconfig.setRtmpPort(RTMPPort);
            baseconfig.setRtspPort(RTSPPort);
            baseconfig.setRealm(Realm);
            baseconfig.setRecordDir(RecordDir);
            baseconfig.setSipLog(SIPLog);
            baseconfig.setUseWanIPRecvStream(UseWanIPRecvStream);
            baseconfig.setWanIP(WanIP);
            return smsBaseconfigService.update(baseconfig).flatMap(aBoolean -> {
                if (aBoolean) return Mono.just("ok");
                else return Mono.justOrEmpty("failed");
            });
        });
    }

    @GetMapping("/verifyproductcode")
    public String verifyproductcode() {
        return "ok";
    }
}