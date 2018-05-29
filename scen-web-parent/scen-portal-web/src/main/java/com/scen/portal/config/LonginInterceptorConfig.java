package com.scen.portal.config;

import com.scen.portal.interceptor.LonginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册登录拦截器
 *
 * @author Scen
 * @date 2018/5/30 0:53
 */
@Configuration
public class LonginInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LonginInterceptor longinInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(longinInterceptor).addPathPatterns("/order/**");
    }
}
