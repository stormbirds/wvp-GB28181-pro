package com.genersoft.iot.vmp.skyeye.controller;

import com.genersoft.iot.vmp.skyeye.enttity.SipBaseconfig;
import com.genersoft.iot.vmp.skyeye.service.ISipBaseconfigService;
import com.genersoft.iot.vmp.skyeye.vo.ServerInfo;
import com.genersoft.iot.vmp.skyeye.vo.UserInfoVo;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @Description com.genersoft.iot.vmp.web.gb28181
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/11 11:22
 */
@RestController
@RequestMapping("/api/v1")
public class SystemController {

    @Resource
    private ISipBaseconfigService sipBaseconfigService;

    @GetMapping("/getserverinfo")
    public ServerInfo getserverinfo() {

        return JSON.parseObject("{\n" +
                "  \"APIAuth\": true,\n" +
                "  \"Authorization\": \"Users\",\n" +
                "  \"ChannelCount\": 16,\n" +
                "  \"CopyrightText\": \"Copyright © 2021 <a href=\\\"//www.stormbirds.cn\\\" target=\\\"_blank\\\">www.stormbirds.cn</a> All rights reserved.\",\n" +
                "  \"DemoUser\": \"guest\",\n" +
                "  \"Hardware\": \"AMD64\",\n" +
                "  \"InterfaceVersion\": \"v1\",\n" +
                "  \"LogoMiniText\": \"STV\",\n" +
                "  \"LogoText\": \"StormBirds\",\n" +
                "  \"PreferStreamFmt\": \"HLS\",\n" +
                "  \"RemainDays\": 30,\n" +
                "  \"RunningTime\": \"0 Days 17 Hours 16 Mins 18 Secs\",\n" +
                "  \"Server\": \"BigBirds-GBS/v0.0.1 (Build/2021.0903.151319; Platform/Linux;)\",\n" +
                "  \"ServerTime\": \"2021-09-04 11:24:04\",\n" +
                "  \"ShowAbout\": true,\n" +
                "  \"ShowShare\": true,\n" +
                "  \"StartUpTime\": \"2021-09-03 18:07:46\",\n" +
                "  \"VersionType\": \"旗舰版\"\n" +
                "}", ServerInfo.class);
    }

    @GetMapping("/userinfo")
    public UserInfoVo userinfo() {

        return JSON.parseObject("{\n" +
                "  \"Token\": \"lpJ2tE47g\",\n" +
                "  \"ID\": 2,\n" +
                "  \"Name\": \"admin\",\n" +
                "  \"Roles\": [\n" +
                "    \"超级管理员\"\n" +
                "  ],\n" +
                "  \"HasAllChannel\": true,\n" +
                "  \"Cas\": false,\n" +
                "  \"LoginAt\": \"2021-09-03 00:33:14\",\n" +
                "  \"RemoteIP\": \"222.90.42.174\"\n" +
                "}", UserInfoVo.class);
    }

    /**
     * 获取系统基础配置
     *
     * @param serial 流媒体服务ID
     * @return 带流媒体服务ID则为请求流媒体服务配置，不带则返回信令服务配置
     */
    @GetMapping("/getbaseconfig")
    public Mono getbaseconfig(@ApiParam(required = false) String serial) {

        return sipBaseconfigService.get(serial);

    }

    @GetMapping("/setbaseconfig")
    public Mono<String> setbaseconfig(@ApiParam Boolean APIAuth,
                                      @ApiParam Integer AckTimeout,
                                      @ApiParam Boolean AllowStreamStartByURL,
                                      @ApiParam String BlackIPList,
                                      @ApiParam String BlackUAList,
                                      @ApiParam String DevicePassword,
                                      @ApiParam String HTTPSCertFile,
                                      @ApiParam String HTTPSKeyFile,
                                      @ApiParam Integer HTTPSPort,
                                      @ApiParam String Host,
                                      @ApiParam Integer KeepaliveTimeout,
                                      @ApiParam Integer Port,
                                      @ApiParam String PreferStreamFmt,
                                      @ApiParam String Realm,
                                      @ApiParam Boolean SIPLog,
                                      @ApiParam String Serial,
                                      @ApiParam String TimeServer) {
        SipBaseconfig baseconfig = new SipBaseconfig();

        baseconfig.setApiauth(APIAuth);
        baseconfig.setAcktimeout(AckTimeout);
        baseconfig.setAllowstreamstartbyurl(AllowStreamStartByURL);
        baseconfig.setBlackiplist(BlackIPList);
        baseconfig.setBlackualist(BlackUAList);
        baseconfig.setDevicepassword(DevicePassword);
        baseconfig.setHttpscertfile(HTTPSCertFile);
        baseconfig.setHttpskeyfile(HTTPSKeyFile);
        baseconfig.setHttpsport(HTTPSPort);
        baseconfig.setHost(Host);
        baseconfig.setKeepalivetimeout(KeepaliveTimeout);
        baseconfig.setPort(Port);
        baseconfig.setSiplog(SIPLog);
        baseconfig.setRealm(Realm);
        baseconfig.setTimeserver(TimeServer);
        baseconfig.setPreferstreamfmt(PreferStreamFmt);

        return sipBaseconfigService.update(baseconfig);
    }
}
