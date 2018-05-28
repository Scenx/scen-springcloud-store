package com.scen.order.config;

import com.scen.common.utils.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 雪花id生成器配置
 * @author Scen
 * @date 2018/5/28 15:15
 */
@Configuration
public class IdConfig {
    @Bean
    public SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(0, 0);
    }
}
