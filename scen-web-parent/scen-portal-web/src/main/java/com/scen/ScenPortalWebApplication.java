package com.scen;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 单点登录微服务消费者
 *
 * @author Scen
 * @date 2018/4/28 10:41
 */
@SpringBootApplication
@EnableFeignClients
@EnableApolloConfig
public class ScenPortalWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScenPortalWebApplication.class, args);
    }
}
