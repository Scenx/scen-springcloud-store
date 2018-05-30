package com.scen.sso.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志级别
 *
 * @author Scen
 * @date 2018/5/30 14:37
 */
@Configuration
public class LoggingLevelConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
