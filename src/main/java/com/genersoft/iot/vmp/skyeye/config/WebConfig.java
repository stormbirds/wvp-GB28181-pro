package com.genersoft.iot.vmp.skyeye.config;

import com.genersoft.iot.vmp.conf.UserSetting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Description com.genersoft.iot.vmp.skyeye.config
 * @Author StormBirds
 * @Email xbaojun@gmail.com
 * @Date 2022/8/11 23:57
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value(value = "${snap.path}")
    private String snapPath;
    @Resource
    private UserSetting userSetting;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/record/**")
                .addResourceLocations("file:" + userSetting.getCloudRecordPath())
                .setCacheControl(CacheControl.noCache());
        registry.addResourceHandler("/snap/**")
                .addResourceLocations("file:" + snapPath)
                .setCacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES));
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("file:"+s)
    }
}
