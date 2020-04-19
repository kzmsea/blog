package com.kzm.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin");       // /* 是拦截所有的文件夹，不包含子文件夹 /** 是拦截所有的文件夹及里面的子文件夹
    }



}
