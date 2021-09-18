package com.cst.config;

import com.cst.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index","/login","/css/**");
//        registry.addInterceptor(localeChangeInterceptor());
    }

//    @Bean
//    public LocaleResolver localeResolver(){
//        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//        localeResolver.setCookieName("localeCookie"); // 将语言信息添加到Cookie中
//        //设置默认区域
//        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE); // 默认简化汉语
//        localeResolver.setCookieMaxAge(86400);//设置cookie有效期.24小时
//        return localeResolver;
//    }

//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        // 参数名
//        return lci;
//    }


//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("templates.i18n.messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }


}
