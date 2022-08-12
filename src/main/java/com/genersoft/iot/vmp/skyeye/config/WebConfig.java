package com.genersoft.iot.vmp.skyeye.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/snap/**")
                .addResourceLocations("file:" + snapPath)
                .setCacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES));
    }
}
