package com.itcrazy.alanmall.user.config;

import com.itcrazy.alanmall.user.intercepter.TokenIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public TokenIntercepter tokenIntercepter(){
        return new TokenIntercepter();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenIntercepter())
                .addPathPatterns("/user/**")
                .excludePathPatterns("/error");
    }
}
