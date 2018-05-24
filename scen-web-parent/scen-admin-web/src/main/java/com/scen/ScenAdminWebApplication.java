package com.scen;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 后台微服务消费者
 *
 * @author Scen
 * @date 2018/4/28 10:41
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableApolloConfig
public class ScenAdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScenAdminWebApplication.class, args);
    }
}