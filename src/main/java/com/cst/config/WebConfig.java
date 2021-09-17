package com.cst.config;

import com.cst.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/error", "/home", "/api/wx/callback/**")
                .excludePathPatterns("/layui/**")
                .excludePathPatterns("/favicon.ico","/custom.css")
                .excludePathPatterns("/druid/*")
        ;
    }

}
