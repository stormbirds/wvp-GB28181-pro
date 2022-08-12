package com.genersoft.iot.vmp.skyeye.controller;

import com.genersoft.iot.vmp.skyeye.service.IDashBoardService;
import com.genersoft.iot.vmp.skyeye.vo.AuthVo;
import com.genersoft.iot.vmp.skyeye.vo.NetVo;
import com.genersoft.iot.vmp.skyeye.vo.StoreListVo;
import com.genersoft.iot.vmp.skyeye.vo.TopVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @ Description cn.stormbirds.skyeye.controller
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/15 18:58
 */
@RestController
@RequestMapping("/api/v1/dashboard")
public class DashBoardController {

    @Resource
    private IDashBoardService dashBoardService;

    @GetMapping("/auth")
    public Mono<AuthVo> auth() {
        return dashBoardService.auth();
    }

    @GetMapping("/store")
    public StoreListVo store() {
        return dashBoardService.store();
    }

    @GetMapping("/top")
    public TopVo top() {
        return dashBoardService.top();
    }

    @GetMapping("/top/net")
    public Mono<NetVo> net() {
        return dashBoardService.net();
    }
}